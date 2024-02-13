package com.example.mvc.mapper;

import com.example.mvc.dto.ClubDto;
import com.example.mvc.models.Club;

import java.util.stream.Collectors;


public class ClubMapper {
    public static Club mapToClub(ClubDto club) {

        return Club.builder()
                .id(club.getId())
                .photoUrl(club.getPhotoUrl())
                .title(club.getTitle())
                .content(club.getContent())
                .createdBy(club.getCreatedBy())
                .updatedOn(club.getUpdatedOn())
                .createdOn(club.getCreatedOn())
                .build();
    }

    public static ClubDto mapToClubDto(Club club) {

        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdBy(club.getCreatedBy())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .events(club.getEvents().stream().map(EventMapper::mapToEventDto).collect(Collectors.toList()))
                .build();

    }
}
