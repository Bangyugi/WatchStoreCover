package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "FROM Comment c WHERE c.product.productId= :productId AND c.commentAvailable = true")
    Page<Comment> getCommentsByProduct(int productId, Pageable pageable);

    @Query(value = "FROM Comment c WHERE c.customer.customerId= :customerId AND c.commentAvailable = true")
    Page<Comment> getCommentsByCustomer(int customerId, Pageable pageable);

    @Query(value = "FROM Comment c WHERE c.commentParent.commentId= :commentParentId AND c.commentAvailable = true")
    Page<Comment> getCommentsByCommentParent(int commentParentId, Pageable pageable);
}
