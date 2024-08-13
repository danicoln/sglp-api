package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Advogado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdvogadoRepository extends MongoRepository<Advogado, String> {

    boolean existsByEmail(String email);
}
