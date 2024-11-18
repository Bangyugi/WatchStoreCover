package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.BlogRequest;
import com.group2.watchstorecover.dto.response.Response;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Response addBlog(BlogRequest blogRequest);

    Response updateBlog(int blogId, BlogRequest blogRequest);

    Response deleteBlog(int blogId);

    Response getById(int blogId);

    Response getAll(Pageable pageable);

    Response getByCustomer (int customerId, Pageable pageable);
}
