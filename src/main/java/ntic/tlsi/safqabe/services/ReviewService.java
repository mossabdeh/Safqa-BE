package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.dtos.ReviewDTO;
import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.entities.Review;
import ntic.tlsi.safqabe.repositories.ClientRepository;
import ntic.tlsi.safqabe.repositories.ProductRepository;
import ntic.tlsi.safqabe.repositories.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Client client = clientRepository.findById(reviewDTO.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid client ID"));
        Product product = productRepository.findById(reviewDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        Review review = convertToEntity(reviewDTO);
        review.setClient(client);
        review.setProduct(product);

        Review savedReview = reviewRepository.save(review);
        return convertToDto(savedReview);
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByClientId(Long clientId) {
        return reviewRepository.findByClientId(clientId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Review convertToEntity(ReviewDTO reviewDTO) {
        return modelMapper.map(reviewDTO, Review.class);
    }

    private ReviewDTO convertToDto(Review review) {
        return modelMapper.map(review, ReviewDTO.class);
    }
}