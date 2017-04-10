package model;

public class GiaoDich {
	int id_giaodich;
	String ngaygiaodich;
	Double tiengiaodich;
	int id_ThuChi;
	String motagiaodich;
	String ghichu;
	 
	public GiaoDich() {

	}
	
	public GiaoDich(int id_giaodich, String ngaygiaodich, Double tiengiaodich, int id_ThuChi, String motagiaodich,
			String ghichu) {
		super();
		this.id_giaodich = id_giaodich;
		this.ngaygiaodich = ngaygiaodich;
		this.tiengiaodich = tiengiaodich;
		this.id_ThuChi = id_ThuChi;
		this.motagiaodich = motagiaodich;
		this.ghichu = ghichu;
	}

	public GiaoDich(String ngaygiaodich, Double tiengiaodich, int id_ThuChi, String motagiaodich, String ghichu) {
		this.ngaygiaodich = ngaygiaodich;
		this.tiengiaodich = tiengiaodich;
		this.id_ThuChi = id_ThuChi;
		this.motagiaodich = motagiaodich;
		this.ghichu = ghichu;
	}

	public int getId_giaodich() {
		return id_giaodich;
	}

	public void setId_giaodich(int id_giaodich) {
		this.id_giaodich = id_giaodich;
	}

	public String getNgaygiaodich() {
		return ngaygiaodich;
	}

	public void setNgaygiaodich(String ngaygiaodich) {
		this.ngaygiaodich = ngaygiaodich;
	}

	public Double getTiengiaodich() {
		return tiengiaodich;
	}

	public void setTiengiaodich(Double tiengiaodich) {
		this.tiengiaodich = tiengiaodich;
	}

	public int getId_ThuChi() {
		return id_ThuChi;
	}

	public void setId_ThuChi(int id_ThuChi) {
		this.id_ThuChi = id_ThuChi;
	}

	public String getMotagiaodich() {
		return motagiaodich;
	}

	public void setMotagiaodich(String motagiaodich) {
		this.motagiaodich = motagiaodich;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
	
	
	
	
}