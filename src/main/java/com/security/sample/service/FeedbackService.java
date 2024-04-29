package com.security.sample.service;


import com.security.sample.dto.FeedbackDto;
import com.security.sample.entity.Feedback;
import com.security.sample.exception.NotFoundException;
import com.security.sample.repository.FeedBackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedBackRepo feedbackRepository;


    public Feedback addFeedback(long userId, long productId, FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setProductId(productId);
        feedback.setStars(feedbackDto.getStars());
        feedback.setComment(feedbackDto.getComment());
        return feedbackRepository.save(feedback);
    }

    public List<Object[]> getFeedbackWithUserId(long userId) {

        return feedbackRepository.getFeedbackWithUserId(userId);
    }


    public void deleteFeedback(Long feedbackId) {
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(feedbackId);
        if (feedbackOptional.isPresent()) {
            feedbackRepository.deleteById(feedbackId);
        } else {
            throw new NotFoundException("No feedback found with ID: " + feedbackId);
        }
    }


    public Feedback updateFeedback(Long feedbackId, Feedback updatedFeedback) {
        Optional<Feedback> existingFeedbackOptional = feedbackRepository.findById(feedbackId);
        if (existingFeedbackOptional.isPresent()) {
            Feedback existingFeedback = existingFeedbackOptional.get();
            existingFeedback.setStars(updatedFeedback.getStars());
            existingFeedback.setComment(updatedFeedback.getComment());
            return feedbackRepository.save(existingFeedback);
        } else {
            return null;
        }
    }


    public List<Object[]> getAllFeedBack() {
        return feedbackRepository.getFeedbackWithUserInfo();
    }
}
