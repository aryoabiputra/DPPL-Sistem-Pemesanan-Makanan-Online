package sistemkantinonline.tampilankurir;

import java.util.Date;

public class Pengiriman {
    private int idPengiriman;
    private Date tanggalKirim;
    private String status;

    public Pengiriman(int idPengiriman, Date tanggalKirim) {
        this.idPengiriman = idPengiriman;
        this.tanggalKirim = tanggalKirim;
        this.status = "Dalam Proses";
    }

    public void updateStatus(String statusBaru) {
        this.status = statusBaru;
    }

    public String getStatus() {
        return status;
    }

    public int getIdPengiriman() { 
        return idPengiriman; 
    }
    
    public Date getTanggalKirim() { 
        return tanggalKirim; 
    }
}