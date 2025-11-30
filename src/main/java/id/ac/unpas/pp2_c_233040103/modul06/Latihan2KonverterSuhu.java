/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul06;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dwi Yulianti
 */
public class Latihan2KonverterSuhu {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2, 5, 5)); 

        JLabel labelCelcius = new JLabel("Celcius:");
        JTextField fieldCelcius = new JTextField();
        JLabel labelFahrenheit = new JLabel("Fahrenheit:");
        JLabel labelHasil = new JLabel(""); 
        JButton buttonKonversi = new JButton("Konversi");

        // Panel tombol di tengah bawah
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButton.add(buttonKonversi);

        // Tambahkan ke frame
        frame.add(labelCelcius);
        frame.add(fieldCelcius);
        frame.add(labelFahrenheit);
        frame.add(labelHasil);
        frame.add(new JLabel(""));
        frame.add(panelButton);

        // Aksi tombol konversi
        buttonKonversi.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = fieldCelcius.getText();
                    double celcius = Double.parseDouble(input);
                    double fahrenheit = (celcius * 9.0 / 5.0) + 32;
                    labelHasil.setText(String.format("%.2f", fahrenheit));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Isi kolom Celcius dengan nilai numerik saja!",
                        "Kesalahan Input",
                        JOptionPane.ERROR_MESSAGE
                    );
                    labelHasil.setText("");
                }
            }
        });
        frame.setVisible(true);
    }
}
