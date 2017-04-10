package model;

public class showGiaoDich {
	int id;
	String ngay;
	double tien;
	String tengiaodich;

	
	public showGiaoDich(int id, String ngay, double tien, String tengiaodich) {
		this.id = id;
		this.ngay = ngay;
		this.tien = tien;
		this.tengiaodich = tengiaodich;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public showGiaoDich() {
	
	}
	public String getNgay() {
		return ngay;
	}
	public void setNgay(String ngay) {
		this.ngay = ngay;
	}
	public double getTien() {
		return tien;
	}
	public void setTien(double tien) {
		this.tien = tien;
	}
	public String getTengiaodich() {
		return tengiaodich;
	}
	public void setTengiaodich(String tengiaodich) {
		this.tengiaodich = tengiaodich;
	}
	
}
