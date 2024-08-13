package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.AdvogadoInput;
import com.sglp.sglp_api.api.dto.model.AdvogadoModel;
import com.sglp.sglp_api.domain.model.Advogado;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdvogadoMapper {

    private final ModelMapper mapper;

    public Advogado toEntity(AdvogadoInput input) {
        return mapper.map(input, Advogado.class);
    }

    public AdvogadoModel toModel(Advogado obj) {
        return mapper.map(obj, AdvogadoModel.class);
    }

    public List<AdvogadoModel> toModelList(List<Advogado> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .toList();
    }
}
