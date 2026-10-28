package com.gumautoni.personallibraryapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LivroRequest(

        @NotBlank(message = "O título é obrigatório")
        @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
        String titulo,

        @NotBlank(message = "O autor é obrigatório")
        @Size(max = 100, message = "O autor deve ter no máximo 100 caracteres")
        String autor,

        @NotBlank(message = "O gênero é obrigatório")
        @Size(max = 80, message = "O gênero deve ter no máximo 80 caracteres")
        String genero,

        @NotNull(message = "O ano de publicação é obrigatório")
        @Positive(message = "O ano de publicação deve ser maior que zero")
        Integer anoPublicacao,

        @NotBlank(message = "O status de leitura é obrigatório")
        String statusLeitura

) {
}