package com.example.test.data.db.repositories;

import com.example.test.core.domain.User;
import com.example.test.core.usecase.UserRepository;
import com.example.test.data.db.entity.UserData;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMongoRepository repository;

    @Override
    public Mono<User> findUserById(final ObjectId id) {
        return repository.findById(id).map(UserData::from);
    }

    @Override
    public Mono<User> updateUser(final User user) {
        return repository.save(UserData.to(user)).map(UserData::from);
    }

    @Override
    public Mono<User> createUser(final User user) {
        return repository.save(UserData.to(user)).map(UserData::from);
    }

}
