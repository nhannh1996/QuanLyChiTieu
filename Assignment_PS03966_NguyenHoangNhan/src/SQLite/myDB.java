package SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDB extends SQLiteOpenHelper {

	public myDB(Context context) {
		super(context, "DataBaseThietKeGiaoDienAndroid", null, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql1 = "create table ThuChi("
				+ "id_ThuChi integer primary key autoincrement,"
				+ "tengiaodich text,"
				+ "thuchi int)";
		String sql2 = "create table GiaoDich("
				+ "id_giaodich integer primary key autoincrement,"
				+ "ngaygiaodich date,"
				+ "tiengiaodich double,"
				+ "id_ThuChi integer not null constraint id_ThuChi references ThuChi(id_ThuChi) on delete cascade,"
				+ "motagiaodich string,"				
				+ "ghichu text)";
		db.execSQL(sql1);
		db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists DataBaseThietKeGiaoDienAndroid");
	}
	
}
