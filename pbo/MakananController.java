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

public class MakananController implements Initializable {

    MakananModel mkn = new MakananModel();

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
    private Button btnadd;

    @FXML
    private Button btndelete;

    @FXML
    private TextField fielddaya;

    @FXML
    private TextField fielddiskon;

    @FXML
    private TextField fieldharga;

    @FXML
    private TextField fieldid;

    @FXML
    private TextField fieldjumlah;

    @FXML
    private TextField fieldnama;

    @FXML
    private TableColumn<Makanan, Integer> kolomdayatahan;

    @FXML
    private TableColumn<Makanan, Double> kolomdiskon;

    @FXML
    private TableColumn<Makanan, Double> kolomharga;

    @FXML
    private TableColumn<Makanan, Integer> kolomid;

    @FXML
    private TableColumn<Makanan, Integer> kolomjumlah;

    @FXML
    private TableColumn<Makanan, String> kolomnama;

    @FXML
    private TableView<Makanan> viewTable;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showMakanan();
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
    void deletedata(ActionEvent event) throws IOException {
        Makanan mkn1 = new Makanan(Integer.parseInt(fieldid.getText()));
        mkn.deleteMakanan(mkn1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Makanan.fxml"));
        Parent root = (Parent) loader.load(); 
        Stage stage = (Stage) btnMakanan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void inputdata(ActionEvent event) throws IOException {
        Makanan mkn1 = new Makanan(fieldnama.getText(), Double.parseDouble(fieldharga.getText()),
                                    Double.parseDouble(fielddiskon.getText()), Integer.parseInt(fieldjumlah.getText()),
                                    Integer.parseInt(fieldid.getText()), Integer.parseInt(fielddaya.getText()));
        mkn.addMakanan(mkn1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Makanan.fxml"));
        Parent root = (Parent) loader.load(); 
        Stage stage = (Stage) btnMakanan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public ObservableList<Makanan> getListMakanan(){
        ObservableList<Makanan> listMakanan = FXCollections.observableArrayList();
        Connection CONN = getConnection();
        String query = "SELECT * FROM makanan;";
        Statement st;
        ResultSet rs;

        try{
            st = CONN.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Makanan mkn = new Makanan(rs.getString("nama_produk"), rs.getDouble("harga"),
                                            rs.getDouble("diskon"), rs.getInt("jumlah"),
                                            rs.getInt("id"), rs.getInt("daya_tahan"));
                listMakanan.add(mkn);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return listMakanan;
    }

    public void showMakanan(){
        ObservableList<Makanan> list = getListMakanan();
        kolomnama.setCellValueFactory(new PropertyValueFactory<Makanan, String>("nama_produk"));
        kolomharga.setCellValueFactory(new PropertyValueFactory<Makanan, Double>("harga"));
        kolomdiskon.setCellValueFactory(new PropertyValueFactory<Makanan, Double>("diskon"));
        kolomjumlah.setCellValueFactory(new PropertyValueFactory<Makanan, Integer>("jumlah"));
        kolomid.setCellValueFactory(new PropertyValueFactory<Makanan, Integer>("id"));
        kolomdayatahan.setCellValueFactory(new PropertyValueFactory<Makanan, Integer>("daya_tahan"));

        viewTable.setItems(list);
    }

}
