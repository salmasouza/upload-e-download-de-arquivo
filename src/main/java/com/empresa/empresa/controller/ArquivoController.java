package com.empresa.empresa.controller;

import com.empresa.empresa.model.Arquivo;
import com.empresa.empresa.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/arquivos")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping("/upload")
    public ResponseEntity<Arquivo> uploadArquivo(@RequestParam("arquivo") MultipartFile arquivo) throws IOException {
        Arquivo arquivoSalvo = arquivoService.salvarArquivo(arquivo);
        return ResponseEntity.ok(arquivoSalvo);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadArquivo(@PathVariable Long id) throws IOException {
        Arquivo arquivo = arquivoService.obterPorId(id);

        ByteArrayResource resource = new ByteArrayResource(arquivo.getDados());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + arquivo.getNome())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(arquivo.getDados().length)
                .body(resource);
    }
}


