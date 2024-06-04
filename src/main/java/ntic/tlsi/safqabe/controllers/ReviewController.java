package ntic.tlsi.safqabe.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import ntic.tlsi.safqabe.dtos.ReviewDTO;
import ntic.tlsi.safqabe.entities.Review;
import ntic.tlsi.safqabe.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@SecurityRequirement(name = "bearerAuth")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTO> addReview(@Valid @RequestBody ReviewDTO reviewDTO) {
            ReviewDTO savedReview = reviewService.saveReview(reviewDTO);
            return ResponseEntity.ok(savedReview);}
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
            List<ReviewDTO> reviews = reviewService.getAllReviews();
            return ResponseEntity.ok(reviews);}

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProductId(@PathVariable Long productId) {
            List<ReviewDTO> reviews = reviewService.getReviewsByProductId(productId);
            return ResponseEntity.ok(reviews);}
    @GetMapping("/client/{clientId}")
        public ResponseEntity<List<ReviewDTO>> getReviewsByClientId(@PathVariable Long clientId) {
            List<ReviewDTO> reviews = reviewService.getReviewsByClientId(clientId);
            return ResponseEntity.ok(reviews);
        }
    }
