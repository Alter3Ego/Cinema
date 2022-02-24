package model.service;

import Entity.User;

import java.math.BigDecimal;

public interface UserService {
    User getUserByEmailAndPass(String email, String password);
    boolean checkEmail(String email);
    boolean addUser(User user);
    User updateBalance(User user, BigDecimal sum);

}
