/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

import java.sql.SQLException;
import model.Role;

/**
 *
 * @author HaShaN
 */
public interface RoleDao {
    public final String GET_BY_ID_SQL = "SELECT * from role WHERE id=?";
    Role getById(int id)throws ClassNotFoundException,SQLException;
}
