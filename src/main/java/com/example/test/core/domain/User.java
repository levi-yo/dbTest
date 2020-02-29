package com.example.test.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class User {

    @JsonIgnore
    private ObjectId id;
    @NotNull
    private String username;
    @NotNull
    private Integer age;
    @NotNull
    private Address address;
    private String hobby;

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    public static class Address {
        @NotNull
        private String si;
        @NotNull
        private String gu;
        @NotNull
        private String dong;
    }
}
