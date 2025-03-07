package com.filmpage.mapper;

import com.filmpage.dto.FilmDto;
import com.filmpage.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FilmMapper {
    FilmDto mapToDto(Film film);
    @Mapping(target = "ratingsNumber", ignore = true)
    @Mapping(target = "ratingSum", ignore = true)
    Film mapToEntity(FilmDto filmDto);

}
