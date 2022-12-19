package pbo;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MakananModel {
    private final Connection CONN;

    public MakananModel() {
        this.CONN = DBHelper.getConnection();
    }
    
    public void addMakanan(Makanan mkn){
        String insert = "INSERT INTO makanan VALUES (" +
                        mkn.getId() + ", " + mkn.getDaya_tahan() + ", '" +
                        mkn.getNama_produk() + "', " + mkn.getHarga() + ", " +
                        mkn.getJumlah() + ", " + mkn.getDiskon() + ");";
        
        try {
            if(CONN.createStatement().executeUpdate(insert) > 0){
                System.out.println("Data berhasil dimasukkan");
            }
            else{
                System.out.println("Data gagal dimasukkan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Data gagal dimasukkan");
        }
    }
    
    public void deleteMakanan(Makanan mkn){
        String delete = "DELETE FROM makanan WHERE id = " +
                        mkn.getId() + ";";
        
        try {
            if(CONN.createStatement().executeUpdate(delete) > 0){
                System.out.println("Data berhasil dihapus");
            }
            else{
                System.out.println("Data gagal dihapus");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Data gagal dihapus");
        }
    }
}
