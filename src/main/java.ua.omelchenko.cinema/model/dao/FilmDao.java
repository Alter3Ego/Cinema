package model.dao;

import Entity.Film;

import java.util.List;

public interface FilmDao extends EntityDao<Film> {
    List<Film> findAllFilms();
}
