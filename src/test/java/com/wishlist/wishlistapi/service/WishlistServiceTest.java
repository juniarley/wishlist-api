package com.wishlist.wishlistapi.service;

import com.wishlist.wishlistapi.domain.exception.ExistingProductWishlistException;
import com.wishlist.wishlistapi.domain.exception.MaximumAmountProductWishlistException;
import com.wishlist.wishlistapi.domain.exception.WishlistNotFoundException;
import com.wishlist.wishlistapi.domain.model.Client;
import com.wishlist.wishlistapi.domain.model.Product;
import com.wishlist.wishlistapi.domain.model.Wishlist;
import com.wishlist.wishlistapi.domain.repository.WishlistRepository;
import com.wishlist.wishlistapi.domain.service.WishlistService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static junit.framework.TestCase.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishlistService wishlistService;

    private Wishlist wishlist;

    private List<Wishlist> wishlistList = new ArrayList<>();

    @Before
    public void init(){
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

    }

    @Test
    public void returnItemListWhen_ListProductWishlistClient(){

        when(wishlistRepository.findAllByClient("001"))
                .thenReturn(wishlistList);

        List<Wishlist> wishlistListNew = wishlistService.findAllByClient("001");

        assertTrue(wishlistListNew.size() == 2);
    }

    @Test
    public void returnItemWhen_SearchProductWishlistClient(){

        when(wishlistRepository.findOrFailByProductAndClient("001","001"))
                .thenReturn(Optional.of(wishlist));

        Wishlist wishlistNew = wishlistService.findOrFailByProductClient("001","001");

        assertThat(wishlistNew).isNotNull();
    }

    @Test
    public void returnItemNotFoundWhen_SearchProductWishlistClient(){

        when(wishlistRepository.findOrFailByProductAndClient("001","001"))
                .thenReturn(Optional.empty());

        try {
            Wishlist wishlistNew = wishlistService.findOrFailByProductClient("001", "001");
        }catch (WishlistNotFoundException e){
            assertThat(e)
                    .isInstanceOf(WishlistNotFoundException.class)
                    .hasMessage("The product of code 001 not in the wishlist client's of code 001");
        }
    }

    @Test
    public void returnItemExistingProductWishlistWhen_SearchProductWishlistClient(){

        when(wishlistRepository.findAllByClient("001"))
                .thenReturn(wishlistList);

        try {
            Wishlist wishlistNew = wishlistService.save(wishlist);
        }catch (ExistingProductWishlistException e){
            assertThat(e)
                    .isInstanceOf(ExistingProductWishlistException.class)
                    .hasMessage("Product of code 001 already existing for Wishlist the client of code 001");
        }
    }

    @Test
    public void returnMaximumAmountProductWishlistWhen_RegisterProductWishlistClient(){

        when(wishlistRepository.countByClient("001"))
                .thenReturn(20L);

        try {
            Wishlist wishlistNew = wishlistService.save(wishlist);
        }catch (MaximumAmountProductWishlistException e){
            assertThat(e)
                    .isInstanceOf(MaximumAmountProductWishlistException.class)
                    .hasMessage("Maximum limit from Wishlist client code 001 reached, maximum limit of 20 products");
        }
    }



    @Test
    public void returnItemWhen_RegisterProductWishlistClient(){

        given(wishlistRepository.save(wishlist)).willReturn(wishlist);

        Wishlist wishlistNew = wishlistService.save(wishlist);

        assertThat(wishlistNew).isNotNull();
    }

    @Test
    public void returnSucessWhen_RemoveProductWishlistClient(){

        when(wishlistRepository.findOrFailByProductAndClient("001","001"))
                .thenReturn(Optional.of(wishlist));
        doNothing().when(wishlistRepository).deleteAllByProductAndClient("001","001");

        wishlistService.removeByProductClient("001","001");

        verify(wishlistRepository, times(1)).deleteAllByProductAndClient("001","001");
    }

    @Test
    public void returnItemNotFoundWhen_RemoveProductWishlistClient(){

        when(wishlistRepository.findOrFailByProductAndClient("001","001"))
                .thenReturn(Optional.empty());

        try {
            wishlistService.removeByProductClient("001","001");
        }catch (WishlistNotFoundException e){
            assertThat(e)
                    .isInstanceOf(WishlistNotFoundException.class)
                    .hasMessage("The product of code 001 not in the wishlist client's of code 001");
        }

    }

}
