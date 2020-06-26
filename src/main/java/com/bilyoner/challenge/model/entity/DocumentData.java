package com.bilyoner.challenge.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Getter
@Setter
@Builder
@Document(collection = "default")
public class DocumentData {

    @Id
    public String id;
    private Long number;
    private Instant insertDate;



}




