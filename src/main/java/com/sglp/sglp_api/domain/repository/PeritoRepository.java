package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Perito;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeritoRepository extends MongoRepository<Perito, String> {
}
