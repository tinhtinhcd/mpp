package com.housing.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.housing.app.idao.IUserDAO;
import com.housing.app.models.UserModel;

@Repository
public class UserDAO implements IUserDAO {

	@Override
	public Optional<UserModel> get(long id) {
		//remove this later
		return dummyData();
	}

	@Override
	public List<UserModel> getAll() {
		return null;
	}

	@Override
	public void save(UserModel t) {

	}

	@Override
	public void update(UserModel t, String[] params) {

	}

	@Override
	public void delete(UserModel t) {

	}

	private Optional<UserModel> dummyData() {
		return Optional.of(new UserModel("tinhtinh", "123456"));
	}

}
