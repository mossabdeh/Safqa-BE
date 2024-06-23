package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.dtos.ProductGetDto;
import ntic.tlsi.safqabe.dtos.RecommendationRequestDTO;
import ntic.tlsi.safqabe.dtos.RecommendationResponseDTO;
import ntic.tlsi.safqabe.dtos.SimilarProductResponse;
import ntic.tlsi.safqabe.dtos.SimilarProduct;
import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.repositories.ProductRepository;
import ntic.tlsi.safqabe.services.JwtUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(RecommendationService.class);

    private final ModelMapper modelMapper = new ModelMapper();
    public RecommendationResponseDTO getRecommendations(RecommendationRequestDTO request) {
        String djangoApiUrl = "http://127.0.0.1:8000/RecSys/recommend/";

        // Fetch the username from the security context
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Generate the JWT token using the JwtUtil service
        String jwtToken = jwtUtil.generateToken(userDetails, userService.getUserIdByEmail(userDetails.getUsername()));

        // Set up the headers with the JWT token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<RecommendationRequestDTO> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<RecommendationResponseDTO> responseEntity = restTemplate.exchange(
                djangoApiUrl,
                HttpMethod.POST,
                requestEntity,
                RecommendationResponseDTO.class
        );

        return responseEntity.getBody();
    }

    public List<ProductGetDto> findProductsByNames(List<String> productNames) {
        List<Product> products = productRepository.findByProductNameIn(productNames);
        return products.stream()
                .map(this::convert2GetDto)
                .collect(Collectors.toList());
    }
    public List<ProductGetDto> getSimilarProducts(String productName) {
        System.out.println("Received request to get similar products for: " + productName);

        String djangoApiUrl = "http://127.0.0.1:8000/ImgRecSys/similar-products/?product_name=" + productName;
        System.out.println("Django API URL: " + djangoApiUrl);

        // Fetch the username from the security context
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Fetched user details: " + userDetails.getUsername());

        // Generate the JWT token using the JwtUtil service
        String jwtToken = jwtUtil.generateToken(userDetails, userService.getUserIdByEmail(userDetails.getUsername()));
        System.out.println("Generated JWT token");

        // Set up the headers with the JWT token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + jwtToken);
        System.out.println("Set HTTP headers with JWT token");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<SimilarProductResponse> responseEntity = restTemplate.exchange(
                djangoApiUrl,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<SimilarProductResponse>() {}
        );
        System.out.println("Received response from Django API");

        SimilarProductResponse similarProductResponse = responseEntity.getBody();
        System.out.println("Django response body: " + similarProductResponse);

        if (similarProductResponse != null && similarProductResponse.getSimilarProducts() != null) {
            System.out.println("Found " + similarProductResponse.getSimilarProducts().size() + " similar products");
        } else {
            System.out.println("No similar products found or response is null");
            return new ArrayList<>();
        }

        List<String> productNames = similarProductResponse.getSimilarProducts().stream()
                .map(SimilarProduct::getProductName)
                .collect(Collectors.toList());
        System.out.println("Extracted product names from response");

        return findProductsByNames(productNames);
    }
    private ProductGetDto convert2GetDto(Product product) {
        return modelMapper.map(product, ProductGetDto.class);
    }
}
