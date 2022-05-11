package ua.omelchenko.cinema.model.service.impl;

import ua.omelchenko.cinema.entity.Film;

import ua.omelchenko.cinema.model.dao.DaoFactory;
import ua.omelchenko.cinema.model.dao.FilmDao;
import ua.omelchenko.cinema.model.service.FilmService;
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
