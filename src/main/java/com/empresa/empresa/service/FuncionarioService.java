package com.empresa.empresa.service;

import com.empresa.empresa.model.Funcionario;
import com.empresa.empresa.model.Pessoa;
import com.empresa.empresa.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;


    public List<Funcionario> findAll(){
        return repository.findAll();
    }

    public Optional<Funcionario> findById(Long id){
        return repository.findById(id);
        //return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public Funcionario insert(Funcionario funcionario){
        return repository.save(funcionario);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    public Funcionario update(Long id, Funcionario funcionario){

        Funcionario entity = repository.getReferenceById(id);
        updateData(entity, funcionario);
        return repository.save(entity);

    }
    private void updateData(Funcionario entity, Funcionario funcionario){
        entity.setFuncao(funcionario.getFuncao());
        entity.setSalario(funcionario.getSalario());

    }

    public List<Funcionario> listarFuncionariosAgrupadosPorFuncao() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao))
                .values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
    public List<Funcionario> findAllByOrderByNome( ) {
        return repository.findAllByOrderByNome();
    }

    public ResponseEntity<List<Funcionario>> aumentarSalarios() {
        List<Funcionario> funcionarios = repository.findAll();
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.1"));
            funcionario.setSalario(novoSalario.setScale(2, RoundingMode.HALF_EVEN));
            repository.save(funcionario);
        }
        return ResponseEntity.ok(funcionarios);
    }

    public Map<String, List<Funcionario>> agruparFuncionariosPorFuncao() {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        List<Funcionario> funcionarios = repository.findAll();

        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            funcionariosPorFuncao.computeIfAbsent(funcao, k -> new ArrayList<>()).add(funcionario);
        }

        return funcionariosPorFuncao;
    }

    public Funcionario obterFuncionarioComMaiorIdade() {
        List<Funcionario> funcionarios = repository.findAll();
        return funcionarios.stream()
                .max(Comparator.comparing(Pessoa::getDataNascimento))
                .orElse(null);
    }

    public BigDecimal salarioTotal() {
        List<Funcionario> funcionarios =repository.findAll();
        BigDecimal totalSalarios = BigDecimal.ZERO;

        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        return totalSalarios;
    }

}
