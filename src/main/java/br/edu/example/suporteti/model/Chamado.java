package br.edu.example.suporteti.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chamado {

    private Long id;

    @NotBlank(message = "Informe o título")
    @Size(min = 5, max = 80, message = "O título deve conter entre 5 e 80 caracteres")
    private String titulo;

    @NotBlank(message = "Informe a descrição")
    @Size(min = 15, message = "A descrição deve conter no mínimo 15 caracteres")
    private String descricao;

    @NotBlank(message = "Informe o solicitante")
    private String solicitante;

    @NotBlank(message = "Informe o e-mail")
    @Email(message = "Informe um e-mail válido")
    private String emailSolicitante;

    @NotNull(message = "Informe a prioridade")
    private Prioridade prioridade;

    @NotNull(message = "Informe o status")
    private Status status;

    public Chamado() {
        this.status = Status.ABERTO;
    }

    public void iniciarAtendimento() {

        if (status == Status.ABERTO){
            this.status = Status.EM_ATENDIMENTO;
        }

    }

    public void resolver() {

        if (status == Status.EM_ATENDIMENTO){
            this.status = Status.RESOLVIDO;
        }

    }

    public void cancelar() {

        if (status == Status.EM_ATENDIMENTO || status == Status.EM_ATENDIMENTO){
            this.status = Status.CANCELADO;
        }

    }


}
