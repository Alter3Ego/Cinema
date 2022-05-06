package model.dao;

import entity.User;

import java.math.BigDecimal;
import java.sql.Connection;

public interface UserDao extends EntityDao<User> {
    User findUserByEmailAndPass(String email, String password);

    boolean findEmail(String email);

    User updateBalance(User user, BigDecimal sum);

    User updateBalance(User user, BigDecimal sum, Connection outsideConnection);

    @Override
    User getById(int id);
}
