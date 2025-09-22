package com.template.template.integration.controller.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.template.dto.user.RegisterUserDto;
import com.template.template.dto.user.RegisterUserRequestDto;
import com.template.template.entity.UserEntity;
import com.template.template.repository.user.IUserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private IUserRepository userRepository;

  @Test
  public void registerUserWithValidRequest() throws Exception {
    RegisterUserRequestDto request = new RegisterUserRequestDto("pochita@test.com", "123456");

    UserEntity userEntity = UserEntity.builder()
        .id(1L)
        .email("pochita@test.com")
        .name(null)
        .lastName(null)
        .role("USER")
        .password("123456")
        .build();

    when(userRepository.register(any(RegisterUserDto.class))).thenReturn(userEntity);
    mockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.email").value("pochita@test.com"))
        .andExpect(jsonPath("$.role").value("USER"));
  }

}
