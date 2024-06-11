package ntic.tlsi.safqabe.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ntic.tlsi.safqabe.entities.Cart;
import ntic.tlsi.safqabe.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@SecurityRequirement(name = "bearerAuth")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{clientId}")
    public Cart getCart(@PathVariable Long clientId) {
        return cartService.getCartByClientId(clientId);
    }

    @PostMapping("/{clientId}/add")
    public Cart addToCart(@PathVariable Long clientId, @RequestParam Long productItemId, @RequestParam int quantity) {
        return cartService.addToCart(clientId, productItemId, quantity);
    }

    @DeleteMapping("/{cartItemId}/remove")
    public Cart removeFromCart(@PathVariable Long cartItemId) {
        return cartService.removeFromCart(cartItemId);
    }

    @PutMapping("/{cartItemId}/update")
    public Cart updateCartItem(@PathVariable Long cartItemId, @RequestParam int quantity) {
        return cartService.updateCartItem(cartItemId, quantity);
    }
}
