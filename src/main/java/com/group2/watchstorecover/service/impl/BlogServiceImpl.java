package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.BlogRequest;
import com.group2.watchstorecover.dto.response.PageCustom;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.entity.Blog;
import com.group2.watchstorecover.entity.Customer;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.mapper.BlogMapper;
import com.group2.watchstorecover.repository.BlogRepository;
import com.group2.watchstorecover.repository.CustomerRepository;
import com.group2.watchstorecover.service.BlogService;
import com.group2.watchstorecover.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    private final RedisService redisService;

    private final static String KEY = "blog";
    private final CustomerRepository customerRepository;

    @Override
    public Response addBlog(BlogRequest blogRequest){
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(blogRequest.getCustomerId(),true).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        Blog blog = blogMapper.toBlog(blogRequest);
        blog.setCustomer(customer);
        blogRepository.save(blog);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(201)
                .message("Blog added successfully")
                .data(blog)
                .build();
    }

    @Override
    public Response updateBlog(int blogId, BlogRequest blogRequest) {
        Blog blog = blogRepository.findByBlogIdAndAvailable(blogId, true).orElseThrow(() -> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        if (blogRequest.getCustomerId() != blog.getCustomer().getCustomerId()){
            throw new AppException(ErrorCode.ERR_CUSTOMER_NOT_CHANGE);
        }
        blogMapper.updateBlog(blog,blogRequest);
        blogRepository.save(blog);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(200)
                .message("Blog updated successfully")
                .data(blog)
                .build();
    }

    @Override
    public Response deleteBlog(int blogId) {
        Blog blog = blogRepository.findByBlogIdAndAvailable(blogId, true).orElseThrow(() -> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        blog.setAvailable(false);
        blogRepository.save(blog);
        redisService.hasDel(KEY);
        return Response.builder()
                .code(200)
                .message("Blog deleted successfully")
                .data(blog)
                .build();
    }

    @Override
    public Response getById(int blogId) {
        String field = "blogId: " + blogId;
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
            Blog blog = blogRepository.findByBlogIdAndAvailable(blogId, true).orElseThrow(() -> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
            blog.setView(blog.getView()+1);
            blogRepository.save(blog);
            response = Response.builder()
                    .code(200)
                    .message("Blog found successfully")
                    .data(blog)
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }

    @Override
    public Response getAll(Pageable pageable){
        String field = pageable.toString();
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
            Page<Blog> page = blogRepository.findAllByAvailable(true,pageable);
            PageCustom<Blog> pageCustom = PageCustom.<Blog>builder()
                    .pageIndex(page.getNumber()+1)
                    .pageSize(page.getSize())
                    .totalPage(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();
            response = Response.builder()
                    .code(200)
                    .message("Get all blog successfully")
                    .data(pageCustom)
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;

    }

    @Override
    public Response getByCustomer(int customerId, Pageable pageable){
        String field = customerId + ":" +pageable.toString();
        Response response = (Response) redisService.hashGet(KEY,field);
        if (response == null){
            Page<Blog> page = blogRepository.findAllByCustomerAndAvailable(customerId,pageable);
            PageCustom<Blog> pageCustom = PageCustom.<Blog>builder()
                    .pageIndex(page.getNumber()+1)
                    .pageSize(page.getSize())
                    .totalPage(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();
            response = Response.builder()
                    .code(200)
                    .message("Get all blog by customer successfully")
                    .data(pageCustom)
                    .build();
            redisService.hashSet(KEY,field,response);
        }
        return response;
    }

}
