package com.xjjlearning.database.mysql;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Country {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @Column(name = "countryname")
    private String countryName;
    @Column(name = "countrycode")
    private String countryCode;
}
