package com.bilyoner.challenge.repository;


import com.bilyoner.challenge.model.entity.DocumentData;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentDataRepository extends MongoRepository<DocumentData,Long> {


    Optional<DocumentData> findByNumber(Long number);

    Optional<DocumentData> findFirstByOrderByNumberDesc();

    Optional<DocumentData> findFirstByOrderByNumberAsc();

    void deleteByNumber(Long number);

    List<DocumentData> findAllByOrderByNumber(Sort sort);


}
