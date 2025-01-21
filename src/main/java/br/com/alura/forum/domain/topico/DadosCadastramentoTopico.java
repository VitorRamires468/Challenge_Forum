package br.com.alura.forum.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastramentoTopico(
        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotBlank
        String autor,

        @NotBlank
        String curso
) {
}
