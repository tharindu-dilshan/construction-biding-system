/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adapter;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author HaShaN
 */
public class AddableHttpRequest extends HttpServletRequestWrapper {

   private HashMap<String, String> params = new HashMap();

    public AddableHttpRequest(HttpServletRequest request) {
        super(request);
    }

   @Override
   public String getParameter(String name) {
           // if we added one with the given name, return that one
        if ( params.get( name ) != null ) {
            return params.get( name );
        } else {
            // otherwise return what's in the original request
            return super.getParameter(name);
        }
   }

   public void addParameter( String name, String value ) {
           params.put( name, value );
   }
}
