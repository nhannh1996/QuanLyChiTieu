package model;

public class ThongKe {
	String tengiaodich;
	double tiengiaodich;
	public String getTengiaodich() {
		return tengiaodich;
	}
	public void setTengiaodich(String tengiaodich) {
		this.tengiaodich = tengiaodich;
	}
	public double getTiengiaodich() {
		return tiengiaodich;
	}
	public void setTiengiaodich(double tiengiaodich) {
		this.tiengiaodich = tiengiaodich;
	}
	public ThongKe(String tengiaodich, double tiengiaodich) {
		super();
		this.tengiaodich = tengiaodich;
		this.tiengiaodich = tiengiaodich;
	}
	public ThongKe() {
	}
	
}
