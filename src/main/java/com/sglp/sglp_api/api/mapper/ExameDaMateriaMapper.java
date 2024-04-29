package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.ExameDaMateriaInput;
import com.sglp.sglp_api.api.dto.model.ExameDaMateriaModel;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExameDaMateriaMapper {

    private final ModelMapper mapper;

    public ExameDaMateria toEntity(ExameDaMateriaInput input) {
        return mapper.map(input, ExameDaMateria.class);
    }

    public ExameDaMateriaModel toModel(ExameDaMateria obj) {
        return mapper.map(obj, ExameDaMateriaModel.class);
    }

    public List<ExameDaMateriaModel> toModelList(List<ExameDaMateria> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
