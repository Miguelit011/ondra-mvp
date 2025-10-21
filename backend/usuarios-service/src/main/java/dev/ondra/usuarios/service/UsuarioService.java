package dev.ondra.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.ondra.usuarios.model.Usuario;
import dev.ondra.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> findAll() {
        return repo.findAll();
    }

    public Optional<Usuario> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        return repo.save(usuario);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
