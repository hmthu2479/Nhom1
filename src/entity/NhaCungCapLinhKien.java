package entity;

public class NhaCungCapLinhKien {
	private String MaNhaCungCap,TenNCC,DiaChi,SoDienThoai;

	public String getMaNhaCungCap() {
		return MaNhaCungCap;
	}

	public String getTenNCC() {
		return TenNCC;
	}

	public String getDiaCHi() {
		return DiaChi;
	}

	public String getSoDienThoai() {
		return SoDienThoai;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public void setMaNhaCungCap(String maNhaCungCap) {
		MaNhaCungCap = maNhaCungCap;
	}

	public void setTenNCC(String tenNCC) {
		TenNCC = tenNCC;
	}

	public void setSoDienThoai(String soDienThoai) {
		SoDienThoai = soDienThoai;
	}

	public NhaCungCapLinhKien(String maNhaCungCap, String tenNCC, String diaChi,
                              String soDienThoai) {
		setMaNhaCungCap(maNhaCungCap);
		setTenNCC(tenNCC);
		setDiaChi(diaChi);
		setSoDienThoai(soDienThoai);
	}
	public NhaCungCapLinhKien(String ma){
		setMaNhaCungCap(ma);
	}
	public NhaCungCapLinhKien(){

	}

	@Override
	public String toString() {
		return "NhaCungCapLinhKien{" +
				"MaNhaCungCap='" + MaNhaCungCap + '\'' +
				", TenNCC='" + TenNCC + '\'' +
				", DiaChi='" + DiaChi + '\'' +
				", SoDienThoai='" + SoDienThoai + '\'' +
				'}';
	}
}
