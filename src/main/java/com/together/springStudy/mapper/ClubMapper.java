package com.together.springStudy.mapper;

import com.together.springStudy.model.ClubData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClubMapper {
    int createClub(ClubData clubData);
    List<ClubData> getAllClub();
}
