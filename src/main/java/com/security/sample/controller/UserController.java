package com.security.sample.controller;

import com.security.sample.dto.UserProfileDto;
import com.security.sample.entity.UserDetails;
import com.security.sample.service.UserDetailsService;
import com.security.sample.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    //ADD USER details to profile
    @PostMapping(path = "/user-profile/{id}")
    public ResponseEntity<StandardResponse> userSave(@RequestBody UserDetails userDetails, @PathVariable int id) {
        String userId = userDetailsService.userProfile(userDetails, id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, id + " user details successfully saved ", id),
                HttpStatus.CREATED
        );
    }

    //Get USER Details

    @GetMapping("/get-user-profile/{userId}")
    public ResponseEntity<Map<String, Object>> getUserDetails(@PathVariable("userId") int userId) {
        String sql = "SELECT u.email, u.first_name, u.last_name, ud.address, ud.contact, ud.image " +
                "FROM shopping_mall.user u " +
                "LEFT JOIN shopping_mall.user_details ud ON u.id = ud.user_id " +
                "WHERE u.id = ?";

        try {
            Map<String, Object> user = jdbcTemplate.queryForMap(sql, userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
//UPDATE USER PROFILE
    @PutMapping(path={"/update/{id}"})

    public ResponseEntity<StandardResponse> updateProfile(
            @RequestBody UserProfileDto userProfileDto,
            @PathVariable int id){
        String updated=userDetailsService.profileUpdate(userProfileDto,id);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,id+"Profile successfully saved ",updated),
                HttpStatus.CREATED
        );
    }

}
