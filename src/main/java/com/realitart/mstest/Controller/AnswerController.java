package com.realitart.mstest.Controller;

import com.realitart.mstest.Domain.Answer;
import com.realitart.mstest.Dtos.setAnswerDTO;
import com.realitart.mstest.Service.IAnswerService;
import com.realitart.mstest.share.mapping.entity.AnswerMapper;
import com.realitart.mstest.share.response.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    IAnswerService answerService;
    @Autowired
    private AnswerMapper mapper;

    @PostMapping
    @Operation(summary = "Create a new answer")
    OperationResponse createAnswer(@RequestBody setAnswerDTO request){
        return answerService.createAnswer(mapper.dtoToModel(request));
    }

    @PutMapping
    @Operation(summary = "Update a answer")
    OperationResponse updateAnswer(@RequestParam Long answerId,@RequestBody Answer request){
        return answerService.updateAnswer(answerId,request);
    }

    @DeleteMapping
    @Operation(summary = "Delete a answer")
    OperationResponse deleteAnswer(@RequestParam Long answerId){
        return answerService.deleteAnswer(answerId);
    }

    @GetMapping
    @Operation(summary = "Get a answer by id")
    Answer getAnswerData(@RequestParam Long answerId){
        return answerService.getAnswer(answerId);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all answers")
    ResponseEntity<Page<Answer>> getAllAnswers(Pageable pageable){
        return ResponseEntity.ok(mapper.modelListToPage(answerService.getAnswers(),pageable));
    }

}
