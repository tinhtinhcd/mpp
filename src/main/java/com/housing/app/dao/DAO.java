package com.housing.app.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.housing.app.idao.Idao;
import com.housing.app.models.AuditModel;

public class DAO<T extends AuditModel> implements Idao<T> {

	private List<T> users = new ArrayList<>();

	@Override
	public Optional<T> get(int id) {
		// remove this later
		return Optional.ofNullable(users.get(id));
	}

	@Override
	public List<T> getAll() {
		return users.stream().filter(Objects::nonNull)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
	}

	@Override
	public int save(T t) {
		users.add(t);
		return users.size();
	}

	@Override
	public void update(T t) {
		users.set(t.getId(), t);
	}

	@Override
	public void delete(T t) {
		users.set(t.getId(), null);
	}

}
