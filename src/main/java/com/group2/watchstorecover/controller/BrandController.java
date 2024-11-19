package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.BrandRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.BrandService;
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
@RequestMapping(UrlConstant.BrandUrl.PREFIX)
@RequiredArgsConstructor
@Tag(name = "BRAND", description = "Brand API")
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "Get all brand")
    @GetMapping(UrlConstant.BrandUrl.GET_ALL)
    public ResponseEntity<?> findAllBrand(
            @RequestParam(value="pageIndex",defaultValue = "1",required = false) int pageIndex,
            @RequestParam(value= "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "brandName", required = false) String sortBy
    ){
        Pageable pageable = PageRequest.of(pageIndex -1 ,pageSize, Sort.by(sortBy).descending());
        Response response = brandService.findAllBrand(pageable);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Get brand by id")
    @GetMapping(UrlConstant.BrandUrl.GET_BY_ID)
    public ResponseEntity<?> findBrandById(@PathVariable("brandId") int brandId)
    {
        Response response = brandService.findBrandById(brandId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Add brand")
    @PostMapping(UrlConstant.BrandUrl.CREATE)
    public ResponseEntity<?> addBrand(@Valid @RequestBody BrandRequest brandRequest){
        Response response = brandService.addBrand(brandRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Update brand")
    @PutMapping(UrlConstant.BrandUrl.UPDATE)
    public ResponseEntity<?> updateBrand(@PathVariable("brandId") int brandId, @Valid @RequestBody BrandRequest brandRequest)
    {
        Response response = brandService.updateBrand(brandId,brandRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Delete brand")
    @DeleteMapping(UrlConstant.BrandUrl.DELETE)
    public ResponseEntity<?> deleteBrand(@PathVariable("brandId") int brandId){
        Response response = brandService.deleteBrand(brandId);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
