package com.hs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hs.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, String> {

    CartItem findByProductId(String productId);
    void deleteByProductId(String productId);
}
