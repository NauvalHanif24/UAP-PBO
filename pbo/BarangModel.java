package pbo;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BarangModel {
    private final Connection CONN;

    public BarangModel() {
        this.CONN = DBHelper.getConnection();
    }

    public void addBarang(Barang brg){
        String insert = "INSERT INTO barang VALUES ('" + brg.getBarcode() +
                        "', '" + brg.getExpired() + "', '" + brg.getCategory() +
                        "', '" + brg.getNama_produk() + "', " + brg.getHarga() +
                        ", " + brg.getJumlah() + ", " + brg.getDiskon() + ");";
        
        try {
            if(CONN.createStatement().executeUpdate(insert) > 0){
                System.out.println("Data berhasil dimasukkan");
            }
            else{
                System.out.println("Data gagal dimasukkan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Data gagal dimasukkan");
        }
    }

    public void deleteBarang(Barang brg){
        String delete = "DELETE FROM barang WHERE barcode = '" + brg.getBarcode() +
                        "';";
        
        try {
            if(CONN.createStatement().executeUpdate(delete) > 0){
                System.out.println("Data berhasil dihapus");
            }
            else{
                System.out.println("Data gagal dihapus");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Data gagal dihapus");
        }
    }
}
