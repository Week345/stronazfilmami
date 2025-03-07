package com.filmpage.controller;

import com.filmpage.dto.FilmDto;
import com.filmpage.dto.SearchRequest;
import com.filmpage.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FilmController {
    private final FilmService service;
    @PostMapping("/add")
    public FilmDto addFilm(@RequestBody FilmDto filmDto) {
        return service.addFilm(filmDto);
    }
    @DeleteMapping("/delete/{id}")
    public void removeFilm(@PathVariable Long id) {
        service.deleteFilm(id);
    }
    @PutMapping("/edit")
    public FilmDto editFilm(@RequestBody FilmDto filmDto) {
        return service.editFilm(filmDto);
    }
    @PostMapping("/rate/{id}")
    public FilmDto rateFilm(@PathVariable Long id, @RequestParam Integer rating) {
        return service.rateFilm(id, rating);
    }
    @PostMapping("/search")
    @PageableAsQueryParam
    public Page<FilmDto> searchFilms(@RequestBody SearchRequest searchQuery, Pageable pageable) {
        return service.search(searchQuery, pageable);
    }

    List<FilmDto> search(SearchRequest dto, Pageable pageable) {
        return service.search(dto, pageable).toList();
    }


}
