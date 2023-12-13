package com.empresa.empresa.service;

import com.empresa.empresa.model.Arquivo;
import com.empresa.empresa.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Transactional
    public Arquivo salvarArquivo(MultipartFile arquivo) throws IOException {
        String nome = arquivo.getOriginalFilename();
        String tipo = arquivo.getContentType();
        byte[] dados = arquivo.getBytes();

        Arquivo arquivoEntidade = new Arquivo();
        arquivoEntidade.setNome(nome);
        arquivoEntidade.setTipo(tipo);
        arquivoEntidade.setDados(dados);

        return arquivoRepository.save(arquivoEntidade);
    }


    @Transactional
    public Arquivo obterPorId(Long id){
        return arquivoRepository.findArquivoById(id);
    }

}