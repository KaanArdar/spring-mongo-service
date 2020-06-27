package com.bilyoner.challenge.service;

import com.bilyoner.challenge.model.dto.DocumentDataRequest;
import com.bilyoner.challenge.model.dto.DocumentDataResponse;

public interface DocumentDateService {

    DocumentDataResponse generateDocumentData(DocumentDataRequest request);

    DocumentDataResponse getDocumentDataWithNumber(Long number);


}
