package br.com.alura.forum.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        String titulo,
        String mensagem,
        LocalDateTime data,
        String status,
        String autor,
        String curso
) {
}
