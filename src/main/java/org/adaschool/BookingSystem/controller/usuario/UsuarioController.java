package org.adaschool.BookingSystem.controller.usuario;

import org.adaschool.BookingSystem.model.Usuario;
import org.adaschool.BookingSystem.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{idUsuario}")
    public Usuario findById(@PathVariable String idUsuario) {
        return usuarioService.findById(idUsuario);
    }

    @PostMapping
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{idUsuario}")
    public Usuario update(@PathVariable String idUsuario, @RequestBody Usuario usuario) {
        return usuarioService.update(idUsuario, usuario);
    }

    @DeleteMapping("/{idUsuario}")
    public void deleteById(@PathVariable String idUsuario) {
        usuarioService.deleteById(idUsuario);
    }
}
