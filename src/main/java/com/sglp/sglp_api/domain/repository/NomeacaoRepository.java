package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.Nomeacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NomeacaoRepository extends MongoRepository<Nomeacao, String> {
}
