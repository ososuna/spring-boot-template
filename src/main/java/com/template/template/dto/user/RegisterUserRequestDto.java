package com.template.template.dto.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterUserRequestDto {
  private String email;
  private String password;
}
