package com.leonardo.shoppingcart.client;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client> findByEmail(String email);

    Boolean existsByEmail(String email);
}
