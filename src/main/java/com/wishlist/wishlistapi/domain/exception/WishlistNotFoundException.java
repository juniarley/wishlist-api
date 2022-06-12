package com.wishlist.wishlistapi.domain.exception;

public class WishlistNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public WishlistNotFoundException(String message) {
        super(message);
    }

    public WishlistNotFoundException(String codeClient, String wishlistId) {
        this(String.format("The product of code %s not in the wishlist client's of code %s", wishlistId, codeClient));
    }
}
