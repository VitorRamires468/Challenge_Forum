package br.com.alura.forum.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime data,
        String curso,
        String status,
        String autor

) {
}
