/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import enums.RoleEnum;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author HaShaN
 */
public class InitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        
        List<RoleEnum> roleList = new ArrayList<>();
        roleList.add(RoleEnum.CONTRACTOR);
        roleList.add(RoleEnum.SUPPLIER);
        
        servletContext.setAttribute("roleList", roleList);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

}
