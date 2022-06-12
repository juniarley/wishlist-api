package com.wishlist.wishlistapi.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.wishlist.wishlistapi.domain.repository")
public interface MongoConfig {
}
