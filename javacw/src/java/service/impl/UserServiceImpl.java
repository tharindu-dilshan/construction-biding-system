/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import java.sql.SQLException;
import java.util.List;
import model.User;
import service.UserService;

/**
 *
 * @author HaShaN
 */
public class UserServiceImpl implements UserService{

    private final UserDao userDao = new UserDaoImpl();
    
    @Override
    public boolean addUser(User user) throws ClassNotFoundException, SQLException {
        return userDao.addUser(user) > 0;
    }

    @Override
    public boolean editUser(User user) throws ClassNotFoundException, SQLException {
        return userDao.editUser(user) > 0;
    }

    @Override
    public User getById(int id) throws ClassNotFoundException, SQLException {
        return userDao.getById(id);
    }

    @Override
    public User getByUserName(String userName) throws ClassNotFoundException, SQLException {
        return userDao.getByUserName(userName);
    }

    @Override
    public User getByEmail(String email) throws ClassNotFoundException, SQLException {
        return userDao.getByEmail(email);
    }

    @Override
    public List<User> getAllExcept(String role) throws ClassNotFoundException, SQLException {
        return userDao.getAllExcept(role);
    }
    
}
