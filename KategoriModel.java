package pbo;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KategoriModel {
    private final Connection CONN;

    public KategoriModel() {
        this.CONN = DBHelper.getConnection();
    }

    public void addKategori(Kategori ktg){
        String insert = "INSERT INTO kategori VALUES ('" + ktg.getNama_kategori() + "');";
        try {
            if(CONN.createStatement().executeUpdate(insert) > 0){
                System.out.println("Data berhasil dimasukkan");
            }
            else{
                System.out.println("Data gagal dimasukkan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Data gagal dimasukkan");
        }
    }

    public void deleteKategori(Kategori ktg){
        String delete = "DELETE FROM kategori WHERE nama_kategori = '" + ktg.getNama_kategori() + "';";
        try {
            if(CONN.createStatement().executeUpdate(delete) > 0){
                System.out.println("Data berhasil dihapus");
            }
            else{
                System.out.println("Data gagal dihapus");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Data gagal dihapus");
        }
    }
}
