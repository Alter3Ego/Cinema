package model.dao;

import model.dao.impl.DaoFactoryImpl;

public abstract class DaoFactory {
    public abstract UserDao createUserDao();
    public abstract SessionDao createSessionDao();

    private static final class DaoFactoryHolder {
        static final DaoFactory daoFactory = new DaoFactoryImpl();
    }

    public static DaoFactory getInstance(){
        return DaoFactoryHolder.daoFactory;
    }
}
