package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.LaudoPericial;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LaudoPericialRepository extends MongoRepository<LaudoPericial, String> {
}
