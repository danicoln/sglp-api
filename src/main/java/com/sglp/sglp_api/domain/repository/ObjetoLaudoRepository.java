package com.sglp.sglp_api.domain.repository;

import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ObjetoLaudoRepository extends MongoRepository<ObjetoLaudo, String> {

    List<ObjetoLaudo> findByExameDaMateriaId(String exameId);
}
