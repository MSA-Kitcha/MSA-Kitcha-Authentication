package com.kitcha.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NicknameDto {
    @NotBlank(message = "닉네임은 비어 있을 수 없습니다.")
    private String nickname;
}
