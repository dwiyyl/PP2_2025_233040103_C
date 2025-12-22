/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040103.modul10.tugasmodul10.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Dwi Yulianti
 */
public class MahasiswaModel {
    private String nama;
    private String NIM;
    private String jurusan;

    public MahasiswaModel() {
    }

    public MahasiswaModel(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.NIM = nim;
        this.jurusan = jurusan;
    }

    // GETTER & SETTER
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getNim() { return NIM; }
    public void setNim(String nim) { this.NIM = nim; }
    public String getJurusan() { return jurusan; }
    public void setJurusan(String jurusan) { this.jurusan = jurusan; }

    // DATABASE
    // INSERT
    public boolean insert() {
        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            Connection conn = Koneksi_db.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, NIM);
            pst.setString(3, jurusan);
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // CEK NIM
    public boolean isNIMExist(String nim) {
        try {
            String sql = "SELECT nim FROM mahasiswa WHERE nim = ?";
            Connection conn = Koneksi_db.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    // LOAD DATA
    public static ArrayList<MahasiswaModel> getAll() {
        ArrayList<MahasiswaModel> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM mahasiswa";
            Connection conn = Koneksi_db.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(new MahasiswaModel(
                        rs.getString("nama"),
                        rs.getString("nim"),
                        rs.getString("jurusan")
                ));
            }
        } catch (Exception e) {
        } return list;
    }

    // CARI DATA
    public static ArrayList<MahasiswaModel> cari(String keyword) {
        ArrayList<MahasiswaModel> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            Connection conn = Koneksi_db.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new MahasiswaModel(
                        rs.getString("nama"),
                        rs.getString("NIM"),
                        rs.getString("jurusan")
                ));
            }

        } catch (Exception e) {
        } return list;
    }
    
    // HAPUS DATA
    public boolean delete() {
        try {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            Connection conn = Koneksi_db.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, NIM);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // UPDATE DATA
    public boolean update() {
        try {
            String sql = "UPDATE mahasiswa SET nama=?, jurusan=? WHERE nim=?";
            Connection conn = Koneksi_db.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, jurusan);
            pst.setString(3, NIM);

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
