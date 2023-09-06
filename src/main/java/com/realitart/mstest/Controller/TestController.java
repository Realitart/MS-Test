package com.realitart.mstest.Controller;


import com.realitart.mstest.Domain.Test;
import com.realitart.mstest.Dtos.completeTestDTO;
import com.realitart.mstest.Dtos.sendTestDTO;
import com.realitart.mstest.Service.ITestService;
import com.realitart.mstest.share.mapping.entity.TestMapper;
import com.realitart.mstest.share.response.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    ITestService testService;
    @Autowired
    private TestMapper mapper;

    @GetMapping
    @Operation(summary = "Get test by id")
    ResponseEntity<Test> getTestData(@RequestParam Long testId){
        return ResponseEntity.ok(testService.getTest(testId));
    }

    @GetMapping("/complete")
    @Operation(summary = "Get complete test by id")
    ResponseEntity<completeTestDTO> getCompleteTestData(@RequestParam(required = false) Long testId, @RequestParam(required = false) Integer code){
        if(testId != null){
            return ResponseEntity.ok(testService.getCompleteTest(testId));
        }
        if (code != null) {
            return ResponseEntity.ok(testService.getTestByCode(code));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/check/{testId}")
    @Operation(summary = "Check test by user")
    ResponseEntity<completeTestDTO> checkTestByUser(@PathVariable Long testId, @RequestParam Long userId){
        return ResponseEntity.ok(testService.checkTestByUser(userId,testId));
    }

    @PostMapping("/send")
    @Operation(summary = "Send test")
    OperationResponse sendTest(@RequestBody sendTestDTO test, @RequestParam Long userId){
        return testService.sendTest(test,userId);
    }

    @PostMapping
    @Operation(summary = "Create a new test")
    OperationResponse createTest(@RequestBody Test request){
        return testService.createTest(request);
    }

    @PutMapping
    @Operation(summary = "Update a test")
    OperationResponse updateTest(@RequestParam Long testId,@RequestBody Test request){
        return testService.updateTest(testId,request);
    }

    @DeleteMapping
    @Operation(summary = "Delete a test")
    OperationResponse deleteTest(@RequestParam Long testId){
        return testService.deleteTest(testId);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all tests")
    ResponseEntity<Page<Test>> getAllTests(@RequestParam(required = false) Long professorId, Pageable pageable){
        if(professorId == null){
            return ResponseEntity.ok(mapper.modelListToPage(testService.getTests(),pageable));
        } else {
            return ResponseEntity.ok(mapper.modelListToPage(testService.getAllTestsByProfessorId(professorId),pageable));
        }
    }


}
