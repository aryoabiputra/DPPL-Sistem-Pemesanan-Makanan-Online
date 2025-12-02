import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class AdminDashboard extends JFrame {
    private AdminKantin admin;
    private JPanel contentPanel;
    private JTable laporanTable;
    private DefaultTableModel laporanModel;
    private JButton currentMenuButton = null;

    public AdminDashboard(AdminKantin admin) {
        this.admin = admin;
        initComponents();
        loadDummyData();
    }

    private void initComponents() {
        setTitle("Dashboard Admin - " + admin.getNama());
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

        JLabel nameLabel = new JLabel(admin.getNama());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel roleLabel = new JLabel("Admin Kantin");
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
        sidebar.add(createMenuButton("Kelola Laporan", "laporan"));
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
                case "laporan":
                    showLaporan();
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

        int totalLaporan = admin.getLaporanList().size();
        double totalTransaksi = 0;
        int totalItem = 0;
        
        for (LaporanTransaksi laporan : admin.getLaporanList()) {
            totalTransaksi += laporan.getTotalTransaksi();
            totalItem += laporan.getTransaksiList().size();
        }

        statsPanel.add(createStatCard("Total Laporan", String.valueOf(totalLaporan), new Color(99, 102, 241)));
        statsPanel.add(createStatCard("Total Transaksi", "Rp " + String.format("%,.0f", totalTransaksi), new Color(34, 197, 94)));
        statsPanel.add(createStatCard("Total Item", String.valueOf(totalItem), new Color(234, 179, 8)));

        // Welcome Message
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel welcomeLabel = new JLabel("Selamat Datang, " + admin.getNama() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel infoLabel = new JLabel("Gunakan menu di samping untuk mengelola laporan transaksi.");
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
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setForeground(Color.WHITE);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private void showLaporan() {
        contentPanel.removeAll();
        
        JPanel laporanPanel = new JPanel(new BorderLayout());
        laporanPanel.setBackground(new Color(240, 242, 245));

        JLabel titleLabel = new JLabel("Kelola Laporan Transaksi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel tablePanel = new JPanel(new BorderLayout(0, 10));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Header with buttons
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        headerPanel.setBackground(Color.WHITE);
        
        JButton tambahButton = new JButton("Tambah Laporan");
        tambahButton.setBackground(new Color(34, 197, 94));
        tambahButton.setForeground(Color.WHITE);
        tambahButton.setFocusPainted(false);
        tambahButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tambahButton.addActionListener(e -> tambahLaporanDialog());
        
        JButton hapusButton = new JButton("Hapus");
        hapusButton.setBackground(new Color(239, 68, 68));
        hapusButton.setForeground(Color.WHITE);
        hapusButton.setFocusPainted(false);
        hapusButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hapusButton.addActionListener(e -> hapusLaporanDialog());
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(59, 130, 246));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.addActionListener(e -> {
            loadLaporanData();
            showDashboard(); // Refresh stats
        });
        
        headerPanel.add(tambahButton);
        headerPanel.add(hapusButton);
        headerPanel.add(refreshButton);

        String[] columns = {"ID", "Periode", "Total Transaksi", "Jumlah Item", "Aksi"};
        laporanModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        
        laporanTable = new JTable(laporanModel);
        laporanTable.setRowHeight(45);
        laporanTable.setFont(new Font("Arial", Font.PLAIN, 13));
        laporanTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        laporanTable.getTableHeader().setBackground(new Color(249, 250, 251));
        laporanTable.setSelectionBackground(new Color(221, 214, 254));

        laporanTable.getColumn("Aksi").setCellRenderer(new ButtonRenderer());
        laporanTable.getColumn("Aksi").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(laporanTable);

        tablePanel.add(headerPanel, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        laporanPanel.add(titleLabel, BorderLayout.NORTH);
        laporanPanel.add(tablePanel, BorderLayout.CENTER);

        loadLaporanData();

        contentPanel.add(laporanPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showProfil() {
        contentPanel.removeAll();
        
        JPanel profilPanel = new JPanel(new BorderLayout());
        profilPanel.setBackground(new Color(240, 242, 245));

        JLabel titleLabel = new JLabel("Profil Admin");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        addProfilField(infoPanel, "ID Admin", String.valueOf(admin.getIdAdmin()));
        addProfilField(infoPanel, "Nama", admin.getNama());
        addProfilField(infoPanel, "Email", admin.getEmail());
        addProfilField(infoPanel, "Total Laporan", String.valueOf(admin.getLaporanList().size()));

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

    private void tambahLaporanDialog() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        JTextField idField = new JTextField();
        JTextField periodeField = new JTextField();
        
        inputPanel.add(new JLabel("ID Laporan:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Periode:"));
        inputPanel.add(periodeField);
        
        int result = JOptionPane.showConfirmDialog(this, inputPanel, 
            "Tambah Laporan Baru", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String periode = periodeField.getText();
                
                if (periode.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Periode tidak boleh kosong!");
                    return;
                }
                
                LaporanTransaksi laporanBaru = new LaporanTransaksi(id, periode, new Date());
                admin.tambahLaporan(laporanBaru);
                loadLaporanData();
                showDashboard(); // Refresh stats
                
                JOptionPane.showMessageDialog(this, 
                    "Laporan berhasil ditambahkan!", 
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "ID harus berupa angka!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void hapusLaporanDialog() {
        int selectedRow = laporanTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih laporan yang ingin dihapus!");
            return;
        }
        
        int idLaporan = (int) laporanModel.getValueAt(selectedRow, 0);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Yakin ingin menghapus laporan #" + idLaporan + "?", 
            "Konfirmasi Hapus", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            admin.hapusLaporan(idLaporan);
            loadLaporanData();
            showDashboard(); // Refresh stats
            JOptionPane.showMessageDialog(this, "Laporan berhasil dihapus!");
        }
    }

    private void lihatDetailLaporan(int row) {
        int idLaporan = (int) laporanModel.getValueAt(row, 0);
        LaporanTransaksi laporan = admin.cariLaporan(idLaporan);
        
        if (laporan != null) {
            StringBuilder detail = new StringBuilder();
            detail.append("ID Laporan: ").append(laporan.getIdLaporan()).append("\n");
            detail.append("Periode: ").append(laporan.getPeriode()).append("\n");
            detail.append("Total Transaksi: Rp ").append(String.format("%,.0f", laporan.getTotalTransaksi())).append("\n");
            detail.append("Jumlah Transaksi: ").append(laporan.getTransaksiList().size()).append("\n\n");
            detail.append("Detail Transaksi:\n");
            detail.append("================\n");
            
            for (Transaksi t : laporan.getTransaksiList()) {
                detail.append("\nID: ").append(t.getIdTransaksi());
                detail.append("\nJumlah: Rp ").append(String.format("%,.0f", t.getJumlah()));
                detail.append("\nMetode: ").append(t.getMetodePembayaran());
                detail.append("\n---\n");
            }
            
            JTextArea textArea = new JTextArea(detail.toString());
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 400));
            
            JOptionPane.showMessageDialog(this, scrollPane, 
                "Detail Laporan #" + idLaporan, 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void loadLaporanData() {
        if (laporanModel != null) {
            laporanModel.setRowCount(0);
            for (LaporanTransaksi laporan : admin.getLaporanList()) {
                laporanModel.addRow(new Object[]{
                    laporan.getIdLaporan(),
                    laporan.getPeriode(),
                    "Rp " + String.format("%,.0f", laporan.getTotalTransaksi()),
                    laporan.getTransaksiList().size(),
                    "Detail"
                });
            }
        }
    }

    private void loadDummyData() {
        Transaksi t1 = new Transaksi(1, new Date(), 25000, "Cash");
        Transaksi t2 = new Transaksi(2, new Date(), 35000, "E-Wallet");
        Transaksi t3 = new Transaksi(3, new Date(), 20000, "Transfer");
        
        Transaksi t4 = new Transaksi(4, new Date(), 40000, "Cash");
        Transaksi t5 = new Transaksi(5, new Date(), 15000, "E-Wallet");

        LaporanTransaksi laporan1 = new LaporanTransaksi(1, "Desember 2025", new Date());
        laporan1.tambahTransaksi(t1);
        laporan1.tambahTransaksi(t2);
        laporan1.tambahTransaksi(t3);
        
        LaporanTransaksi laporan2 = new LaporanTransaksi(2, "November 2025", new Date());
        laporan2.tambahTransaksi(t4);
        laporan2.tambahTransaksi(t5);

        admin.tambahLaporan(laporan1);
        admin.tambahLaporan(laporan2);
    }

    // Button Renderer
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Detail" : value.toString());
            setBackground(new Color(59, 130, 246));
            setForeground(Color.WHITE);
            setFocusPainted(false);
            return this;
        }
    }

    // Button Editor
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private int currentRow;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "Detail" : value.toString();
            button.setText(label);
            button.setBackground(new Color(59, 130, 246));
            button.setForeground(Color.WHITE);
            isPushed = true;
            currentRow = row;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                lihatDetailLaporan(currentRow);
            }
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminKantin admin = new AdminKantin(1, "Admin A", "admin@unri.ac.id");
            new AdminDashboard(admin).setVisible(true);
        });
    }
}