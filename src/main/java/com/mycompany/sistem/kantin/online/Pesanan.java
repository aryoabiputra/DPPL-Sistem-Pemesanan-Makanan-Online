/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Acer
 */
import java.util.Date;

public class Pesanan {
    private int idPesanan;
    private String alamatTujuan;
    private Date tanggalPesan;
    
    public Pesanan(int idPesanan, String alamatTujuan, Date tanggalPesan) {
        this.idPesanan = idPesanan;
        this.alamatTujuan = alamatTujuan;
        this.tanggalPesan = tanggalPesan;
    }
    
    public void tampilkanDetail() {
        System.out.println("ID: " + idPesanan);
        System.out.println("Alamat: " + alamatTujuan);
        System.out.println("Tanggal: " + tanggalPesan);
    }
    
    // Getters and Setters
    public int getIdPesanan() { return idPesanan; }
    public void setIdPesanan(int idPesanan) { this.idPesanan = idPesanan; }
    public String getAlamatTujuan() { return alamatTujuan; }
    public void setAlamatTujuan(String alamatTujuan) { this.alamatTujuan = alamatTujuan; }
    public Date getTanggalPesan() { return tanggalPesan; }
    public void setTanggalPesan(Date tanggalPesan) { this.tanggalPesan = tanggalPesan; }
}