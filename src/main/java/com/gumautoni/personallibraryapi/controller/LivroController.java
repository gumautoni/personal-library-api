package com.gumautoni.personallibraryapi.controller;

import com.gumautoni.personallibraryapi.dto.LivroRequest;
import com.gumautoni.personallibraryapi.model.Livro;
import com.gumautoni.personallibraryapi.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> create(
            @Valid @RequestBody LivroRequest request
    ) {
        Livro livro = livroService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livro);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.ok(livroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(
            @PathVariable Long id,
            @Valid @RequestBody LivroRequest request
    ) {
        return ResponseEntity.ok(
                livroService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);

        return ResponseEntity.noContent().build();
    }
}