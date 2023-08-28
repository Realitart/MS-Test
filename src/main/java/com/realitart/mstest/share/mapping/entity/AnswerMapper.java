package com.realitart.mstest.share.mapping.entity;



import com.realitart.mstest.Domain.Answer;
import com.realitart.mstest.Dtos.getAnswerDTO;
import com.realitart.mstest.share.mapping.configuration.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public class AnswerMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<Answer> modelListToPage(List<Answer> modelList, Pageable pageable) {
        return new PageImpl<>(modelList, pageable, modelList.size());
    }
    public getAnswerDTO modelToDto(Answer model) {
        return mapper.map(model, getAnswerDTO.class);
    }
    
    public List<getAnswerDTO> modelListToDtoList(List<Answer> modelList) {
        return Collections.singletonList(mapper.map(modelList, getAnswerDTO.class));
    }

    public Answer dtoToModel(getAnswerDTO dto) {
        return mapper.map(dto, Answer.class);
    }

}
