package com.wishlist.wishlistapi.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection="wishlist")
public class Wishlist {

    @Id
    private String id;

    private Product product;

    private Client client;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate = new Date();
}
