package com.secretary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.secretary.R;
import com.secretary.util.PreferencesUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import android.widget.ListView;
import android.widget.SimpleAdapter;


public class Software extends Activity implements  Runnable {
	List<Map<String, Object>> list = null;
	ListView itemlist = null;
	 private ProgressDialog pd;
	 
	@Override
	public void onCreate(Bundle icicle) {  
        super.onCreate(icicle);  
		setContentView(R.layout.infos);
		setTitle("�����Ϣ");
		itemlist = (ListView) findViewById(R.id.itemlist);
        pd = ProgressDialog.show(this, "���Ժ�..", "�����ռ����Ѿ���װ�������Ϣ...", true,
                false);
        Thread thread = new Thread(this);
        thread.start();
    }  
	
 
	private void refreshListItems() {
		list = fetch_installed_apps();
		SimpleAdapter notes = new SimpleAdapter(this, list, R.layout.info_row,
				new String[] { "name", "desc" }, new int[] { R.id.name,
						R.id.desc });
		itemlist.setAdapter(notes);
		setTitle("�����Ϣ,�Ѿ���װ"+list.size()+"��Ӧ��.");
	}
	
	
	
	public List fetch_installed_apps(){
        List<ApplicationInfo> packages = getPackageManager().getInstalledApplications(0);
		list = new ArrayList<Map<String, Object>>(
				packages.size());
		Iterator<ApplicationInfo> l = packages.iterator();
		
		while (l.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			ApplicationInfo app = (ApplicationInfo) l.next();
			String packageName = app.packageName;
            String label = "";
            try {
                label = getPackageManager().getApplicationLabel(app).toString();
            } catch (Exception e) {  
            	Log.i("Exception",e.toString());
            }
    		map = new HashMap<String, Object>();
     		map.put("name", label);
    		map.put("desc", packageName);
    		list.add(map);
		}
		return list;
    }
	
	@Override
	public void run() {
    	fetch_installed_apps();
       handler.sendEmptyMessage(0);
	}
	
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            refreshListItems();
            pd.dismiss();
        }
    };
    
}
