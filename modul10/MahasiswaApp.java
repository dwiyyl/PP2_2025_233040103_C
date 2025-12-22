/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul10;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Dwi Yulianti
 */
public class MahasiswaApp extends JFrame {
    
    // Komponen GUI
    JTextField txtNama, txtNIM, txtJurusan;
    JButton btnSimpan, btnEdit, btnHapus, btnClear;
    JTable tableMahasiswa;
    DefaultTableModel model;
    JTextField txtCari; // Latihan 3: input pencarian
    JButton btnCari; // Latihan 3: tombol cari

   
    public MahasiswaApp() {
    // Setup Frame
    setTitle("Aplikasi CRUD Mahasiswa JDBC");
    setSize (600, 500);
    setDefaultCloseOperation (EXIT_ON_CLOSE);
    setLocationRelativeTo (null);
    setLayout (new BorderLayout());
    
    // 1. Panel Form (Input Data)
    JPanel panelForm = new JPanel (new GridLayout (4, 2, 10, 10));
    panelForm.setBorder (BorderFactory.createEmptyBorder (10, 10, 10, 10));
    
    panelForm.add(new JLabel ("Nama: "));
    txtNama = new JTextField();
    panelForm.add(txtNama);
    
    panelForm.add(new JLabel ("NIM:"));
    txtNIM = new JTextField();
    panelForm.add(txtNIM);
    
    panelForm.add(new JLabel ("Jurusan: "));
    txtJurusan = new JTextField();
    panelForm.add (txtJurusan);
    
    // Panel Tombol
    JPanel panelTombol = new JPanel (new FlowLayout());
    btnSimpan = new JButton("Simpan");
    btnEdit = new JButton("Edit");
    btnHapus = new JButton("Hapus");
    btnClear = new JButton("Clear");
 
    panelTombol.add (btnSimpan);
    panelTombol.add(btnEdit);
    panelTombol.add (btnHapus);
    panelTombol.add (btnClear);
    
    // Latihan 3: Panel Pencarian
    panelTombol.add(new JLabel("Cari Nama:"));
    txtCari = new JTextField(15);
    panelTombol.add(txtCari);

    btnCari = new JButton("Cari");
    panelTombol.add(btnCari);

    
    // Gabungkan Panel Form dan Tombol di bagian Atas (NORTH)
    // Latihan 3
    JPanel panelAtas = new JPanel(new BorderLayout());
    panelAtas.add(panelForm, BorderLayout.CENTER);
    panelAtas.add(panelTombol, BorderLayout.SOUTH);
    add(panelAtas, BorderLayout.NORTH);

    // 2. Tabel Data (Menampilkan Data)
    model = new DefaultTableModel();
    model.addColumn("No");
    model.addColumn("Nama");
    model.addColumn("NIM");
    model.addColumn("Jurusan");

    tableMahasiswa = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
    add(scrollPane, BorderLayout.CENTER);

    // --- Event Listeners ---

    // Listener Klik Tabel (Untuk mengambil data saat baris diklik)
    tableMahasiswa.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tableMahasiswa.getSelectedRow();
            txtNama.setText(model.getValueAt(row, 1).toString());
            txtNIM.setText(model.getValueAt(row, 2).toString());
            txtJurusan.setText(model.getValueAt(row, 3).toString());
        }
    });

    // Aksi Tombol Simpan (CREATE)
    btnSimpan.addActionListener(e -> tambahData());

    // Aksi Tombol Edit (UPDATE)
    btnEdit.addActionListener(e -> ubahData());

    // Aksi Tombol Hapus (DELETE)
    btnHapus.addActionListener(e -> hapusData());

    // Aksi Tombol Clear
    btnClear.addActionListener(e -> kosongkanForm());

    // Latihan 3: Aksi Tombol Cari
    btnCari.addActionListener(e -> cariData());

    // Load data saat aplikasi pertama kali jalan
    loadData();
    }

    // --- LOGIKA CRUD ---

    // 1. READ (Menampilkan Data)
    private void loadData() {
        model.setRowCount(0); // Reset tabel
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");

            int no = 1;
            while (res.next()) {
                model.addRow(new Object[]{
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage());
        }
    }
    
    // 2. CREATE (Menambah Data)
    //LATIHAN 2
    private void tambahData() {

    // Latihan 2: Validasi Input
    if (txtNama.getText().trim().isEmpty() ||
        txtNIM.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Data tidak boleh kosong!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        return; // batalkan penyimpanan
    }

    try {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, txtNama.getText());
        pst.setString(2, txtNIM.getText());
        pst.setString(3, txtJurusan.getText());

        pst.execute();
        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
        loadData();
        kosongkanForm();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
    }
}

    // 3. UPDATE (Mengubah Data berdasarkan NIM) private void ubahData() {
    private void ubahData() {    
        try {
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNama.getText());
            pst.setString (2, txtJurusan.getText());
            pst.setString(3, txtNIM.getText()); // Kunci update
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }
    
    // 4. DELETE (Menghapus Data)
    private void hapusData() {
        try {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNIM.getText());

            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal Hapus: " + e.getMessage());
        }
    }

    // Latihan 3: Method cariData()
    private void cariData() {
    // Jika field cari kosong, tampilkan semua data
    if (txtCari.getText().trim().isEmpty()) {
        loadData();
        return;
    }
        
    model.setRowCount(0); // reset tabel

    try {
        String keyword = txtCari.getText();

        String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, "%" + keyword + "%");
        ResultSet res = pst.executeQuery();

        int no = 1;
        while (res.next()) {
            model.addRow(new Object[]{
                no++,
                res.getString("nama"),
                res.getString("nim"),
                res.getString("jurusan")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Gagal Cari Data: " + e.getMessage());
    }
}

// Latihan 4: Metode Pengecekan Duplikasi NIM
    private boolean isNIMExists(String nim) {
        String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
        try (Connection conn = KoneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, nim);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Jika hasil COUNT(*) lebih dari 0, berarti NIM sudah ada
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Cek NIM: " + e.getMessage());
            return true; // Jika error, anggap ada (supaya aman tidak insert)
        }
        return false;
    }
    
    // Method untuk mengosongkan form
    private void kosongkanForm() {
        txtNama.setText(null);
        txtNIM.setText(null);
        txtJurusan.setText(null);
    }

    public static void main(String[] args) {
        // Menjalankan Aplikasi
        SwingUtilities.invokeLater(() ->
                new MahasiswaApp().setVisible(true));
    }
}
