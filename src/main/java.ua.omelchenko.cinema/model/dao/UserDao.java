package model.dao;

import Entity.User;

public interface UserDao extends EntityDao<User> {
    User findUserByEmailAndPass(String email, String password);
    boolean findEmail(String email);

}
