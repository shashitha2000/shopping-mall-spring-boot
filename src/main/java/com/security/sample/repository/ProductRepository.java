package com.security.sample.repository;

import com.security.sample.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);
    @Modifying
    @Query(value = "update product set product_name = ?1, price = ?2, description = ?3, image_url = ?4 where id = ?5", nativeQuery = true)
    void updateProgram(String productName, double price, String description, String imageUrl, long id);



}
