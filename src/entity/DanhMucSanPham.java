	package entity;
	
	public class DanhMucSanPham {
		private String maDanhMuc;
		private String tenDanhMuc;
		public String getMaDanhMuc() {
			return maDanhMuc;
		}
		public String getTenDanhMuc() {
			return tenDanhMuc;
		}
		
		public void setMaDanhMuc(String maDanhMuc) {
			this.maDanhMuc = maDanhMuc;
		}
		public void setTenDanhMuc(String tenDanhMuc) {
			this.tenDanhMuc = tenDanhMuc;
		}
		public DanhMucSanPham(String maDanhMuc, String tenDanhMuc) {
			setMaDanhMuc(maDanhMuc);
			setTenDanhMuc(tenDanhMuc);
		}
		public DanhMucSanPham(){
			
		}
		public DanhMucSanPham(String ma){
			setMaDanhMuc(ma);
		}

		@Override
		public String toString() {
			return "DanhMucSanPham{" +
					"maDanhMuc='" + maDanhMuc + '\'' +
					", tenDanhMuc='" + tenDanhMuc + '\'' +
					'}';
		}
	}
