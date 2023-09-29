package com.realitart.mstest.Service.Impl;

import com.realitart.mstest.Domain.Answer;
import com.realitart.mstest.Domain.Question;
import com.realitart.mstest.Domain.Repositories.*;
import com.realitart.mstest.Domain.Test;
import com.realitart.mstest.Domain.UserTest;
import com.realitart.mstest.Dtos.*;
import com.realitart.mstest.Service.ITestService;
import com.realitart.mstest.share.exceptions.ResourceNotFoundException;
import com.realitart.mstest.share.mapping.entity.AnswerMapper;
import com.realitart.mstest.share.mapping.entity.QuestionMapper;
import com.realitart.mstest.share.response.OperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestServiceImpl implements ITestService {

    private static final String ENTITY = "Test";

    @Autowired
    ITestRepository _TestRepo;
    @Autowired
    IQuestionRepository _QuestionRepo;
    @Autowired
    IAnswerRepository _AnswerRepo;
    @Autowired
    IUserTestRepository _UserTestRepo;

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    AnswerMapper AnswerMapper;
    @Override
    public OperationResponse createTest(Test request) {
        try{
            request.setId(null);
            _TestRepo.save(request);
            return new OperationResponse(true, "Test creado correctamente");
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al crear el Test", e);
        }
    }

    @Override
    public OperationResponse updateTest(Long TestId, Test request) {
        return _TestRepo.findById(TestId).map(
                Test -> {
                    if(request.getName() != null) Test.setName(request.getName());
                    if(request.getExpiration() != null) Test.setExpiration(request.getExpiration());
                    if(request.getStart() != null) Test.setStart(request.getStart());
                    if(request.getMinScoreToAprove() != null) Test.setMinScoreToAprove(request.getMinScoreToAprove());
                    _TestRepo.save(Test);

                    return new OperationResponse(true, "Test actualizado correctamente");
                }
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, TestId));
    }

    @Override
    public OperationResponse deleteTest(Long TestId) {
        try{
            _TestRepo.deleteById(TestId);
            return new OperationResponse(true, "Test eliminado correctamente");
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al eliminar el Test", e);
        }
    }

    @Override
    public Test getTest(Long TestId) {
        try{
            return _TestRepo.findById(TestId).get();
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener el Test", e);
        }
    }

    @Override
    public completeTestDTO checkTestByUser(Long userId , Long TestId) {
        try {
            Test test = _TestRepo.findById(TestId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, TestId));
            completeTestDTO testDTO = new completeTestDTO();
            List<Question> questions = _QuestionRepo.findAllByTestId(test);

            testDTO.setId(test.getId());
            testDTO.setCode(test.getCode());
            testDTO.setExpiration(test.getExpiration());
            testDTO.setMinScoreToAprove(test.getMinScoreToAprove());
            testDTO.setName(test.getName());
            testDTO.setStart(test.getStart());

            testDTO.setQuestionsAnsAnswers(
                    questions.stream().map(question -> {
                        QuestionAndAnswerDTO questionAndAnswerDTO = new QuestionAndAnswerDTO();
                        questionAndAnswerDTO.setStatement(question.getStatement());
                        questionAndAnswerDTO.setPoints(question.getPoints());
                        questionAndAnswerDTO.setAnswers(
                                _AnswerRepo.findByQuestionId(question).stream().map(answer -> {
                                    getAnswerDTO answerDTO = new getAnswerDTO();

                                    answerDTO.setAnswer(answer.getAnswer());
                                    answerDTO.setIsCorrect(answer.getIsCorrect());
                                    answerDTO.setId(answer.getId());
                                    answerDTO.setIsChosen(_UserTestRepo.existsByAnswerIdAndUserId(answer,userId));

                                    return answerDTO;
                                }).toList()
                        );
                        return questionAndAnswerDTO;
                    }).toList()
            );

            return testDTO;

        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al obtener el Test", e);
        }
    }



    @Override
    public completeTestDTO getTestByCode(Integer code) {
        try {
            Test test = _TestRepo.findByCode(code);
            boolean isAvailable = test.getStart().isBefore(LocalDateTime.now()) && test.getExpiration().isAfter(LocalDateTime.now());
            if (!isAvailable) {
                throw new ResourceNotFoundException("El test no está disponible. Inicia el " + test.getStart() + " y termina el " + test.getExpiration() + ".");
            }
            completeTestDTO testDTO = new completeTestDTO();


            testDTO.setId(test.getId());
            testDTO.setCode(test.getCode());
            testDTO.setExpiration(test.getExpiration());
            testDTO.setMinScoreToAprove(test.getMinScoreToAprove());
            testDTO.setName(test.getName());
            testDTO.setStart(test.getStart());

            testDTO.setQuestionsAnsAnswers(
                    _QuestionRepo.findAllByTestId(test).stream().map(question -> {
                        QuestionAndAnswerDTO questionAndAnswerDTO = new QuestionAndAnswerDTO();
                        questionAndAnswerDTO.setQuestionId(question.getId());
                        questionAndAnswerDTO.setStatement(question.getStatement());
                        questionAndAnswerDTO.setPoints(question.getPoints());
                        questionAndAnswerDTO.setAnswers(
                                _AnswerRepo.findByQuestionId(question).stream().map(answer -> {
                                    getAnswerDTO answerDTO = new getAnswerDTO();

                                    answerDTO.setAnswer(answer.getAnswer());
                                    answerDTO.setId(answer.getId());

                                    return answerDTO;
                                }).toList()
                        );
                        return questionAndAnswerDTO;
                    }).toList()
            );

            return testDTO;

        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al obtener el Test", e);
        }
    }

    @Override
    public completeTestDTO getCompleteTest(Long TestId) {
        try {
            Test test = _TestRepo.findById(TestId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, TestId));
            completeTestDTO testDTO = new completeTestDTO();

            testDTO.setId(test.getId());
            testDTO.setCode(test.getCode());
            testDTO.setExpiration(test.getExpiration());
            testDTO.setMinScoreToAprove(test.getMinScoreToAprove());
            testDTO.setName(test.getName());
            testDTO.setStart(test.getStart());

            testDTO.setQuestionsAnsAnswers(
                    _QuestionRepo.findAllByTestId(test).stream().map(question -> {
                        QuestionAndAnswerDTO questionAndAnswerDTO = new QuestionAndAnswerDTO();
                        questionAndAnswerDTO.setQuestionId(question.getId());
                        questionAndAnswerDTO.setStatement(question.getStatement());
                        questionAndAnswerDTO.setPoints(question.getPoints());
                        questionAndAnswerDTO.setAnswers(
                                _AnswerRepo.findByQuestionId(question).stream().map(answer -> {
                                    getAnswerDTO answerDTO = new getAnswerDTO();

                                    answerDTO.setAnswer(answer.getAnswer());
                                    answerDTO.setId(answer.getId());

                                    return answerDTO;
                                }).toList()
                        );
                        return questionAndAnswerDTO;
                    }).toList()
            );

            return testDTO;

        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al obtener el Test", e);
        }
    }

    @Override
    public OperationResponse sendTest(sendTestDTO request, Long userId) {
        try {
            Test test = _TestRepo.findById(request.getId()).orElseThrow(() -> new ResourceNotFoundException(ENTITY, request.getId()));
            if (test.getExpiration().isBefore(LocalDateTime.now())) throw new ResourceNotFoundException("El test ya expiró.");


            request.getQuestionsAnsAnswers().forEach(questionAndAnswer -> {
                Question question = _QuestionRepo.findById(questionAndAnswer.getQuestionId()).orElseThrow(() -> new ResourceNotFoundException("Question", questionAndAnswer.getQuestionId()));
                Answer answer =  _AnswerRepo.findById(questionAndAnswer.getAnswerId()).orElseThrow(() -> new ResourceNotFoundException("Answer", questionAndAnswer.getAnswerId()));
                UserTest userTest = new UserTest();
                userTest.setTestId(test);
                userTest.setUserId(userId);
                userTest.setAnswerId(answer);
                userTest.setScore(
                        answer.getIsCorrect()? question.getPoints() : 0
                );
                _UserTestRepo.save(userTest);
                });

            return new OperationResponse(true, "Test enviado correctamente");

        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al obtener el Test", e);
        }
    }

    @Override
    public List<Test> getTests() {
        try{
            return _TestRepo.findAll();
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener los Tests", e);
        }
    }

    @Override
    public List<Test> getAllTestsByProfessorId(Long professorId) {
        try{
            return _TestRepo.findAllByProfessorId(professorId);
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener los Tests", e);
        }
    }

    @Override
    public List<IFullTest> getAllTestsByStudentId(Long studentId) {
        try{
            var response = _TestRepo.findAllByStudentId(studentId);
            return response;
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener los Tests", e);
        }
    }


}
