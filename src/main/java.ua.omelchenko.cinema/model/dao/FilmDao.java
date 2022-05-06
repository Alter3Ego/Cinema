package model.dao;

import entity.Film;

import java.util.List;

public interface FilmDao extends EntityDao<Film> {
    List<Film> findAllFilms();
}
