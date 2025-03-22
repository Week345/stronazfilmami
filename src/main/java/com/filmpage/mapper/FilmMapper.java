package com.filmpage.mapper;

import com.filmpage.dto.CategoryDto;
import com.filmpage.dto.FilmDto;
import com.filmpage.model.Film;
import com.filmpage.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FilmMapper {
    FilmDto mapToDto(Film film);
    @Mapping(target = "ratingSum", ignore = true)
    Film mapToEntity(FilmDto filmDto);
    @Mapping(target = "films", ignore = true)
    Category mapToCategoryDto(CategoryDto categoryDto);
    CategoryDto mapToCategory(Category category);

}
