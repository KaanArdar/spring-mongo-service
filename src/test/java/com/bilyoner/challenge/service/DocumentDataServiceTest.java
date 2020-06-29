package com.bilyoner.challenge.service;

import com.bilyoner.challenge.exception.NotFoundEntityException;
import com.bilyoner.challenge.exception.UnprocessableEntityException;
import com.bilyoner.challenge.mapper.DocumentDataMapper;
import com.bilyoner.challenge.model.dto.DocumentDataRequest;
import com.bilyoner.challenge.model.dto.DocumentDataResponse;
import com.bilyoner.challenge.model.entity.DocumentData;
import com.bilyoner.challenge.repository.DocumentDataRepository;
import com.bilyoner.challenge.service.impl.DocumentDataServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentDataServiceTest {

    private final String SORT_FIELD = "number";
    private DocumentDataServiceImpl documentDataService;

    @Mock
    private DocumentDataRepository documentDataRepository;

    @Before
    public void set_up() {
        documentDataService = new DocumentDataServiceImpl(documentDataRepository);
    }


    @Test
    public void generateDocumentData_success() {
        DocumentDataRequest documentDataRequest = DocumentDataRequest.builder().number(7L).build();
        DocumentData data = DocumentDataMapper.requestToEntity(documentDataRequest);
        Optional<DocumentData> empty = Optional.empty();

        when(documentDataRepository.findByNumber(documentDataRequest.getNumber())).thenReturn(empty);
        when(documentDataRepository.save(anyObject())).thenReturn(data);

        DocumentDataResponse documentDataResponse = documentDataService.generateDocumentData(documentDataRequest);

        assertEquals(documentDataResponse.getNumber(), documentDataRequest.getNumber());

    }

    @Test(expected = UnprocessableEntityException.class)
    public void generateDocumentData_unprocessable() {
        DocumentDataRequest documentDataRequest = DocumentDataRequest.builder().number(7L).build();
        DocumentData data = DocumentDataMapper.requestToEntity(documentDataRequest);


        when(documentDataRepository.findByNumber(documentDataRequest.getNumber())).thenReturn(Optional.of(data));
        when(documentDataRepository.save(anyObject())).thenReturn(data);

        DocumentDataResponse documentDataResponse = documentDataService.generateDocumentData(documentDataRequest);

        assertEquals(documentDataResponse.getNumber(), documentDataRequest.getNumber());

    }

    @Test
    public void getDocumentDataWithNumber_success() {
        Long number = 5L;
        Optional<DocumentData> data = Optional.ofNullable(DocumentData.builder().number(5L).build());

        when(documentDataRepository.findByNumber(number)).thenReturn(data);

        DocumentDataResponse documentDataResponse = documentDataService.getDocumentDataWithNumber(number);

        assertEquals(number, documentDataResponse.getNumber());

    }

    @Test(expected = NotFoundEntityException.class)
    public void getDocumentDataWithNumber_NotFoundEntity() {
        Long number = 5L;
        Optional<DocumentData> data = Optional.empty();

        when(documentDataRepository.findByNumber(number)).thenReturn(data);

        DocumentDataResponse documentDataResponse = documentDataService.getDocumentDataWithNumber(number);

        assertEquals(number, documentDataResponse.getNumber());

    }

    @Test
    public void getDocumentDataForMaxNumber_success() {
        Long number = 5L;
        Optional<DocumentData> data = Optional.ofNullable(DocumentData.builder().number(5L).build());

        when(documentDataRepository.findFirstByOrderByNumberDesc()).thenReturn(data);

        DocumentDataResponse documentDataResponse = documentDataService.getDocumentDataForMaxNumber();

        assertEquals(number, documentDataResponse.getNumber());

    }

    @Test(expected = NotFoundEntityException.class)
    public void getDocumentDataForMaxNumber_NotFoundEntity() {
        Long number = 5L;
        Optional<DocumentData> data = Optional.empty();

        when(documentDataRepository.findFirstByOrderByNumberDesc()).thenReturn(data);

        DocumentDataResponse documentDataResponse = documentDataService.getDocumentDataForMaxNumber();

        assertEquals(number, documentDataResponse.getNumber());

    }

    @Test
    public void getDocumentDataForMinNumber_success() {
        Long number = 5L;
        Optional<DocumentData> data = Optional.ofNullable(DocumentData.builder().number(5L).build());

        when(documentDataRepository.findFirstByOrderByNumberAsc()).thenReturn(data);

        DocumentDataResponse documentDataResponse = documentDataService.getDocumentDataForMinNumber();

        assertEquals(number, documentDataResponse.getNumber());

    }

    @Test(expected = NotFoundEntityException.class)
    public void getDocumentDataForMinNumber_NotFoundEntity() {
        Long number = 5L;

        Optional<DocumentData> data = Optional.empty();

        when(documentDataRepository.findFirstByOrderByNumberAsc()).thenReturn(data);

        DocumentDataResponse documentDataResponse = documentDataService.getDocumentDataForMinNumber();

        assertEquals(number, documentDataResponse.getNumber());

    }


    @Test(expected = UnprocessableEntityException.class)
    public void deleteDocumentData_unprocessable() {
        Long number = 5L;
        Optional<DocumentData> data = Optional.empty();

        when(documentDataRepository.findByNumber(number)).thenReturn(data);

        documentDataService.deleteDocumentData(number);

    }

    @Test
    public void deleteDocumentData() {
        Long number = 5L;
        Optional<DocumentData> data = Optional.ofNullable(DocumentData.builder().number(5L).build());

        when(documentDataRepository.findByNumber(number)).thenReturn(data);

        documentDataService.deleteDocumentData(number);
    }


    @Test
    public void findAllDocumentDataWithOrder_DESC() {
        Sort.Direction filter = Sort.Direction.DESC;
        DocumentData data = DocumentData.builder().number(5L).build();
        DocumentData data2 = DocumentData.builder().number(6L).build();

        when(documentDataRepository.findAllByOrderByNumber(Sort.by(filter, SORT_FIELD))).thenReturn(Arrays.asList(data, data2));

        List<DocumentDataResponse> allDocumentDataWithOrder = documentDataService.findAllDocumentDataWithOrder(filter);

        assertEquals(2, allDocumentDataWithOrder.size());
        assertEquals(allDocumentDataWithOrder.get(0).getNumber(), data.getNumber());
    }

    @Test
    public void findAllDocumentDataWithOrder_ASC() {
        Sort.Direction filter = Sort.Direction.ASC;
        DocumentData data = DocumentData.builder().number(5L).build();
        DocumentData data2 = DocumentData.builder().number(6L).build();

        when(documentDataRepository.findAllByOrderByNumber(Sort.by(filter, SORT_FIELD))).thenReturn(Arrays.asList(data2, data));

        List<DocumentDataResponse> allDocumentDataWithOrder = documentDataService.findAllDocumentDataWithOrder(filter);

        assertEquals(2, allDocumentDataWithOrder.size());
        assertEquals(allDocumentDataWithOrder.get(0).getNumber(), data2.getNumber());
    }


}
