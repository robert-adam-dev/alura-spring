package br.com.estudo.aluraspring.repository;

import br.com.estudo.aluraspring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional <Usuario> findByEmail(String email);
}
