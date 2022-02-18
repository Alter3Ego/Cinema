package model.service.impl;

import Entity.User;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.service.UserService;

public class UserServiceImpl implements UserService {
    private final DaoFactory daoFactory;

    public UserServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public User getUserByEmailAndPass(String email, String password) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findUserByEmailAndPass(email,password);
        }
    }

    @Override
    public boolean checkEmail(String email) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findEmail(email);
        }
    }

    @Override
    public boolean addUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.create(user);
        }
    }
}
