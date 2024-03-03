package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Processo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessoRepository extends MongoRepository<Processo, String> {
}
