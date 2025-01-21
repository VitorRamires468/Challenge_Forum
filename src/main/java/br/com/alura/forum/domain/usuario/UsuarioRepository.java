package br.com.alura.forum.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.nome = :autor and u.ativo = true")
    Optional<Usuario> buscarUsuarioAtivo(String autor);

    UserDetails findByEmail(String username);
}
