/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline.dataakun;

/**
 *
 * @author aryop
 */
public class Akun {

    private String userName;
    private String password;
    private String nama;
    private String noHp;
    private String kategori;

    public Akun(String userName, String password, String nama, String noHp, String kategori) {
        this.userName = userName;
        this.password = password;
        this.nama = nama;
        this.noHp = noHp;
        this.kategori = kategori;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getKategori() {
        return kategori;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public void setNoHp(String noHp){
        this.noHp = noHp;
    }

}
