package entity;

public class KhachHang {
    private String maKH;
	private String tenKH;
	private String diaChiKH;
	private String eMail;
	private String soDT;

	public KhachHang(String maKH, String tenKH, String diaChiKH, String eMail, String soDT) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diaChiKH = diaChiKH;
		this.eMail = eMail;
		this.soDT = soDT;
	}

	public KhachHang(String maKH) {
		this.maKH = maKH;
	}

	public KhachHang() {
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getDiaChiKH() {
		return diaChiKH;
	}

	public void setDiaChiKH(String diaChiKH) {
		this.diaChiKH = diaChiKH;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	@Override
	public String toString() {
		return "KhachHang{" +
				"maKH='" + maKH + '\'' +
				", tenKH='" + tenKH + '\'' +
				", diaChi='" + diaChiKH + '\'' +
				", eMail='" + eMail + '\'' +
				", soDienThoai='" + soDT + '\'' +
				'}';
	}
}
