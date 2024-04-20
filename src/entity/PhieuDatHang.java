package entity;

import java.util.Date;

public class PhieuDatHang {
    private String maPhieu;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private Date ngayLap;
    private Date ngayNhanHangDuKien;    

    public PhieuDatHang() {
    }

    public PhieuDatHang(String maPhieu) {
        this.maPhieu = maPhieu;
    }

	public PhieuDatHang(String maPhieu, KhachHang khachHang, NhanVien nhanVien, Date ngayLap,
			Date ngayNhanHangDuKien) {
		super();
		this.maPhieu = maPhieu;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayLap = ngayLap;
		this.ngayNhanHangDuKien = ngayNhanHangDuKien;
	}

	public String getmaPhieu() {
		return maPhieu;
	}

	public void setmaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
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

	public Date getngayLap() {
		return ngayLap;
	}

	public void setngayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public Date getNgayNhanHangDuKien() {
		return ngayNhanHangDuKien;
	}

	public void setNgayNhanHangDuKien(Date ngayNhanHangDuKien) {
		this.ngayNhanHangDuKien = ngayNhanHangDuKien;
	}

	@Override
	public String toString() {
		return "HoaDonDatHang [maPhieu=" + maPhieu + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien
				+ ", ngayLap=" + ngayLap + ", ngayNhanHangDuKien=" + ngayNhanHangDuKien + "]";
	}

    
}
