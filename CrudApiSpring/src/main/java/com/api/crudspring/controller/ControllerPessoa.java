package com.api.crudspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.crudspring.model.Pessoa;
import com.api.crudspring.service.ServicePessoa;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class ControllerPessoa {
	
	@Autowired
	private ServicePessoa repository;	
	
	@GetMapping("/pessoa")
	public List<Pessoa> getAllPessoas(){
		return repository.findAll();
	}
	
	@GetMapping("/pessoa/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id){
        Optional<Pessoa> pessoaOptional = repository.findById(id);

        return pessoaOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
	
	@PostMapping("/pessoa")
	public Pessoa addPessoa(@RequestBody Pessoa pessoa) {
		return repository.save(pessoa);
	}
	
	@DeleteMapping("/pessoa/{id}")
	public ResponseEntity<String> deletePessoaById(@PathVariable Long id) {
        // verifica se a pessoa existe antes de tentar deletar
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Pessoa deletada com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@PutMapping("pessoa/{id}")
	public String put(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
	    Optional<Pessoa> optionalPessoa = repository.findById(id);

	    if (optionalPessoa.isPresent()) {
	        Pessoa pessoa = optionalPessoa.get();

	        if (StringUtils.isNotBlank(pessoaAtualizada.getNome())) {
	            pessoa.setNome(pessoaAtualizada.getNome());
	        }

	        if (StringUtils.isNotBlank(pessoaAtualizada.getSobrenome())) {
	            pessoa.setSobrenome(pessoaAtualizada.getSobrenome());
	        }

	        if (pessoaAtualizada.getIdade() != 0) {
	            pessoa.setIdade(pessoaAtualizada.getIdade());
	        }

	        repository.save(pessoa);
	        return "Usuário atualizado com sucesso!";
	    }

	    return "Usuário não encontrado.";
	}
}
