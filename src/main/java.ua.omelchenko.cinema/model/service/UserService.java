package model.service;

import Entity.User;

public interface UserService {
    User getUserByEmailAndPass(String email, String password);
    boolean checkEmail(String email);
    boolean addUser(User user);

}
