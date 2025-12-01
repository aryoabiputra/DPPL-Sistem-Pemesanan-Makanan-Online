/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline.dataakun;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aryop
 */
public class DaftarAkunKantin {

    private static final List<AkunKantin> daftarAkunKantin = new ArrayList<>();

    // data dummy awal
    static {
        daftarAkunKantin.add(new AkunKantin("k", "k", "Aryo", "0821", "Kantin"));
    }

    public static void tambahAkun(String userName, String password, String nama, String noHp, String kategori) {
        daftarAkunKantin.add(new AkunKantin(userName, password, nama, noHp, kategori));
    }

    public static AkunKantin cekLogin(String userName, String password) {
        for (AkunKantin a : daftarAkunKantin) {
            if (a.getUserName().equalsIgnoreCase(userName)
                    && a.getPassword().equals(password)) {
                return a;
            }
        }
        return null;
    }

    public static AkunKantin cariByUsername(String userName) {
        for (AkunKantin a : daftarAkunKantin) {
            if (a.getUserName().equalsIgnoreCase(userName)) {
                return a;
            }
        }
        return null;
    }

}
