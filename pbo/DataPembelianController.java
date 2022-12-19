package pbo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static db.DBHelper.getConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DataPembelianController implements Initializable {

    PenjualanModel pjl = new PenjualanModel();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBarang;

    @FXML
    private Button btnData;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnKategori;

    @FXML
    private Button btnMakanan;

    @FXML
    private TableColumn<Penjualan, Double> kolomHarga;

    @FXML
    private TableColumn<Penjualan, String> kolomId;

    @FXML
    private TableColumn<Penjualan, Integer> kolomJumlah;

    @FXML
    private TableColumn<Penjualan, String> kolomNama;

    @FXML
    private TableView<Penjualan> tableView;

    @FXML
    private TextField txtHarga;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtjumlah;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showPenjualan();
    }

    @FXML
    void MenuAwal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void MenuBarang(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Barang.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnBarang.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void MenuData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPembelian.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnData.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void MenuKategori(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Kategori.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnKategori.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void MenuMakanan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Makanan.fxml"));
        Parent root = (Parent) loader.load(); 
        Stage stage = (Stage) btnMakanan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void addData(ActionEvent event) throws IOException {
        Penjualan pjl1 = new Penjualan(Integer.parseInt(txtjumlah.getText()), txtNama.getText(),
                                        txtId.getText(), Double.parseDouble(txtHarga.getText()));
        pjl.addPenjualan(pjl1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPembelian.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnAdd.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void deleteData(ActionEvent event) throws IOException {
        Penjualan pjl1 = new Penjualan(txtId.getText());
        pjl.deletePenjualan(pjl1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPembelian.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnDelete.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public ObservableList<Penjualan> getListPenjualan(){
        ObservableList<Penjualan> listPenjualan = FXCollections.observableArrayList();
        Connection CONN = getConnection();
        String query = "SELECT * FROM data_pembelian;";
        Statement st;
        ResultSet rs;

        try{
            st = CONN.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Penjualan pjl1 = new Penjualan(rs.getInt("jumlah"), rs.getString("nama"),
                                                rs.getString("kode"), rs.getDouble("harga"));
                listPenjualan.add(pjl1);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return listPenjualan;
    }

    public void showPenjualan(){
        ObservableList<Penjualan> list = getListPenjualan();
        kolomId.setCellValueFactory(new PropertyValueFactory<Penjualan, String>("idBarcode"));
        kolomNama.setCellValueFactory(new PropertyValueFactory<Penjualan, String>("namaProduk"));
        kolomHarga.setCellValueFactory(new PropertyValueFactory<Penjualan, Double>("harga"));
        kolomJumlah.setCellValueFactory(new PropertyValueFactory<Penjualan, Integer>("jumlahProduk"));

        tableView.setItems(list);
    }

}
