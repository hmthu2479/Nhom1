package entity;

public class ChiTietHoaDon {
    private SanPham sanPham;
    private int soLuong;
    private HoaDon hoaDon;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(SanPham sanPham, HoaDon hoaDon, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.hoaDon = hoaDon;
    }



    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "sanPham=" + sanPham +
                ", soLuong=" + soLuong +
                ", hoaDon=" + hoaDon +
                '}';
    }
}
