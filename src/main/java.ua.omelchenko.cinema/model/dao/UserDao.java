package model.dao;

import Entity.User;
import java.math.BigDecimal;

public interface UserDao extends EntityDao<User> {
    User findUserByEmailAndPass(String email, String password);
    boolean findEmail(String email);
    User updateBalance(User user, BigDecimal sum);
}
