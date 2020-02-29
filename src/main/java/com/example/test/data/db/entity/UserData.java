package com.example.test.data.db.entity;

import com.example.test.core.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Document("User")
public class UserData {

    @Id
    private ObjectId id;
    private String name;
    private Integer age;
    private AddressData userAddress;
    private String hobby;

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    public static class AddressData {
        private String si;
        private String gu;
        private String dong;

        public User.Address from() {
            return User.Address.of(
                    si,
                    gu,
                    dong
            );
        }

        public static UserData.AddressData to(User.Address address) {
            return UserData.AddressData.of(
                    address.getSi(),
                    address.getGu(),
                    address.getDong()
            );
        }
    }

    public User from() {
        return User.of(
                id,
                name,
                age,
                userAddress.from(),
                hobby
        );
    }

    public static UserData to(User user) {
        return UserData.of(
                user.getId(),
                user.getUsername(),
                user.getAge(),
                AddressData.to(user.getAddress()),
                user.getHobby()
        );
    }
}
