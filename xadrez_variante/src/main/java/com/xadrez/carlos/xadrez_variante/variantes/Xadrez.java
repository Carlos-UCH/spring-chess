package com.xadrez.carlos.xadrez_variante.variantes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "xadrez")
@Table(name = "Xadrez")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Xadrez{

    public Xadrez(DadosCadastrados dados){
        this.ativo = true;
        this.criador = dados.criador();
        this.variante = dados.variante();
        this.lances = dados.lances();
    }

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //@Enumerated(EnumType.STRING)
    private Long id;
    private String criador;
    private String variante; 
    private String lances;  

    private Boolean ativo;

    public void atualizarInformacoes(@Valid DadosAtualizar dados){
        if(dados.criador() != null){
            this.criador = dados.criador();
        }if (dados.variante() != null ) {
            this.variante = dados.variante();
        }if (dados.lances() != null) {
            this.lances = dados.lances();
        }
        
        this.criador = dados.criador();
    }

    public void inativar(){
        this.ativo = false;
    }

    public void reativar(){
        this.ativo = true;
    }
}

