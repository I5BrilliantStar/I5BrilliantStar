package com.example.project02.controller.response;


import com.example.project02.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public
class UserJoinResponse {
    private Integer id;
    private String name;

    public static UserJoinResponse fromUser(User user) {
        return new UserJoinResponse(
                user.getId(),
                user.getUsername()
        );
    }

}
