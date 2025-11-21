/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline.tampilanuser;

import sistemkantinonline.tampilanuser.ItemKeranjang;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static final List<ItemKeranjang> items = new ArrayList<>();

    public static void tambahItem(ItemKeranjang itemBaru) {
//        // kalau nama sama, tambahkan jumlah saja
//        for (ItemKeranjang item : items) {
//            if (item.getNama().equals(itemBaru.getNama())) {
//                item.setJumlah(item.getJumlah() + itemBaru.getJumlah());
//                return;
//            }
//        }
//        items.add(itemBaru);

        for (ItemKeranjang item : items) {
            // Gabung hanya kalau NAMA & HARGA sama
            if (item.getNama().equals(itemBaru.getNama())
                    && item.getHarga() == itemBaru.getHarga()) {

                // tambah jumlah saja
                item.setJumlah(item.getJumlah() + itemBaru.getJumlah());
                // total otomatis benar kalau getTotal() = harga * jumlah
                return;
            }
        }

        // kalau belum ada item dengan nama+harga yang sama → tambah baris baru
        items.add(itemBaru);

    }

    public static List<ItemKeranjang> getItems() {
        return items;
    }

    public static void kosongkan() {
        items.clear();
    }

}
