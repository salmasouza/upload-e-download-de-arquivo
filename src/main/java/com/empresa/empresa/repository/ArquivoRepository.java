package com.empresa.empresa.repository;

import com.empresa.empresa.model.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    Arquivo findArquivoById(Long id);
}
