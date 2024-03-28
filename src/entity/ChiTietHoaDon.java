package entity;

public class ChiTietHoaDon {
    private LinhKien linhKien;
    private int soLuong;
    private HoaDon hoaDon;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(LinhKien linhKien, HoaDon hoaDon, int soLuong) {
        this.linhKien = linhKien;
        this.soLuong = soLuong;
        this.hoaDon = hoaDon;
    }



    public LinhKien getLinhKien() {
        return linhKien;
    }

    public void setLinhKien(LinhKien linhKien) {
        this.linhKien = linhKien;
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
                "linhKien=" + linhKien +
                ", soLuong=" + soLuong +
                ", hoaDon=" + hoaDon +
                '}';
    }
}
