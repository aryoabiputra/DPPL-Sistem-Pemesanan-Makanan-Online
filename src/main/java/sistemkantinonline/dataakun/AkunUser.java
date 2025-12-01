/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline.dataakun;

/**
 *
 * @author aryop
 */
public class AkunUser extends Akun {

    private String fakultas;
    private String jurusan;
    private String prodi;
    private String alamatDefault;

    public AkunUser(String userName, String password, String nama, String noHp, String kategori,  String fakultas, String jurusan, String prodi, String alamat) {

        super(userName, password, nama, noHp, kategori);

        this.fakultas = fakultas;
        this.jurusan = jurusan;
        this.prodi = prodi;
        this.alamatDefault = alamat;
    }

    public String getFakultas() {
        return fakultas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getProdi() {
        return prodi;
    }
    
    public String getAlamatDefault(){
        return alamatDefault;
    }
  
    public void setFakultas(String fakultas){
        this.fakultas = fakultas;
    }
    
    public void setJurusan(String jurusan){
        this.jurusan = jurusan;
    }
    
    public void setProdi(String prodi){
        this.prodi = prodi;
    }
    
    public void setAlamatDefault(String alamat){
        this.alamatDefault = alamat;
    }

}
