package sistemkantinonline.tampilankurir;

import java.util.ArrayList;
import java.util.List;

public class Kurir {
    private int idKurir;
    private String nama;
    private List<Pesanan> daftarPesanan;

    public Kurir(int idKurir, String nama) {
        this.idKurir = idKurir;
        this.nama = nama;
        this.daftarPesanan = new ArrayList<>();
    }

    public void melacakPesanan(int idPesanan) {
        for (Pesanan p : daftarPesanan) {
            if (p.getIdPesanan() == idPesanan) {
                System.out.println("=== Status Pesanan ===");
                p.tampilkanDetail();
                return;
            }
        }
        System.out.println("Pesanan dengan ID " + idPesanan + " tidak ditemukan");
    }

    public void updateStatusPesanan(int idPesanan, String statusBaru) {
        for (Pesanan p : daftarPesanan) {
            if (p.getIdPesanan() == idPesanan) {
                p.setStatus(statusBaru);
                System.out.println("Status pesanan #" + idPesanan + " berhasil diupdate menjadi: " + statusBaru);
                return;
            }
        }
        System.out.println("Pesanan tidak ditemukan");
    }

    public void tambahPesanan(Pesanan pesanan) {
        daftarPesanan.add(pesanan);
    }

    public void hapusPesanan(int idPesanan) {
        daftarPesanan.removeIf(p -> p.getIdPesanan() == idPesanan);
    }

    public List<Pesanan> getDaftarPesanan() {
        return daftarPesanan;
    }

    public String getNama() {
        return nama;
    }

    public int getIdKurir() {
        return idKurir;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}