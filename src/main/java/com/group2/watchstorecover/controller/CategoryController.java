package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.CategoryProductRequest;
import com.group2.watchstorecover.dto.request.CategoryRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.CategoryProductService;
import com.group2.watchstorecover.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.CategoryUrl.PREFIX)
@RequiredArgsConstructor
@Tag(name = "Category", description = "Category API")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryProductService categoryProductService;

    @Operation(summary = "Get all categories")
    @GetMapping(UrlConstant.CategoryUrl.GET_ALL)
    private ResponseEntity<?> getAll(
            @RequestParam(value = "pageIndex", defaultValue = "1", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "categoryName", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, Sort.by(sortBy).descending());
        Response response = categoryService.findAll(pageable);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Get category by id")
    @GetMapping(UrlConstant.CategoryUrl.GET_BY_ID)
    private ResponseEntity<?> getById(@PathVariable("categoryId") int categoryId) {
        Response response = categoryService.findById(categoryId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Add category")
    @PostMapping(UrlConstant.CategoryUrl.CREATE)
    private ResponseEntity<?> create(@Valid @RequestBody CategoryRequest categoryRequest) {
        Response response = categoryService.addCategory(categoryRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Update category")
    @PutMapping(UrlConstant.CategoryUrl.UPDATE)
    private ResponseEntity<?> update(@PathVariable("categoryId") int categoryId, @Valid @RequestBody CategoryRequest categoryRequest) {
        Response response = categoryService.updateCategory(categoryId, categoryRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Delete category")
    @DeleteMapping(UrlConstant.CategoryUrl.DELETE)
    private ResponseEntity<?> delete(@PathVariable("categoryId") int categoryId) {
        Response response = categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Add category for product")
    @PostMapping(UrlConstant.CategoryUrl.ADD_CATEGORY_TO_PRODUCT)
    public ResponseEntity<?> addCategoryForProduct(@RequestBody CategoryProductRequest request){
        Response response = categoryProductService.addCategoryProduct(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "Remove category for product")
    @DeleteMapping(UrlConstant.CategoryUrl.REMOVE_CATEGORY_FROM_PRODUCT)
    public ResponseEntity<?> deleteCategoryForProduct(@PathVariable("categoryId") int categoryId, @PathVariable("productId") int productId){
        Response response = categoryProductService.deleteCategoryProduct(categoryId, productId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
}
