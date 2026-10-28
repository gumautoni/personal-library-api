package com.gumautoni.personallibraryapi.service;

import com.gumautoni.personallibraryapi.dto.LivroRequest;
import com.gumautoni.personallibraryapi.exception.LivroNotFoundException;
import com.gumautoni.personallibraryapi.model.Livro;
import com.gumautoni.personallibraryapi.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro create(LivroRequest request) {
        Livro livro = new Livro(
                request.titulo(),
                request.autor(),
                request.genero(),
                request.anoPublicacao(),
                request.statusLeitura()
        );

        return livroRepository.save(livro);
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public Livro findById(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() ->
                        new LivroNotFoundException("Livro não encontrado")
                );
    }

    public Livro update(Long id, LivroRequest request) {
        Livro livro = findById(id);

        livro.setTitulo(request.titulo());
        livro.setAutor(request.autor());
        livro.setGenero(request.genero());
        livro.setAnoPublicacao(request.anoPublicacao());
        livro.setStatusLeitura(request.statusLeitura());

        return livroRepository.save(livro);
    }

    public void delete(Long id) {
        Livro livro = findById(id);
        livroRepository.delete(livro);
    }
}