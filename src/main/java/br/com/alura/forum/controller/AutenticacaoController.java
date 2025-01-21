package br.com.alura.forum.controller;


import br.com.alura.forum.domain.usuario.DadosAutenticacao;
import br.com.alura.forum.domain.usuario.Usuario;
import br.com.alura.forum.infra.security.DadosToken;
import br.com.alura.forum.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){

        var authentication = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());

        var authenticated = manager.authenticate(authentication);

        var token = tokenService.gerarToken((Usuario) authenticated);

        return ResponseEntity.ok(new DadosToken(token));
    }
}
