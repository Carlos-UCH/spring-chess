package com.xadrez.carlos.xadrez_variante.variantes;


public record DadosDetalhamentoXadrez(
    Long id, 
    String criador, 
    String variante, 
    String lances, 
    Boolean ativo) {

        public DadosDetalhamentoXadrez(Xadrez xadrez){
            this(
            xadrez.getId(), 
            xadrez.getCriador(), 
            xadrez.getVariante(), 
            xadrez.getLances(), 
            xadrez.getAtivo());
        }

} 
