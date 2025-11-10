/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul06;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Dwi Yulianti
 */
public class ContohFlowLayout {
    public static void main(String[] args) {
        // 1. Buat Frame (Window)
        JFrame frame = new JFrame("Contoh FLowLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        
        // 2. Buat Panel (Container)
        // JPanel secara default sudah menggunakan Flowlayout
        JPanel panel = new JPanel();
        // Anda juga bisa mengaturnya secara eksplisit;
        // panel.setLayout(new Flowlayout(Flowlayout.CENTER); // CENTER, LEFT, atau RIGHT
        
        // 3. Buat dan tambahkan komponen
        panel.add(new JButton("Tombol 1"));
        panel.add(new JButton("Tombol 2"));
        panel.add(new JButton("Tombol Tiga"));
        panel.add(new JButton("Tombol Empat Panjang"));
        panel.add(new JButton("Tombol 5"));
        
        // 4. Tambahkan panel ke frame
        frame.add(panel);
        
        // 5. Tampilkan frame
        frame.setVisible(true);
    }
}
