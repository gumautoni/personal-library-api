package com.gumautoni.personallibraryapi.repository;

import com.gumautoni.personallibraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}