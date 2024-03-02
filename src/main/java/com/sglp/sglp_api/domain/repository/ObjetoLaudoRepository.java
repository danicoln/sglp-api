package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObjetoLaudoRepository extends MongoRepository<ObjetoLaudo, String> {
}
