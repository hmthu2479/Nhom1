package entity;

import java.util.Date;

public class NhanVien {
    private String maNhanVien;
	private String hoTen;
	private Date ngaySinh;
	private String diaChi;
	private String soDT;
	private String eMail;

	public NhanVien(String maNhanVien, String hoTen, Date ngaySinh, String diaChi, String soDT, String eMail) {
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.soDT = soDT;
		this.eMail = eMail;
	}

	public NhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public NhanVien() {
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@Override
	public String toString() {
		return "NhanVien{" +
				"maNhanVien='" + maNhanVien + '\'' +
				", hoTen='" + hoTen + '\'' +
				", ngaySinh=" + ngaySinh +
				", diaChi='" + diaChi + '\'' +
				", soDT='" + soDT + '\'' +
				", eMail='" + eMail + '\'' +
				'}';
	}
}
