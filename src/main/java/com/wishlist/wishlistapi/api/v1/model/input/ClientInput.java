package com.wishlist.wishlistapi.api.v1.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Builder
public class ClientInput {
    @NotBlank
    private String code;
    @NotBlank
    private String name;
}
