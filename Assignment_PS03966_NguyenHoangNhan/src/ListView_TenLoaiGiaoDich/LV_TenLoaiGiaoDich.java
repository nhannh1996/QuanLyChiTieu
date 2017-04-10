
package ListView_TenLoaiGiaoDich;

import java.util.ArrayList;

import com.example.assignment_ps03966_nguyenhoangnhan.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import model.ThuChi;

public class LV_TenLoaiGiaoDich extends BaseAdapter {
	ArrayList<ThuChi> list;
	Context context;
	public LV_TenLoaiGiaoDich(Context c, ArrayList<ThuChi> l){
		list = l;
		context = c;
	}
	class View_Mot_O{
		TextView tv1,tv2;
	}
	@Override 
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup parent) {
		// TODO Auto-generated method stub
		View_Mot_O cell;
		LayoutInflater inf = ((Activity)context).getLayoutInflater();
	
		if(arg1==null){
			cell = new View_Mot_O();
			arg1 = inf.inflate(R.layout.listview_tenloaigiaodich, null);
			cell.tv1 = (TextView) arg1.findViewById(R.id.textView1);
			cell.tv2 = (TextView) arg1.findViewById(R.id.textView2);
			arg1.setTag(cell);
		}else{
			cell = (View_Mot_O) arg1.getTag();
		}
		cell.tv1.setText(list.get(position).getTengiaodich());
		String s;
		if(list.get(position).getThuchi()==1){
			s = "Thu";
		}else{
			s= "Chi";
		}
		cell.tv2.setText(s);
		return arg1;
	}
		
}
