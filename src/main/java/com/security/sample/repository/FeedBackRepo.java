package com.security.sample.repository;

import com.security.sample.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepo extends JpaRepository<Feedback, Long> {

    List<Feedback> findAll();
    @Query("SELECT u.firstName, u.lastName, f.comment, f.stars " +
            "FROM User u " +
            "LEFT JOIN Feedback f ON u.id = f.userId " +
            "WHERE u.id = :userId")
    List<Object[]> getFeedbackWithUserId(long userId);

    @Query("SELECT u.firstName, u.lastName, f.comment, f.stars " +
            "FROM User u " +
            "INNER JOIN Feedback f ON u.id = f.userId"   )
    List<Object[]> getFeedbackWithUserInfo();
}
