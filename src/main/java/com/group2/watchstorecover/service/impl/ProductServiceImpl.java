package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.ProductRequest;
import com.group2.watchstorecover.dto.response.PageCustom;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.entity.Brand;
import com.group2.watchstorecover.entity.Product;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.mapper.ProductMapper;
import com.group2.watchstorecover.repository.BrandRepository;
import com.group2.watchstorecover.repository.ProductRepository;
import com.group2.watchstorecover.service.ProductService;
import com.group2.watchstorecover.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final RedisService redisService;
    private final static String KEY ="product";
    private final BrandRepository brandRepository;

    @Override
    public Response addProduct(ProductRequest productRequest){
        if (productRepository.existsByProductName(productRequest.getProductName())){
            throw new AppException(ErrorCode.PRODUCT_EXISTS);
        }
        Product product = productMapper.toProduct(productRequest);
        Brand brand = brandRepository.findByBrandIdAndBrandAvailable(productRequest.getBrandId(),true).orElseThrow(()->new AppException(ErrorCode.BRAND_NOT_FOUND));
        product.setBrand(brand);
         redisService.hasDel(KEY);
         return Response.builder()
                 .code(201)
                 .message("Add new product successfully")
                 .data(productRepository.save(product))
                 .build();
    }

    @Override
    public Response updateProductById (int productId, ProductRequest productRequest){
        Product product = productRepository.findByProductIdAndProductAvailable(productId,true).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        if (productRepository.existsByProductName(productRequest.getProductName())){
            throw new AppException(ErrorCode.PRODUCT_EXISTS);
        }
        productMapper.updateProduct(product,productRequest);
        Brand brand = brandRepository.findByBrandIdAndBrandAvailable(productRequest.getBrandId(),true).orElseThrow(()->new AppException(ErrorCode.BRAND_NOT_FOUND));
        product.setBrand(brand);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(200)
                .message("Update product successfully")
                .data(productRepository.save(product))
                .build();
    }

    @Override
    public Response deleteProductById(int productId){
        Product product = productRepository.findByProductIdAndProductAvailable(productId,true).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        redisService.hasDel(KEY);
        productRepository.delete(product);
        return Response.builder()
                .code(200)
                .message("Delete product successfully")
                .build();
    }


    @Override
    public Response findAllProducts(Pageable pageable){
        String field = pageable.toString();
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
            Page<Product> page = productRepository.findAllByProductAvailable(true,pageable);
            PageCustom<Product> pageCustom = PageCustom.<Product>builder()
                    .pageIndex(page.getNumber()+1)
                    .pageSize(page.getSize())
                    .totalPage(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();
            response = Response.builder()
                    .code(200)
                    .message("Get all product successfully")
                    .data(pageCustom)
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }

    @Override
    public Response findProductById(int productId){
        String field = "productId: " + productId;
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
            Product product = productRepository.findByProductIdAndProductAvailable(productId,true).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            response = Response.builder()
                    .code(200)
                    .data(product)
                    .message("Get single product by id successfully")
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }
}
