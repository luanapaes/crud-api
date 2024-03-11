package com.api.crudspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pessoas")
	public List<Pessoa> getAllPessoas(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id){
        Optional<Pessoa> pessoaOptional = repository.findById(id);

        return pessoaOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pessoas")
	public Pessoa addPessoa(@RequestBody Pessoa pessoa) {
		return repository.save(pessoa);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/pessoas/{id}")
	public ResponseEntity<String> deletePessoaById(@PathVariable Long id) {
        // verifica se a pessoa existe antes de tentar deletar
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@PutMapping("pessoas/{id}")
//	public String put(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
//	    Optional<Pessoa> optionalPessoa = repository.findById(id);
//
//	    if (optionalPessoa.isPresent()) {
//	        Pessoa pessoa = optionalPessoa.get();
//
//	        if (StringUtils.isNotBlank(pessoaAtualizada.getNome())) {
//	            pessoa.setNome(pessoaAtualizada.getNome());
//	        }
//
//	        if (StringUtils.isNotBlank(pessoaAtualizada.getSobrenome())) {
//	            pessoa.setSobrenome(pessoaAtualizada.getSobrenome());
//	        }
//
//	        if (pessoaAtualizada.getIdade() != 0) {
//	            pessoa.setIdade(pessoaAtualizada.getIdade());
//	        }
//
//	        Pessoa pessoaAtualizadaNoBanco = repository.save(pessoa);
//	        return put.ok(pessoaAtualizadaNoBanco);
//	    }
//
//	    return "Usuário não encontrado.";
//	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("pessoas/{id}")
	public ResponseEntity<Pessoa> put(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {

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

	        Pessoa pessoaAtualizadaNoBanco = repository.save(pessoa);
	        return ResponseEntity.ok(pessoaAtualizadaNoBanco);
	    }

	    return ResponseEntity.notFound().build();
	}

	
}
