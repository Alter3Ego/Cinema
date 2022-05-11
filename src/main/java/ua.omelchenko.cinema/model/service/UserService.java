package ua.omelchenko.cinema.model.service;

import ua.omelchenko.cinema.entity.User;

import java.math.BigDecimal;
import java.sql.Connection;

public interface UserService {
    User getUserByEmailAndPass(String email, String password);
    boolean checkEmail(String email);
    boolean addUser(User user);
    User updateBalance(User user, BigDecimal sum);
    User updateBalance(User user, BigDecimal sum, Connection outsideConnection);
    User getUserById(int id);

}
