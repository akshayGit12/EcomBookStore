package com.nareshit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nareshit.entity.BooksModule;
import com.nareshit.entity.Orders;

public interface OrderModuleRepo extends JpaRepository<Orders, Long> {

    // Last 7 days orders by a customer
    @Query(value = "SELECT * FROM orders o WHERE o.custmer_id = :customerId AND o.created_date > CURDATE() - INTERVAL 7 DAY",
           nativeQuery = true)
    List<Orders> findAnyLastweekPlaced(@Param("customerId") Long customerId);

    // Find by book title
    @Query("SELECT b FROM BooksModule b WHERE b.title = :title")
    BooksModule findByBookName(@Param("title") String title);

}

