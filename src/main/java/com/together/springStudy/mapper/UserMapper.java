package com.together.springStudy.mapper;

import com.together.springStudy.model.UserData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int register(UserData userData);
    UserData login(UserData userData);
}
