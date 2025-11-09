/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Acer
 */
import java.util.Date;

public class Transaksi {
    private int idTransaksi;
    private Date tanggal;
    private double jumlah;
    private String metodePembayaran;
    
    public Transaksi(int idTransaksi, Date tanggal, double jumlah, String metodePembayaran) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.metodePembayaran = metodePembayaran;
    }
    
    public void tampilkanDetail() {
        System.out.println("ID Transaksi: " + idTransaksi);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Jumlah: Rp " + jumlah);
        System.out.println("Metode: " + metodePembayaran);
    }
    
    // Getters and Setters
    public int getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(int idTransaksi) { this.idTransaksi = idTransaksi; }
    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
    public double getJumlah() { return jumlah; }
    public void setJumlah(double jumlah) { this.jumlah = jumlah; }
    public String getMetodePembayaran() { return metodePembayaran; }
    public void setMetodePembayaran(String metodePembayaran) { this.metodePembayaran = metodePembayaran; }
}
