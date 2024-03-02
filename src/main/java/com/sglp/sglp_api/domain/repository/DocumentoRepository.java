package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Documento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentoRepository extends MongoRepository<Documento, String> {
}
