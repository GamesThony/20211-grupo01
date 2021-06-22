package com.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.model.Venda;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Integer> {
	@Query(value = "select ceil(count(*) / 10.0) from Venda", nativeQuery = true)
	int getPageCount();
	
	@Query(value = "select * from Venda limit 10 offset ?1", nativeQuery = true)
	List<Venda> getPage(int page);
	
	@Query(value = "select * from Venda order by id_venda desc limit 1", nativeQuery = true)
	Venda getLast();
}