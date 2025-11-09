/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Acer
 */
import java.util.Date;

public class Pengiriman {
    private int idPengiriman;
    private Date tanggalKirim;
    private String status;
    
    public Pengiriman(int idPengiriman, Date tanggalKirim, String status) {
        this.idPengiriman = idPengiriman;
        this.tanggalKirim = tanggalKirim;
        this.status = status;
    }
    
    public void updateStatus(String statusBaru) {
        this.status = statusBaru;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    // Getters and Setters
    public int getIdPengiriman() { return idPengiriman; }
    public void setIdPengiriman(int idPengiriman) { this.idPengiriman = idPengiriman; }
    public Date getTanggalKirim() { return tanggalKirim; }
    public void setTanggalKirim(Date tanggalKirim) { this.tanggalKirim = tanggalKirim; }
    public void setStatus(String status) { this.status = status; }
}

