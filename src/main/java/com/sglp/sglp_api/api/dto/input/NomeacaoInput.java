package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NomeacaoInput {

    //TODO:Resolver erro de data:
    /*
    * org.bson.codecs.configuration.CodecConfigurationException: Can't find a codec for CodecCacheKey{clazz=class java.time.OffsetDateTime, types=null}.
     * */
    //private OffsetDateTime dataNomeacao;
    private ProcessoInput processo;
}
