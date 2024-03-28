	package entity;
	
	public class DanhMucLinhKien {
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
		public DanhMucLinhKien(String maDanhMuc, String tenDanhMuc) {
			setMaDanhMuc(maDanhMuc);
			setTenDanhMuc(tenDanhMuc);
		}
		public DanhMucLinhKien(){
			
		}
		public DanhMucLinhKien(String ma){
			setMaDanhMuc(ma);
		}

		@Override
		public String toString() {
			return "DanhMucLinhKien{" +
					"maDanhMuc='" + maDanhMuc + '\'' +
					", tenDanhMuc='" + tenDanhMuc + '\'' +
					'}';
		}
	}
