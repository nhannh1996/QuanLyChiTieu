package com.example.assignment_ps03966_nguyenhoangnhan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import ListView_TenLoaiGiaoDich.LV_ShowGiaoDich;
import ListView_TenLoaiGiaoDich.LV_TenLoaiGiaoDich;
import SQLite.GiaoDichDAO;
import SQLite.ThuChiDAO;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import model.GiaoDich;
import model.ThongKe;
import model.ThuChi;
import model.TongTien;
import model.showGiaoDich;

public class MainActivity extends Activity {
	TabHost tabs;

	GiaoDichDAO dbGiaoDich;
	ArrayList<ThuChi> listCapNhatTenGiaoDich;
	Spinner sptengiaodich;
	TextView tvTuNgay, tvDenNgay;
	ListView lvGiaoDich;
	ArrayList<showGiaoDich> listShowGiaoDich;

	Spinner spCaiDat;
	String[] spcaidat = { "Phân nhóm", "Thu", "Chi" };
	ListView lvCaiDat;
	ArrayList<ThuChi> listCaiDat;
	ImageView imgCaiDat;
	ThuChiDAO dbCaiDat;
	TextView tvSoDu;
	String layngay = "";
	Button btntim;
	Calendar calendar = Calendar.getInstance();

	// thongke
	Spinner spThongKe, spThongKeNam;
	String s[] = { "Tất cả tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
			"Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" };
	String dauthang[] = { "", "01/01", "02/01", "03/01", "04/01", "05/01", "06/01", "07/01", "08/01", "09/01", "10/01",
			"11/01", "12/01" };
	String cuoithang[] = { "", "01/31", "02/29", "03/31", "04/30", "05/31", "06/30", "07/31", "08/31", "09/30", "10/31",
			"11/30", "12/31" };
	String sNam[] = { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021" };
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<TongTien> listDataHeader;
	HashMap<String, List<ThongKe>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		anhxa();
		// tab
		tabs = (TabHost) findViewById(R.id.tabhost);
		tabs.setup();
		TabSpec tabEdit = tabs.newTabSpec("ThuChi");
		tabEdit.setIndicator("Thu Chi");
		tabEdit.setContent(R.id.tab1);
		tabs.addTab(tabEdit);
		TabSpec tabList = tabs.newTabSpec("ThongKe");
		tabList.setIndicator("Thống Kê");
		tabList.setContent(R.id.tab2);
		tabs.addTab(tabList);
		TabSpec tab = tabs.newTabSpec("CaiDat");
		tab.setIndicator("Cài Đặt");
		tab.setContent(R.id.tab3);
		tabs.addTab(tab);
		tabs.setCurrentTab(0);

		listShowGiaoDich = dbGiaoDich.getShowGD1();
		ArrayList<ThuChi> listshowChuyen = new ArrayList<ThuChi>();
		listshowChuyen = dbCaiDat.getThuChi();
		for (int i = 0; i < listShowGiaoDich.size(); i++) {
			for (int j = 0; j < listshowChuyen.size(); j++) {
				if (listShowGiaoDich.get(i).getTengiaodich()
						.equals(String.valueOf(listshowChuyen.get(j).getId_thuchi()))) {
					listShowGiaoDich.get(i).setTengiaodich(listshowChuyen.get(j).getTengiaodich());
					break;
				}
			}
		}
		LV_ShowGiaoDich adaptershow = new LV_ShowGiaoDich(MainActivity.this, listShowGiaoDich);
		lvGiaoDich.setAdapter(adaptershow);
		lvGiaoDich.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

				AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
				alert.setTitle("Do you want delete?");
				alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dbGiaoDich.deleteGiaoDich(listShowGiaoDich.get(position).getId());
						Toast.makeText(MainActivity.this, "You deleted success", Toast.LENGTH_SHORT).show();
						capnhatshowgiaodich();
						capnhattiengd();
						capnhatthongke();
						spinnerThongKe();
						dialog.cancel();

					}
				});
				alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				alert.show();

			}
		});
		// chon ngay show giao dich
		tvTuNgay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
								calendar.set(Calendar.YEAR, year);
								calendar.set(Calendar.MONTH, monthOfYear);
								calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
								monthOfYear = monthOfYear + 1;
								String thang = String.valueOf(monthOfYear);
								if (monthOfYear < 10) {
									thang = "0" + monthOfYear;
								}
								tvTuNgay.setText(year + "/" + thang + "/" + dayOfMonth);

							}
						}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH));
				datePickerDialog.show();
			}
		});
		tvDenNgay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btntim.setEnabled(true);
				DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
								calendar.set(Calendar.YEAR, year);
								calendar.set(Calendar.MONTH, monthOfYear);
								calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
								monthOfYear = monthOfYear + 1;
								String thang = String.valueOf(monthOfYear);
								if (monthOfYear < 10) {
									thang = "0" + monthOfYear;
								}
								tvDenNgay.setText(year + "/" + thang + "/" + dayOfMonth);
							}
						}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH));
				datePickerDialog.show();
			}
		});

		btntim.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listShowGiaoDich = dbGiaoDich.getShowGD2(tvTuNgay.getText().toString(), tvDenNgay.getText().toString());
				ArrayList<ThuChi> listshowChuyen = new ArrayList<ThuChi>();
				listshowChuyen = dbCaiDat.getThuChi();
				for (int i = 0; i < listShowGiaoDich.size(); i++) {
					for (int j = 0; j < listshowChuyen.size(); j++) {
						if (listShowGiaoDich.get(i).getTengiaodich()
								.equals(String.valueOf(listshowChuyen.get(j).getId_thuchi()))) {
							listShowGiaoDich.get(i).setTengiaodich(listshowChuyen.get(j).getTengiaodich());
							break;
						}
					}
				}
				LV_ShowGiaoDich adaptershow = new LV_ShowGiaoDich(MainActivity.this, listShowGiaoDich);
				lvGiaoDich.setAdapter(adaptershow);
			}
		});

		imgCaiDat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog alert = new Dialog(MainActivity.this);
				alert.setTitle("       Thêm loại giao dịch");
				alert.setContentView(R.layout.dialog_themloaigiaodich);
				alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
				final EditText tengd = (EditText) alert.findViewById(R.id.editText1);
				final Spinner sp = (Spinner) alert.findViewById(R.id.spinner1);
				final String sloai[] = { "Thu", "Chi" };
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
						android.R.layout.simple_spinner_item, sloai);
				adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
				sp.setAdapter(adapter);
				Button btnThem = (Button) alert.findViewById(R.id.buttonThem);
				Button btnHuy = (Button) alert.findViewById(R.id.buttonHuy);
				btnThem.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						String ten = tengd.getText().toString();
						if (ten.length() == 0) {
							Toast.makeText(MainActivity.this, "Tên giao dịch không được để trống", Toast.LENGTH_SHORT)
									.show();
							tengd.requestFocus();
							return;
						}
						for (int i = 0; i < listCaiDat.size(); i++) {
							if (ten.equalsIgnoreCase(listCaiDat.get(i).getTengiaodich())) {
								Toast.makeText(MainActivity.this, "Thêm thất bại, tên giao dịch đã có",
										Toast.LENGTH_SHORT).show();
								tengd.requestFocus();
								return;
							}
						}
						int loai = 1;
						for (int i = 0; i < sloai.length; i++) {
							if (sp.getSelectedItem().equals("Thu")) {
								loai = 1;
								break;
							} else {
								loai = 2;
								break;
							}
						}
						ThuChi tc = new ThuChi(ten, loai);
						dbCaiDat.insertThuChi(tc);
						capnhatCaiDat();
						toastThanhCong();
						alert.cancel();
					}
				});
				btnHuy.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						alert.cancel();
					}
				});
				alert.show();

			}
		});
		lvCaiDat.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

				AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
				alert.setTitle("Có thể chứa các phần liên quan\nBạn có chắc muốn xóa nó?");
				alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						ArrayList<GiaoDich> list = new ArrayList<GiaoDich>();
						list = dbGiaoDich.getGiaoDich();
						dbCaiDat.deleteThuChi(listCaiDat.get(position).getId_thuchi());
						for (int i = 0; i < list.size(); i++) {
							if (listCaiDat.get(position).getId_thuchi() == list.get(i).getId_ThuChi()) {
								dbGiaoDich.deleteGiaoDich(list.get(i).getId_giaodich());
							}
						}
						Toast.makeText(MainActivity.this, "You deleted success", Toast.LENGTH_SHORT).show();
						capnhatCaiDat();
						capnhatshowgiaodich();
						capnhattiengd();
						capnhatthongke();
						spinnerThongKe();
						dialog.cancel();

					}
				});
				alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				alert.show();
			}
		});
		ArrayAdapter<String> adapterName = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_spinner_item, sNam);
		adapterName.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spThongKeNam.setAdapter(adapterName);
		spThongKeNam.setSelection(6);
		spThongKeNam.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				capnhatthongke();
				spinnerThongKe();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		capnhatCaiDat();
		capnhattiengd();
		capnhatthongke();
		capnhatCaiDat();
		capnhatcaidatspPhanNhom();
		spinnerThongKe();
	}

	public void spinnerThongKe() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,
				s);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spThongKe.setAdapter(adapter);
		spThongKe.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (position == 0) {
					capnhatthongke();
				} else {
					expListView = (ExpandableListView) findViewById(R.id.listViewThongKe);
					listDataHeader = new ArrayList<TongTien>();
					listDataChild = new HashMap<String, List<ThongKe>>();
					double tongtienthu = 0;
					double tongtienchi = 0;
					tongtienthu = dbGiaoDich.getThongKeSumTien(1, spThongKeNam.getSelectedItem() + "",
							dauthang[position], cuoithang[position]);
					tongtienchi = dbGiaoDich.getThongKeSumTien(2, spThongKeNam.getSelectedItem() + "",
							dauthang[position], cuoithang[position]);

					// Adding child data
					// Toast.makeText(MainActivity.this,tongtienthu+"",
					// Toast.LENGTH_LONG).show();
					TongTien t1 = new TongTien("Tổng thu", tongtienthu);
					TongTien t2 = new TongTien("Tổng chi", tongtienchi);
					listDataHeader.add(t1);
					listDataHeader.add(t2);

					List<ThongKe> listthongkethu = new ArrayList<ThongKe>();
					List<ThongKe> listthongkechi = new ArrayList<ThongKe>();
					listthongkethu = dbGiaoDich.getThongKe(1, spThongKeNam.getSelectedItem() + "", dauthang[position],
							cuoithang[position]);
					listthongkechi = dbGiaoDich.getThongKe(2, spThongKeNam.getSelectedItem() + "", dauthang[position],
							cuoithang[position]);

					listDataChild.put(listDataHeader.get(0).getTen(), listthongkethu);
					listDataChild.put(listDataHeader.get(1).getTen(), listthongkechi);
					listAdapter = new ExpandableListAdapter(MainActivity.this, listDataHeader, listDataChild);
					expListView.setAdapter(listAdapter);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}

		});

	}

	public void capnhatthongke() {
		expListView = (ExpandableListView) findViewById(R.id.listViewThongKe);
		listDataHeader = new ArrayList<TongTien>();
		listDataChild = new HashMap<String, List<ThongKe>>();
		double tongtienthu = 0;
		double tongtienchi = 0;
		tongtienthu = dbGiaoDich.getThongKeSumTien(1, spThongKeNam.getSelectedItem() + "", "01/01", "12/31");
		tongtienchi = dbGiaoDich.getThongKeSumTien(2, spThongKeNam.getSelectedItem() + "", "01/01", "12/31");
		TongTien t1 = new TongTien("Tổng thu", tongtienthu);
		TongTien t2 = new TongTien("Tổng chi", tongtienchi);
		listDataHeader.add(t1);
		listDataHeader.add(t2);

		List<ThongKe> listthongkethu = new ArrayList<ThongKe>();
		List<ThongKe> listthongkechi = new ArrayList<ThongKe>();
		listthongkethu = dbGiaoDich.getThongKe(1, spThongKeNam.getSelectedItem() + "", "01/01", "12/31");
		listthongkechi = dbGiaoDich.getThongKe(2, spThongKeNam.getSelectedItem() + "", "01/01", "12/31");

		listDataChild.put(listDataHeader.get(0).getTen(), listthongkethu);
		listDataChild.put(listDataHeader.get(1).getTen(), listthongkechi);

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
		expListView.setAdapter(listAdapter);

	}

	public void capnhattiengd() {
		dbGiaoDich.getTienGiaoDich();
		double sodu = dbGiaoDich.getTienGiaoDich();
		tvSoDu.setText(String.valueOf(sodu) + " $");
	}

	public void capnhatCaiDat() {
		listCaiDat = dbCaiDat.getThuChi();
		LV_TenLoaiGiaoDich adapter = new LV_TenLoaiGiaoDich(MainActivity.this, listCaiDat);
		lvCaiDat.setAdapter(adapter);

	}

	public void capnhatcaidatspPhanNhom() {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,
				spcaidat);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spCaiDat.setAdapter(adapter);
		spCaiDat.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (spcaidat[position] == "Thu") {

					listCaiDat = dbCaiDat.getThuChiThu();
					LV_TenLoaiGiaoDich adapter = new LV_TenLoaiGiaoDich(MainActivity.this, listCaiDat);
					lvCaiDat.setAdapter(adapter);
				} else {
					if (spcaidat[position] == "Chi") {
						listCaiDat = dbCaiDat.getThuChiChi();
						LV_TenLoaiGiaoDich adapter = new LV_TenLoaiGiaoDich(MainActivity.this, listCaiDat);
						lvCaiDat.setAdapter(adapter);
					} else {
						capnhatCaiDat();
					}

				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		;
	}

	public void capnhatTenGiaoDich() {
		listCapNhatTenGiaoDich = dbCaiDat.getThuChiTenGiaoDich();

		ArrayAdapter<ThuChi> adapter = new ArrayAdapter<ThuChi>(MainActivity.this, android.R.layout.simple_spinner_item,
				listCapNhatTenGiaoDich);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sptengiaodich.setAdapter(adapter);
	}

	public void capnhatshowgiaodich() {
		listShowGiaoDich = dbGiaoDich.getShowGD1();
		ArrayList<ThuChi> listshowChuyen = new ArrayList<ThuChi>();
		listshowChuyen = dbCaiDat.getThuChi();
		for (int i = 0; i < listShowGiaoDich.size(); i++) {
			for (int j = 0; j < listshowChuyen.size(); j++) {
				if (listShowGiaoDich.get(i).getTengiaodich()
						.equals(String.valueOf(listshowChuyen.get(j).getId_thuchi()))) {
					listShowGiaoDich.get(i).setTengiaodich(listshowChuyen.get(j).getTengiaodich());
					break;
				}
			}
		}
		LV_ShowGiaoDich adaptershow = new LV_ShowGiaoDich(MainActivity.this, listShowGiaoDich);
		lvGiaoDich.setAdapter(adaptershow);
	}

	public void toastThanhCong() {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.ToastLayout));
		TextView text = (TextView) layout.findViewById(R.id.textView1);
		text.setText("Thêm thành công");
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void anhxa() {
		//giao dich
		dbGiaoDich = new GiaoDichDAO(MainActivity.this);
		listCapNhatTenGiaoDich = new ArrayList<ThuChi>();
		tvTuNgay = (TextView) findViewById(R.id.textViewChonTuNgay);
		tvDenNgay = (TextView) findViewById(R.id.textViewChonDenNgay);
		tvSoDu = (TextView) findViewById(R.id.textViewSoDu);
		lvGiaoDich = (ListView) findViewById(R.id.listViewGiaoDich);
		listShowGiaoDich = new ArrayList<showGiaoDich>();
		btntim = (Button) findViewById(R.id.buttonThuChiTim);
			//cai dat
		spCaiDat = (Spinner) findViewById(R.id.spinner1CaiDat);
		lvCaiDat = (ListView) findViewById(R.id.listView1CaiDat);
		imgCaiDat = (ImageView) findViewById(R.id.imageView1CaiDat);
		dbCaiDat = new ThuChiDAO(MainActivity.this);
		listCaiDat = new ArrayList<ThuChi>();
		// thongke
		spThongKeNam = (Spinner) findViewById(R.id.spinnerThongKeNam);
		spThongKe = (Spinner) findViewById(R.id.spinneThongKe);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.them:
			final Dialog alert = new Dialog(MainActivity.this);
			alert.setContentView(R.layout.dialog_themgiaodich);
			alert.setTitle("	         Thêm giao dịch");
			alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
			final EditText edtChonNgay = (EditText) alert.findViewById(R.id.editTextChonNgay);
			edtChonNgay.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
							new DatePickerDialog.OnDateSetListener() {

								@Override
								public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
									calendar.set(Calendar.YEAR, year);
									calendar.set(Calendar.MONTH, monthOfYear);
									calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
									monthOfYear = monthOfYear + 1;
									String thang = String.valueOf(monthOfYear);
									if (monthOfYear < 10) {
										thang = "0" + monthOfYear;
									}
									edtChonNgay.setText(year + "/" + thang + "/" + dayOfMonth);

								}
							}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH));
					datePickerDialog.show();
				}
			});
			final EditText sotien = (EditText) alert.findViewById(R.id.editText1);
			sptengiaodich = (Spinner) alert.findViewById(R.id.spinner1);
			capnhatTenGiaoDich();
			final EditText mota = (EditText) alert.findViewById(R.id.editText2);
			final EditText ghichu = (EditText) alert.findViewById(R.id.editText3);
			Button luu = (Button) alert.findViewById(R.id.buttonLuu);
			Button luudong = (Button) alert.findViewById(R.id.buttonLuuDong);
			luu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String ngay = edtChonNgay.getText().toString();
					if (sptengiaodich.getSelectedItemPosition() == -1) {
						Toast.makeText(MainActivity.this, "Bạn chưa Thêm loại giao dịch!", Toast.LENGTH_LONG).show();
						return;
					}
					String tengd = sptengiaodich.getSelectedItem().toString();

					ArrayList<ThuChi> listID = new ArrayList<ThuChi>();
					listID = dbCaiDat.getThuChi();
					int id = 1;
					for (int i = 0; i < listID.size(); i++) {
						if (listID.get(i).getTengiaodich().equals(tengd)) {
							id = listID.get(i).getId_thuchi();
						}
					}
					String mt = mota.getText().toString();
					String gc = ghichu.getText().toString();

					if (ngay.length() == 0) {
						Toast.makeText(MainActivity.this, "Bạn chưa chọn ngày giao dịch", Toast.LENGTH_LONG).show();
						return;
					}
					if (sotien.getText().toString().length() == 0) {
						Toast.makeText(MainActivity.this, "Bạn chưa nhập số tiền", Toast.LENGTH_LONG).show();

						sotien.requestFocus();
						return;
					}

					Double tien = Double.parseDouble(sotien.getText().toString());

					if (tengd.length() == 0) {
						Toast.makeText(MainActivity.this, "Hãy thêm loại giao dịch", Toast.LENGTH_LONG).show();
						sptengiaodich.requestFocus();
						return;
					}

					GiaoDich gd = new GiaoDich(ngay, tien, id, mt, gc);
					dbGiaoDich.insertGiaoDich(gd);
					toastThanhCong();
					capnhatshowgiaodich();
					capnhattiengd();
					capnhatthongke();
					alert.cancel();

				}
			});
			luudong.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					alert.cancel();
				}
			});
			alert.show();
			break;
		case R.id.saoke:

			capnhatshowgiaodich();
			tvTuNgay.setText("Chọn ngày");
			tvDenNgay.setText("Chọn ngày");
			Toast.makeText(MainActivity.this, "Bạn đã chọn sao kê", Toast.LENGTH_LONG).show();
			break;
		case R.id.byNhan:

			final AlertDialog.Builder alertBy = new AlertDialog.Builder(MainActivity.this);
			alertBy.setTitle("Design by Nguyen Hoang Nhan");

			alertBy.show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
