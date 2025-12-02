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

    public int getIdTransaksi() { 
        return idTransaksi; 
    }
    
    public Date getTanggal() { 
        return tanggal; 
    }
    
    public double getJumlah() { 
        return jumlah; 
    }
    
    public String getMetodePembayaran() { 
        return metodePembayaran; 
    }
}