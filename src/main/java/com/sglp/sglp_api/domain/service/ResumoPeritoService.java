package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.model.ResumoPerito;
import com.sglp.sglp_api.domain.repository.ResumoPeritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResumoPeritoService {

    @Autowired
    private ResumoPeritoRepository peritoRepository;

    public List<ResumoPerito> listar() {
        return peritoRepository.findAll();
    }

    @Transactional
    public ResumoPerito criar(ResumoPerito perito) {
        return peritoRepository.save(perito);
    }
}
