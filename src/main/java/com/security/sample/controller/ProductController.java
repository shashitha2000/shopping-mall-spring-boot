package com.security.sample.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.sample.dto.FeedbackDto;
import com.security.sample.entity.Feedback;
import com.security.sample.entity.Product;
import com.security.sample.service.FeedbackService;
import com.security.sample.service.ProductService;
import com.security.sample.util.StandardResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private FeedbackService feedbackService;

            //ADMIN  & CUSTOMER
    @GetMapping("/get-all-products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
//ADMIN
    @PostMapping("/post-product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    //ADMIN CUSTOMER
    @GetMapping("/get-product-by-name/{productName}")
    public ResponseEntity<Product> getProductByName(@PathVariable String productName) {
        Optional<Product> productOptional = productService.findByProductName(productName);
        return productOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //ADMIN
    @PutMapping(path={"/update/{id}"})

    public ResponseEntity<StandardResponse> updateProduct(
            @RequestBody Product product,
            @PathVariable(value = "id") int id){
        String updated=productService.productUpdate(product,id);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,id+"Programm successfully saved",updated),
                HttpStatus.CREATED
        );
    }

//ADMIN
@DeleteMapping(path={"/delete-product/{id}"})
public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") long id) throws ChangeSetPersister.NotFoundException {
    boolean productDeleted = productService.productDelete(id);
    if (productDeleted) {
        return ResponseEntity.ok().build(); // Return 200 OK status for successful deletion
    } else {
        return ResponseEntity.notFound().build(); // Return 404 Not Found status if product was not found
    }
}


    //--------------------------------------------------------------FEEDBACK

    //add feed back
    @PostMapping("/feed-back/{userId}/{productId}")
    public Feedback addFeedbackForItem(@PathVariable long userId, @PathVariable long productId, @RequestBody FeedbackDto feedbackDto) {
        return feedbackService.addFeedback(userId, productId, feedbackDto);
    }
    //get all feed back
    @GetMapping("/get-all-feed-back")
    public ResponseEntity<List<Object[]>> getAllFeedBack() {
        List<Object[]> feedbackWithUserInfo = feedbackService.getAllFeedBack();
        return new ResponseEntity<>(feedbackWithUserInfo, HttpStatus.OK);
    }
    @GetMapping("/get-all-feed-back/{userId}")
    public List<Object[]> getFeedbackWithUserId(@PathVariable long userId) {
        return feedbackService.getFeedbackWithUserId(userId);
    }

    @DeleteMapping("/delete-feed-back/{feedbackId}")
    public void deleteFeedback(@PathVariable Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
    }

    @PutMapping("/update-feed-back/{feedbackId}")
    public Feedback updateFeedback(@PathVariable Long feedbackId, @RequestBody Feedback updatedFeedback) {
        return feedbackService.updateFeedback(feedbackId, updatedFeedback);
    }

}