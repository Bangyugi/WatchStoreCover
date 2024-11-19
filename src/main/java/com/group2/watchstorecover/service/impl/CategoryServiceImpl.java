package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.CategoryRequest;
import com.group2.watchstorecover.dto.response.PageCustom;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.entity.Category;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.mapper.CategoryMapper;
import com.group2.watchstorecover.repository.CategoryRepository;
import com.group2.watchstorecover.service.CategoryService;
import com.group2.watchstorecover.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final RedisService redisService;

    private final static String KEY = "category";

    @Override
    public Response findAll(Pageable pageable){
        String field = pageable.toString();
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
            Page<Category> page = categoryRepository.findAllByCategoryAvailable(true,pageable);
            PageCustom<Category> pageCustom = PageCustom.<Category>builder()
                    .pageIndex(page.getNumber()+1)
                    .pageSize(page.getSize())
                    .totalPage(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();
            response = Response.builder()
                    .code(200)
                    .message("List category: "+page.getNumber()+1)
                    .data(pageCustom)
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }

    @Override
    public Response findById(int categoryId){
        String field = "categoryId: "+ categoryId;
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
            Category category = categoryRepository.findByCategoryIdAndCategoryAvailable(categoryId, true).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
            response = Response.builder()
                    .code(200)
                    .data(category)
                    .message("Find category by category_id successfully")
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }

    @Override
    public Response addCategory(CategoryRequest categoryRequest){
        if (categoryRepository.existsCategoryByCategoryName(categoryRequest.getCategoryName())){
            throw new AppException(ErrorCode.CATEGORY_EXISTS);
        }
        Category category = categoryMapper.toCategory(categoryRequest);
        categoryRepository.save(category);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(201)
                .data(category)
                .message("Category added successfully")
                .build();
    }

    @Override
    public Response updateCategory(int categoryId, CategoryRequest categoryRequest){
        Category category = categoryRepository.findByCategoryIdAndCategoryAvailable(categoryId, true).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        if (categoryRepository.existsCategoryByCategoryName(categoryRequest.getCategoryName())){
            throw new AppException(ErrorCode.CATEGORY_EXISTS);
        }
        category.setCategoryName(categoryRequest.getCategoryName());
        categoryRepository.save(category);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(200)
                .data(category)
                .message("Category updated successfully")
                .build();
    }

    @Override
    public Response deleteCategory(int categoryId){
        Category category = categoryRepository.findByCategoryIdAndCategoryAvailable(categoryId, true).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryRepository.delete(category);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(200)
                .data(category)
                .message("Category deleted successfully")
                .build();
    }


}
