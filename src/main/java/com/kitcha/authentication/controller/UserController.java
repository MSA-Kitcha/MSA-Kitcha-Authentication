package com.kitcha.authentication.controller;

import com.kitcha.authentication.dto.InterestDto;
import com.kitcha.authentication.dto.LoginDto;
import com.kitcha.authentication.dto.SignUpDto;
import com.kitcha.authentication.service.InterestService;
import com.kitcha.authentication.service.LoginService;
import com.kitcha.authentication.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.util.Collections.singletonMap;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final LoginService loginService;
    private final SignUpService signUpService;
    private final InterestService interestService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginMember(@Valid @RequestBody LoginDto dto) {
        Pair<String, String> infomations = loginService.authenticate(dto);
        String jwtToken = infomations.getFirst();
        String role = infomations.getSecond();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        return ResponseEntity.ok().headers(headers).body(
                Map.of("message", "로그인 성공", "role", role));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Map<String, String>> signUpMember(@Valid @RequestBody SignUpDto dto) {
        signUpService.signUpMember(dto);

        return ResponseEntity.ok(singletonMap("message", "회원가입 성공"));
    }

    @PostMapping("/interest")
    public ResponseEntity<Map<String, String>> setInterest(@RequestHeader("X-User-Email") String email, @Valid @RequestBody InterestDto dto) {
        interestService.setInterest(email, dto);

        return ResponseEntity.ok(singletonMap("message", "관심사 설정 성공"));
    }
}
