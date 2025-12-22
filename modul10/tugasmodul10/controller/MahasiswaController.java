/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul10.tugasmodul10.controller;

import id.ac.unpas.pp2_c_233040103.modul10.tugasmodul10.model.MahasiswaModel;
import id.ac.unpas.pp2_c_233040103.modul10.tugasmodul10.view.MahasiswaView;

import javax.swing.*;

/**
 *
 * @author Dwi Yulianti
 */
public class MahasiswaController {
     private MahasiswaView view;

    public MahasiswaController(MahasiswaView view) {
        this.view = view;

        view.btnSimpan.addActionListener(e -> simpanData());
        view.btnCari.addActionListener(e -> cariData());
        view.btnHapus.addActionListener(e -> hapusData());
        view.btnEdit.addActionListener(e -> editData());
        view.btnClear.addActionListener(e -> clearForm());
 
        loadData();
    }

    // SIMPAN DATA
    private void simpanData() {
    if (view.txtNama.getText().trim().isEmpty() ||
        view.txtNIM.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(view,
                "Data tidak boleh kosong!");
        return;
    }

    String nim = view.txtNIM.getText().toUpperCase();
    MahasiswaModel model = new MahasiswaModel();

    if (model.isNIMExist(nim)) {
        JOptionPane.showMessageDialog(view,
                "NIM sudah terdaftar!");
        return;
    }

    model.setNama(view.txtNama.getText());
    model.setNim(nim);
    model.setJurusan(view.txtJurusan.getText());

    if (model.insert()) {
        JOptionPane.showMessageDialog(view,
                "Data berhasil disimpan");
        loadData();
        clearForm();
    }
}

    private void loadData() {
        view.model.setRowCount(0);
        for (MahasiswaModel m : MahasiswaModel.getAll()) {
            view.model.addRow(new Object[]{
                m.getNama(),
                m.getNim(),
                m.getJurusan()
            });
        }
    }

    // CARI DATA
    private void cariData() {
        String keyword = view.txtCari.getText();
        if (keyword.trim().isEmpty()) {
            loadData();
            return;
        }
        view.model.setRowCount(0);
        for (MahasiswaModel m : MahasiswaModel.cari(keyword)) {
            view.model.addRow(new Object[]{
                m.getNama(),
                m.getNim(),
                m.getJurusan()
            });
        }
    }

   // HAPUS DATA
   private void hapusData() {
    if (view.txtNIM.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(view,
                "Pilih data yang akan dihapus!");
        return;
    }
    int konfirmasi = JOptionPane.showConfirmDialog(
            view,
            "Yakin ingin menghapus data ini?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
    );
    if (konfirmasi == JOptionPane.YES_OPTION) {
        MahasiswaModel model = new MahasiswaModel();

        model.setNim(view.txtNIM.getText().toUpperCase());
        if (model.delete()) {
            JOptionPane.showMessageDialog(view,
                    "Data berhasil dihapus");
            loadData();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view,
                    "Gagal menghapus data");
        }
    }
}
    
   //  EDIT DATA
   private void editData() {
    if (view.txtNIM.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(view,
                "Pilih data yang akan diedit!");
        return;
    }
    String nim = view.txtNIM.getText().toUpperCase();
    MahasiswaModel model = new MahasiswaModel();

    // set data baru
    model.setNama(view.txtNama.getText());
    model.setNim(nim);
    model.setJurusan(view.txtJurusan.getText());

    int konfirmasi = JOptionPane.showConfirmDialog(
            view,
            "Yakin ingin mengubah data ini?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
    );

    if (konfirmasi == JOptionPane.YES_OPTION) {
        if (model.update()) { // method update() harus ada di MahasiswaModel
            JOptionPane.showMessageDialog(view,
                    "Data berhasil diperbarui");
            loadData();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view,
                    "Gagal memperbarui data");
        }
    }
}
   
    private void clearForm() {
        view.txtNama.setText("");
        view.txtNIM.setText("");
        view.txtJurusan.setText("");
    }
}
