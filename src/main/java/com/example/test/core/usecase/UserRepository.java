package com.example.test.core.usecase;

import com.example.test.core.domain.User;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> findUserById(final ObjectId id);
    Mono<User> updateUser(final User user);
    Mono<User> createUser(final User user);
}
