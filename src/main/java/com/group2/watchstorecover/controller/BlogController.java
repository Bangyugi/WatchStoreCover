package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.BlogRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.BlogUrl.PREFIX)
@RequiredArgsConstructor
@Tag(name = "BLOG", description = "Blog APIs")
public class BlogController {

    private final BlogService blogService;

    @Operation(summary = "Get all blog")
    @GetMapping(UrlConstant.BlogUrl.GET_ALL)
    public ResponseEntity<?> getAllBlog(
        @RequestParam(value="pageIndex", defaultValue = "1", required = false) int pageIndex,
        @RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy",defaultValue = "blogCreateDate",required = false) String sortBy
    ){
        Pageable pageable = PageRequest.of(pageIndex -1, pageSize, Sort.by(sortBy).descending());
        Response response = blogService.getAll(pageable);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Get all blog by customer")
    @GetMapping(UrlConstant.BlogUrl.GET_ALL_BY_CUSTOMER)
    public ResponseEntity<?> getAllBlogByCustomer(@PathVariable("customerId")int customerId,
                                                  @RequestParam(value = "pageIndex",defaultValue = "1",required = false)int pageIndex,
                                                  @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                  @RequestParam(value = "sortBy",defaultValue = "blogCreateDate",required = false) String sortBy
    ){
        Pageable pageable = PageRequest.of(pageIndex -1, pageSize, Sort.by(sortBy).descending());
        Response response = blogService.getByCustomer(customerId,pageable);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Get blog by id")
    @GetMapping(UrlConstant.BlogUrl.GET_BY_ID)
    public ResponseEntity<?> getBlogById(@PathVariable("blogId") int blogId){
        Response response = blogService.getById(blogId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Add blog")
    @PostMapping(UrlConstant.BlogUrl.CREATE)
    public ResponseEntity<?> addBlog(@Valid @RequestBody BlogRequest blogRequest){
        Response response = blogService.addBlog(blogRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation (summary = "Update blog")
    @PutMapping(UrlConstant.BlogUrl.UPDATE)
    public ResponseEntity<?> updateBlog(@PathVariable("blogId") int blogId, @Valid @RequestBody BlogRequest blogRequest){
        Response response = blogService.updateBlog(blogId,blogRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Delete blog")
    @DeleteMapping(UrlConstant.BlogUrl.TEMPORARY_DELETE)
    public ResponseEntity<?> deleteBlog(@PathVariable("blogId") int blogId){
        Response response = blogService.deleteBlog(blogId);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
