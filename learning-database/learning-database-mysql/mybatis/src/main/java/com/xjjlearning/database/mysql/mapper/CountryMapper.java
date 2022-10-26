package com.xjjlearning.database.mysql.mapper;

import com.xjjlearning.database.mysql.model.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CountryMapper {
    List<Country> selectAll();
}
