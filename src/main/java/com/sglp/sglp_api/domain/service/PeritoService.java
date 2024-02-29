package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.model.Perito;
import com.sglp.sglp_api.domain.repository.PeritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeritoService {

    @Autowired
    private PeritoRepository peritoRepository;

    public List<Perito> listar() {
        return peritoRepository.findAll();
    }

    @Transactional
    public Perito criar(Perito perito) {
        return peritoRepository.save(perito);
    }
}
