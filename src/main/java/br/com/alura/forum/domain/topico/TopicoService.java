package br.com.alura.forum.domain.topico;

import br.com.alura.forum.domain.usuario.Usuario;
import br.com.alura.forum.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosDetalhamentoTopico cadastrarTopico(DadosCadastramentoTopico dados) {
        Optional<Usuario> optionalUsuario = usuarioRepository.buscarUsuarioAtivo(dados.autor());
        Usuario usuario;
        Topico topico;
        if(optionalUsuario.isPresent()){
            usuario = optionalUsuario.get();
            topico = new Topico(dados,usuario);
            topicoRepository.save(topico);
        }else{
            throw new NoSuchElementException("Usuario não cadastrado!");
        }

        return new DadosDetalhamentoTopico(topico.getId(), topico.getTitulo(), topico.getMensagem(),topico.getData(), topico.getCurso(), topico.getStatus().toString(), topico.getAutor().getNome());
    }

    public DadosDetalhamentoTopico atualizarInformacoes(Long id, DadosCadastramentoTopico dados) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        Topico topico;
        if(optionalTopico.isPresent()){
            topico = optionalTopico.get();
            topico.atualizarInformacoes(dados);
        }else{
            throw new NoSuchElementException("Topico não cadastrado!");
        }
        System.out.println(topico.getMensagem());
        return new DadosDetalhamentoTopico(topico.getId(), topico.getTitulo(), topico.getMensagem(),topico.getData(), topico.getCurso(), topico.getStatus().toString(), topico.getAutor().getNome());
    }

    public void deletarTopico(Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        Topico topico;
        if(!optionalTopico.isPresent()){
            throw new NoSuchElementException("Topico não cadastrado!");
        }
        topico = optionalTopico.get();
        topicoRepository.deleteById(id);

    }
}
