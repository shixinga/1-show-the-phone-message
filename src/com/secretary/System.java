package com.secretary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.secretary.R;
import com.secretary.util.PreferencesUtil;

 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class System extends Activity implements OnItemClickListener{
	private static final String TAG = "System";

	ListView itemlist = null;
	List<Map<String, Object>> list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infos);
		setTitle("ϵͳ��Ϣ");
		itemlist = (ListView) findViewById(R.id.itemlist);
		refreshListItems();
	}

	private void refreshListItems() {
		list = buildListForSimpleAdapter();
		SimpleAdapter notes = new SimpleAdapter(this, list, R.layout.info_row,
				new String[] { "name", "desc" }, new int[] { R.id.name,
						R.id.desc });
		itemlist.setAdapter(notes);
		itemlist.setOnItemClickListener(this);
		itemlist.setSelection(0);
	}
	
	private List<Map<String, Object>> buildListForSimpleAdapter() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(3);
		// Build a map for the attributes
		Map<String, Object> map = new HashMap<String, Object>();
		
		map = new HashMap<String, Object>();
		map.put("id", PreferencesUtil.VER_INFO);
		map.put("name", "����ϵͳ�汾");
		map.put("desc", "��ȡ/proc/version��Ϣ");
		list.add(map);

		
		map = new HashMap<String, Object>();
		map.put("id", PreferencesUtil.SystemProperty);
 		map.put("name", "ϵͳ��Ϣ");
		map.put("desc", "�ֻ��豸��ϵͳ��Ϣ.");
		// map.put("icon", R.drawable.mem);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("id", PreferencesUtil.TEL_STATUS);
 		map.put("name", "��Ӫ����Ϣ");
		map.put("desc", "�ֻ��������Ӫ����Ϣ.");
		list.add(map);
 
		return list;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent intent = new Intent();
		Log.i(TAG, "item clicked! [" + position + "]");
		Bundle info = new Bundle();
		Map<String, Object> map = list.get(position);
		info.putInt("id",  (Integer) map.get("id"));
		info.putString("name", (String) map.get("name"));
		info.putString("desc", (String) map.get("desc"));
		info.putInt("position", position);
		intent.putExtra("android.intent.extra.info", info);
		intent.setClass(System.this, ShowInfo.class);
		startActivityForResult(intent, 0);
	}
}
