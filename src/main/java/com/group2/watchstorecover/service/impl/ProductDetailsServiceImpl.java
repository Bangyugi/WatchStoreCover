package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.ProductDetailsRequest;
import com.group2.watchstorecover.dto.response.PageCustom;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.entity.Product;
import com.group2.watchstorecover.entity.ProductDetails;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.mapper.ProductDetailsMapper;
import com.group2.watchstorecover.repository.ProductDetailsRepository;
import com.group2.watchstorecover.repository.ProductRepository;
import com.group2.watchstorecover.service.ProductDetailsService;
import com.group2.watchstorecover.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


@Service
@RequiredArgsConstructor
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductDetailsMapper productDetailsMapper;
    private final RedisService redisService;

    private final static String KEY = "productdetails";
    private final ProductRepository productRepository;

    @Override
    public Response addProductDetails(ProductDetailsRequest productDetailsRequest){
        Product product = productRepository.findByProductIdAndProductAvailable(productDetailsRequest.getProductId(),true).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        if (productDetailsRepository.existsByProduct(product))
        {
            throw new AppException(ErrorCode.PRODUCT_DETAILS_EXISTS);
        }
        ProductDetails productDetails = productDetailsMapper.toProductDetails(productDetailsRequest);
        productDetails.setProduct(product);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(201)
                .message("add product details for product successfully")
                .data(productDetailsRepository.save(productDetails))
                .build();
    }

    @Override
    public Response updateProductDetails(int productDetailsId, ProductDetailsRequest productDetailsRequest){
        ProductDetails productDetails = productDetailsRepository.findByProductDetailsId(productDetailsId).orElseThrow(()->new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        if (productDetailsRequest.getProductId()!= productDetails.getProduct().getProductId()){
            throw new AppException(ErrorCode.PRODUCT_NOT_CHANGE);
        }

        productDetailsMapper.updateProductDetails(productDetails,productDetailsRequest);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(200)
                .message("update product details for product successfully")
                .data(productDetailsRepository.save(productDetails))
                .build();
    }

    @Override
    public Response getProductDetails(Pageable pageable){
        String field = pageable.toString();
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
           Page<ProductDetails> page =  productDetailsRepository.findAll(pageable);
            PageCustom<ProductDetails> pageCustom = PageCustom.<ProductDetails>builder()
                    .pageIndex(page.getNumber()+1)
                    .pageSize(page.getSize())
                    .totalPage(page.getTotalPages())
                    .content(page.getContent())
                    .build();
            response = Response.builder()
                    .code(200)
                    .message("get all product details successfully")
                    .data(pageCustom)
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }

    @Override
    public Response getProductDetailsById(int productDetailsId){
        String field = "productdetailsid: " +productDetailsId;
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
            ProductDetails productDetails = productDetailsRepository.findByProductDetailsId(productDetailsId).orElseThrow(()->new AppException(ErrorCode.ERR_ID_NOT_FOUND));
            response = Response.builder()
                    .code(200)
                    .message("get product details by id successfully")
                    .data(productDetails)
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }

    @Override
    public Response getProductDetailsByProductId(int productId){
        String field = "productdetailsproductId: " +productId;
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
            Product product = productRepository.findByProductIdAndProductAvailable(productId,true).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            ProductDetails productDetails = productDetailsRepository.findByProduct(product).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_DETAILS_NOT_FOUND));
            response = Response.builder()
                    .code(200)
                    .data(productDetails)
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }
}
