package com.wishlist.wishlistapi.api.v1.model.input;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Setter
@Getter
@Builder
public class WishlistInput {

    @Valid
    @NotNull
    private ProductInput product;

    @Valid
    @NotNull
    private ClientInput client;

}
