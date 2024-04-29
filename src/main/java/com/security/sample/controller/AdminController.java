package com.security.sample.controller;

import com.security.sample.entity.Category;
import com.security.sample.entity.User;
import com.security.sample.service.CategoryService;


import com.security.sample.service.serviceImpl.UserServiceImpl;
import com.security.sample.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/admin")
public class AdminController {


//    @GetMapping({"/forAdmin"})
//    @PreAuthorize("hasRole('Admin')")
//    public String forAdmin() {
//
//        return "This URL is only accessible to the admin";
//    }


    //CATEGORY
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserServiceImpl userService;


    //get all
    @GetMapping("/get-all-users")
    public ResponseEntity<StandardResponse> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"success",userList),
                HttpStatus.OK
        );
    }

    //delete user
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        try {
            userService.deleteUserById(userId);
            return new ResponseEntity<>("User with ID " + userId + " has been deleted", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Handle case where user with given ID is not found
            return new ResponseEntity<>("User with ID " + userId + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}

