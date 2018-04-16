/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sonal
 */
public class AdminUser extends User {

    public AdminUser(){
        this("admin", "admin");
    }

    public AdminUser(String username, String password){
        super(username, password);
    }

        public String getPermission(){
        return "Elevated";
    }
}