/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.RoleDao;
import dao.impl.RoleDaoImpl;
import java.sql.SQLException;
import model.Role;
import service.RoleService;

/**
 *
 * @author HaShaN
 */
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao=new RoleDaoImpl();
    
    @Override
    public Role getById(int id) throws ClassNotFoundException, SQLException {
        return roleDao.getById(id);
    }
    
}
