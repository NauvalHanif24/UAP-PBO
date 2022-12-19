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

public class KategoriController implements Initializable {

    KategoriModel ktg = new KategoriModel();

    @FXML
    private TextField TextNama;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBarang;

    @FXML
    private Button btnData;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnKategori;

    @FXML
    private Button btnMakanan;

    @FXML
    private Button btndelete;

    @FXML
    private TableColumn<Kategori, String> kolomnAMA;

    @FXML
    private TableView<Kategori> viewTable;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showKategori();
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
    void adddata(ActionEvent event) throws IOException {
        Kategori ktg1 = new Kategori(TextNama.getText());
        ktg.addKategori(ktg1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Kategori.fxml"));
        Parent root = (Parent) loader.load(); 
        Stage stage = (Stage) btnMakanan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void deletedata(ActionEvent event) throws IOException {
        Kategori ktg1 = new Kategori(TextNama.getText());
        ktg.deleteKategori(ktg1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Kategori.fxml"));
        Parent root = (Parent) loader.load(); 
        Stage stage = (Stage) btnMakanan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public ObservableList<Kategori> getListKategori(){
        ObservableList<Kategori> listKategori = FXCollections.observableArrayList();
        Connection CONN = getConnection();
        String query = "SELECT * FROM kategori;";
        Statement st;
        ResultSet rs;

        try{
            st = CONN.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Kategori ktg1 = new Kategori(rs.getString("nama_kategori"));
                listKategori.add(ktg1);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return listKategori;
    }

    public void showKategori(){
        ObservableList<Kategori> list = getListKategori();
        kolomnAMA.setCellValueFactory(new PropertyValueFactory<Kategori, String>("nama_kategori"));

        viewTable.setItems(list);
    }

}
