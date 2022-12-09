package com.xjjlearning.database.mysql.service.impl;

import com.xjjlearning.database.mysql.mapper.CountryMapper;
import com.xjjlearning.database.mysql.model.Country;
import com.xjjlearning.database.mysql.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryMapper countryMapper;

    @Override
    public List<Country> getAllCountry() {
        return countryMapper.selectAll();
    }
}
