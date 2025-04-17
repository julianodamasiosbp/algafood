package com.acme.algafood.infrastructure.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.acme.algafood.core.storage.StorageProperties;
import com.acme.algafood.domain.service.FotoStorageService;

public class LocalFotoStorageService implements FotoStorageService {

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void armazenar(NovaFoto novaFoto) {

        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeAquivo());
            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
        } catch (IOException e) {
            throw new StorageException("Não foi possível armazenar o arquivo. ", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        return storageProperties.getLocal()
                .getDiretorioFotos()
                .resolve(Path.of(nomeArquivo));
    }

    @Override
    public void remover(String nomeArquivo) {
        Path arquivoPath = getArquivoPath(nomeArquivo);

        try {
            Files.deleteIfExists(arquivoPath);
        } catch (IOException e) {
            throw new StorageException("Não foi possível excluir o arquivo. ", e);
        }
    }

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);

            FotoRecuperada fotoRecuperada = FotoRecuperada
                    .builder().inputStream(Files.newInputStream(arquivoPath)).build();

            return fotoRecuperada;
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar o arquivo.", e);
        }
    }

}
