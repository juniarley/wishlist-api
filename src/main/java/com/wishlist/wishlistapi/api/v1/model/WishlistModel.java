package com.wishlist.wishlistapi.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WishlistModel {

    private String id;
    private ProductModel product;
    private ClientModel client;

}
