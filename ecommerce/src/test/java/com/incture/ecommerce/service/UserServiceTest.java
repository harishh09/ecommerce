package com.incture.ecommerce.service;

import com.incture.ecommerce.entity.User;
import com.incture.ecommerce.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testRegisterUser() {

        User user = User.builder()
                .id(1L)
                .name("Harish")
                .email("harish@gmail.com")
                .password("123456")
                .role("USER")
                .build();

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.registerUser(user);

        assertNotNull(savedUser);
        assertEquals("Harish", savedUser.getName());

        verify(userRepository, times(1)).save(user);
    }
}