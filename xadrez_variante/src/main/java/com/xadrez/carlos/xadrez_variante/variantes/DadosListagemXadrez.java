package com.xadrez.carlos.xadrez_variante.variantes;

public record DadosListagemXadrez(Long id, String criador, String lances, String variante) {
    

    public DadosListagemXadrez(Xadrez xadrez){
        this(xadrez.getId(), xadrez.getCriador(), xadrez.getLances(), xadrez.getVariante());


    }
}
