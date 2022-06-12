package com.wishlist.wishlistapi.domain.service;

import com.wishlist.wishlistapi.domain.exception.ExistingProductWishlistException;
import com.wishlist.wishlistapi.domain.exception.MaximumAmountProductWishlistException;
import com.wishlist.wishlistapi.domain.exception.WishlistNotFoundException;
import com.wishlist.wishlistapi.domain.model.Wishlist;
import com.wishlist.wishlistapi.domain.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public List<Wishlist> findAllByClient(String codeClient){
        return wishlistRepository.findAllByClient(codeClient);
    }

    public Wishlist findOrFailByProductClient(String codeClient, String codeProduct) {
        return wishlistRepository.findOrFailByProductAndClient(codeClient, codeProduct)
                .orElseThrow(() -> new WishlistNotFoundException(codeClient, codeProduct));
    }

    public Wishlist save(Wishlist wishlist) {
        String codeClient = wishlist.getClient().getCode();
        String codeProduct= wishlist.getProduct().getCode();
        Long wishlistCountItemClient =  wishlistRepository.countByClient(codeClient);

        if(wishlistCountItemClient < 20) {

            Optional<Wishlist> wishlistExisting = wishlistRepository.findOrFailByProductAndClient(codeClient, codeProduct);
            if (wishlistExisting.isPresent()) {
                throw new ExistingProductWishlistException(codeClient, codeProduct);
            }

            return wishlistRepository.save(wishlist);
        }else{
            throw new MaximumAmountProductWishlistException(codeClient, codeClient);
        }
    }

    public void removeByProductClient(String codeClient, String codeProduct) {

        wishlistRepository.findOrFailByProductAndClient(codeClient, codeProduct)
                .orElseThrow(() -> new WishlistNotFoundException(codeClient, codeProduct));

        wishlistRepository.deleteAllByProductAndClient(codeClient, codeProduct);
    }
}
