package com.housing.app.idao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public interface Idao<T> {

	Optional<T> get(int id);

	List<T> getAll();

	int save(T t);

	void update(T t);

	void delete(T t);
}
