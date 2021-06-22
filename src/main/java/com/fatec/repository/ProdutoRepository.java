package com.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
	@Query(value = "select ceil(count(*) / 10.0) from Produto", nativeQuery = true)
	int getPageCount();
	
	@Query(value = "select * from Produto limit 10 offset ?1", nativeQuery = true)
	List<Produto> getPage(int page);
	
	@Query(value = "select * from Produto order by id_produto desc limit 1", nativeQuery = true)
	Produto getLast();
}