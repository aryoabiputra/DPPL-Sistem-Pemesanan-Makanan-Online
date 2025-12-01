/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemkantinonline.tampilankantin;

import java.awt.*;
import javax.swing.*;
import sistemkantinonline.tampilankantin.DaftarPesananKantin;
import sistemkantinonline.tampilankantin.LaporanPenjualan;
import sistemkantinonline.tampilankantin.ManajemenMenu;
import sistemkantinonline.tampilankantin.EditProfilKantin;
/**
 *
 * @author riald
 */
public class HomeKantin extends javax.swing.JFrame {
    
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    private JButton btnMenu, btnPesanan, btnLaporan;
        private void updateSidebarProfile() {
        String currentNama = "Rialdi";
        String currentKantin = "Kantin MBG";
        
        this.setTitle("Dashboard Pemilik Kantin - " + currentNama);
    }

    /**
     * Creates new form HomeKantin
     */
    public HomeKantin() {
        setTitle("Dashboard Pemilik Kantin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        initComponents();
        setupCustomComponents();
        setLocationRelativeTo(null);
        revalidate();
        repaint();
    }
    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 40));
        button.setMinimumSize(new Dimension(180, 40));
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        return button;
    }

    private void setupCustomComponents() {
        getContentPane().setLayout(new BorderLayout());
        
        sidebarPanel = new JPanel();
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        getContentPane().removeAll();
        
        contentPanel.setLayout(cardLayout);
        
        contentPanel.add(new ManajemenMenu(), "MENU"); 
        contentPanel.add(new DaftarPesananKantin(), "PESANAN");
        contentPanel.add(new LaporanPenjualan(), "LAPORAN");
        
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(0, 150, 255)); 
        sidebarPanel.setPreferredSize(new Dimension(200, 700));
        
        btnMenu = createNavButton("Manajemen Menu");
        btnPesanan = createNavButton("Daftar Pesanan");
        btnLaporan = createNavButton("Laporan Penjualan");
        
        JButton btnProfileDisplay = createNavButton("Aryo - Kantin"); 
    
        // Atur font/gaya agar terlihat seperti judul
        btnProfileDisplay.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnProfileDisplay.setBackground(new Color(0, 150, 255)); // Warna sidebar
        btnProfileDisplay.setForeground(Color.WHITE); // Warna teks
        btnProfileDisplay.setBorderPainted(false); // Hapus border

        // Tambahkan ke Sidebar, di bagian paling atas
        sidebarPanel.add(Box.createVerticalStrut(10)); // Spasi atas
        sidebarPanel.add(btnProfileDisplay);
        sidebarPanel.add(Box.createVerticalStrut(20)); // Spasi ke tombol menu
        
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(btnMenu);
        sidebarPanel.add(btnPesanan);
        sidebarPanel.add(btnLaporan);
        sidebarPanel.add(Box.createVerticalGlue());
        
        JButton btnLogout = createNavButton("Logout");
        sidebarPanel.add(Box.createVerticalGlue());
        sidebarPanel.add(btnLogout);
        sidebarPanel.add(Box.createVerticalStrut(20));
        btnLogout.addActionListener(e -> {
            this.dispose();
            new sistemkantinonline.Login().setVisible(true);
        });
        JButton btnProfil = createNavButton("Edit Profil");
        sidebarPanel.add(btnProfil);
        btnProfil.addActionListener(e -> {
            new sistemkantinonline.tampilankantin.EditProfilKantin(this, true).setVisible(true);
        });
        
        JButton btnEditProfil = createNavButton("Edit Profil");
        btnEditProfil.addActionListener(e -> {
            new sistemkantinonline.tampilankantin.EditProfilKantin(this, true).setVisible(true);
        });
        
        btnMenu.addActionListener(e -> {
            cardLayout.show(contentPanel, "MENU");
        });
        
        btnPesanan.addActionListener(e -> {
            cardLayout.show(contentPanel, "PESANAN");
        });

        btnLaporan.addActionListener(e -> {
            cardLayout.show(contentPanel, "LAPORAN");
        });
        
        getContentPane().add(sidebarPanel, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        cardLayout.show(contentPanel, "MENU");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeKantin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeKantin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeKantin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeKantin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeKantin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
