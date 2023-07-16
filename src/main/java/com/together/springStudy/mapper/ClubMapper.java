package com.together.springStudy.mapper;

import com.together.springStudy.model.ClubData;
import com.together.springStudy.model.CreateClubData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClubMapper {
    int createClub(CreateClubData createClubData);
    List<ClubData> getAllClub();
}
