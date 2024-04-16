package entity;

public class ChiTietHoaDonDatHang {
    private SanPham sanPham;
    private int soLuong;
    private HoaDonDatHang hoaDon;

    public ChiTietHoaDonDatHang() {
    }

    public ChiTietHoaDonDatHang(SanPham sanPham, HoaDonDatHang hoaDon, int soLuong) {
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

    public HoaDonDatHang getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDonDatHang hoaDon) {
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
