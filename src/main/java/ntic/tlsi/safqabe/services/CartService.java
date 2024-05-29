package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.entities.Cart;
import ntic.tlsi.safqabe.entities.CartItem;
import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.entities.ProductItem;
import ntic.tlsi.safqabe.repositories.CartRepository;
import ntic.tlsi.safqabe.repositories.ClientRepository;
import ntic.tlsi.safqabe.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    public Cart getCartByClientId(Long clientId) {
        return cartRepository.findByClientId(clientId);
    }

    public Cart addToCart(Long clientId, Long productItemId, int quantity) {
        Cart cart = cartRepository.findByClientId(clientId);
        if (cart == null) {
            cart = new Cart();
            Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
            cart.setClient(client);
        }

        Optional<ProductItem> productItemOpt = productItemRepository.findById(productItemId);
        if (!productItemOpt.isPresent()) {
            throw new RuntimeException("Product item not found");
        }

        ProductItem productItem = productItemOpt.get();
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProductItem().getId().equals(productItemId))
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProductItem(productItem);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(productItem.getPrice() * quantity);
            cart.getItems().add(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setPrice(cartItem.getProductItem().getPrice() * cartItem.getQuantity());
        }

        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());
        return cartRepository.save(cart);
    }

    public Cart removeFromCart(Long clientId, Long productItemId) {
        Cart cart = cartRepository.findByClientId(clientId);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProductItem().getId().equals(productItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product item not found in cart"));

        cart.getItems().remove(cartItem);
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());
        return cartRepository.save(cart);
    }

    public Cart updateCartItem(Long clientId, Long productItemId, int quantity) {
        Cart cart = cartRepository.findByClientId(clientId);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProductItem().getId().equals(productItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product item not found in cart"));

        cartItem.setQuantity(quantity);
        cartItem.setPrice(cartItem.getProductItem().getPrice() * quantity);
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());
        return cartRepository.save(cart);
    }
}
