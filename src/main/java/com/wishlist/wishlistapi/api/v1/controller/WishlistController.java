package com.wishlist.wishlistapi.api.v1.controller;

import com.wishlist.wishlistapi.api.v1.assembler.WishlistInputDisassembler;
import com.wishlist.wishlistapi.api.v1.assembler.WishlistModelAssembler;
import com.wishlist.wishlistapi.api.v1.model.WishlistModel;
import com.wishlist.wishlistapi.api.v1.model.input.WishlistInput;
import com.wishlist.wishlistapi.domain.service.WishlistService;
import com.wishlist.wishlistapi.domain.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/v1/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private WishlistModelAssembler wishlistModelAssembler;

    @Autowired
    private WishlistInputDisassembler wishlistInputDisassembler;

    @GetMapping("/client/{codeClient}")
    public List<WishlistModel> listAllClient(@PathVariable String codeClient) {
        List<Wishlist> allWishlist = wishlistService.findAllByClient(codeClient);

        return wishlistModelAssembler.toCollectionModel(allWishlist);
    }

    @GetMapping("/client/{codeClient}/product/{codeProduct}")
    public WishlistModel find(@PathVariable String codeClient, @PathVariable String codeProduct) {
        Wishlist wishlist = wishlistService.findOrFailByProductClient(codeClient, codeProduct);

        return wishlistModelAssembler.toModel(wishlist);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WishlistModel add(@RequestBody @Valid WishlistInput wishlistInput) {
        Wishlist wishlist = wishlistInputDisassembler.toDomainObject(wishlistInput);

        wishlist = wishlistService.save(wishlist);

        return wishlistModelAssembler.toModel(wishlist);
    }

    @DeleteMapping("/client/{codeClient}/product/{codeProduct}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String codeClient, @PathVariable String codeProduct) {
        wishlistService.removeByProductClient(codeClient, codeProduct);
    }

 }
