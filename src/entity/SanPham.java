package entity;

public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double giaBan;
    private DanhMucSanPham danhMucSanPham;
    private NhaCungCapSanPham nhaCungCapSanPham;
    private Ke ke;

    
    public SanPham(String maSanPham, String tenSanPham, int soLuong, double giaBan, DanhMucSanPham danhMucSanPham,
			NhaCungCapSanPham nhaCungCapSanPham, Ke ke) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.danhMucSanPham = danhMucSanPham;
		this.nhaCungCapSanPham = nhaCungCapSanPham;
		this.ke = ke;
	}

	public SanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public SanPham() {
    }

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public DanhMucSanPham getDanhMucSanPham() {
		return danhMucSanPham;
	}

	public void setDanhMucSanPham(DanhMucSanPham danhMucSanPham) {
		this.danhMucSanPham = danhMucSanPham;
	}

	public NhaCungCapSanPham getNhaCungCapSanPham() {
		return nhaCungCapSanPham;
	}

	public void setNhaCungCapSanPham(NhaCungCapSanPham nhaCungCapSanPham) {
		this.nhaCungCapSanPham = nhaCungCapSanPham;
	}

	public Ke getKe() {
		return ke;
	}

	public void setKe(Ke ke) {
		this.ke = ke;
	}

	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", soLuong=" + soLuong + ", giaBan="
				+ giaBan + ", danhMucSanPham=" + danhMucSanPham + ", nhaCungCapSanPham=" + nhaCungCapSanPham + ", ke="
				+ ke + "]";
	}


}

