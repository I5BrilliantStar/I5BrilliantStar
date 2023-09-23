package com.example.project02.service;


import com.example.project02.entity.User;
import com.example.project02.entity.UserEntity;
import com.example.project02.exception.ErrorCode;
import com.example.project02.exception.SimpleException;
import com.example.project02.repository.UserEntityRepository;
import com.example.project02.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.RowSet;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;


    public String profile(String token) {
        token = token.split(" ")[1];
        return JwtTokenUtils.getUsername(token, secretKey);
    }

    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserName(userName).map(User::fromEntity).orElseThrow(
                () -> new SimpleException(ErrorCode.USER_NOT_FOUND, String.format("userName is %s", userName))
        );
    }

    public String login(String userName, String password) {
        User savedUser = loadUserByUsername(userName);

        if (!encoder.matches(password, savedUser.getPassword())) {
            throw new SimpleException(ErrorCode.INVALID_PASSWORD);
        }
        return JwtTokenUtils.generateAccessToken(userName, secretKey, expiredTimeMs);
    }


    @Transactional
    public User join(String userName, String password) {
        // check the userId not exist
        userRepository.findByUserName(userName).ifPresent(it -> {
            throw new SimpleException(ErrorCode.DUPLICATED_USER_NAME, String.format("userName is %s", userName));
        });

        UserEntity savedUser = userRepository.save(UserEntity.of(userName, encoder.encode(password)));
        return User.fromEntity(savedUser);
    }

}

