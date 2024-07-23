package br.edu.fema.modelo.atividadesfixacao.Application.exceptions.handler;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StandardError {

    private Integer status;
    private long timestamp;
    private String message;

    public StandardError() {
        super();
    }

    public StandardError(Integer status, long timestamp, String message) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }

}
