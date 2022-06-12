package com.wishlist.wishlistapi.domain.exception;

public class ExistingProductWishlistException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ExistingProductWishlistException(String message) {
        super(message);
    }

    public ExistingProductWishlistException(String codeClient, String codeProduct) {
        this(String.format("Product of code %s already existing for Wishlist the client of code %s", codeProduct, codeClient));
    }
}
