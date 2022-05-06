package model.service.impl;

import entity.Film;
import model.dao.DaoFactory;
import model.dao.FilmDao;
import model.service.FilmService;
import java.util.List;

public class FilmServiceImpl implements FilmService {
    private final DaoFactory daoFactory;

    public FilmServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Film> getAllFilms() {
        try (FilmDao dao = daoFactory.createFilmDao()) {
            return dao.findAllFilms();
        }
    }
}
