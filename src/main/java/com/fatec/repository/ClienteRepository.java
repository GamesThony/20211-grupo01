package com.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {
	@Query(value = "select ceil(count(*) / 10.0) from Cliente", nativeQuery = true)
	int getPageCount();
	
	@Query(value = "select * from Cliente limit 10 offset ?1", nativeQuery = true)
	List<Cliente> getPage(int page);
}