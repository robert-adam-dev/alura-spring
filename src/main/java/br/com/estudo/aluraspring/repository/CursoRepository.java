package br.com.estudo.aluraspring.repository;

import br.com.estudo.aluraspring.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String nomeCurso);
}
