package com.bilyoner.challenge.repository;

import com.bilyoner.challenge.model.entity.DocumentData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DocumentDataRepository extends MongoRepository<DocumentData,Long> {


    Optional<DocumentData> findByNumber(Long number);

}
