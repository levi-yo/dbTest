package com.example.test.data.db.repositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.io.IOException;

@TestConfiguration
@EnableReactiveMongoRepositories(basePackages = {
        "com.example.test.data.db.repositories"
}, reactiveMongoTemplateRef = "userTestMongoTemplate")
public class TestEmbeddedMongoConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public MongodExecutable mongodExecutable() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        IMongodConfig mongoDConfig = new MongodConfigBuilder()
                .version(Version.Main.V3_4)
                .net(new Net("127.0.0.1", 27017, false))
                .build();

        return starter.prepare(mongoDConfig);
    }

    @Bean("userTestMongoTemplate")
    public ReactiveMongoTemplate mongoTemplate() throws IOException {
        MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
        return new ReactiveMongoTemplate(mongo, "USER_TEST_DB");
    }
}
