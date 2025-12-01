package sistemkantinonline.tampilanuser;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Acer
 */
//import java.util.Date;
import java.util.List;

public class Pesanan {

    private int noPesanan;
    private String alamat;
    private String metode;
    private int total;
    private List<PesananItem> items;

    public Pesanan(int noPesanan, String alamat, String metode,
            int total, List<PesananItem> items) {
        this.noPesanan = noPesanan;
        this.alamat = alamat;
        this.metode = metode;
        this.total = total;
        this.items = items;
    }

    public int getNoPesanan() {
        return noPesanan;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getMetode() {
        return metode;
    }

    public int getTotal() {
        return total;
    }

    public List<PesananItem> getItems() {
        return items;
    }
}
