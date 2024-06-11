package ntic.tlsi.safqabe.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ntic.tlsi.safqabe.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
@SecurityRequirement(name = "bearerAuth")
public class RecommendationController {
    @Autowired
    private  RecommendationService recommendationService;
    @PostMapping
    public List<String> getRecommendations(@RequestBody String productName) {
        return recommendationService.predict(productName);
    }
}
