package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.BrandRequest;
import com.group2.watchstorecover.dto.response.PageCustom;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.entity.Brand;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.mapper.BrandMapper;
import com.group2.watchstorecover.repository.BrandRepository;
import com.group2.watchstorecover.service.BrandService;
import com.group2.watchstorecover.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final RedisService redisService;

    private final static String KEY = "brand";

    @Override
    public Response addBrand(BrandRequest brandRequest){
        if (brandRepository.existsByBrandName(brandRequest.getBrandName())){
            throw new AppException(ErrorCode.BRAND_EXISTS);
        }
        Brand brand = brandMapper.toBrand(brandRequest);
        brandRepository.save(brand);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(201)
                .message("Brand added successfully")
                .data(brand)
                .build();
    }

    @Override
    public Response updateBrand(int brandId, BrandRequest brandRequest){
        Brand brand = brandRepository.findByBrandIdAndBrandAvailable(brandId, true).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
        if (brandRepository.existsByBrandName(brandRequest.getBrandName())){
            throw  new AppException(ErrorCode.BRAND_EXISTS);
        }
        brand.setBrandName(brandRequest.getBrandName());
        brand.setBrandDetails(brandRequest.getBrandDetails());
        brandRepository.save(brand);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(200)
                .message("Brand updated successfully")
                .data(brand)
                .build();

    }

    @Override
    public Response deleteBrand(int brandId){
        Brand brand = brandRepository.findByBrandIdAndBrandAvailable(brandId, true).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
        brandRepository.delete(brand);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(200)
                .message("Brand deleted successfully")
                .data(brand)
                .build();
    }

    @Override
    public Response findAllBrand(Pageable pageable){
        String field = pageable.toString();
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
            Page<Brand> page = brandRepository.findAllByBrandAvailable(true,pageable);
            PageCustom<Brand> pageCustom = PageCustom.<Brand>builder()
                    .pageIndex(page.getNumber()+1)
                    .pageSize(page.getSize())
                    .totalPage(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();

            response = Response.builder()
                    .code(200)
                    .data(pageCustom)
                    .message("List brand: "+page.getNumber()+1)
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }

    @Override
    public Response findBrandById(int brandId){
        String field ="Brand id: " +brandId;
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response==null){
            Brand brand = brandRepository.findByBrandIdAndBrandAvailable(brandId,true).orElseThrow(()-> new AppException(ErrorCode.BRAND_NOT_FOUND));
            response = Response.builder()
                    .code(200)
                    .message("Brand found successfully")
                    .data(brand)
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }

}
