package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.PeritoInput;
import com.sglp.sglp_api.api.dto.model.PeritoModel;
import com.sglp.sglp_api.domain.model.Perito;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PeritoMapper {

    private final ModelMapper mapper;

    public Perito toEntity(PeritoInput input) {
        return mapper.map(input, Perito.class);
    }

    public PeritoModel toModel(Perito obj) {
        return mapper.map(obj, PeritoModel.class);
    }

    public List<PeritoModel> toModelList(List<Perito> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .toList();
    }
}
