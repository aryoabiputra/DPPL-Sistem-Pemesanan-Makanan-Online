import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdministrasiPelaporanFrame extends JFrame {
    private JTable tableLaporan;
    private JTable tableTransaksi;
    private DefaultTableModel modelLaporan;
    private DefaultTableModel modelTransaksi;
    private List<LaporanTransaksi> daftarLaporan;
    private List<Transaksi> daftarTransaksi;
    private JTabbedPane tabbedPane;

    public AdministrasiPelaporanFrame() {
        daftarLaporan = new ArrayList<>();
        daftarTransaksi = new ArrayList<>();

        setTitle("Administrasi & Pelaporan - Admin");
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

        JLabel lblHeader = new JLabel("Administrasi & Pelaporan");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.add(lblHeader, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        // Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Tab 1: Laporan Transaksi
        JPanel panelLaporan = createLaporanPanel();
        tabbedPane.addTab("Laporan Transaksi", panelLaporan);

        // Tab 2: Data Transaksi
        JPanel panelTransaksi = createTransaksiPanel();
        tabbedPane.addTab("Data Transaksi", panelTransaksi);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createLaporanPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JLabel lblTitle = new JLabel("Laporan Transaksi");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnExportPDF = new JButton("Export PDF");
        btnExportPDF.setPreferredSize(new Dimension(150, 35));
        btnExportPDF.setBackground(new Color(16, 185, 129));
        btnExportPDF.setForeground(Color.WHITE);
        btnExportPDF.setFocusPainted(false);
        btnExportPDF.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Laporan berhasil di-export ke PDF!");
        });

        JButton btnExportExcel = new JButton("Export Excel");
        btnExportExcel.setPreferredSize(new Dimension(150, 35));
        btnExportExcel.setBackground(new Color(37, 99, 235));
        btnExportExcel.setForeground(Color.WHITE);
        btnExportExcel.setFocusPainted(false);
        btnExportExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Laporan berhasil di-export ke Excel!");
        });

        buttonPanel.add(btnExportPDF);
        buttonPanel.add(btnExportExcel);

        topPanel.add(lblTitle, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        // Table
        String[] columns = {"ID Laporan", "Periode", "Total Transaksi", "Tanggal Dibuat", "Aksi"};
        modelLaporan = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Only action column
            }
        };
        tableLaporan = new JTable(modelLaporan);
        tableLaporan.setRowHeight(45);
        tableLaporan.setFont(new Font("Arial", Font.PLAIN, 13));
        tableLaporan.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tableLaporan.getTableHeader().setBackground(new Color(249, 250, 251));
        tableLaporan.getTableHeader().setPreferredSize(new Dimension(0, 40));
        
        // Add button renderer and editor for action column
        tableLaporan.getColumn("Aksi").setCellRenderer(new LaporanButtonRenderer());
        tableLaporan.getColumn("Aksi").setCellEditor(new LaporanButtonEditor(new JCheckBox(), this));
        
        JScrollPane scrollPane = new JScrollPane(tableLaporan);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createTransaksiPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("Data Transaksi");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        topPanel.add(lblTitle, BorderLayout.WEST);

        String[] columns = {"ID Transaksi", "Tanggal", "Jumlah", "Metode Pembayaran"};
        modelTransaksi = new DefaultTableModel(columns, 0);
        tableTransaksi = new JTable(modelTransaksi);
        tableTransaksi.setRowHeight(45);
        tableTransaksi.setFont(new Font("Arial", Font.PLAIN, 13));
        tableTransaksi.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tableTransaksi.getTableHeader().setBackground(new Color(249, 250, 251));
        tableTransaksi.getTableHeader().setPreferredSize(new Dimension(0, 40));
        
        JScrollPane scrollPane = new JScrollPane(tableTransaksi);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void loadDummyData() {
        try {
            // Contoh data transaksi
            daftarTransaksi.add(new Transaksi(1, new Date(), 150000, "Transfer"));
            daftarTransaksi.add(new Transaksi(2, new Date(), 230000, "Cash"));
            daftarTransaksi.add(new Transaksi(3, new Date(), 120000, "E-Wallet"));

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Transaksi t : daftarTransaksi) {
                modelTransaksi.addRow(new Object[]{
                    t.getIdTransaksi(),
                    sdf.format(t.getTanggal()),
                    "Rp " + String.format("%,.0f", t.getJumlah()),
                    t.getMetodePembayaran()
                });
            }

            // Contoh laporan
            daftarLaporan.add(new LaporanTransaksi(1, "Oktober 2025", 500000, new Date()));
            daftarLaporan.add(new LaporanTransaksi(2, "November 2025", 600000, new Date()));

            for (LaporanTransaksi l : daftarLaporan) {
                modelLaporan.addRow(new Object[]{
                    l.getIdLaporan(),
                    l.getPeriode(),
                    "Rp " + String.format("%,.0f", l.getTotalTransaksi()),
                    sdf.format(l.getTanggalDibuat()),
                    "Detail"
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lihatDetailLaporan(int row) {
        if (row < 0 || row >= daftarLaporan.size()) {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!");
            return;
        }
        
        LaporanTransaksi l = daftarLaporan.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String detail = "=== Detail Laporan ===\n\n" +
                       "ID Laporan: " + l.getIdLaporan() + "\n" +
                       "Periode: " + l.getPeriode() + "\n" +
                       "Total Transaksi: Rp " + String.format("%,.0f", l.getTotalTransaksi()) + "\n" +
                       "Tanggal Dibuat: " + sdf.format(l.getTanggalDibuat()) + "\n" +
                       "Jumlah Transaksi: " + l.getDaftarTransaksi().size();
        
        JOptionPane.showMessageDialog(this, detail, "Detail Laporan", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdministrasiPelaporanFrame().setVisible(true);
        });
    }
}

// Button Renderer untuk Laporan
class LaporanButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
    public LaporanButtonRenderer() {
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

// Button Editor untuk Laporan
class LaporanButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private int row;
    private AdministrasiPelaporanFrame adminFrame;
    
    public LaporanButtonEditor(JCheckBox checkBox, AdministrasiPelaporanFrame frame) {
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
            adminFrame.lihatDetailLaporan(row);
        }
        isPushed = false;
        return label;
    }
    
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}