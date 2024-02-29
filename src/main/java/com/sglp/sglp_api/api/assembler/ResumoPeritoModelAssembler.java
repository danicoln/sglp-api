package com.sglp.sglp_api.api.assembler;

import com.sglp.sglp_api.api.dto.model.ResumoPeritoModel;
import com.sglp.sglp_api.domain.model.ResumoPerito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResumoPeritoModelAssembler {

    @Autowired
    ModelMapper modelMapper;

    public ResumoPeritoModel toModel (ResumoPerito resumoPerito){
        return modelMapper.map(resumoPerito, ResumoPeritoModel.class);
    }

    public List<ResumoPeritoModel> toCollectionModel(List<ResumoPerito> resumos) {
        return resumos.stream()
                .map(resumo -> toModel(resumo))
                .collect(Collectors.toList());
    }
}
