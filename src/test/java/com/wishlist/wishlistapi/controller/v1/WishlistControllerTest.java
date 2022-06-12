package com.wishlist.wishlistapi.controller.v1;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.Mockito.*;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.wishlist.wishlistapi.api.v1.assembler.WishlistInputDisassembler;
import com.wishlist.wishlistapi.api.v1.assembler.WishlistModelAssembler;
import com.wishlist.wishlistapi.api.v1.controller.WishlistController;
import com.wishlist.wishlistapi.api.v1.model.input.ClientInput;
import com.wishlist.wishlistapi.api.v1.model.input.ProductInput;
import com.wishlist.wishlistapi.api.v1.model.input.WishlistInput;
import com.wishlist.wishlistapi.domain.exception.ExistingProductWishlistException;
import com.wishlist.wishlistapi.domain.exception.MaximumAmountProductWishlistException;
import com.wishlist.wishlistapi.domain.model.Client;
import com.wishlist.wishlistapi.domain.model.Product;
import com.wishlist.wishlistapi.domain.model.Wishlist;
import com.wishlist.wishlistapi.domain.service.WishlistService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;


import java.util.*;

@SpringBootTest
public class WishlistControllerTest {

    @Autowired
    private WishlistController wishlistController;

    @MockBean
    private WishlistService wishlistService;

    @Autowired
    private WishlistModelAssembler wishlistModelAssembler;

    @Autowired
    private WishlistInputDisassembler wishlistInputDisassembler;

    private Wishlist wishlist;

    private WishlistInput wishlistInput;

      private List<Wishlist> wishlistList = new ArrayList<>();



    @BeforeEach
    public void setup() {
        standaloneSetup(this.wishlistController);

        wishlist = Wishlist.builder()
                .id("507f1f77bcf86cd799439011")
                .client(Client.builder().name("Mary").code("001").build())
                .product(Product.builder().name("Book").code("001").build())
                .build();

        Wishlist wishlist1 = Wishlist.builder()
                .id("507f1f77bcf86cd799js011")
                .client(Client.builder().name("Mary").code("001").build())
                .product(Product.builder().name("Bike").code("002").build())
                .build();

        wishlistList.add(wishlist);
        wishlistList.add(wishlist1);

        wishlistInput = WishlistInput.builder()
                .client(ClientInput.builder().name("Mary").code("001").build())
                .product(ProductInput.builder().name("Bike").code("002").build())
                .build();

    }

    @Test
    public void returnSuccessWhen_ListProductWishlistClient() {

        when(wishlistService.findAllByClient("001"))
                .thenReturn(wishlistList);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("v1/wishlist/client/{codeClient}","001")
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void returnSuccessWhen_ListProductWishlistClientEmpty() {

        List<Wishlist> listWishlist =  new ArrayList<>();

        when(wishlistService.findAllByClient("001"))
                .thenReturn(listWishlist);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("v1/wishlist/client/{codeClient}","001","001")
                .then()
                .statusCode(HttpStatus.OK.value());
    }


    @Test
    public void returnAlreadyExistsWhen_RegisrterProductWishlistClient() {

        doThrow(new ExistingProductWishlistException("002", "002")).when(wishlistService)
                .save(wishlist);

        given()
                .contentType(ContentType.JSON)
                .body(WishlistInput.class)
                .when()
                .post("v1/wishlist")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void returnFullListWhen_RegisrterProductWishlistClient() {

        doThrow(new MaximumAmountProductWishlistException("002", "002")).when(wishlistService)
                .save(wishlist);

        given()
                .contentType(ContentType.JSON)
                .body(WishlistInput.class)
                .when()
                .post("v1/wishlist")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void returnNoContentWhen_RemoveProductWishlistClient() {

        doNothing().when(wishlistService).removeByProductClient("001","001");

        given()
                .accept(ContentType.JSON)
                .when()
                .delete("v1/wishlist/client/{codeClient}/product/{codeProduct}","001","001")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

    }

}
