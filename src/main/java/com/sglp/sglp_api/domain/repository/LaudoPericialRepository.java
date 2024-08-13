package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.LaudoPericial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LaudoPericialRepository extends MongoRepository<LaudoPericial, String> {

    Optional<LaudoPericial> findTopByOrderByNumeroDesc();

    List<LaudoPericial> findAllByAtivo(boolean ativo);
}
