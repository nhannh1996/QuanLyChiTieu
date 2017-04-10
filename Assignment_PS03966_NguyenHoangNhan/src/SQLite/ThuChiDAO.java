package SQLite;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import model.ThuChi;

public class ThuChiDAO {
	SQLiteDatabase db;
	public ThuChiDAO(Context context){
		myDB database = new myDB(context);
		db = database.getWritableDatabase();
	}
	public long insertThuChi(ThuChi tc){
		ContentValues values = new ContentValues();
		values.put("tengiaodich", tc.getTengiaodich() );
		values.put("thuchi", tc.getThuchi());
		return db.insert("ThuChi", null, values);
	}
	public int updatThuChi(ThuChi tc){
		ContentValues values = new ContentValues();
		values.put("tengiaodich", tc.getTengiaodich() );
		values.put("thuchi", tc.getThuchi());
		return db.update("ThuChi", values,"id_ThuChi=?", new String[]{tc.getId_thuchi()+""} );
	}
	public long deleteThuChi(int id){
		return db.delete("ThuChi", "id_ThuChi=?", new String[]{id+""});
	}

	public ArrayList<ThuChi> getThuChi(){
		ArrayList<ThuChi> list = new ArrayList<ThuChi>();
		Cursor c = db.rawQuery("select *  from ThuChi",null);
		if(c.moveToFirst()){
			do{
				int x = c.getInt(0);
				String ten = c.getString(1);
				int loai = c.getInt(2);				
				ThuChi tc = new ThuChi(x,ten,loai);
				list.add(tc);
			}while(c.moveToNext());
		}
		return list;
	}
	public ArrayList<ThuChi> getThuChiThu(){
		ArrayList<ThuChi> list = new ArrayList<ThuChi>();
		Cursor c = db.rawQuery("select *  from ThuChi where thuchi = 1",null);
		if(c.moveToFirst()){
			do{
				int x = c.getInt(0);
				String ten = c.getString(1);
				int loai = c.getInt(2);				
				ThuChi tc = new ThuChi(x,ten,loai);
				list.add(tc);
			}while(c.moveToNext());
		}
		return list;
	}
	public ArrayList<ThuChi> getThuChiChi(){
		ArrayList<ThuChi> list = new ArrayList<ThuChi>();
		Cursor c = db.rawQuery("select *  from ThuChi where thuchi = 2",null);
		if(c.moveToFirst()){
			do{
				int x = c.getInt(0);
				String ten = c.getString(1);
				int loai = c.getInt(2);				
				ThuChi tc = new ThuChi(x,ten,loai);
				list.add(tc);
			}while(c.moveToNext());
		}
		return list;
	}
	public ArrayList<ThuChi> getThuChiTenGiaoDich(){
		ArrayList<ThuChi> list = new ArrayList<ThuChi>();
		Cursor c = db.rawQuery("select *  from ThuChi",null);
		if(c.moveToFirst()){
			do{
				String ten = c.getString(1);		
				ThuChi tc = new ThuChi(ten);
				list.add(tc);
			}while(c.moveToNext());
		}
		return list;
	}

	
}
