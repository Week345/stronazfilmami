package com.filmpage.repository;

import com.filmpage.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, QuerydslPredicateExecutor<Film> {
    List<Film> findByActive(Boolean active);
    List<Film> findByActiveTrue();
}
