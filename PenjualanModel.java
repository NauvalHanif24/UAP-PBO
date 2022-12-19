package pbo;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PenjualanModel {
    private final Connection CONN;

    public PenjualanModel() {
        this.CONN = DBHelper.getConnection();
    }

    public void addPenjualan(Penjualan pjl){
        String insert = "INSERT INTO data_pembelian VALUES ('" + pjl.getIdBarcode() + 
                        "', '" + pjl.getNamaProduk() + "', " + pjl.getHarga() + ", " + 
                        pjl.getJumlahProduk() + ");";
        try {
            if(CONN.createStatement().executeUpdate(insert) > 0){
                System.out.println("Data berhasil dimasukkan");
            }
            else{
                System.out.println("Data gagal dimasukkan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Data gagal dimasukkan");
        }
    }

    public void deletePenjualan(Penjualan pjl){
        String delete = "DELETE FROM data_pembelian WHERE kode = '" + pjl.getIdBarcode() + "';";
        try {
            if(CONN.createStatement().executeUpdate(delete) > 0){
                System.out.println("Data berhasil diHapus");
            }
            else{
                System.out.println("Data gagal dihapus");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Data gagal dihapus");
        }
    }
}
