package com.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.model.Fornecedor;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor, String> {
	@Query(value = "select ceil(count(*) / 10.0) from Fornecedor", nativeQuery = true)
	int getPageCount();
	
	@Query(value = "select * from Fornecedor limit 10 offset ?1", nativeQuery = true)
	List<Fornecedor> getPage(int page);
}