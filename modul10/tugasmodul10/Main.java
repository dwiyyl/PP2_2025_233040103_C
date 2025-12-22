/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul10.tugasmodul10;

import id.ac.unpas.pp2_c_233040103.modul10.tugasmodul10.view.MahasiswaView;
import id.ac.unpas.pp2_c_233040103.modul10.tugasmodul10.controller.MahasiswaController;

/**
 *
 * @author Dwi Yulianti
 */
public class Main {
     public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MahasiswaView view = new MahasiswaView();
            new MahasiswaController(view);
            view.setVisible(true);
        });
    }
}



























