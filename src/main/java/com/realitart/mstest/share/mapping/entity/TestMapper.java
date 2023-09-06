package com.realitart.mstest.share.mapping.entity;



import com.realitart.mstest.Domain.Test;
import com.realitart.mstest.share.mapping.configuration.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class TestMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<Test> modelListToPage(List<Test> modelList, Pageable pageable) {
        return new PageImpl<>(modelList, pageable, modelList.size());
    }

}
