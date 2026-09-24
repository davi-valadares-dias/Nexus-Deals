package com.nexusdeals.nexus_deals.controller;

import com.nexusdeals.nexus_deals.model.Usuario;
import com.nexusdeals.nexus_deals.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nexusdeals.nexus_deals.config.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioSalvo = usuarioService.cadastrar(usuario);
            usuarioSalvo.setSenha(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
        } catch (IllegalArgumentException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        try {
            String email = credenciais.get("email");
            String senha = credenciais.get("senha");

            Usuario usuario = usuarioService.autenticar(email, senha);
            String token = jwtUtil.gerarToken(usuario.getEmail());

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("token", token);
            resposta.put("nome", usuario.getNome());
            resposta.put("email", usuario.getEmail());

            return ResponseEntity.ok(resposta);
        } catch (IllegalArgumentException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
        }
    }
}