package dev.ondra.usuarios.controller.auth;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ondra.usuarios.config.JwtService;
import dev.ondra.usuarios.model.Usuario;
import dev.ondra.usuarios.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository userRepo, JwtService jwtService, AuthenticationManager authManager, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return userRepo.save(usuario);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Usuario usuario) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getContrasena()));
            String token = jwtService.generateToken(usuario.getEmail());
            return Map.of("token", token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}
