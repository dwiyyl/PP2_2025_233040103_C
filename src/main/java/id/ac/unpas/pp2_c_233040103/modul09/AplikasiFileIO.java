/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul09;

/**
 *
 * @author Dwi Yulianti
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO extends JFrame {
    
    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;
    private JButton btnAppendText; // Latihan 4: Tambah  field  tombol baru
    
    // Latihan 3: Field untuk Object Config
    private UserConfig currentConfig;
    
    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        currentConfig = new UserConfig();
        
        // Inisialisasi Komponen
        this.textArea = new JTextArea();
        this.textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.fileChooser = new JFileChooser();

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnAppendText = new JButton("Tambah Text (Append)"); // Latihan 4: Tombol Append
        btnSaveBinary = new JButton("Simpan Config (Binary)"); // Latihan 3: Simpan Objek
        btnLoadBinary = new JButton("Muat Config (Binary)");
        
        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText); // Latihan 4: Tambahkan ke panel
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);

        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Handling

        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener(e -> bukaFileTeks());

        // 2. MENULIS FILE TEKS (Text Stream)
        btnSaveText.addActionListener(e -> simpanFileTeks());

        // 3. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanObjectConfig());

        // 4. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener(e -> muatObjectConfig());
        
        // 5.
        btnAppendText.addActionListener(e -> appendFileTeks());

        // LATIHAN 2: Metode Baca Otomati
        bacaOtomatisLastNotes();
    }
    
    // LATIHAN 4: Metode baru Untuk Simpan Tambah (Append)
    private void appendFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(
                // Kunci: Gunakan konstruktor FileWriter(File, boolean append). true = append.
                new FileWriter(file, true))) { 
                
                // Tambahkan baris baru sebelum konten baru
                writer.newLine(); 
                // Tulis konten dari textArea
                writer.write(textArea.getText());
                
                JOptionPane.showMessageDialog(this, "Text berhasil ditambahkan ke akhir file!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan ke file: " + ex.getMessage());
            }
        }
    }
    
    // LATIHAN 3: Serialization (Menyimpan Objek Utuh)
    private void simpanObjectConfig() {
        // Ambil data terbaru dari UI sebelum disimpan
        this.currentConfig.setFontsize(this.textArea.getFont().getSize());
        this.currentConfig.setUsername("User_" + System.currentTimeMillis() % 1000); // Contoh update username

        // Gunakan ObjectOutputStream untuk menulis objek
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("user_config.ser"))) { 
            
            // 1. Menyimpan objek utuh ke file
            oos.writeObject(this.currentConfig);
            
            String msg = "Objek UserConfig (User: " + this.currentConfig.getUsername() + 
                         ", Font: " + this.currentConfig.getFontsize() + ") berhasil disimpan!";
            JOptionPane.showMessageDialog(this, msg);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan objek: " + ex.getMessage());
        }
    }
    
    // LATIHAN 3: DESERIALIZATION (Membaca Objek Utuh)
    private void muatObjectConfig() {
        // Gunakan ObjectInputStream untuk membaca objek
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("user_config.ser"))) {
            
            // 1. Membaca objek dari file (kembalian bertipe Object)
            Object obj = ois.readObject();
            
            // 2. Casting: Mengubah Objek generik kembali menjadi UserConfig
            UserConfig loadedConfig = (UserConfig) obj; 
            this.currentConfig = loadedConfig;
            
            // 3. Terapkan data yang dimuat ke aplikasi
            this.textArea.setFont(new Font("Monospaced", Font.PLAIN, this.currentConfig.getFontsize()));
            
            String msg = "Objek UserConfig (User: " + this.currentConfig.getUsername() + 
                         ", Font: " + this.currentConfig.getFontsize() + ") berhasil dimuat!";
            JOptionPane.showMessageDialog(this, msg);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File user_config.ser belum dibuat! (Harap Simpan dahulu)");
        } catch (ClassNotFoundException ex) {
            // Ditangkap jika kelas objek tidak ditemukan saat deserialization
            JOptionPane.showMessageDialog(this, "Kelas UserConfig tidak ditemukan: " + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca objek: " + ex.getMessage());
        }
    }
    
    // LATIHAN 2: Metode Baca Otomatis
    private void bacaOtomatisLastNotes() {
        File file = new File("last_notes.txt");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            textArea.setText(""); 
            
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (FileNotFoundException ex) {
            // File tidak ditemukan. Aplikasi tetap berjalan tanpa error.
            //System.out.println("File last_notes.txt tidak ditemukan. TextArea kosong.");
        } catch (IOException ex) {
            // Tangani masalah I/O lainnya
            System.err.println("Gagal membaca last_notes.txt: " + ex.getMessage());
        }
    }
    
    // Contoh: Membaca File Teks dengan Try-Catch-Finally Konvensional
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally

            try {
                // Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); // Kosongkan area

                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                // Blok Finally: Selalu dijalankan untuk menutup resource
                try {
                    if (reader != null) {
                        reader.close(); // PENTING: Menutup stream
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern)
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Try-with-resources otomatis menutup stream tanpa blok finally manual
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}

