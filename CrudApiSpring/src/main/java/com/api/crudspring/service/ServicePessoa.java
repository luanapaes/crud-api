package com.api.crudspring.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.crudspring.model.Pessoa;

@Repository
public interface ServicePessoa extends JpaRepository<Pessoa, Long>{

}
