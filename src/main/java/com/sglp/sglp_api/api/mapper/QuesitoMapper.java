package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.QuesitoInput;
import com.sglp.sglp_api.api.dto.model.QuesitoModel;
import com.sglp.sglp_api.domain.model.Quesito;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuesitoMapper {

    private final ModelMapper mapper;

    public Quesito toEntity(QuesitoInput input) {
        return mapper.map(input, Quesito.class);
    }

    public QuesitoModel toModel(Quesito obj) {
        return mapper.map(obj, QuesitoModel.class);
    }

    public List<QuesitoModel> toModelList(List<Quesito> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .toList();
    }
}
