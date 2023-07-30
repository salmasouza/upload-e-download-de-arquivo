package com.empresa.empresa.repository;

import com.empresa.empresa.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByFuncao( String funcao);
    List<Funcionario> findAllByOrderByNome();

    List<Funcionario> findAll();

}
