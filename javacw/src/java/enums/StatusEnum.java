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
public enum StatusEnum {
    INACTIVE(0), ACTIVE(1), DELETED(2);
    
    private short statusId;

    private StatusEnum(int statusId) {
        this.statusId = (short)statusId;
    }

    public short getStatusId() {
        return statusId;
    }
}
