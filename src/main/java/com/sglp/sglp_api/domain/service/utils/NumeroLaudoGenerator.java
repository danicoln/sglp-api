package com.sglp.sglp_api.domain.service.utils;

import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.repository.LaudoPericialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Component
public class NumeroLaudoGenerator {

    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final String NUMBER_PATTERN = "%s-%04d";

    private LaudoPericialRepository laudoPericialRepository;

    public String gerarNumeroDoLaudo(LocalDateTime dataDoLaudo) {
        String dataFormatada = dataDoLaudo.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
        int numeroIncremental = getUltimoNumeroIncremental() + 1;
        return String.format(NUMBER_PATTERN, dataFormatada, numeroIncremental);
    }

    private int getUltimoNumeroIncremental() {
        return laudoPericialRepository.findTopByOrderByNumeroDesc()
                .map(NumeroLaudoGenerator::extrairNumeroIncremental)
                .orElse(0);
    }

    private static int extrairNumeroIncremental(LaudoPericial ultimoLaudo) {
        String ultimoNumero = ultimoLaudo.getNumero();
        if (ultimoNumero != null && !ultimoNumero.isEmpty()) {
            String[] partes = ultimoNumero.split("-");
            if (partes.length == 2 && partes[1].matches("\\d{4}")) {
                return Integer.parseInt(partes[1]);
            }
        }
        return 0;
    }

}
