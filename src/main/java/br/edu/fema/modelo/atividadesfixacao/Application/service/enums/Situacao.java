package br.edu.fema.modelo.atividadesfixacao.Application.service.enums;

import br.edu.fema.modelo.atividadesfixacao.utils.conversao.enums.interfaces.ValorEnum;
import br.edu.fema.modelo.atividadesfixacao.utils.conversao.enums.service.DesserializadorEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonDeserialize(using = DesserializadorEnum.class)
public enum Situacao implements ValorEnum<String> {

    DISPONIVEL("D", "Disponivel"),
    INDISPONIVEL("I", "Indisponivel");

    private String valor;
    private String descricao;

}
