package ua.omelchenko.cinema.model.dao;


import ua.omelchenko.cinema.model.dao.impl.DaoFactoryImpl;

public abstract class DaoFactory {
    public abstract UserDao createUserDao();
    public abstract TicketDao createTicketDao();
    public abstract SessionDao createSessionDao();
    public abstract FilmDao createFilmDao();


    private static final class DaoFactoryHolder {
        static final DaoFactory daoFactory = new DaoFactoryImpl();
    }

    public static DaoFactory getInstance(){
        return DaoFactoryHolder.daoFactory;
    }
}
