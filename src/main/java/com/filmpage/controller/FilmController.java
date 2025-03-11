package com.filmpage.controller;

import com.filmpage.dto.FilmDto;
import com.filmpage.dto.SearchRequest;
import com.filmpage.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class FilmController {
    private final FilmService service;
    @PostMapping("/films/add")
    public FilmDto addFilm(@RequestBody FilmDto filmDto) {
        return service.addFilm(filmDto);
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
    @PostMapping("/films/{id}/upload")
    public FilmDto uploadFilm(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return service.uploadImage(id, file);
    }

    List<FilmDto> search(SearchRequest dto, Pageable pageable) {
        return service.search(dto, pageable).toList();
    }


}
