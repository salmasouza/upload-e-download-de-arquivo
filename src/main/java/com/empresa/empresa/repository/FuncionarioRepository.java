package com.empresa.empresa.repository;

import com.empresa.empresa.model.Funcionario;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByFuncao( String funcao);
    List<Funcionario> findAllByOrderByNome();

    List<Funcionario> findAll();

    Optional<Funcionario> findFirstByOrderByDataNascimentoDesc();;

    @Query("select p from Funcionario p WHERE  MONTH(p.dataNascimento) IN (10, 12)")
    List<Funcionario> findByDataNascimento();
}
