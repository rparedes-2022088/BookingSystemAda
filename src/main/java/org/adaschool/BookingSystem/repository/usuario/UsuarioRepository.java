package org.adaschool.BookingSystem.repository.usuario;

import org.adaschool.BookingSystem.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> findAll() {
        return usuarios;
    }

    public Optional<Usuario> findById(String id) {
        return usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
    }

    public Usuario save(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    public void deleteById(String id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarios.stream().filter(usuario -> usuario.getEmail().equals(email)).findFirst();
    }
}
