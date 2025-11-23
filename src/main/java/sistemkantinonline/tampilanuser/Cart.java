/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline.tampilanuser;

import sistemkantinonline.tampilanuser.ItemKeranjang;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    

    private static List<ItemKeranjang> items = new ArrayList<>();

    public static List<ItemKeranjang> getItems() {
        return items;
    }

    public static void tambahItem(ItemKeranjang itemBaru) {
        for (ItemKeranjang i : items) {
            if (i.getNama().equals(itemBaru.getNama())
                    && i.getHarga() == itemBaru.getHarga()) {
                i.setJumlah(i.getJumlah() + itemBaru.getJumlah());
                return;
            }
        }
        items.add(itemBaru);
    }

    public static void kosongkan() {
        items.clear();
    }
}
