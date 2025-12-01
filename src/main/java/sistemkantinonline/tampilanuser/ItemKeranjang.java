/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline.tampilanuser;

/**
 *
 * @author aryop
 */
public class ItemKeranjang {

    private String nama;
    private int harga;
    private int jumlah;

    public ItemKeranjang(String nama, int harga, int jumlah) {
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int j) {
        jumlah = j;
    }

    public int getTotal() {
        return harga * jumlah;
    }

    public PesananItem toPesananItem() {
        return new PesananItem(nama, harga, jumlah);
    }
}
