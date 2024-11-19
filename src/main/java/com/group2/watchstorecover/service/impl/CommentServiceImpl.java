package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.CommentRequest;
import com.group2.watchstorecover.dto.response.PageCustom;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.entity.Comment;
import com.group2.watchstorecover.entity.Customer;
import com.group2.watchstorecover.entity.Product;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.mapper.CommentMapper;
import com.group2.watchstorecover.repository.CommentRepository;
import com.group2.watchstorecover.repository.CustomerRepository;
import com.group2.watchstorecover.repository.ProductRepository;
import com.group2.watchstorecover.service.CommentService;
import com.group2.watchstorecover.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final RedisService redisService;

    private final static String KEY = "comment";
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public Response addComment(CommentRequest commentRequest){
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(commentRequest.getCustomerId(),true).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        Product product = productRepository.findByProductIdAndProductAvailable(commentRequest.getProductId(),true).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        Optional<Comment> commentParent = commentRepository.findById(commentRequest.getCommentParentId());
        Comment comment = commentMapper.toComment(commentRequest);
        comment.setCustomer(customer);
        comment.setProduct(product);
        if (commentParent.isPresent()){
            comment.setCommentParent(commentParent.get());
        }
        commentRepository.save(comment);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(201)
                .message("Add comment successfully")
                .data(comment)
                .build();
    }

    @Override
    public Response upComment(int commentId, CommentRequest commentRequest){
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new AppException(ErrorCode.COMMENT_NOT_FOUND));
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(commentRequest.getCustomerId(),true).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        Product product = productRepository.findByProductIdAndProductAvailable(commentRequest.getProductId(),true).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        Optional<Comment> commentParent = commentRepository.findById(commentRequest.getCommentParentId());
        if (commentRequest.getCustomerId()!= customer.getCustomerId() || commentRequest.getProductId() != product.getProductId() || commentRequest.getCommentParentId() != commentParent.get().getCommentId()){
            throw new AppException(ErrorCode.COMMENT_NOT_FOUND);
        }
        commentMapper.updateComment(comment,commentRequest);
        commentRepository.save(comment);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(200)
                .message("Update comment successfully")
                .data(comment)
                .build();

    }

    @Override
    public Response deleteComment(int commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new AppException(ErrorCode.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(200)
                .message("Delete comment successfully")
                .build();
    }

    @Override
    public Response getCommentByProduct(int productId, Pageable pageable) {
        String field = "productId: " + productId + pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY, field);
        if(response == null) {
            Page<Comment> page = commentRepository.getCommentsByProduct(productId, pageable);
            PageCustom<Comment> pageCustom = PageCustom.<Comment>builder()
                    .pageIndex(page.getNumber()+1)
                    .pageSize(page.getSize())
                    .totalPage(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();

            response = Response.builder()
                    .code(200).data(pageCustom)
                    .message("Get comments by product success")
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return response ;
    }

    @Override
    public Response getCommentByCustomer(int customerId, Pageable pageable) {
        String field = "customerId: " + customerId + pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY, field) ;
        if(response ==null) {
            Page<Comment> page = commentRepository.getCommentsByCustomer(customerId, pageable);
            PageCustom<Comment> pageCustom = PageCustom.<Comment>builder()
                    .pageIndex(page.getNumber()+1)
                    .pageSize(page.getSize())
                    .totalPage(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();

            response =  Response.builder()
                    .code(200)
                    .data(pageCustom)
                    .message("Get comments by customer success")
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return  response ;
    }

    @Override
    public Response getCommentByCommentParent(int commentParentId, Pageable pageable) {
        String field = "commentParentId: " + commentParentId + pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY , field) ;
        if(response == null) {
            Page<Comment> page = commentRepository.getCommentsByCommentParent(commentParentId, pageable);
            PageCustom<Comment> pageCustom = PageCustom.<Comment>builder()
                    .pageIndex(page.getNumber()+1)
                    .pageSize(page.getSize())
                    .totalPage(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();

            response =  Response.builder()
                    .code(200).data(pageCustom)
                    .message("Get comments by comment success")
                    .build();
        }
        return  response  ;
    }

    @Override
    public Response getCommentById(int commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new AppException(ErrorCode.COMMENT_NOT_FOUND));
        return Response.builder()
                .code(200)
                .message("Get comment by id successfully")
                .data(comment)
                .build();
    }

}
