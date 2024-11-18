package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Optional<Blog> findByBlogIdAndAvailable(int blogId, boolean b);

    Page<Blog> findAllByAvailable(boolean b, Pageable pageable);

    @Query(value = "FROM Blog b where b.customer.customerId = :customerId and b.available = true")
    Page<Blog> findAllByCustomerAndAvailable(int customerId, Pageable pageable);
}
