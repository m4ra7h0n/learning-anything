package com.xjjlearning.springframework.security.service.impl;

import com.xjjlearning.springframework.security.entity.MetaResource;
import com.xjjlearning.springframework.security.service.MetaResourceService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class MetaResourceServiceImpl implements MetaResourceService {

    @Override
    public Set<MetaResource> queryPatternsAndMethods() {

        Set<MetaResource> metaResources = new HashSet<>();

        MetaResource e = new MetaResource();
        e.setPattern("/foo/param/*");
        e.setMethod("GET");

        MetaResource m = new MetaResource();
        m.setPattern("/foo/prefilter");
        m.setMethod("POST");

        metaResources.add(e);
        metaResources.add(m);

        return metaResources;
    }
}
