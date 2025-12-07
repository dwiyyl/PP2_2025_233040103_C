/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul08.controller;

import id.ac.unpas.pp2_c_233040103.modul08.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_233040103.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersegiPanjangController {
    // Model dan view sebagai atribut kelas
    private PersegiPanjangModel model;
    private PersegiPanjangView view;
    
    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;
        
        // Menghubungkan tombol di View dengan logic di Controller
        this.view.addHitungListener(new HitungListener()); // Pastikan nama method sudah benar
        
        // Menghubungkan tombol di View dengan logic di Controller
        this.view.addResetListener(new ResetListener()); //latihan3
    }
    
    // Inner class untuk menangani event klik tombol
    class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 1. Ambil data dari View dan konversi (di getPanjang/getLebar)
                double p = view.getPanjang();
                double l = view.getLebar();

                // 2. Kirim data ke Model
                model.setPanjang(p);
                model.setLebar(l);

                // 3. Jalankan logika bisnis di Model
                model.hitungLuas();
                model.hitungKeliling(); //latihan2

                // 4. Ambil hasil dari Model dan tampilkan kembali di View
                double hasilLuas = model.getLuas();
                view.setHasilLuas(hasilLuas);

                double hasilKeliling = model.getKeliling(); //latihan2
                view.setHasilKeliling (hasilKeliling); //latihan2
            } catch (NumberFormatException ex) {
                // Handle jika user memasukkan huruf
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
        }
    }
    
    // Latihan3: Class untuk menangani event klik tombol Reset
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Controller hanya bertugas memanggil method reset dari View
            view.resetView(); 
        }
    }
}
