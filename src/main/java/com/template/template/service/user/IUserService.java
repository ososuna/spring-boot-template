package com.template.template.service.user;

import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.entity.UserEntity;
import com.template.template.exception.BadRequestException;

public interface IUserService {
  UserEntity register(RegisterUserRequestDto createUserRequestDto) throws BadRequestException;
}