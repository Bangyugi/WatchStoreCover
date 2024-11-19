package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.CommentRequest;
import com.group2.watchstorecover.dto.response.Response;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Response addComment(CommentRequest commentRequest);

    Response upComment (int commentId, CommentRequest commentRequest);

    Response deleteComment(int commentId);

    Response getCommentByProduct(int productId, Pageable pageable);

    Response getCommentByCustomer(int customerId, Pageable pageable);

    Response getCommentByCommentParent(int commentParentId, Pageable pageable);

    Response getCommentById(int commentId);
}
