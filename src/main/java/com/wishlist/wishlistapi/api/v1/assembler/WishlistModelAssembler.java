package com.wishlist.wishlistapi.api.v1.assembler;

import com.wishlist.wishlistapi.api.v1.model.WishlistModel;
import com.wishlist.wishlistapi.domain.model.Wishlist;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WishlistModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public WishlistModel toModel(Wishlist wishlist) {
        return modelMapper.map(wishlist, WishlistModel.class);
    }

    public List<WishlistModel> toCollectionModel(List<Wishlist> wishlists) {
        return wishlists.stream()
                .map(wishlist -> toModel(wishlist))
                .collect(Collectors.toList());
    }
}
