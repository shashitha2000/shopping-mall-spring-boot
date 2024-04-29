package com.security.sample.repository;

import com.security.sample.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(long userId);
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
}