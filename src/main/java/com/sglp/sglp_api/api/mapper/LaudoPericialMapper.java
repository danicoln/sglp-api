package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.LaudoPericialInput;
import com.sglp.sglp_api.api.dto.model.LaudoPericialModel;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LaudoPericialMapper {

    private final ModelMapper mapper;

    public LaudoPericial toEntity(LaudoPericialInput input) {
        return mapper.map(input, LaudoPericial.class);
    }

    public LaudoPericialModel toModel(LaudoPericial obj) {
        return mapper.map(obj, LaudoPericialModel.class);
    }

    public List<LaudoPericialModel> toModelList(List<LaudoPericial> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
