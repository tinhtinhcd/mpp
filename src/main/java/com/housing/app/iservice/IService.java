package com.housing.app.iservice;

import java.util.List;
import java.util.Optional;

import com.housing.app.models.AuditModel;

public interface IService<T> {

	Optional<T> get(long id);

	List<T> getAll();

	void save(T t);

	void update(T t, String[] params);

	void delete(T t);
}
