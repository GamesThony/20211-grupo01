package com.fatec.service;

import java.util.List;

public interface BaseService<T, E> {
	int getPageCount();
	List<T> getPage(int page);
	String save(T entity);
	String delete(E id);
	T find(E id);
	String update(E id, T entity);
}
