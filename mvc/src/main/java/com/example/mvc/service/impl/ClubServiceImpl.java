package com.example.mvc.service.impl;

import com.example.mvc.dto.ClubDto;
import com.example.mvc.mapper.ClubMapper;
import com.example.mvc.models.Club;
import com.example.mvc.models.UserEntity;
import com.example.mvc.repository.ClubRepository;
import com.example.mvc.repository.UserRepository;
import com.example.mvc.security.SecurityUtil;
import com.example.mvc.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.mvc.mapper.ClubMapper.mapToClub;
import static com.example.mvc.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        String username= SecurityUtil.getSessionUser();
        System.out.println("this is the useerrrrrrrrrrrrrrrrrrr:" + username);
        UserEntity user = userRepository.findByEmail(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String username= SecurityUtil.getSessionUser();
        System.out.println("this is the useerrrrrrrrrrrrrrrrrrr:" + username);
        UserEntity user = userRepository.findByEmail(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }



}
