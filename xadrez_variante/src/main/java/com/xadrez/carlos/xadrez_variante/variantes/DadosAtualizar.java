package com.xadrez.carlos.xadrez_variante.variantes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizar(
    @NotNull
    Long id,
    @NotBlank
    String criador,
    String lances, 
    String variante) {

    
}