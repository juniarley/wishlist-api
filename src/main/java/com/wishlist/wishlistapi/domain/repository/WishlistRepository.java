package com.wishlist.wishlistapi.domain.repository;

import com.wishlist.wishlistapi.domain.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    @Query("{ 'client.code': ?0}")
    public List<Wishlist>  findAllByClient(String codeClient);

    @Query("{'client.code': ?0, 'product.code': ?1}")
    Optional<Wishlist> findOrFailByProductAndClient(String codeClient, String codeProduct);

    @Query(value = "{'client.code': ?0, 'product.code': ?1}", delete = true)
    void deleteAllByProductAndClient(String codeClient, String codeProduct);

    Long countByClient(String codeClient);
}
