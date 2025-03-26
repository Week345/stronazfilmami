package com.filmpage.service;

import com.filmpage.dto.CreateFilmDto;
import com.filmpage.dto.FilmDto;
import com.filmpage.dto.SearchRequest;
import com.filmpage.dto.UpdateFilmDto;
import com.filmpage.exception.FilmNotFound;
import com.filmpage.exception.FilmVariableNull;
import com.filmpage.mapper.FilmMapper;
import com.filmpage.model.Film;
import com.filmpage.model.QFilm;
import com.filmpage.repository.FilmRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class FilmService {
    private final FilmRepository repository;
    private final FilmMapper mapper;
    private final CategoryService categoryService;

    public Page<FilmDto> search(SearchRequest dto, Pageable pageable) {
        QFilm film = QFilm.film;
        BooleanBuilder builder = new BooleanBuilder();
        if (dto.getCategory() != null) {
            builder.and(film.categories.any().name.containsIgnoreCase(dto.getCategory()));
        }
        if (dto.getSearchQuery() != null) {
            builder.and(film.title.containsIgnoreCase(dto.getSearchQuery()))
                    .or(film.description.containsIgnoreCase(dto.getSearchQuery()));
        }

        return repository.findAll(builder, pageable).map(mapper::mapToDto);
    }
    public FilmDto addFilm(CreateFilmDto createFilmDto) {
        validateAdding(createFilmDto);

        return mapper.mapToDto(repository.save(mapper.mapToFilm(createFilmDto)));

    }
    public void deleteFilm(Long id) {
            Film film = repository.findById(id).orElseThrow(() -> new FilmNotFound("Nie znaleziono filmu"));
            film.setActive(false);
            repository.save(film);
    }
    public FilmDto editFilm(UpdateFilmDto updateFilmDto) {
            Film film = repository.findById(updateFilmDto.getId()).orElseThrow();
            mapper.map(film, updateFilmDto);
            return mapper.mapToDto(repository.save(film));
    }
    public FilmDto rateFilm(Long id, int rating) {
        Optional<Film> film = repository.findById(id);
        if (film.isEmpty()) {
            throw new FilmNotFound("Nie znaleziono filmu");
        }
        Film filmRating = film.get();
        filmRating.addRating(rating);
        return mapper.mapToDto(repository.save(filmRating));
    }
    private void validateAdding(CreateFilmDto filmDto) {
        if (filmDto.getTitle() == null || filmDto.getTitle().isEmpty()) {
            throw new FilmVariableNull("Tytuł nie może mieć wartości null lub być pusty");
        }
        if (filmDto.getProdYear() == null) {
            throw new FilmVariableNull("Rok produkcji nie może mieć wartości null");
        }
        if (filmDto.getDescription() == null || filmDto.getDescription().isEmpty()) {
            throw new FilmVariableNull("Opis nie może mieć wartośći null lub być pusty");
        }

    }
    public FilmDto getFilm(Long id) {
        return mapper.mapToDto(repository.findById(id).orElseThrow(() -> new FilmNotFound("Nie znaleziono filmu")));
    }

    public List<FilmDto> getAllFilms() {
        return repository.findByActiveTrue().stream().map(mapper::mapToDto).toList();
    }
}
