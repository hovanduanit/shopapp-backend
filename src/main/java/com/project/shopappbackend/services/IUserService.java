package com.project.shopappbackend.services;


import com.project.shopappbackend.dtos.UserDTO;
import com.project.shopappbackend.exceptions.DataNotFoundException;
import com.project.shopappbackend.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws DataNotFoundException;
    String login(String phoneNumber, String password);
}
