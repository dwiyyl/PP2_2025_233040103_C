/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul07;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Dwi Yulianti
 */
public class TugasModul07 extends JFrame {
    
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;
    
// Method untuk membuat desain Tab Input
private JPanel createInputPanel() {
    JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Komponen Nama
    panel.add(new JLabel("Nama Siswa:"));
    txtNama = new JTextField();
    panel.add(txtNama);

    // Komponen Mata Pelajaran (ComboBox)
    panel.add(new JLabel("Mata Pelajaran:"));
    String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
                       "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
    cmbMatkul = new JComboBox<>(matkul);
    panel.add(cmbMatkul);

    // Komponen Nilai
    panel.add(new JLabel("Nilai (0-100):"));
    txtNilai = new JTextField();
    panel.add(txtNilai);

    // Tombol Simpan
    JButton btnSimpan = new JButton("Simpan Data");
    panel.add(new JLabel("")); // Placeholder kosong agar tombol di kanan
    panel.add(btnSimpan);

    // Tombol Reset
    JButton btnReset = new JButton("Reset Input");
    panel.add(new JLabel(""));
    panel.add(btnReset);
    
    // Event Handling Tombol Simpan
    btnSimpan.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            prosesSimpan();
        }
    });
    
    // Event Handling Tombol Reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetInput(); 
            }
        });

    return panel;
}

// Method untuk membuat desain Tab Tabel
private JPanel createTablePanel() {
    JPanel panel = new JPanel(new BorderLayout());

    // Setup Model Tabel (Kolom)
    String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
    tableModel = new DefaultTableModel(kolom, 0);
    tableData = new JTable(tableModel);

    // Membungkus tabel dengan ScrollPane (agar bisa discroll jika data banyak)
    JScrollPane scrollPane = new JScrollPane(tableData);
    panel.add(scrollPane, BorderLayout.CENTER);

    //Tambah tombol hapus di panel bagian bawah Tab Daftar Nilai
    JButton btnHapus = new JButton("Hapus Baris Terpilih");
    
    // Gunakan JPanel tambahan untuk menampung tombol agar tata letak rapi
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(btnHapus);
    panel.add(bottomPanel, BorderLayout.SOUTH);
    
    // Event Handling Tombol Hapus
    btnHapus.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            prosesHapus(); // Panggil method baru untuk penghapusan
        }
    });
    
    return panel;
}

// Method untuk menghapus baris tabel yang dipilih
private void prosesHapus() {
    // Dapatkan indeks baris yang dipilih
    int selectedRow = tableData.getSelectedRow();
    
    // Jika ada baris yang dipilih (indeks > -1)
    if (selectedRow > -1) {
        // Tampilkan dialog konfirmasi
        int dialogResult = JOptionPane.showConfirmDialog(this, 
                          "Yakin ingin menghapus data ini?", 
                          "Konfirmasi Hapus", 
                          JOptionPane.YES_NO_OPTION);
                                                        
        if (dialogResult == JOptionPane.YES_OPTION) {
            // Hapus baris dari model tabel
            tableModel.removeRow(selectedRow); 
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus.", "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        // Jika tidak ada baris yang dipilih
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus terlebih dahulu.", 
                "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}

// Method Untuk membersihkan inputan (Digunakan oleh tombol Reset dan prosesSimpan)
    private void resetInput() {
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
    }
    
// Logika Validasi dan Penyimpanan Data
private void prosesSimpan() {
    // 1. Ambil data dari input
    String nama = txtNama.getText();
    String matkul = (String) cmbMatkul.getSelectedItem();
    String strNilai = txtNilai.getText();

    // 2. VALIDASI INPUT

    // Validasi 1: Cek apakah nama kosong
    if (nama.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", 
                  "Error Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validasi 2: Cek apakah nilai berupa angka dan dalam range valid
    int nilai;
    try {
        nilai = Integer.parseInt(strNilai);
        if (nilai < 0 || nilai > 100) {
            JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!",
                     "Error Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!",
                     "Error Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Validasi 3: Pastikan nama minimal 3 karakter
    if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama Siswa minimal 3 karakter!", 
                     "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return; 
        }
    
    // 3. Logika Bisnis (Menentukan Grade)
    String grade;
    int rangeNilai = nilai / 10;

    switch (rangeNilai) {
        case 10: // Nilai 100
        case 9:  // Nilai 90-99
        case 8:  // Nilai 80-89
            grade = "A";
            break;
        case 7:  // Nilai 70-79
            grade = "AB";
            break;
        case 6:  // Nilai 60-69
            grade = "B";
            break;
        case 5:  // Nilai 50-59
            grade = "BC";
            break;
        case 4:  // Nilai 40-49
            grade = "C";
            break;
        case 3:  // Nilai 30-39
            grade = "D";
            break;
        default: // Nilai 0-29
            grade = "E";
            break;
    }

    // 4. Masukkan ke Tabel (Update Model)
    Object[] dataBaris = {nama, matkul, nilai, grade};
    tableModel.addRow(dataBaris);

    // 5. Reset Form dan Pindah Tab
    txtNama.setText("");
    txtNilai.setText("");
    cmbMatkul.setSelectedIndex(0);

    JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
    tabbedPane.setSelectedIndex(1);
    }

public TugasModul07() {
    // 1. Konfigurasi Frame Utama
    setTitle("Aplikasi Manajemen Nilai Siswa");
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // 2. Inisialisasi Tabbed Pane
    tabbedPane = new JTabbedPane();

    // 3. Membuat Panel untuk Tab 1 (Form Input)
    JPanel panelInput = createInputPanel();
    tabbedPane.addTab("Input Data", panelInput);

    // 4. Membuat Panel untuk Tab 2 (Tabel Data)
    JPanel panelTabel = createTablePanel();
    tabbedPane.addTab("Daftar Nilai", panelTabel);

    // Menambahkan TabbedPane ke Frame
    add(tabbedPane);
}

// Terakhir, buat method main untuk menjalankan kelas ini.
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new TugasModul07().setVisible(true);
    });
}
}
