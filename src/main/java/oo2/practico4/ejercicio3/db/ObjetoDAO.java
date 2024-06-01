package oo2.practico4.ejercicio3.db;

import java.util.List;

public interface ObjetoDAO<E> {

	void create(E obj);

	void update(E obj);

	void remove(String id);

	void remove(E obj);

	E find(String id);

	List<E> findAll();
}
