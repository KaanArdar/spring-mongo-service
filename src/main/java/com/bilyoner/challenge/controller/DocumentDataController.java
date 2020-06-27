package com.bilyoner.challenge.controller;

import com.bilyoner.challenge.model.dto.DocumentDataRequest;
import com.bilyoner.challenge.model.dto.DocumentDataResponse;
import com.bilyoner.challenge.service.DocumentDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{number}")
    public ResponseEntity<DocumentDataResponse> getDocumentDataById(@PathVariable("number") long number) {
        return ResponseEntity.ok(documentDateService.getDocumentDataWithNumber(number));
    }


    @GetMapping("/max-number")
    public ResponseEntity<DocumentDataResponse> getDocumentDataByMaxNumber() {
        return ResponseEntity.ok(documentDateService.getDocumentDataForMaxNumber());
    }

    @GetMapping("/min-number")
    public ResponseEntity<DocumentDataResponse> getDocumentDataByMinNumber() {
        return ResponseEntity.ok(documentDateService.getDocumentDataForMinNumber());
    }


    @DeleteMapping("/{number}")
    public ResponseEntity deleteDocumentDataByNumber(@PathVariable("number") long number) {
        documentDateService.deleteDocumentData(number);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DocumentDataResponse>> getAllDocumentDataWithOrder(@RequestParam(name = "order", defaultValue = "ASC") Sort.Direction filter) {
        return ResponseEntity.ok(documentDateService.findAllDocumentDataWithOrder(filter));
    }

}
