package com.realitart.mstest.Service.Impl;

import com.realitart.mstest.Domain.Question;
import com.realitart.mstest.Domain.Repositories.IQuestionRepository;
import com.realitart.mstest.Service.IQuestionService;
import com.realitart.mstest.share.exceptions.ResourceNotFoundException;
import com.realitart.mstest.share.response.OperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {

    private static final String ENTITY = "Question";

    @Autowired
    IQuestionRepository _QuestionRepo;
    @Override
    public OperationResponse createQuestion(Question request) {
        try{
            _QuestionRepo.save(request);
            return new OperationResponse(true, "Question creado correctamente");
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al crear el Question", e);
        }
    }

    @Override
    public OperationResponse updateQuestion(Long QuestionId, Question request) {
        return _QuestionRepo.findById(QuestionId).map(
                question -> {
                    if(request.getPoints() != null) question.setPoints(request.getPoints());
                    if(request.getStatement() != null) question.setStatement(request.getStatement());
                    _QuestionRepo.save(question);

                    return new OperationResponse(true, "Question actualizado correctamente");
                }
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, QuestionId));
    }

    @Override
    public OperationResponse deleteQuestion(Long QuestionId) {
        try{
            _QuestionRepo.deleteById(QuestionId);
            return new OperationResponse(true, "Question eliminado correctamente");
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al eliminar el Question", e);
        }
    }

    @Override
    public Question getQuestion(Long QuestionId) {
        try{
            return _QuestionRepo.findById(QuestionId).get();
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener el Question", e);
        }
    }

    @Override
    public List<Question> getQuestions() {
        try{
            return _QuestionRepo.findAll();
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener los Questions", e);
        }
    }
}
