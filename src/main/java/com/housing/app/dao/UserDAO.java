package com.housing.app.dao;

import org.springframework.stereotype.Component;

import com.housing.app.idao.IUserDAO;
import com.housing.app.models.UserModel;

@Component
public class UserDAO extends DAO<UserModel> implements IUserDAO {

}
