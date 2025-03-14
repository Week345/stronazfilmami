package com.filmpage.service;

import com.filmpage.dto.FilmDto;
import com.filmpage.dto.SearchRequest;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class FilmService {
    private final FilmRepository repository;
    private final FilmMapper mapper;

    public Page<FilmDto> search(SearchRequest dto, Pageable pageable) {
        QFilm film = QFilm.film;
        BooleanBuilder builder = new BooleanBuilder();
        if (dto.getCategory() != null) {
            builder.and(film.category.containsIgnoreCase(dto.getCategory()));
        }
        if (dto.getSearchQuery() != null) {
            builder.and(film.title.containsIgnoreCase(dto.getSearchQuery()))
                    .or(film.description.containsIgnoreCase(dto.getSearchQuery()));
        }

        return repository.findAll(builder, pageable).map(mapper::mapToDto);
    }
    public FilmDto addFilm(FilmDto filmDto) {
        validateAdding(filmDto);
        return mapper.mapToDto(repository.save(mapper.mapToEntity(filmDto)));

    }
    public void deleteFilm(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new FilmNotFound("Nie znaleziono filmu");
        }
    }
    public FilmDto editFilm(FilmDto filmDto) {
        if (repository.existsById(filmDto.getId())) {
            return mapper.mapToDto(repository.save(mapper.mapToEntity(filmDto)));
        } else {
            throw new FilmNotFound("Nie znaleziono filmu");
        }

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
    private void validateAdding(FilmDto filmDto) {
        if (filmDto.getTitle() == null || filmDto.getTitle().isEmpty()) {
            throw new FilmVariableNull("Tytuł nie może mieć wartości null lub być pusty");
        }
        if (filmDto.getCategory() == null || filmDto.getCategory().isEmpty()) {
            throw new FilmVariableNull("Kategoria nie może mieć wartośći null lub być pusta");
        }
        if (filmDto.getRating() == null) {
            throw new FilmVariableNull("Ocena nie może mieć wartości null");
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

    public FilmDto uploadImage(Long id, MultipartFile file) throws IOException {
        Optional<Film> film = repository.findById(id);
        if (film.isEmpty()) {
            throw new FilmNotFound("Nie znaleziono filmu");
        }
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Zły format pliku");
        }
        Film existFilm = film.get();
        existFilm.setImage(file.getBytes());
        existFilm.setImageType(file.getContentType());
        return mapper.mapToDto(repository.save(existFilm)); 
    }
}
