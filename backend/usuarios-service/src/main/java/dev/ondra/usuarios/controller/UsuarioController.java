package dev.ondra.usuarios.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ondra.usuarios.model.Usuario;
import dev.ondra.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return service.findAll();
    }

    @GetMapping("/{email}")
    public Optional<Usuario> getUsuarioByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return service.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/ping")
    public String ping() {
        return "Microservicio de Usuarios operativo ✅";
    }
}
