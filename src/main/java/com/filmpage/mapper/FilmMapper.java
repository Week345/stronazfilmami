package com.filmpage.mapper;

import com.filmpage.dto.CategoryDto;
import com.filmpage.dto.CreateFilmDto;
import com.filmpage.dto.FilmDto;
import com.filmpage.dto.UpdateFilmDto;
import com.filmpage.model.Category;
import com.filmpage.model.Film;
import com.filmpage.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel ="spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class FilmMapper {
    @Autowired
    CategoryService categoryService;

    public abstract FilmDto mapToDto(Film film);
    @Mapping(target = "ratingSum", ignore = true)
    public abstract Film mapToEntity(FilmDto filmDto);
    @Mapping(target = "films", ignore = true)
    public abstract Category mapToCategoryDto(CategoryDto categoryDto);
    public abstract CategoryDto mapToCategory(Category category);
    @Mapping(target = "categories", source = "createFilmDto",qualifiedByName = "mapCategory")
    @Mapping(target = "ratingSum", ignore = true)
    public abstract Film mapToFilm(CreateFilmDto createFilmDto);

    @Named("mapCategory")
    public List<Category> mapCategory(CreateFilmDto createFilmDto) {
        String category1 = createFilmDto.getCategory1();
        String category2 = createFilmDto.getCategory2();
    return List.of(categoryService.findByName(category1), categoryService.findByName(category2));
    }
    public void map(Film filmTarget, UpdateFilmDto filmSource) {
        filmTarget.setTitle(filmSource.getTitle());
        filmTarget.setDescription(filmSource.getDescription());
        filmTarget.setCategories(mapCategory(filmSource.getCategory1(), filmSource.getCategory2()));
        filmTarget.setAwards(filmSource.getAwards());
        filmTarget.setProdYear(filmSource.getProdYear());
        filmTarget.setIMDBRating(filmSource.getIMDBRating());
        filmTarget.setImageURL(filmSource.getImageURL());
    }
    public List<Category> mapCategory(String category1, String category2) {
        return List.of(categoryService.findByName(category1), categoryService.findByName(category2));
    }
}
