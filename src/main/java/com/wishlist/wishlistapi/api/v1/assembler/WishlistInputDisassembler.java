package com.wishlist.wishlistapi.api.v1.assembler;

import com.wishlist.wishlistapi.api.v1.model.input.WishlistInput;
import com.wishlist.wishlistapi.domain.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class WishlistInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Wishlist toDomainObject(WishlistInput wishlistInput) {
        return modelMapper.map(wishlistInput, Wishlist.class);
    }

    public void copyToDomainObject(WishlistInput wishlistInput, Wishlist wishlist) {
        modelMapper.map(wishlistInput, wishlist);
    }
}
