package com.bilyoner.challenge.mapper;

import com.bilyoner.challenge.model.dto.DocumentDataRequest;
import com.bilyoner.challenge.model.dto.DocumentDataResponse;
import com.bilyoner.challenge.model.entity.DocumentData;

import java.time.Instant;

public class DocumentDataMapper {

    public static DocumentData requestToEntity(DocumentDataRequest request){
        return DocumentData.builder()
                .insertDate(Instant.now())
                .number(request.getNumber())
                .build();
    }


    public static DocumentDataResponse EntityToResponse(DocumentData data){
        return DocumentDataResponse.builder()
                .insertDate(data.getInsertDate())
                .number(data.getNumber())
                .build();
    }

}
