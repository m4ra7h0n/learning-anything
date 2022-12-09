package com.xjjlearning.database.mysql;

import com.xjjlearning.database.mysql.mapper.CountryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServiceImpl {
    @Resource
    CountryMapper mapper;

    void test() {
        List<Country> countries = mapper.selectAll();
        countries.stream()
                .map((Country country) -> country.getCountryName() + " " + country.getCountryCode())
                .forEach(System.out::println);
    }
}
