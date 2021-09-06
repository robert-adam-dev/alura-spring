package br.com.estudo.aluraspring.controller;

import br.com.estudo.aluraspring.controller.dto.DetalhesTopicoDto;
import br.com.estudo.aluraspring.controller.dto.TopicoDto;
import br.com.estudo.aluraspring.controller.form.AtualizacaoTopicoForm;
import br.com.estudo.aluraspring.controller.form.TopicoForm;
import br.com.estudo.aluraspring.model.Topico;
import br.com.estudo.aluraspring.repository.CursoRepository;
import br.com.estudo.aluraspring.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){

        if(nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        }else{
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        }

    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity <DetalhesTopicoDto> detalhes(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);

        if(topico.isPresent()){
            return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
        Optional<Topico> topico = topicoRepository.findById(id);

        if(topico.isPresent()){
            Topico topicoAtualizado = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topicoAtualizado));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id){

        Optional<Topico> topico = topicoRepository.findById(id);

        if(topico.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
