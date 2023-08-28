package com.realitart.mstest.Service.Impl;

import com.realitart.mstest.Domain.Qualification;
import com.realitart.mstest.Domain.Repositories.IQualificationRepository;
import com.realitart.mstest.Service.IQualificationService;
import com.realitart.mstest.share.exceptions.ResourceNotFoundException;
import com.realitart.mstest.share.response.OperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements IQualificationService {

    private static final String ENTITY = "Qualification";

    @Autowired
    IQualificationRepository _QualificationRepo;
    @Override
    public OperationResponse createQualification(Qualification request) {
        try{
            _QualificationRepo.save(request);
            return new OperationResponse(true, "Qualification creado correctamente");
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al crear el Qualification", e);
        }
    }

    @Override
    public OperationResponse updateQualification(Long QualificationId, Qualification request) {
        return _QualificationRepo.findById(QualificationId).map(
                qualification -> {
                    if(request.getScore() != null) qualification.setScore(request.getScore());
                    _QualificationRepo.save(qualification);

                    return new OperationResponse(true, "Question actualizado correctamente");
                }
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, QualificationId));
    }

    @Override
    public OperationResponse deleteQualification(Long QualificationId) {
        try{
            _QualificationRepo.deleteById(QualificationId);
            return new OperationResponse(true, "Qualification eliminado correctamente");
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al eliminar el Qualification", e);
        }
    }

    @Override
    public Qualification getQualification(Long QualificationId) {
        try{
            return _QualificationRepo.findById(QualificationId).get();
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener el Qualification", e);
        }
    }

    @Override
    public List<Qualification> getQualifications() {
        try{
            return _QualificationRepo.findAll();
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener los Qualifications", e);
        }
    }


}
