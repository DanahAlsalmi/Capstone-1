package com.example.capstone1.Controller;
import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.ProductService;
import com.example.capstone1.Service.UserService;
import com.example.capstone1.Service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final WishlistService wishlistService;
    private final UserService userService;
    private final ProductService productService;



    //test Done.
    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    //test Done
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    //test Done
    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable int userId, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = userService.updateUser(userId, user);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User Not found"));
    }

    //test Done
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User Not found"));
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.status(200).body(user);
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("User Not found"));
        }
    }

    @PostMapping("/subscribe-prime/{userId}")
    public ResponseEntity subscribeToPrime(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(new ApiResponse("User not found"));
        }
        if (user.isPrime()) {
            return ResponseEntity.status(400).body(new ApiResponse("User is already a Prime member"));
        }
        if (user.getBalance() < 100) {
            return ResponseEntity.status(400).body(new ApiResponse("Insufficient balance"));
        }

        User subscribedUser = userService.subscribeToPrime(userId);
        return ResponseEntity.status(200).body(subscribedUser);
    }

    @PutMapping("/cancel-prime/{userId}")
    public ResponseEntity cancelPrimeSubscription(@PathVariable int userId) {
        boolean success = userService.cancelPrimeSubscription(userId);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Prime subscription cancelled successfully."));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("Prime subscription not found"));
        }
    }


    @PostMapping("/add-wishlist/{userId}/{productId}")
    public ResponseEntity<?> addItemToWishlist(@PathVariable int userId, @PathVariable int productId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(new ApiResponse("User not found"));
        }
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Product not found"));
        }
        wishlistService.addItemToWishlist(userId, productId);
        return ResponseEntity.status(200).body(new ApiResponse("Product added to wishlist"));
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<?> getUserWishlist(@PathVariable int userId) {
        ArrayList<Product> wishlist = wishlistService.getUserWishlist(userId);
        if (wishlist != null) {
            return ResponseEntity.status(200).body(wishlist);
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("User not found"));
        }
    }

    @DeleteMapping("/wishlist/{userId}/{productId}")
    public ResponseEntity<?> removeItemFromWishlist(@PathVariable int userId, @PathVariable int productId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(new ApiResponse("User not found"));
        }
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Product not found"));
        }
        wishlistService.removeItemFromWishlist(userId, productId);
        return ResponseEntity.status(200).body(new ApiResponse("Product removed from wishlist"));
    }
}
