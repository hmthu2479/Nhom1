package entity;

public class Ke {
	private String maKe;
	private String tenKe;
	private String vitri;
	
	public String getMaKe() {
		return maKe;
	}
	public void setMaKe(String maKe) {
		this.maKe = maKe;
	}
	public String getTenKe() {
		return tenKe;
	}
	public void setTenKe(String tenKe) {
		this.tenKe = tenKe;
	}
	public String getVitri() {
		return vitri;
	}
	public void setVitri(String vitri) {
		this.vitri = vitri;
	}
	public Ke(String maKe) {
		setMaKe(maKe);
	}
	public Ke() {
		
	}
	public Ke(String maKe, String tenKe, String vitri) {
		setMaKe(maKe);
		setTenKe(tenKe);
		setVitri(vitri);
	}
	@Override
	public String toString() {
		return "Ke [maKe=" + maKe + ", tenKe=" + tenKe + ", vitri=" + vitri + "]";
	}
	
}
