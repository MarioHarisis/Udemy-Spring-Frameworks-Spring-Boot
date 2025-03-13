package com.springboot.exceptions.springboot_exceptions.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.exceptions.springboot_exceptions.models.domain.User;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private List<User> users;

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Integer id) {

        // forma optimizada de hacerlo
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();

        /*
         * Forma tradicional-----------------
         * 
         * User user = null;
         * 
         * for (User item : users) {
         * if (item.getId().equals(id)) {
         * user = item;
         * break;
         * }
         * }
         * return Optional.ofNullable(user); // si user es null devuelve Optional EMPTY
         */
    }

}
