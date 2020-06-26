package com.bilyoner.challenge.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Setter
@Getter
@Builder
public class DocumentDataResponse {

    private Long number;
    private Instant insertDate;

}
