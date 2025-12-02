import java.util.ArrayList;
import java.util.List;

public class AdminKantin {
    private int idAdmin;
    private String nama;
    private String email;
    private List<LaporanTransaksi> laporanList;

    public AdminKantin(int idAdmin, String nama, String email) {
        this.idAdmin = idAdmin;
        this.nama = nama;
        this.email = email;
        this.laporanList = new ArrayList<>();
    }

    public void kelolaLaporan() {
        System.out.println("Mengelola laporan transaksi");
    }

    public void tambahLaporan(LaporanTransaksi laporan) {
        laporanList.add(laporan);
    }

    public void hapusLaporan(int idLaporan) {
        laporanList.removeIf(l -> l.getIdLaporan() == idLaporan);
    }

    public LaporanTransaksi cariLaporan(int idLaporan) {
        for (LaporanTransaksi laporan : laporanList) {
            if (laporan.getIdLaporan() == idLaporan) {
                return laporan;
            }
        }
        return null;
    }

    public List<LaporanTransaksi> getLaporanList() {
        return laporanList;
    }

    public String getNama() { 
        return nama; 
    }
    
    public int getIdAdmin() { 
        return idAdmin; 
    }
    
    public String getEmail() { 
        return email; 
    }
}