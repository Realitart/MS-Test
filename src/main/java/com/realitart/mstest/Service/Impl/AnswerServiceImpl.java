package com.realitart.mstest.Service.Impl;

import com.realitart.mstest.Domain.Answer;
import com.realitart.mstest.Domain.Repositories.IAnswerRepository;
import com.realitart.mstest.Service.IAnswerService;
import com.realitart.mstest.share.exceptions.ResourceNotFoundException;
import com.realitart.mstest.share.response.OperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements IAnswerService {

    private static final String ENTITY = "Answer";

    @Autowired
    IAnswerRepository _answerRepo;
    @Override
    public OperationResponse createAnswer(Answer request) {
        try{
            request.setId(null);
            _answerRepo.save(request);
            return new OperationResponse(true, "Answer creado correctamente");
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al crear el answer", e);
        }
    }

    @Override
    public OperationResponse updateAnswer(Long AnswerId, Answer request) {

        return _answerRepo.findById(AnswerId).map(
                answer -> {
                    if(request.getAnswer() != null) answer.setAnswer(request.getAnswer());
                    if(request.getEnable() != null) answer.setEnable(request.getEnable());
                    if(request.getIsCorrect() != null) answer.setIsCorrect(request.getIsCorrect());
                    _answerRepo.save(answer);

                    return new OperationResponse(true, "Answer actualizado correctamente");
                }
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, AnswerId));
    }

    @Override
    public OperationResponse deleteAnswer(Long AnswerId) {
        try{
            _answerRepo.deleteById(AnswerId);
            return new OperationResponse(true, "Answer eliminado correctamente");
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al eliminar el answer", e);
        }
    }

    @Override
    public Answer getAnswer(Long AnswerId) {
        try{
            return _answerRepo.findById(AnswerId).get();
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener el answer", e);
        }
    }

    @Override
    public List<Answer> getAnswers() {
        try{
            return _answerRepo.findAll();
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener los answers", e);
        }
    }
}
