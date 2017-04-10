package model;

public class ThuChi {
	int id_thuchi;
	String tengiaodich;
	int thuchi;
	
	public ThuChi() {	
	}
	
	public ThuChi(String tengiaodich) {
		this.tengiaodich = tengiaodich;
	}
	
		
	
	public ThuChi(int id_thuchi, String tengiaodich) {
		this.id_thuchi = id_thuchi;
		this.tengiaodich = tengiaodich;
	}

	public ThuChi(int id_thuchi, String tengiaodich, int thuchi) {
	
		this.id_thuchi = id_thuchi;
		this.tengiaodich = tengiaodich;
		this.thuchi = thuchi;
	}

	public ThuChi(int thuchi) {
		this.thuchi = thuchi;
	}

	public ThuChi(String tengiaodich, int thuchi) {
		super();
		this.tengiaodich = tengiaodich;
		this.thuchi = thuchi;
	}

	public int getId_thuchi() {
		return id_thuchi;
	}

	public void setId_thuchi(int id_thuchi) {
		this.id_thuchi = id_thuchi;
	}

	public String getTengiaodich() {
		return tengiaodich;
	}
	public void setTengiaodich(String tengiaodich) {
		this.tengiaodich = tengiaodich;
	}
	public int getThuchi() {
		return thuchi;
	}
	public void setThuchi(int thuchi) {
		this.thuchi = thuchi;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tengiaodich;
	}

	
	
	
}
