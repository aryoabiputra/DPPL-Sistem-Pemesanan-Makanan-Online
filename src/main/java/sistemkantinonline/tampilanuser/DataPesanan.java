/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline.tampilanuser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataPesanan {

    private static List<Pesanan> riwayat = new ArrayList<>();

    public static void tambahPesanan(Pesanan p) {
        riwayat.add(p);
    }

    public static List<Pesanan> getRiwayat() {
        return riwayat;
    }
}


//Berfungsi sebagai pengganti database untuk menyimpan data pesanan
//public class DataPesanan {
//
//    // 1 pesanan = 1 objek DataPesanan
//    private final String noPesanan;
//    private final List<ItemKeranjang> items;
//    private final String metodePembayaran;
//    private final int total;
//
//    // riwayat semua pesanan
//    private static final List<DataPesanan> RIWAYAT = new ArrayList<>();
//
//    public DataPesanan(String noPesanan,List<ItemKeranjang> items,String metodePembayaran,int total) {
//        this.noPesanan = noPesanan;
//        this.items = items;
//        this.metodePembayaran = metodePembayaran;
//        this.total = total;
//    }
//
//    // dipanggil dari checkoutfixed saat klik "Pesan"
//    public static void tambahPesanan(List<ItemKeranjang> items,
//            String metodePembayaran,
//            int total,
//            String noPesanan) {
//
//        // copy dulu supaya tidak ikut kehapus ketika Cart.kosongkan()
//        List<ItemKeranjang> copy = new ArrayList<>();
//        for (ItemKeranjang item : items) {
//            copy.add(new ItemKeranjang(
//                    item.getNama(),
//                    item.getHarga(),
//                    item.getJumlah()
//            ));
//        }
//
//        DataPesanan pesanan = new DataPesanan(noPesanan, copy, metodePembayaran, total);
//        RIWAYAT.add(pesanan);
//    }
//
//    public static List<DataPesanan> getRiwayat() {
//        return Collections.unmodifiableList(RIWAYAT);
//    }
//
//    public static DataPesanan getTerakhir() {
//        if (RIWAYAT.isEmpty()) {
//            return null;
//        }
//        return RIWAYAT.get(RIWAYAT.size() - 1);
//    }
//
//    // getter
//    public String getNoPesanan() {
//        return noPesanan;
//    }
//
//    public List<ItemKeranjang> getItems() {
//        return Collections.unmodifiableList(items);
//    }
//
//    public String getMetodePembayaran() {
//        return metodePembayaran;
//    }
//
//    public int getTotal() {
//        return total;
//    }
//}
