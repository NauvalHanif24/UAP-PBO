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

public class BarangController implements Initializable {

    BarangModel brg = new BarangModel();

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
    private Button btnadd;

    @FXML
    private TableColumn<Barang, String> kolombarcode;

    @FXML
    private TableColumn<Barang, Double> kolomdiskon;

    @FXML
    private TableColumn<Barang, String> kolomexpired;

    @FXML
    private TableColumn<Barang, Double> kolomharga;

    @FXML
    private TableColumn<Barang, Integer> kolomjumlah;

    @FXML
    private TableColumn<Barang, String> kolomkategori;

    @FXML
    private TableColumn<Barang, String> kolomnama;

    @FXML
    private TextField textBarcode;

    @FXML
    private TextField textExpired;

    @FXML
    private TextField textKategori;

    @FXML
    private TextField textNama;

    @FXML
    private TextField textdiskon;

    @FXML
    private TextField textharrga;

    @FXML
    private TextField textjumlah;

    @FXML
    private TableView<Barang> viewTable;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showBarang();
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
    void deleteData(ActionEvent event) throws IOException {
        Barang brg1 = new Barang(textBarcode.getText());
        brg.deleteBarang(brg1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Barang.fxml"));
        Parent root = (Parent) loader.load(); 
        Stage stage = (Stage) btnDelete.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void inpudata(ActionEvent event) throws IOException {
        Barang brg1 = new Barang(textNama.getText(), Double.parseDouble(textharrga.getText()),
                                Double.parseDouble(textdiskon.getText()), Integer.parseInt(textjumlah.getText()),
                                textBarcode.getText(), textExpired.getText(), textKategori.getText());
        brg.addBarang(brg1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Barang.fxml"));
        Parent root = (Parent) loader.load(); 
        Stage stage = (Stage) btnadd.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public ObservableList<Barang> getListBarang(){
        ObservableList<Barang> listBarang = FXCollections.observableArrayList();
        Connection CONN = getConnection();
        String query = "SELECT * FROM barang;";
        Statement st;
        ResultSet rs;

        try{
            st = CONN.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Barang brg1 = new Barang(rs.getString("nama_produk"), rs.getDouble("harga"), 
                                        rs.getDouble("diskon"), rs.getInt("jumlah"), 
                                        rs.getString("barcode"), rs.getString("expired"),
                                        rs.getString("kategori"));
                listBarang.add(brg1);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return listBarang;
    }

    public void showBarang(){
        ObservableList<Barang> list = getListBarang();
        kolomnama.setCellValueFactory(new PropertyValueFactory<Barang, String>("nama_produk"));
        kolomharga.setCellValueFactory(new PropertyValueFactory<Barang, Double>("harga"));
        kolomdiskon.setCellValueFactory(new PropertyValueFactory<Barang, Double>("diskon"));
        kolomjumlah.setCellValueFactory(new PropertyValueFactory<Barang, Integer>("jumlah"));
        kolombarcode.setCellValueFactory(new PropertyValueFactory<Barang, String>("barcode"));
        kolomexpired.setCellValueFactory(new PropertyValueFactory<Barang, String>("expired"));
        kolomkategori.setCellValueFactory(new PropertyValueFactory<Barang, String>("category"));

        viewTable.setItems(list);
    }

}
