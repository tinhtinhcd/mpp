package com.housing.app.idao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface Idao<T> {

	Optional<T> get(long id);

	List<T> getAll();

	void save(T t);

	void update(T t, String[] params);

	void delete(T t);
}
