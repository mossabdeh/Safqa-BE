package ntic.tlsi.safqabe.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ntic.tlsi.safqabe.dtos.ProductGetDto;
import ntic.tlsi.safqabe.dtos.RecommendationRequestDTO;
import ntic.tlsi.safqabe.dtos.RecommendationResponseDTO;
import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
@SecurityRequirement(name = "bearerAuth")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/get-recommendations")
    public RecommendationResponseDTO getRecommendations(@RequestBody RecommendationRequestDTO request) {
        return recommendationService.getRecommendations(request);
    }

    @PostMapping("/find-products")
    public List<ProductGetDto> findProductsByRecommendations(@RequestBody RecommendationRequestDTO request) {
        RecommendationResponseDTO recommendations = recommendationService.getRecommendations(request);
        return recommendationService.findProductsByNames(recommendations.getRecommendations());
    }

    @GetMapping("/similar-products")
    public List<ProductGetDto> getSimilarProducts(@RequestParam("productName") String productName) {
        return recommendationService.getSimilarProducts(productName);
    }


}
