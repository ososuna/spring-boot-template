package com.template.template.service.user;

import org.springframework.stereotype.Service;

import com.template.template.dto.user.RegisterUserDto;
import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.entity.UserEntity;
import com.template.template.exception.BadRequestException;
import com.template.template.repository.user.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final IUserRepository userRepository;

  @Override
  public UserEntity register(RegisterUserRequestDto createUserRequestDto) throws BadRequestException {
    RegisterUserDto registerUserDto = RegisterUserDto.createWithUserAndPassword(createUserRequestDto.getEmail(),
        createUserRequestDto.getPassword());
    return this.userRepository.register(registerUserDto);
  }

}
