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
public class DaftarAkunUser {

    private static final List<AkunUser> daftarAkunUser = new ArrayList<>();

    // data dummy awal
    static {
        daftarAkunUser.add(new AkunUser("a", "a", "Aryo", "0821", "User", "Teknik", "Teknik Elektro", "Teknik Informatika", "Jl. Bangau Sakti"));
    }

    public static void tambahAkun(String userName, String password, String nama, String noHp, String kategori, String fakultas, String jurusan, String prodi,String alamat) {
        daftarAkunUser.add(new AkunUser(userName, password, nama, noHp, kategori, fakultas, jurusan, prodi, alamat));
    }

    public static AkunUser cekLogin(String userName, String password) {
        for (AkunUser a : daftarAkunUser) {
            if (a.getUserName().equalsIgnoreCase(userName)
                    && a.getPassword().equals(password)) {
                return a;
            }
        }
        return null;
    }

    public static AkunUser cariByUsername(String userName) {
        for (AkunUser a : daftarAkunUser) {
            if (a.getUserName().equalsIgnoreCase(userName)) {
                return a;
            }
        }
        return null;
    }

}
