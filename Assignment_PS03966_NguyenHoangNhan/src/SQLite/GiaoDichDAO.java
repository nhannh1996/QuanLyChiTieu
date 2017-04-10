package SQLite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import model.GiaoDich;
import model.ThongKe;
import model.showGiaoDich;

public class GiaoDichDAO {
	SQLiteDatabase db;
	public GiaoDichDAO(Context context){
		myDB database  = new myDB(context);
		db = database.getWritableDatabase();
	}
	public long insertGiaoDich(GiaoDich gd){
		ContentValues values = new ContentValues();
		values.put("ngaygiaodich", gd.getNgaygiaodich());
		values.put("tiengiaodich", gd.getTiengiaodich());
		values.put("id_ThuChi", gd.getId_ThuChi());
		values.put("motagiaodich", gd.getMotagiaodich());		
		values.put("ghichu", gd.getGhichu());
		return db.insert("GiaoDich", null, values);
	}
	public int updatGiaoDich(GiaoDich gd){
		ContentValues values = new ContentValues();
		values.put("ngaygiaodich", gd.getNgaygiaodich());
		values.put("tiengiaodich", gd.getTiengiaodich());
		values.put("id_ThuChi", gd.getId_ThuChi());
		values.put("motagiaodich", gd.getMotagiaodich());		
		values.put("ghichu", gd.getGhichu());
		return db.update("GiaoDich", values, "id_giaodich=?", new String[]{gd.getId_giaodich()+""});
	}
	public long deleteGiaoDich(int id){
		return db.delete("GiaoDich","id_giaodich=?" , new String[]{id+""});
	}
	public double getTienGiaoDich(){
		double tien1 = 0,tien2=0;
		Cursor c1 = db.rawQuery("select sum(tiengiaodich) from giaodich,thuchi where giaodich.id_ThuChi = thuchi.id_ThuChi and thuchi =1", null);
		Cursor c2 = db.rawQuery("select sum(tiengiaodich) from giaodich,thuchi where giaodich.id_ThuChi = thuchi.id_ThuChi and thuchi =2", null);
		
		if(c1.moveToFirst()){
			do{
				tien1 = c1.getDouble(0);
				
			}while(c1.moveToNext());
		}
		if(c2.moveToFirst()){
			do{
				tien2 = c2.getDouble(0);
				
			}while(c2.moveToNext());
		}
		return tien1-tien2;
	}
	public ArrayList<GiaoDich> getGiaoDich(){
		ArrayList<GiaoDich> list = new ArrayList<GiaoDich>();
		Cursor c = db.rawQuery("select * from GiaoDich",null);
		if(c.moveToFirst()){
			do{
				int id = c.getInt(0);
				String ngay = c.getString(1);
				double tien = c.getDouble(2);
				int id_thuchi = c.getInt(3);
				String motagd = c.getString(4);
				String ghichu = c.getString(5);
				GiaoDich gd = new GiaoDich(id,ngay,tien,id_thuchi,motagd,ghichu);
				list.add(gd);
			}while(c.moveToNext());
		}
		return list;
	}
	public ArrayList<showGiaoDich> getShowGD1(){
		ArrayList<showGiaoDich> list = new ArrayList<showGiaoDich>();
		Cursor c = db.rawQuery("select * from GiaoDich",null);
		if(c.moveToFirst()){
			do{
				int id = c.getInt(0);
				String ngay = c.getString(1);
				double tien = c.getDouble(2);
				String ten = String.valueOf(c.getInt(3));
				showGiaoDich show = new showGiaoDich(id,ngay,tien,ten);
				list.add(show);
			}while(c.moveToNext());
		}
		return list;
	}
	
	public ArrayList<showGiaoDich> getShowGD2(String tungay,String denngay){
		ArrayList<showGiaoDich> list = new ArrayList<showGiaoDich>();
		Cursor c = db.rawQuery("select * from GiaoDich where ngaygiaodich between '"+tungay+"' and '"+denngay+"'",null);
		if(c.moveToFirst()){
			do{
				int id = c.getInt(0);
				String ngay = c.getString(1);
				double tien = c.getDouble(2);
				String ten = String.valueOf(c.getInt(3));
				showGiaoDich show = new showGiaoDich(id,ngay,tien,ten);
				list.add(show);
			}while(c.moveToNext());
		}
		return list;
	}

	public ArrayList<ThongKe> getThongKe(int thuchi,String nam,String ngaybatdau,String ngayketthuc){
		ArrayList<ThongKe> list = new ArrayList<ThongKe>();
		Cursor c = db.rawQuery("SELECT tengiaodich,tiengiaodich FROM giaodich,thuchi "
				+ "where giaodich.id_thuchi = thuchi.id_thuchi  "
				+ "and thuchi.thuchi = "+ thuchi+" "
				+ "and giaodich.ngaygiaodich between '"+nam+"/"+ngaybatdau+"' and '"+nam+"/"+ngayketthuc+"'",null);
		if(c.moveToFirst()){
			do{;
				String ten = c.getString(0);
				double tien = c.getDouble(1);
				ThongKe tk = new ThongKe(ten,tien);
				list.add(tk);
			}while(c.moveToNext());
		}
		return list;
	}

	public double getThongKeSumTien(int thuchi,String nam,String ngaybatdau,String ngayketthuc){
		double tongtien = 0;
		Cursor c = db.rawQuery("select sum(tiengiaodich) from giaodich,thuchi where giaodich.id_thuchi = thuchi.id_thuchi "
				+ "and thuchi.thuchi = "+thuchi+" and ngaygiaodich between '"+nam+"/"+ngaybatdau+"' and '"+nam+"/"+ngayketthuc+"'",null);
		if(c.moveToFirst()){
			do{
				tongtien = c.getDouble(0);
			}while(c.moveToNext());
		}
		return tongtien;
	}
}
