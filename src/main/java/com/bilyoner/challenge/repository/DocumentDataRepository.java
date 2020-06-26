package com.bilyoner.challenge.repository;

import com.bilyoner.challenge.model.entity.DocumentData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentDataRepository extends MongoRepository<DocumentData,Long> {
}
