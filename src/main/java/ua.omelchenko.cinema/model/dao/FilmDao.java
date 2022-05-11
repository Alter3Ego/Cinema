package ua.omelchenko.cinema.model.dao;

import ua.omelchenko.cinema.entity.Film;

import java.util.List;

public interface FilmDao extends EntityDao<Film> {
    List<Film> findAllFilms();
}
