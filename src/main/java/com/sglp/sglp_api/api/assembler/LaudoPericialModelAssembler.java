package com.sglp.sglp_api.api.assembler;

import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.api.dto.model.LaudoPericialModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class LaudoPericialModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public LaudoPericialModel toModel(LaudoPericial laudoPericial) {
        return modelMapper.map(laudoPericial, LaudoPericialModel.class);
    }

    public List<LaudoPericialModel> toCollectionModel(List<LaudoPericial> laudos) {

        return laudos.stream()
                .map(laudoPericial -> toModel(laudoPericial))
                .collect(Collectors.toList());
    }
}
