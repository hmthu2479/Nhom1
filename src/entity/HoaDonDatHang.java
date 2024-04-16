package entity;

import java.util.Date;

public class HoaDonDatHang {
    private String maHoaDon;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private Date ngayLapHD;
    private Date ngayNhanHangDuKien;    

    public HoaDonDatHang() {
    }

    public HoaDonDatHang(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

	public HoaDonDatHang(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, Date ngayLapHD,
			Date ngayNhanHangDuKien) {
		super();
		this.maHoaDon = maHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayLapHD = ngayLapHD;
		this.ngayNhanHangDuKien = ngayNhanHangDuKien;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Date getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(Date ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public Date getNgayNhanHangDuKien() {
		return ngayNhanHangDuKien;
	}

	public void setNgayNhanHangDuKien(Date ngayNhanHangDuKien) {
		this.ngayNhanHangDuKien = ngayNhanHangDuKien;
	}

	@Override
	public String toString() {
		return "HoaDonDatHang [maHoaDon=" + maHoaDon + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien
				+ ", ngayLapHD=" + ngayLapHD + ", ngayNhanHangDuKien=" + ngayNhanHangDuKien + "]";
	}

    
}
