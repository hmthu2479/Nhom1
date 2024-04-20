package entity;

public class ChiTietPhieuDatHang {
    private SanPham sanPham;
    private int soLuong;
    private PhieuDatHang phieu;

    public ChiTietPhieuDatHang() {
    }

    public ChiTietPhieuDatHang(SanPham sanPham, PhieuDatHang phieu, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.phieu = phieu;
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

    public PhieuDatHang getphieu() {
        return phieu;
    }

    public void setphieu(PhieuDatHang phieu) {
        this.phieu = phieu;
    }

	@Override
	public String toString() {
		return "ChiTietPhieuDatHang [sanPham=" + sanPham + ", soLuong=" + soLuong + ", phieu=" + phieu + "]";
	}

   
}
