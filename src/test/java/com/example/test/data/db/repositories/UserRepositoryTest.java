package com.example.test.data.db.repositories;

import com.example.test.core.domain.User;
import com.example.test.core.usecase.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(
        initializers = ConfigFileApplicationContextInitializer.class,
        classes = {
                TestEmbeddedMongoConfig.class,
                UserRepositoryImpl.class
        })
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User initData;

    @BeforeEach
    public void setup() {
        initData = userRepository.createUser(
                User.of(
                        new ObjectId(),
                        "yeoseonggae",
                        29,
                        User.Address.of("seoul", "kangbuk", "mia"),
                        "none"
                )
        ).block();
        System.out.println("==========================================================");
        System.out.println("[setup User Data] : " + initData);
        System.out.println("==========================================================");
    }

    @Test
    @DisplayName("유저 데이터 생성 테스트")
    public void createUserTest() {
        //준비
        User createUser = User.of(
                new ObjectId(),
                "UserName",
                29,
                User.Address.of("seoul", "kangbuk", "mia"),
                "none"
        );

        //실행
        Mono<User> createdUser = userRepository.createUser(createUser);

        //단언
        StepVerifier.create(createdUser)
                .assertNext(user -> {
                    System.out.println("==========================================================");
                    System.out.println("[Create User Data] : " + user);
                    System.out.println("==========================================================");
                    assertEquals(user.getUsername(), "UserName");
                    assertEquals(user.getAge(), 29);
                    assertEquals(user.getHobby(), "none");
                    assertEquals(user.getAddress().getSi(), "seoul");
                }).verifyComplete();

    }

    @Test
    @DisplayName("유저 데이터 수정 테스트")
    public void updateUserTest() {
        //준비
        initData.setUsername("여성게");

        //실행
        Mono<User> updatedUser = userRepository.updateUser(initData);

        //단언
        StepVerifier.create(updatedUser)
                .assertNext(user -> {
                    System.out.println("==========================================================");
                    System.out.println("[Update User Data] : " + user);
                    System.out.println("==========================================================");
                    assertEquals(user.getId().toHexString(), initData.getId().toHexString());
                    assertEquals(user.getUsername(), "여성게");
                    assertEquals(user.getAge(), 29);
                    assertEquals(user.getHobby(), "none");
                    assertEquals(user.getAddress().getSi(), "seoul");
                }).verifyComplete();
    }

    @Test
    @DisplayName("유저 이름으로 유저 데이터 조회 테스트")
    public void findUserByNameTest() {
        //준비
        final ObjectId id = initData.getId();

        //실행
        Mono<User> findUser = userRepository.findUserById(id);

        //단언
        StepVerifier.create(findUser)
                .assertNext(user -> {
                    System.out.println("==========================================================");
                    System.out.println("[Find User Data] : " + user);
                    System.out.println("==========================================================");
                    assertEquals(user.getUsername(), "yeoseonggae");
                    assertEquals(user.getAge(), 29);
                    assertEquals(user.getHobby(), "none");
                    assertEquals(user.getAddress().getSi(), "seoul");
                }).verifyComplete();
    }
}
