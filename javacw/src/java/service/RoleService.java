/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import model.Role;

/**
 *
 * @author HaShaN
 */
public interface RoleService {
    Role getById(int id)throws ClassNotFoundException,SQLException;
}
