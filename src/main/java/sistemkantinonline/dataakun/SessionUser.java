/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline.dataakun;

/**
 *
 * @author aryop
 */
public class SessionUser {
    
    private static AkunUser currentUser;

    public static void setCurrentUser(AkunUser user) {
        currentUser = user;
    }

    public static AkunUser getCurrentUser() {
        return currentUser;
    }
    
}
