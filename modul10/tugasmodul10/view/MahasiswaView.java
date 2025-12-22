/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul10.tugasmodul10.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author Dwi Yulianti
 */

public class MahasiswaView extends JFrame {
    // FORM
    public JTextField txtNama;
    public JTextField txtNIM;
    public JTextField txtJurusan;
    public JTextField txtCari;

    // BUTTON
    public JButton btnSimpan;
    public JButton btnEdit;
    public JButton btnHapus;
    public JButton btnClear;
    public JButton btnCari;

    // TABLE
    public JTable table;
    public DefaultTableModel model;

    public MahasiswaView() {
        setTitle("Aplikasi Mahasiswa (MVC)");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // FORM
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // BUTTON
        JPanel panelButton = new JPanel();

        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelButton.add(btnSimpan);
        panelButton.add(btnEdit);
        panelButton.add(btnHapus);
        panelButton.add(btnClear);

        panelButton.add(new JLabel("Cari Nama"));
        txtCari = new JTextField(10);
        panelButton.add(txtCari);

        btnCari = new JButton("Cari");
        panelButton.add(btnCari);

        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.add(panelForm, BorderLayout.CENTER);
        panelTop.add(panelButton, BorderLayout.SOUTH);

        add(panelTop, BorderLayout.NORTH);

        // TABLE
        model = new DefaultTableModel(
                new String[]{"Nama", "NIM", "Jurusan"}, 0
        );
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            int row = table.getSelectedRow();

            txtNama.setText(model.getValueAt(row, 0).toString());
            txtNIM.setText(model.getValueAt(row, 1).toString());
            txtJurusan.setText(model.getValueAt(row, 2).toString());
        }
    });
    }    
}
