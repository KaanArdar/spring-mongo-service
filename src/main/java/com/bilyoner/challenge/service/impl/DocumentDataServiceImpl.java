package com.bilyoner.challenge.service.impl;

import com.bilyoner.challenge.exception.NotFoundEntityException;
import com.bilyoner.challenge.exception.UnprocessableEntityException;
import com.bilyoner.challenge.mapper.DocumentDataMapper;
import com.bilyoner.challenge.model.dto.DocumentDataRequest;
import com.bilyoner.challenge.model.dto.DocumentDataResponse;
import com.bilyoner.challenge.model.entity.DocumentData;
import com.bilyoner.challenge.repository.DocumentDataRepository;
import com.bilyoner.challenge.service.DocumentDateService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentDataServiceImpl implements DocumentDateService {

    private final DocumentDataRepository documentDataRepository;

    public DocumentDataServiceImpl(DocumentDataRepository documentDataRepository) {
        this.documentDataRepository = documentDataRepository;
    }

    @Override
    public DocumentDataResponse generateDocumentData(DocumentDataRequest documentDataRequest) {
        Optional<DocumentData> byNumber = documentDataRepository.findByNumber(documentDataRequest.getNumber());

        if (byNumber.isPresent()){
            throw new UnprocessableEntityException("number is unprocessable!");
        }

        DocumentData data = documentDataRepository.save(DocumentDataMapper.requestToEntity(documentDataRequest));
        return DocumentDataMapper.EntityToResponse(data);
    }


    @Override
    public DocumentDataResponse getDocumentDataWithNumber(long number) {
        return documentDataRepository.findByNumber(number)
                .map(DocumentDataMapper::EntityToResponse)
                .orElseThrow(()-> new NotFoundEntityException("number is not found!"));
    }

    @Override
    public DocumentDataResponse getDocumentDataForMaxNumber() {
        return documentDataRepository.findFirstByOrderByNumberDesc()
                .map(DocumentDataMapper::EntityToResponse)
                .orElseThrow(()-> new NotFoundEntityException("number is not found!"));
    }

    @Override
    public DocumentDataResponse getDocumentDataForMinNumber() {
        return documentDataRepository.findFirstByOrderByNumberAsc()
                .map(DocumentDataMapper::EntityToResponse)
                .orElseThrow(()-> new NotFoundEntityException("number is not found!"));
    }

    @Override
    public void deleteDocumentData(long number) {
        documentDataRepository.findByNumber(number)
                .map(data ->
                {
                    documentDataRepository.deleteByNumber(number);
                    return true;
                })
                .orElseThrow(() -> new UnprocessableEntityException("number is unprocessable!"));
    }

    @Override
    public List<DocumentDataResponse> findAllDocumentDataWithOrder(Sort.Direction filter) {
        return documentDataRepository.findAllByOrderByNumber(Sort.by(filter, "number"))
                .stream()
                .map(DocumentDataMapper::EntityToResponse)
                .collect(Collectors.toList());
    }
}
