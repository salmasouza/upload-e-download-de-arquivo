package com.empresa.empresa.controller;

import com.empresa.empresa.model.Funcionario;
import com.empresa.empresa.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value ="/api")
public class FuncionarioControler {
    @Autowired
    private FuncionarioService service;

    @GetMapping(value = "/funcionarios")
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/funcionario/{id}")
    public ResponseEntity<Optional<Funcionario>> findById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = service.findById(id);
        return ResponseEntity.ok().body(funcionario);
    }

    @PostMapping(value = "/funcionario")
    public ResponseEntity<Funcionario> insert(@RequestBody Funcionario funcionario) {
        funcionario = service.insert(funcionario);
        return ResponseEntity.ok().body(funcionario);
    }

    @DeleteMapping(value = "/funcionario/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/funcionario/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        funcionario = service.update(id, funcionario);
        return ResponseEntity.ok().body(funcionario);

    }

    @GetMapping(value = "/funcionario/funv")
    public ResponseEntity<List<Funcionario>> listarFuncionariosAgrupadosPorFuncao() {
        // Agrupar os funcionários por função usando ResponseEntity.
        List<Funcionario> funcionariosAgrupadosPorFuncao = service.listarFuncionariosAgrupadosPorFuncao();
        return ResponseEntity.ok(funcionariosAgrupadosPorFuncao);
    }

    @GetMapping("/funcionarios/ordem-alfabetica")
    public List<Funcionario> findAllByOrderByNome( ) {
        return service.findAllByOrderByNome();
    }
    @GetMapping("/data")
    public List<Funcionario>  findByDataNascimento() {
        return service.findByDataNascimento();
    }




    @GetMapping("/mais-velho")
    public ResponseEntity<String> buscarFuncionarioMaisVelho() {
        // Buscar o funcionário com a maior idade.
        Optional<Funcionario> funcionarioMaisVelho = service.buscarFuncionarioMaisVelho();
        if (funcionarioMaisVelho.isPresent()) {
            // Calcular a idade do funcionário mais velho.
            int idade =service.calcularIdade(funcionarioMaisVelho.get());
            return ResponseEntity.ok("Funcionário mais velho: " + funcionarioMaisVelho.get().getNome() + ", Idade: " + idade);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    @GetMapping("/funcionarios/aumentar-salarios")
    public ResponseEntity<List<Funcionario>> aumentarSalarios() {
        return service.aumentarSalarios();
    }

    @GetMapping("/funcionarios/agrupar-por-funcao")
    public Map<String, List<Funcionario>> agruparFuncionariosPorFuncao() {
        return service.agruparFuncionariosPorFuncao();
    }

    @GetMapping("/funcionarios/total-salarios")
    public ResponseEntity<BigDecimal> salarioTotal() {
        BigDecimal totalSalarios = service.salarioTotal();
        return ResponseEntity.ok(totalSalarios);
    }

}











