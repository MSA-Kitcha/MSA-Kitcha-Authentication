package com.kitcha.authentication.service;

import com.kitcha.authentication.Utils.JwtUtils;
import com.kitcha.authentication.dto.CustomUserDetails;
import com.kitcha.authentication.dto.NicknameDto;
import com.kitcha.authentication.entity.UserEntity;
import com.kitcha.authentication.exception.DuplicateException;
import com.kitcha.authentication.exception.EmailNotFoundException;
import com.kitcha.authentication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;

    public void setNickname(String email, NicknameDto dto) throws RuntimeException{
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new EmailNotFoundException("올바른 사용자가 아닙니다.");
        }

        if (userRepository.existsByNickname(dto.getNickname())) {
            throw new DuplicateException("이미 사용중인 닉네임 입니다.");
        }


        userEntity.setNickname(dto.getNickname());
        userRepository.save(userEntity);

    }
}
