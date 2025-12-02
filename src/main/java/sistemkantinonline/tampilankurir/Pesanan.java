package sistemkantinonline.tampilankurir;

import java.util.Date;

public class Pesanan {
    private int idPesanan;
    private String alamatTujuan;
    private Date tanggalPesan;
    private String status;

    public Pesanan(int idPesanan, String alamatTujuan, Date tanggalPesan) {
        this.idPesanan = idPesanan;
        this.alamatTujuan = alamatTujuan;
        this.tanggalPesan = tanggalPesan;
        this.status = "Pending";
    }

    public void tampilkanDetail() {
        System.out.println("ID: " + idPesanan);
        System.out.println("Alamat: " + alamatTujuan);
        System.out.println("Tanggal: " + tanggalPesan);
        System.out.println("Status: " + status);
    }

    public int getIdPesanan() { 
        return idPesanan; 
    }
    
    public String getAlamatTujuan() { 
        return alamatTujuan; 
    }
    
    public Date getTanggalPesan() { 
        return tanggalPesan; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }

    public void setAlamatTujuan(String alamatTujuan) {
        this.alamatTujuan = alamatTujuan;
    }
}