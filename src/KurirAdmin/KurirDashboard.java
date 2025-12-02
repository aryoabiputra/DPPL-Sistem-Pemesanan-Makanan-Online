import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class KurirDashboard extends JFrame {
    private Kurir kurir;
    private JPanel contentPanel;
    private JTable pesananTable;
    private JTable pengirimanTable;
    private DefaultTableModel pesananModel;
    private DefaultTableModel pengirimanModel;
    private JButton currentMenuButton = null;

    public KurirDashboard(Kurir kurir) {
        this.kurir = kurir;
        initComponents();
        loadDummyData();
    }

    private void initComponents() {
        setTitle("Dashboard Kurir - " + kurir.getNama());
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Sidebar
        JPanel sidebar = createSidebar();
        mainPanel.add(sidebar, BorderLayout.WEST);

        // Content Area
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(240, 242, 245));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Show dashboard by default
        showDashboard();

        add(mainPanel);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(51, 153, 255)); // Warna biru dari Login
        sidebar.setPreferredSize(new Dimension(250, 700));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Profile Section
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(new Color(51, 153, 255)); // Warna biru dari Login
        profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 30, 20));

        JLabel nameLabel = new JLabel(kurir.getNama());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel roleLabel = new JLabel("Kurir");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        roleLabel.setForeground(new Color(230, 240, 255)); // Warna lebih terang untuk role
        roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        profilePanel.add(nameLabel);
        profilePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        profilePanel.add(roleLabel);

        sidebar.add(profilePanel);
        sidebar.add(createSeparator());

        // Menu Buttons
        sidebar.add(createMenuButton("Dashboard", "dashboard"));
        sidebar.add(createMenuButton("Daftar Pesanan", "pesanan"));
        sidebar.add(createMenuButton("Riwayat Pengiriman", "pengiriman"));
        sidebar.add(createMenuButton("Profil", "profil"));
        
        sidebar.add(Box.createVerticalGlue());
        
        // Logout Button
        JButton logoutButton = createMenuButton("Keluar", "logout");
        logoutButton.setBackground(new Color(220, 38, 38));
        sidebar.add(logoutButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));

        return sidebar;
    }

    private JButton createMenuButton(String text, String action) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(250, 45));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 153, 255)); // Warna biru dari Login
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (currentMenuButton != button) {
                    button.setBackground(new Color(30, 120, 220)); // Hover color lebih gelap
                }
            }
            public void mouseExited(MouseEvent e) {
                if (currentMenuButton != button) {
                    button.setBackground(new Color(51, 153, 255));
                }
            }
        });

        button.addActionListener(e -> {
            if (currentMenuButton != null) {
                currentMenuButton.setBackground(new Color(51, 153, 255));
            }
            
            switch (action) {
                case "dashboard":
                    showDashboard();
                    currentMenuButton = button;
                    button.setBackground(new Color(20, 100, 200)); // Active color
                    break;
                case "pesanan":
                    showPesanan();
                    currentMenuButton = button;
                    button.setBackground(new Color(20, 100, 200)); // Active color
                    break;
                case "pengiriman":
                    showPengiriman();
                    currentMenuButton = button;
                    button.setBackground(new Color(20, 100, 200)); // Active color
                    break;
                case "profil":
                    showProfil();
                    currentMenuButton = button;
                    button.setBackground(new Color(20, 100, 200)); // Active color
                    break;
                case "logout":
                    int confirm = JOptionPane.showConfirmDialog(this, 
                        "Apakah Anda yakin ingin keluar?", 
                        "Konfirmasi", 
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                    break;
            }
        });

        return button;
    }

    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(250, 1));
        separator.setForeground(new Color(100, 180, 255)); // Warna separator lebih terang
        return separator;
    }

    private void showDashboard() {
        contentPanel.removeAll();
        
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(new Color(240, 242, 245));

        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Stats Cards
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        statsPanel.setBackground(new Color(240, 242, 245));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        int totalPesanan = kurir.getDaftarPesanan().size();
        int pending = 0, dalamPengiriman = 0, selesai = 0;
        
        for (Pesanan p : kurir.getDaftarPesanan()) {
            String status = p.getStatus();
            if (status.equals("Pending")) pending++;
            else if (status.equals("Dalam Pengiriman")) dalamPengiriman++;
            else if (status.equals("Selesai")) selesai++;
        }

        statsPanel.add(createStatCard("Total Pesanan", String.valueOf(totalPesanan), new Color(59, 130, 246)));
        statsPanel.add(createStatCard("Dalam Pengiriman", String.valueOf(dalamPengiriman), new Color(234, 179, 8)));
        statsPanel.add(createStatCard("Selesai", String.valueOf(selesai), new Color(34, 197, 94)));

        // Welcome Message
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel welcomeLabel = new JLabel("Selamat Datang, " + kurir.getNama() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel infoLabel = new JLabel("Gunakan menu di samping untuk mengelola pesanan dan pengiriman Anda.");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoLabel.setForeground(new Color(100, 116, 139));
        infoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(infoLabel);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 242, 245));
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(statsPanel, BorderLayout.CENTER);

        dashboardPanel.add(topPanel, BorderLayout.NORTH);
        dashboardPanel.add(welcomePanel, BorderLayout.CENTER);

        contentPanel.add(dashboardPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        valueLabel.setForeground(Color.WHITE);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private void showPesanan() {
        contentPanel.removeAll();
        
        JPanel pesananPanel = new JPanel(new BorderLayout());
        pesananPanel.setBackground(new Color(240, 242, 245));

        JLabel titleLabel = new JLabel("Daftar Pesanan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel tablePanel = new JPanel(new BorderLayout(0, 10));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(51, 153, 255)); // Gunakan warna biru yang sama
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.addActionListener(e -> loadPesananData());

        String[] columns = {"ID", "Alamat Tujuan", "Tanggal", "Status"};
        pesananModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        pesananTable = new JTable(pesananModel);
        pesananTable.setRowHeight(40);
        pesananTable.setFont(new Font("Arial", Font.PLAIN, 13));
        pesananTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        pesananTable.getTableHeader().setBackground(new Color(249, 250, 251));
        pesananTable.setSelectionBackground(new Color(199, 210, 254));
        
        pesananTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = pesananTable.getSelectedRow();
                    if (row != -1) {
                        updateStatusDialog(row);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(pesananTable);
        
        JLabel infoLabel = new JLabel("Double-click untuk update status pesanan");
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        infoLabel.setForeground(new Color(100, 100, 100));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(new JLabel(""), BorderLayout.WEST);
        headerPanel.add(refreshButton, BorderLayout.EAST);

        tablePanel.add(headerPanel, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(infoLabel, BorderLayout.SOUTH);

        pesananPanel.add(titleLabel, BorderLayout.NORTH);
        pesananPanel.add(tablePanel, BorderLayout.CENTER);

        loadPesananData();

        contentPanel.add(pesananPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showPengiriman() {
        contentPanel.removeAll();
        
        JPanel pengirimanPanel = new JPanel(new BorderLayout());
        pengirimanPanel.setBackground(new Color(240, 242, 245));

        JLabel titleLabel = new JLabel("Riwayat Pengiriman");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        String[] columns = {"ID", "Tanggal Kirim", "Status"};
        pengirimanModel = new DefaultTableModel(columns, 0);
        pengirimanTable = new JTable(pengirimanModel);
        pengirimanTable.setRowHeight(40);
        pengirimanTable.setFont(new Font("Arial", Font.PLAIN, 13));
        pengirimanTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        pengirimanTable.getTableHeader().setBackground(new Color(249, 250, 251));
        pengirimanTable.setSelectionBackground(new Color(187, 247, 208));

        JScrollPane scrollPane = new JScrollPane(pengirimanTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        pengirimanPanel.add(titleLabel, BorderLayout.NORTH);
        pengirimanPanel.add(tablePanel, BorderLayout.CENTER);

        loadPengirimanData();

        contentPanel.add(pengirimanPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showProfil() {
        contentPanel.removeAll();
        
        JPanel profilPanel = new JPanel(new BorderLayout());
        profilPanel.setBackground(new Color(240, 242, 245));

        JLabel titleLabel = new JLabel("Profil Kurir");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        addProfilField(infoPanel, "ID Kurir", String.valueOf(kurir.getIdKurir()));
        addProfilField(infoPanel, "Nama", kurir.getNama());
        addProfilField(infoPanel, "Total Pesanan", String.valueOf(kurir.getDaftarPesanan().size()));

        profilPanel.add(titleLabel, BorderLayout.NORTH);
        profilPanel.add(infoPanel, BorderLayout.CENTER);

        contentPanel.add(profilPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void addProfilField(JPanel panel, String label, String value) {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Arial", Font.BOLD, 14));
        labelComponent.setForeground(new Color(100, 116, 139));

        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("Arial", Font.PLAIN, 16));

        fieldPanel.add(labelComponent, BorderLayout.NORTH);
        fieldPanel.add(valueComponent, BorderLayout.CENTER);

        panel.add(fieldPanel);
    }

    private void updateStatusDialog(int row) {
        int idPesanan = (int) pesananModel.getValueAt(row, 0);
        String currentStatus = (String) pesananModel.getValueAt(row, 3);
        
        String[] statusOptions = {"Pending", "Dalam Pengiriman", "Selesai", "Dibatalkan"};
        String newStatus = (String) JOptionPane.showInputDialog(
            this,
            "Pilih status baru untuk pesanan #" + idPesanan,
            "Update Status",
            JOptionPane.QUESTION_MESSAGE,
            null,
            statusOptions,
            currentStatus
        );
        
        if (newStatus != null && !newStatus.equals(currentStatus)) {
            kurir.updateStatusPesanan(idPesanan, newStatus);
            loadPesananData();
            showDashboard(); // Refresh dashboard stats
            JOptionPane.showMessageDialog(this, 
                "Status pesanan berhasil diupdate!", 
                "Sukses", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void loadPesananData() {
        if (pesananModel != null) {
            pesananModel.setRowCount(0);
            for (Pesanan p : kurir.getDaftarPesanan()) {
                pesananModel.addRow(new Object[]{
                    p.getIdPesanan(),
                    p.getAlamatTujuan(),
                    p.getTanggalPesan(),
                    p.getStatus()
                });
            }
        }
    }

    private void loadPengirimanData() {
        Pengiriman pg1 = new Pengiriman(1, new Date());
        Pengiriman pg2 = new Pengiriman(2, new Date());
        pg2.updateStatus("Selesai");
        Pengiriman pg3 = new Pengiriman(3, new Date());
        pg3.updateStatus("Dalam Proses");

        pengirimanModel.addRow(new Object[]{pg1.getIdPengiriman(), pg1.getTanggalKirim(), pg1.getStatus()});
        pengirimanModel.addRow(new Object[]{pg2.getIdPengiriman(), pg2.getTanggalKirim(), pg2.getStatus()});
        pengirimanModel.addRow(new Object[]{pg3.getIdPengiriman(), pg3.getTanggalKirim(), pg3.getStatus()});
    }

    private void loadDummyData() {
        Pesanan p1 = new Pesanan(1, "Gedung C Lt.2, Jurusan Teknik Elektro", new Date());
        Pesanan p2 = new Pesanan(2, "Laboratorium Teknik Elektro dan Informatika", new Date());
        p2.setStatus("Dalam Pengiriman");
        Pesanan p3 = new Pesanan(3, "Perpustakaan, Lt.1", new Date());
        p3.setStatus("Selesai");

        kurir.tambahPesanan(p1);
        kurir.tambahPesanan(p2);
        kurir.tambahPesanan(p3);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Kurir kurir = new Kurir(1, "Kurir A");
            new KurirDashboard(kurir).setVisible(true);
        });
    }
}