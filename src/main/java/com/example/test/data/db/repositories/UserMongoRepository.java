package com.example.test.data.db.repositories;

import com.example.test.data.db.entity.UserData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserMongoRepository extends ReactiveMongoRepository<UserData, ObjectId> { }
