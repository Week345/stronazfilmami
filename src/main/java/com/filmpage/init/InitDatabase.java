package com.filmpage.init;

import com.filmpage.model.Film;
import com.filmpage.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class InitDatabase {
    private final FilmRepository repository;
//    @PostConstruct
    void init() {
        Film tytulfilm = new Film();
        tytulfilm.setTitle("Django");
        repository.save(tytulfilm);
    }
}
