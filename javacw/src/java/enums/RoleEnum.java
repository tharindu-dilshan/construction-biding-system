/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package enums;

/**
 *
 * @author HaShaN
 */
public enum RoleEnum {
    ADMIN(1), SUPPLIER(2), CONTRACTOR(3);
    private int roleId;
    
    private RoleEnum(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
    
}
