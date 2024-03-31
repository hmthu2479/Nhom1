package entity;

public class NhaCungCapSanPham {
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

	public NhaCungCapSanPham(String maNhaCungCap, String tenNCC, String diaChi,
                              String soDienThoai) {
		setMaNhaCungCap(maNhaCungCap);
		setTenNCC(tenNCC);
		setDiaChi(diaChi);
		setSoDienThoai(soDienThoai);
	}
	public NhaCungCapSanPham(String ma){
		setMaNhaCungCap(ma);
	}
	public NhaCungCapSanPham(){

	}

	@Override
	public String toString() {
		return "NhaCungCapSanPham{" +
				"MaNhaCungCap='" + MaNhaCungCap + '\'' +
				", TenNCC='" + TenNCC + '\'' +
				", DiaChi='" + DiaChi + '\'' +
				", SoDienThoai='" + SoDienThoai + '\'' +
				'}';
	}
}
