package com.housing.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housing.app.idao.IUserDAO;
import com.housing.app.iservice.IUserService;
import com.housing.app.models.UserModel;

@Service
public class UserServive implements IUserService {

	@Autowired
	IUserDAO userdao;

	@Override
	public Optional<UserModel> get(long id) {
		return userdao.get(id);
	}

	@Override
	public List<UserModel> getAll() {
		return userdao.getAll();
	}

	@Override
	public void save(UserModel t) {
		userdao.save(t);

	}

	@Override
	public void update(UserModel t, String[] params) {
		userdao.update(t, params);
	}

	@Override
	public void delete(UserModel t) {
		userdao.delete(t);
	}

}
