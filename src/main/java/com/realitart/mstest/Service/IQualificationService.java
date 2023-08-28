package com.realitart.mstest.Service;

import com.realitart.mstest.Domain.Qualification;
import com.realitart.mstest.share.response.OperationResponse;

import java.util.List;

public interface IQualificationService {
    OperationResponse createQualification(Qualification request);

    OperationResponse updateQualification(Long QualificationId, Qualification request);

    OperationResponse deleteQualification(Long QualificationId);

    Qualification getQualification(Long QualificationId);

    List<Qualification> getQualifications();
}
