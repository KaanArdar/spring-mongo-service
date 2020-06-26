package com.bilyoner.challenge.service.impl;

import com.bilyoner.challenge.mapper.DocumentDataMapper;
import com.bilyoner.challenge.model.dto.DocumentDataRequest;
import com.bilyoner.challenge.model.dto.DocumentDataResponse;
import com.bilyoner.challenge.model.entity.DocumentData;
import com.bilyoner.challenge.repository.DocumentDataRepository;
import com.bilyoner.challenge.service.DocumentDateService;
import org.springframework.stereotype.Service;

@Service
public class DocumentDataServiceImpl implements DocumentDateService {

    private final DocumentDataRepository documentDataRepository;

    public DocumentDataServiceImpl(DocumentDataRepository documentDataRepository) {
        this.documentDataRepository = documentDataRepository;
    }

    @Override
    public DocumentDataResponse generateDocumentData(DocumentDataRequest documentDataRequest) {
        DocumentData data = documentDataRepository.insert(DocumentDataMapper.requestToEntity(documentDataRequest));
        return DocumentDataMapper.EntityToResponse(data);
    }
}
