package com.bilyoner.challenge.service;

import com.bilyoner.challenge.model.dto.DocumentDataRequest;
import com.bilyoner.challenge.model.dto.DocumentDataResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DocumentDateService {

    DocumentDataResponse generateDocumentData(DocumentDataRequest request);

    DocumentDataResponse getDocumentDataWithNumber(long number);

    DocumentDataResponse getDocumentDataForMaxNumber();

    DocumentDataResponse getDocumentDataForMinNumber();

    void deleteDocumentData(long number);

    List<DocumentDataResponse> findAllDocumentDataWithOrder(Sort.Direction filter);
}
