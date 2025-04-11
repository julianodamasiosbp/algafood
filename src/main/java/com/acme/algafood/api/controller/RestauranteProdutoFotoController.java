package com.acme.algafood.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.acme.algafood.api.model.request.FotoProdutoInput;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
            FotoProdutoInput fotoProdutoInput) {
        try {
            var nomeDoArquivo = UUID.randomUUID().toString() + "_"
                    + fotoProdutoInput.getArquivo().getOriginalFilename();

            var arquivoFoto = Path.of("/home/juliano/projetos/catalogo", nomeDoArquivo);

            System.out.println(fotoProdutoInput.getDescricao());
            System.out.println(arquivoFoto);
            System.out.println(fotoProdutoInput.getArquivo().getContentType());

            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
