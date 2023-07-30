package com.empresa.empresa.controller;

import com.empresa.empresa.model.Pessoa;
import com.empresa.empresa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api")
public class PessoaControler {

    @Autowired
    private PessoaService service;

    @GetMapping(value="/pessoas")
    public ResponseEntity<List<Pessoa>> findAll(){
        List<Pessoa> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value ="/pessoa/{id}")
    public ResponseEntity<Optional<Pessoa>> findById(@PathVariable Long id){
        Optional<Pessoa> pessoa = service.findById(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping(value = "/pessoa")
    public ResponseEntity<Pessoa> insert(@RequestBody Pessoa pessoa){
        pessoa = service.insert(pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @DeleteMapping(value = "/pessoa/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/pessoa/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa){
        pessoa = service.update(id, pessoa);
        return ResponseEntity.ok().body(pessoa);

    }
}
