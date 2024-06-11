package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.entities.Cart;
import ntic.tlsi.safqabe.entities.CartItem;
import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.entities.ProductItem;
import ntic.tlsi.safqabe.repositories.CartItemRepository;
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
    @Autowired
    private CartItemRepository cartItemRepository;

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

    public Cart removeFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        Cart cart = cartItem.getCart();
        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());
        return cartRepository.save(cart);
    }

    public Cart updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItem.setQuantity(quantity);
        cartItem.setPrice(cartItem.getProductItem().getPrice() * quantity);

        Cart cart = cartItem.getCart();
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());
        cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
    }
}
