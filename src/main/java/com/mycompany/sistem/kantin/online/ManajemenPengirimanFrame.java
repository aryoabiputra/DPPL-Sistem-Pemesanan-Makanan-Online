import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ManajemenPengirimanFrame extends JFrame {
    private JTable tablePesanan;
    private JTable tablePengiriman;
    private DefaultTableModel modelPesanan;
    private DefaultTableModel modelPengiriman;
    private List<Pesanan> daftarPesanan;
    private List<Pengiriman> daftarPengiriman;
    private JTabbedPane tabbedPane;
    
    public ManajemenPengirimanFrame() {
        daftarPesanan = new ArrayList<>();
        daftarPengiriman = new ArrayList<>();
        
        setTitle("Manajemen Pengiriman - Kurir");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        loadDummyData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(37, 99, 235));
        headerPanel.setPreferredSize(new Dimension(1200, 80));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel lblHeader = new JLabel("🚚 Manajemen Pengiriman");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.add(lblHeader, BorderLayout.WEST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Tab 1: Pesanan
        JPanel panelPesanan = createPesananPanel();
        tabbedPane.addTab("📦 Daftar Pesanan", panelPesanan);
        
        // Tab 2: Pengiriman
        JPanel panelPengiriman = createPengirimanPanel();
        tabbedPane.addTab("🚚 Status Pengiriman", panelPengiriman);
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createPesananPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Top Panel with Search and Add Button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JLabel lblTitle = new JLabel("Daftar Pesanan");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        
        JTextField txtSearch = new JTextField(20);
        txtSearch.setPreferredSize(new Dimension(200, 35));
        
        JButton btnSearch = new JButton("🔍 Cari");
        btnSearch.setPreferredSize(new Dimension(100, 35));
        btnSearch.setBackground(new Color(37, 99, 235));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        
        JButton btnTambah = new JButton("➕ Tambah Pesanan");
        btnTambah.setPreferredSize(new Dimension(160, 35));
        btnTambah.setBackground(new Color(16, 185, 129));
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFocusPainted(false);
        btnTambah.addActionListener(e -> tambahPesanan());
        
        buttonPanel.add(txtSearch);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnTambah);
        
        topPanel.add(lblTitle, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);
        
        // Table
        String[] columns = {"ID Pesanan", "Alamat Tujuan", "Tanggal Pesan", "Aksi"};
        modelPesanan = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only action column
            }
        };
        
        tablePesanan = new JTable(modelPesanan);
        tablePesanan.setRowHeight(45);
        tablePesanan.setFont(new Font("Arial", Font.PLAIN, 13));
        tablePesanan.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tablePesanan.getTableHeader().setBackground(new Color(249, 250, 251));
        tablePesanan.getTableHeader().setPreferredSize(new Dimension(0, 40));
        
        // Add button renderer and editor for action column
        tablePesanan.getColumn("Aksi").setCellRenderer(new ButtonRenderer());
        tablePesanan.getColumn("Aksi").setCellEditor(new ButtonEditor(new JCheckBox(), this, true));
        
        JScrollPane scrollPane = new JScrollPane(tablePesanan);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createPengirimanPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JLabel lblTitle = new JLabel("Status Pengiriman");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        topPanel.add(lblTitle, BorderLayout.WEST);
        
        // Table
        String[] columns = {"ID Pengiriman", "Tanggal Kirim", "Status", "Update Status"};
        modelPengiriman = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only action column
            }
        };
        
        tablePengiriman = new JTable(modelPengiriman);
        tablePengiriman.setRowHeight(45);
        tablePengiriman.setFont(new Font("Arial", Font.PLAIN, 13));
        tablePengiriman.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tablePengiriman.getTableHeader().setBackground(new Color(249, 250, 251));
        tablePengiriman.getTableHeader().setPreferredSize(new Dimension(0, 40));
        
        // Add button renderer and editor
        tablePengiriman.getColumn("Update Status").setCellRenderer(new ButtonRenderer());
        tablePengiriman.getColumn("Update Status").setCellEditor(new ButtonEditor(new JCheckBox(), this, false));
        
        JScrollPane scrollPane = new JScrollPane(tablePengiriman);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void loadDummyData() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // Pesanan
        try {
            daftarPesanan.add(new Pesanan(1, "Jl. Bangau Sakti No. 123, Pekanbaru", sdf.parse("08/11/2025")));
            daftarPesanan.add(new Pesanan(2, "Jl. Binakrida No. 45, Pekanbaru", sdf.parse("08/11/2025")));
            daftarPesanan.add(new Pesanan(3, "Jl. Taman Karya No. 78, Pekanbaru", sdf.parse("07/11/2025")));
            daftarPesanan.add(new Pesanan(4, "Jl. Manyar Sakti No. 12, Pekanbaru", sdf.parse("07/11/2025")));
            daftarPesanan.add(new Pesanan(5, "Jl. Swakarya No. 56, Pekanbaru", sdf.parse("06/11/2025")));
            
            for (Pesanan p : daftarPesanan) {
                modelPesanan.addRow(new Object[]{
                    p.getIdPesanan(),
                    p.getAlamatTujuan(),
                    sdf.format(p.getTanggalPesan()),
                    "Detail"
                });
            }
            
            // Pengiriman
            daftarPengiriman.add(new Pengiriman(1, sdf.parse("08/11/2025"), "Sedang Dikirim"));
            daftarPengiriman.add(new Pengiriman(2, sdf.parse("07/11/2025"), "Terkirim"));
            daftarPengiriman.add(new Pengiriman(3, sdf.parse("06/11/2025"), "Terkirim"));
            daftarPengiriman.add(new Pengiriman(4, sdf.parse("05/11/2025"), "Sedang Dikirim"));
            
            for (Pengiriman p : daftarPengiriman) {
                modelPengiriman.addRow(new Object[]{
                    p.getIdPengiriman(),
                    sdf.format(p.getTanggalKirim()),
                    p.getStatus(),
                    "Update"
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void tambahPesanan() {
        JDialog dialog = new JDialog(this, "Tambah Pesanan Baru", true);
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // ID Pesanan
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID Pesanan:"), gbc);
        JTextField txtId = new JTextField(20);
        txtId.setText(String.valueOf(daftarPesanan.size() + 1));
        txtId.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);
        
        // Alamat
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Alamat Tujuan:"), gbc);
        JTextField txtAlamat = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(txtAlamat, gbc);
        
        // Tanggal
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Tanggal:"), gbc);
        JTextField txtTanggal = new JTextField(20);
        txtTanggal.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        gbc.gridx = 1;
        formPanel.add(txtTanggal, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBackground(new Color(16, 185, 129));
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFocusPainted(false);
        btnSimpan.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String alamat = txtAlamat.getText();
                Date tanggal = new SimpleDateFormat("dd/MM/yyyy").parse(txtTanggal.getText());
                
                if (alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Alamat tidak boleh kosong!");
                    return;
                }
                
                Pesanan pesanan = new Pesanan(id, alamat, tanggal);
                daftarPesanan.add(pesanan);
                modelPesanan.addRow(new Object[]{
                    id, alamat, txtTanggal.getText(), "Detail"
                });
                
                JOptionPane.showMessageDialog(dialog, "Pesanan berhasil ditambahkan!");
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
            }
        });
        
        JButton btnBatal = new JButton("Batal");
        btnBatal.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnBatal);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    public void updateStatusPengiriman(int row) {
        String[] options = {"Sedang Dikirim", "Terkirim", "Dibatalkan"};
        String currentStatus = (String) modelPengiriman.getValueAt(row, 2);
        
        String newStatus = (String) JOptionPane.showInputDialog(
            this,
            "Pilih status baru:",
            "Update Status Pengiriman",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            currentStatus
        );
        
        if (newStatus != null) {
            modelPengiriman.setValueAt(newStatus, row, 2);
            daftarPengiriman.get(row).updateStatus(newStatus);
            JOptionPane.showMessageDialog(this, "Status berhasil diupdate!");
        }
    }
    
    public void lihatDetailPesanan(int row) {
        Pesanan p = daftarPesanan.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String detail = "=== Detail Pesanan ===\n\n" +
                       "ID Pesanan: " + p.getIdPesanan() + "\n" +
                       "Alamat Tujuan: " + p.getAlamatTujuan() + "\n" +
                       "Tanggal Pesan: " + sdf.format(p.getTanggalPesan());
        
        JOptionPane.showMessageDialog(this, detail, "Detail Pesanan", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManajemenPengirimanFrame().setVisible(true);
        });
    }
}

// Button Renderer
class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        setBackground(new Color(37, 99, 235));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        return this;
    }
}

// Button Editor
class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private int row;
    private ManajemenPengirimanFrame parentFrame;
    private AdministrasiPelaporanFrame adminFrame;
    private boolean isPesananTable;
    
    public ButtonEditor(JCheckBox checkBox, ManajemenPengirimanFrame frame, boolean isPesanan) {
        super(checkBox);
        this.parentFrame = frame;
        this.isPesananTable = isPesanan;
        button = new JButton();
        button.setOpaque(true);
        button.setBackground(new Color(37, 99, 235));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        
        button.addActionListener(e -> fireEditingStopped());
    }
    
    public ButtonEditor(JCheckBox checkBox, AdministrasiPelaporanFrame frame) {
        super(checkBox);
        this.adminFrame = frame;
        button = new JButton();
        button.setOpaque(true);
        button.setBackground(new Color(37, 99, 235));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        
        button.addActionListener(e -> fireEditingStopped());
    }
    
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.row = row;
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }
    
    public Object getCellEditorValue() {
        if (isPushed) {
            if (parentFrame != null) {
                if (isPesananTable) {
                    parentFrame.lihatDetailPesanan(row);
                } else {
                    parentFrame.updateStatusPengiriman(row);
                }
            } else if (adminFrame != null) {
                adminFrame.lihatDetailLaporan(row);
            }
        }
        isPushed = false;
        return label;
    }
    
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}