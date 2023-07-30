package com.empresa.empresa.service;

import com.empresa.empresa.model.Pessoa;
import com.empresa.empresa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;
    public List<Pessoa> findAll(){
        return repository.findAll();
    }


    public Optional<Pessoa> findById(Long id){
        return repository.findById(id);
        //return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public Pessoa insert(Pessoa pessoa){
        return repository.save(pessoa);
    }

    public void delete(Long id) {

            repository.deleteById(id);
    }

    public Pessoa update(Long id, Pessoa pessoa){

            Pessoa entity = repository.getReferenceById(id);
            updateData(entity, pessoa);
            return repository.save(entity);

    }
    private void updateData(Pessoa entity, Pessoa pessoa){
        entity.setNome(pessoa.getNome());
        entity.setDataNascimento(pessoa.getDataNascimento());

    }

}
