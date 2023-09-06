package com.realitart.mstest.share.mapping.entity;



import com.realitart.mstest.Domain.Question;
import com.realitart.mstest.Dtos.QuestionDTO;
import com.realitart.mstest.share.mapping.configuration.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public class QuestionMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<Question> modelListToPage(List<Question> modelList, Pageable pageable) {
        return new PageImpl<>(modelList, pageable, modelList.size());
    }
    public QuestionDTO modelToDto(Question model) {
        return mapper.map(model, QuestionDTO.class);
    }

    public List<QuestionDTO> modelListToDtoList(List<Question> modelList) {
        return Collections.singletonList(mapper.map(modelList, QuestionDTO.class));
    }

    public Question dtoToModel(QuestionDTO request) {
        return mapper.map(request, Question.class);
    }
}
