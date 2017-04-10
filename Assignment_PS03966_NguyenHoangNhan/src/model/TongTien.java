package model;

public class TongTien {
	String ten;
	double tongtien;
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public double getTongtien() {
		return tongtien;
	}
	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}
	public TongTien(String ten, double tongtien) {
		super();
		this.ten = ten;
		this.tongtien = tongtien;
	}
	public TongTien() {
		super();
	}
	
}
