/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Acer
 */
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class LaporanTransaksi {
    private int idLaporan;
    private String periode;
    private double totalTransaksi;
    private Date tanggalDibuat;
    private List<Transaksi> daftarTransaksi;
    
    public LaporanTransaksi(int idLaporan, String periode, double totalTransaksi, Date tanggalDibuat) {
        this.idLaporan = idLaporan;
        this.periode = periode;
        this.totalTransaksi = totalTransaksi;
        this.tanggalDibuat = tanggalDibuat;
        this.daftarTransaksi = new ArrayList<>();
    }
    
    public double hitungTotal() {
        double total = 0;
        for (Transaksi t : daftarTransaksi) {
            total += t.getJumlah();
        }
        return total;
    }
    
    public void exportPDF() {
        System.out.println("Exporting laporan ke PDF...");
    }
    
    public void exportExcel() {
        System.out.println("Exporting laporan ke Excel...");
    }
    
    // Getters and Setters
    public int getIdLaporan() { return idLaporan; }
    public void setIdLaporan(int idLaporan) { this.idLaporan = idLaporan; }
    public String getPeriode() { return periode; }
    public void setPeriode(String periode) { this.periode = periode; }
    public double getTotalTransaksi() { return totalTransaksi; }
    public void setTotalTransaksi(double totalTransaksi) { this.totalTransaksi = totalTransaksi; }
    public Date getTanggalDibuat() { return tanggalDibuat; }
    public void setTanggalDibuat(Date tanggalDibuat) { this.tanggalDibuat = tanggalDibuat; }
    public List<Transaksi> getDaftarTransaksi() { return daftarTransaksi; }
}
