package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.ResumoPerito;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResumoPeritoRepository extends MongoRepository<ResumoPerito, String> {
}
