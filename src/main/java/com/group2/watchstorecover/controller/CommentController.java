package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.CommentRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlConstant.CommentUrl.PREFIX)
@RequiredArgsConstructor
@Tag(name = "COMMENT", description = "Comment API")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Get comment by product id")
    @GetMapping(UrlConstant.CommentUrl.GET_BY_PRODUCT_ID)
    public ResponseEntity<?> getCommentByProduct(@PathVariable("productId") int productId ,
                                                 @RequestParam(defaultValue = "1" , required = false) int pageIndex ,
                                                 @RequestParam(defaultValue = "5" , required = false) int pageElement){
        Sort sort = Sort.by("commentDate").descending() ;
        Pageable pageable = PageRequest.of(pageIndex -1 , pageElement, sort) ;
        Response response = commentService.getCommentByProduct(productId, pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "Get comment by customer id")
    @GetMapping(UrlConstant.CommentUrl.GET_BY_CUSTOMER_ID)
    public ResponseEntity<?> getCommentByCustomer(@PathVariable("customerId") int customerId ,
                                                  @RequestParam(defaultValue = "1" , required = false) int pageIndex ,
                                                  @RequestParam(defaultValue = "5" , required = false) int pageElement){
        Pageable pageable = PageRequest.of(pageIndex -1 , pageElement) ;
        Response response = commentService.getCommentByCustomer(customerId , pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "Get comment by comment parent id")
    @GetMapping(UrlConstant.CommentUrl.Get_BY_COMMENT_ID)
    public ResponseEntity<?> getCommentByComment(@PathVariable("commentParentId") int commentParentId ,
                                                 @RequestParam(defaultValue = "1" , required = false) int pageIndex ,
                                                 @RequestParam(defaultValue = "5" , required = false) int pageElement){
        Pageable pageable = PageRequest.of(pageIndex -1 , pageElement) ;
        Response response = commentService.getCommentByCommentParent(commentParentId, pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "Add new comment")
    @PostMapping(UrlConstant.CommentUrl.CREATE)
    public ResponseEntity<?> addComment(@RequestBody @Valid CommentRequest request){
        Response response = commentService.addComment(request);
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "Update comment")
    @PutMapping(UrlConstant.CommentUrl.UPDATE)
    public ResponseEntity<?> upDComment(@PathVariable("commentId") int commentId , @RequestBody @Valid CommentRequest request){
        Response response = commentService.upComment(commentId,request);
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "Delete comment")
    @DeleteMapping(UrlConstant.CommentUrl.DELETE)
    public ResponseEntity<?> delComments(@PathVariable("commentId") int commentId){
        Response response = commentService.deleteComment(commentId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "Get comment by id")
    @GetMapping(UrlConstant.CommentUrl.GET_BY_ID)
    public ResponseEntity<?> getCommentById(@PathVariable("commentId") int commentId){
        Response response = commentService.getCommentById(commentId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

}
