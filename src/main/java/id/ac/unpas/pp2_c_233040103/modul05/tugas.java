/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dwi Yulianti
 */
public class tugas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Atur Layout Manager
                frame.setLayout(new BorderLayout());

                // Buat komponen
                JLabel label = new JLabel("Label ada di atas (NORTH)");
                JButton buttonSouth = new JButton("Tombol ada di bawah (SOUTH)");
                JButton buttonWest = new JButton("WEST");
                JButton buttonEast = new JButton("EAST");
                JButton buttonCenter = new JButton("CENTER");

                // Tambahkan aksi untuk masing-masing tombol
                buttonSouth.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });

                buttonWest.addActionListener(e -> {
                    label.setText("Tombol di WEST diklik!");
                });

                buttonEast.addActionListener(e -> {
                    label.setText("Tombol di EAST diklik!");
                });

                buttonCenter.addActionListener(e -> {
                    label.setText("Tombol di CENTER diklik!");
                });

                // Tambahkan komponen ke frame
                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSouth, BorderLayout.SOUTH);
                frame.add(buttonWest, BorderLayout.WEST);
                frame.add(buttonEast, BorderLayout.EAST);
                frame.add(buttonCenter, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }
}
