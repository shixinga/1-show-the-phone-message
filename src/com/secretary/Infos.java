package com.secretary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.secretary.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Infos extends Activity implements OnItemClickListener {
	private static final String TAG = "eoeInfosAssistant";
	ListView itemlist = null;
	List<Map<String, Object>> list;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		itemlist = (ListView) findViewById(R.id.itemlist);
		refreshListItems();
	}

	private void refreshListItems() {
		list = buildListForSimpleAdapter();
		SimpleAdapter notes = new SimpleAdapter(this, list, R.layout.item_row,
				new String[] { "name", "desc", "img" }, new int[] { R.id.name,
						R.id.desc, R.id.img });
		itemlist.setAdapter(notes);
		itemlist.setOnItemClickListener(this);
		itemlist.setSelection(0);
	}

	private List<Map<String, Object>> buildListForSimpleAdapter() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(3);
		// Build a map for the attributes
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "ϵͳ��Ϣ");
		map.put("desc", "ϵͳ�汾,��Ӫ�̼���ϵͳ��Ϣ.");
		map.put("img", R.drawable.system);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "Ӳ����Ϣ");
		map.put("desc", "CPU,Ӳ��,�ڴ��Ӳ����Ϣ.");
		map.put("img", R.drawable.hardware);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "�����Ϣ");
		map.put("desc", "�Ѿ���װ�������Ϣ.");
		map.put("img", R.drawable.software);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "����ʱ��Ϣ");
		map.put("desc", "����ʱ����Ϣ.");
		map.put("img", R.drawable.running);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "�ļ������");
		map.put("desc", "�ļ�ϵͳ.");
		map.put("img", R.drawable.file_explorer);
		list.add(map);
		
		return list;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent intent = new Intent();
		Log.i(TAG, "item clicked! [" + position + "]");
		switch (position) {
		case 0:
			intent.setClass(Infos.this, System.class);
			startActivity(intent);
			break;
		case 1:
			intent.setClass(Infos.this, Hardware.class);
			startActivity(intent);
			break;
		case 2:
			intent.setClass(Infos.this, Software.class);
			startActivity(intent);
			break;
		case 3:
			intent.setClass(Infos.this, Runing.class);
			startActivity(intent);
			break;
		case 4:
			intent.setClass(Infos.this, wenjian.class);
			startActivity(intent);
			break;
		}
	}
}