package com.xadrez.carlos.xadrez_variante.variantes;

import jakarta.validation.constraints.NotBlank;


public record DadosCadastrados(
@NotBlank   
String criador, 
String variante, 
String lances) {
    
}
