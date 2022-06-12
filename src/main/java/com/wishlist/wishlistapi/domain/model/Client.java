package com.wishlist.wishlistapi.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Client {

    private String code;

    private String name;

}
