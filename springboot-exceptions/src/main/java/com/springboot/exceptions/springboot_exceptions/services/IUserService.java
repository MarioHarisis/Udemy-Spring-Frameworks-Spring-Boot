package com.springboot.exceptions.springboot_exceptions.services;

import com.springboot.exceptions.springboot_exceptions.models.domain.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();

    Optional<User> findById(Integer id);

}
