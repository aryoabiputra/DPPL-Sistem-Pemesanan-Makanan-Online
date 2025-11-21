/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemkantinonline;

/**
 *
 * @author aryop
 */
public class BuatNomorPesanan {
    private static int counter = 0;

    public static synchronized String next() {
        counter++;
        return String.format("A%03d", counter); // A001, A002, A003, ...
    }
}
