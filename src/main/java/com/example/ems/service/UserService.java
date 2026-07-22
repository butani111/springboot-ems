package com.example.ems.service;

import com.example.ems.dto.UserRequest;
import com.example.ems.dto.UserResponse;

public interface UserService {
    public UserResponse createUser(UserRequest req);
}
