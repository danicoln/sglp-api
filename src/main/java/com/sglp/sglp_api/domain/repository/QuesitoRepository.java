package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Quesito;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuesitoRepository extends MongoRepository<Quesito, String> {

    List<Quesito> findAllByLaudoId(String laudoId);
}
