/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline;

/**
 *
 * @author aryop
 */
public class Menu {

    private String nama;
    private double harga;
    private String pathGambar;

    public Menu(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public Menu(String nama, double harga, String pathGambar) {
        this.nama = nama;
        this.harga = harga;
        this.pathGambar = pathGambar;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String getPathGambar() {
        return pathGambar;
    }

}
