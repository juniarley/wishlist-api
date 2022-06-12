package com.wishlist.wishlistapi.domain.exception;

public class MaximumAmountProductWishlistException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public MaximumAmountProductWishlistException(String message) {
        super(message);
    }

    public MaximumAmountProductWishlistException(String codeClient, String codeProduct) {
        this(String.format("Maximum limit from Wishlist client code %s reached, maximum limit of 20 products", codeClient));
    }
}
