package com.bilyoner.challenge.controller;

import com.bilyoner.challenge.model.dto.DocumentDataRequest;
import com.bilyoner.challenge.model.dto.DocumentDataResponse;
import com.bilyoner.challenge.service.DocumentDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("document-data")
public class DocumentDataController {

    private final DocumentDateService documentDateService;

    public DocumentDataController(DocumentDateService documentDateService) {
        this.documentDateService = documentDateService;
    }

    @PostMapping
    public ResponseEntity<DocumentDataResponse> documentData(@RequestBody DocumentDataRequest request) {
        return ResponseEntity.ok(documentDateService.generateDocumentData(request));
    }


}
