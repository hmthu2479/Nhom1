package entity;

import java.sql.Date;
import java.util.Objects;

public class DonDatHang {
	private String maDonDatHang;
	private KhachHang maKH;
	private NhanVien maNV;
	private Date ngayDat,ngayGiao,ngayChuyen;;
	private String noiNhan;
	public DonDatHang() {
		super();
	}

	public DonDatHang(String maDonDatHang) {
		this.maDonDatHang = maDonDatHang;
	}

	public DonDatHang(String maDonDatHang, KhachHang maKH, NhanVien maNV, Date ngayDat, Date ngayGiao, Date ngayChuyen,
					  String noiNhan) {
		super();
		this.maDonDatHang = maDonDatHang;
		this.maKH = maKH;
		this.maNV = maNV;
		this.ngayDat = ngayDat;
		this.ngayGiao = ngayGiao;
		this.ngayChuyen = ngayChuyen;
		this.noiNhan = noiNhan;
	}
	public String getMaDonDatHang() {
		return maDonDatHang;
	}
	public void setMaDonDatHang(String maDonDatHang) {
		this.maDonDatHang = maDonDatHang;
	}
	public KhachHang getMaKH() {
		return maKH;
	}
	public void setMaKH(KhachHang maKH) {
		this.maKH = maKH;
	}
	public NhanVien getMaNV() {
		return maNV;
	}
	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}
	public Date getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}
	public Date getNgayGiao() {
		return ngayGiao;
	}
	public void setNgayGiao(Date ngayGiao) {
		this.ngayGiao = ngayGiao;
	}
	public Date getNgayChuyen() {
		return ngayChuyen;
	}
	public void setNgayChuyen(Date ngayChuyen) {
		this.ngayChuyen = ngayChuyen;
	}
	public String getNoiNhan() {
		return noiNhan;
	}
	public void setNoiNhan(String noiNhan) {
		this.noiNhan = noiNhan;
	}
	@Override
	public String toString() {
		return "DonDatHang [maDonDatHang=" + maDonDatHang + ", ngayDat=" + ngayDat + ", ngayGiao=" + ngayGiao
				+ ", ngayChuyen=" + ngayChuyen + ", noiNhan=" + noiNhan + "]";
	}

}
