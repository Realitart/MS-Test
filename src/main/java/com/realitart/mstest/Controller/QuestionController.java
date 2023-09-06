package com.realitart.mstest.Controller;

import com.realitart.mstest.Domain.Question;
import com.realitart.mstest.Dtos.QuestionDTO;
import com.realitart.mstest.Service.IQuestionService;
import com.realitart.mstest.share.mapping.entity.QuestionMapper;
import com.realitart.mstest.share.response.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    IQuestionService questionService;
    @Autowired
    private QuestionMapper mapper;

    @PostMapping
    @Operation(summary = "Create a new question")
    OperationResponse createQuestion(@RequestBody QuestionDTO request){
        return questionService.createQuestion(mapper.dtoToModel(request));
    }

    @PutMapping
    @Operation(summary = "Update a question")
    OperationResponse updateQuestion(@RequestParam Long questionId,@RequestBody Question request){
        return questionService.updateQuestion(questionId,request);
    }

    @DeleteMapping
    @Operation(summary = "Delete a question")
    OperationResponse deleteQuestion(@RequestParam Long questionId){
        return questionService.deleteQuestion(questionId);
    }

    @GetMapping
    @Operation(summary = "Get a question by id")
    Question getQuestionData(@RequestParam Long questionId){
        return questionService.getQuestion(questionId);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all questions")
    ResponseEntity<Page<Question>> getAllQuestions(Pageable pageable){
        return ResponseEntity.ok(mapper.modelListToPage(questionService.getQuestions(),pageable));
    }

}
