package com.filmpage.controller;

import com.filmpage.dto.CreateFilmDto;
import com.filmpage.dto.FilmDto;
import com.filmpage.dto.SearchRequest;
import com.filmpage.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class FilmController {
    private final FilmService service;
    @PostMapping("/films/add")
    public FilmDto addFilm(@RequestBody CreateFilmDto createFilmDto){
        return service.addFilm(createFilmDto);
    }
    @DeleteMapping("/films/delete/{id}")
    public void removeFilm(@PathVariable Long id) {
        service.deleteFilm(id);
    }
    @PutMapping("/films/edit")
    public FilmDto editFilm(@RequestBody FilmDto filmDto) {
        return service.editFilm(filmDto);
    }
    @PostMapping("/films/rate/{id}")
    public FilmDto rateFilm(@PathVariable Long id, @RequestParam Integer rating) {
        return service.rateFilm(id, rating);
    }
    @PostMapping("/films/search")
    @PageableAsQueryParam
    public Page<FilmDto> searchFilms(@RequestBody SearchRequest searchQuery, Pageable pageable) {
        return service.search(searchQuery, pageable);
    }
    @GetMapping("/films/{id}")
    public FilmDto getFilm(@PathVariable Long id) {
        return service.getFilm(id);
    }
    @GetMapping("/films/all")
    public List<FilmDto> getAllFilms() {
        return service.getAllFilms();
    }

    List<FilmDto> search(SearchRequest dto, Pageable pageable) {
        return service.search(dto, pageable).toList();
    }


}
