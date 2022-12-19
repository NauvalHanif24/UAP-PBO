package pbo;

public class Makanan extends Produk{
    private int id;
    private int daya_tahan;

    public Makanan(String nama_produk, double harga, double diskon, int jumlah, int id, int daya_tahan) {
        super(nama_produk, harga, diskon, jumlah);
        this.id = id;
        this.daya_tahan = daya_tahan;
    }

    public Makanan(int id) {
        this.id = id;
    }

    public Makanan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDaya_tahan() {
        return daya_tahan;
    }

    public void setDaya_tahan(int daya_tahan) {
        this.daya_tahan = daya_tahan;
    }
}
