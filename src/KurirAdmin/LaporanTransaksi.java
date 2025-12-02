import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LaporanTransaksi {
    private int idLaporan;
    private String periode;
    private double totalTransaksi;
    private Date tanggalDibuat;
    private List<Transaksi> transaksiList;

    public LaporanTransaksi(int idLaporan, String periode, Date tanggalDibuat) {
        this.idLaporan = idLaporan;
        this.periode = periode;
        this.tanggalDibuat = tanggalDibuat;
        this.transaksiList = new ArrayList<>();
        this.totalTransaksi = 0;
    }

    public double hitungTotal() {
        totalTransaksi = 0;
        for (Transaksi t : transaksiList) {
            totalTransaksi += t.getJumlah();
        }
        return totalTransaksi;
    }

    public void exportPDF() {
        System.out.println("Exporting laporan " + idLaporan + " to PDF...");
    }

    public void exportExcel() {
        System.out.println("Exporting laporan " + idLaporan + " to Excel...");
    }

    public void tambahTransaksi(Transaksi transaksi) {
        transaksiList.add(transaksi);
        hitungTotal();
    }

    public int getIdLaporan() { 
        return idLaporan; 
    }
    
    public String getPeriode() { 
        return periode; 
    }
    
    public double getTotalTransaksi() { 
        return totalTransaksi; 
    }
    
    public Date getTanggalDibuat() {
        return tanggalDibuat;
    }
    
    public List<Transaksi> getTransaksiList() { 
        return transaksiList; 
    }
}