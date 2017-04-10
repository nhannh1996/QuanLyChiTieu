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
import model.showGiaoDich;

public class LV_ShowGiaoDich extends BaseAdapter{
	ArrayList<showGiaoDich> list;
	Context context;
	public LV_ShowGiaoDich(Context c, ArrayList<showGiaoDich> list){
		context = c;
		this.list = list;
	}
	class View_Mot_O{
		TextView tvNgay,tvTien,tvTen;
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
		LayoutInflater inf = ((Activity)context).getLayoutInflater();
		View_Mot_O cell;
		if(arg1==null){
			cell = new View_Mot_O();
			arg1 = inf.inflate(R.layout.layout_giaodich, null);
			cell.tvNgay = (TextView) arg1.findViewById(R.id.textView1);
			cell.tvTien = (TextView) arg1.findViewById(R.id.textView2);
			cell.tvTen = (TextView) arg1.findViewById(R.id.textView3);
			arg1.setTag(cell);
		}else{
			cell = (View_Mot_O) arg1.getTag();
		}
		
		
		cell.tvNgay.setText(list.get(position).getNgay());
		cell.tvTien.setText(String.valueOf(list.get(position).getTien())+" $");
		cell.tvTen.setText(list.get(position).getTengiaodich());
		return arg1;
	}

}
