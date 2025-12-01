/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline.dataakun;

/**
 *
 * @author aryop
 */
public class SessionKantin {
    
    private static AkunKantin currentKantin;

    public static void setCurrentUser(AkunKantin kantin) {
        currentKantin = kantin;
    }

    public static AkunKantin getCurrentKantin() {
        return currentKantin;
    }
    
}
