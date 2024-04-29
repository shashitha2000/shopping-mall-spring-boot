package com.security.sample.controller;

import com.security.sample.dto.CartQuantityDto;
import com.security.sample.entity.CartItem;
import com.security.sample.entity.Product;
import com.security.sample.service.CartItemService;
import com.security.sample.service.ProductService;
import com.security.sample.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;


    //ADMIN CUSTOMER THEATER_OWNER
    @PostMapping("/add/{userId}")
    public CartItem addCartItem(@RequestBody CartItem cartItem, @PathVariable long userId) {
        return cartItemService.addCartItem(cartItem, userId);
    }

    //GET Product from Cart

    @GetMapping("/userCart/{userId}")
    public ResponseEntity<List<Product>> getProductsInUserCart(@PathVariable long userId) {
        List<Product> products = productService.getProductsInUserCart(userId);
        return ResponseEntity.ok(products);
    }

    //UPDATE CART ITEM

    @PutMapping(path="/update-quantity/{userId}/{productId}")

    public ResponseEntity<StandardResponse> updateQuantity(@PathVariable long userId, @PathVariable long productId, @RequestBody CartQuantityDto cartQuantityDto){
        String updated=cartItemService.Update(cartQuantityDto,userId,productId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,userId+" Quantity successfully updated",updated),
                HttpStatus.CREATED
        );
    }

    //DELETE CART ITEM

    @DeleteMapping(path="/delete-cart-item/{userId}/{productId}")
    public String deleteCartItem( @PathVariable long userId, @PathVariable long productId){

        boolean item=cartItemService.itemDelete(userId,productId);
        return "item deleted";
    }

}