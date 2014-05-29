package com.example.getapps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView listview = null;

	private List<AppInfo> mlistAppInfo = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_app_list);

		listview = (ListView) findViewById(R.id.listviewApp);
		mlistAppInfo = new ArrayList<AppInfo>();
		queryAppInfo(); // ��ѯ����Ӧ�ó�����Ϣ
		BrowseApplicationInfoAdapter browseAppAdapter = new BrowseApplicationInfoAdapter(
				this, mlistAppInfo);
		listview.setAdapter(browseAppAdapter);
		listview.setOnItemClickListener(this);
	}

	// �����ת����Ӧ�ó���
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		Intent intent = mlistAppInfo.get(position).getIntent();
		startActivity(intent);
	}

	// �����������Activity����Ϣ��������Launch����
	public void queryAppInfo() {
		PackageManager pm = this.getPackageManager(); // ���PackageManager����
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		// ͨ����ѯ���������ResolveInfo����.
		// List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent,
		// PackageManager.MATCH_DEFAULT_ONLY);//PackageManager.MATCH_DEFAULT_ONLY���ϵͳ����
		List<ResolveInfo> resolveInfos = pm
				.queryIntentActivities(mainIntent, 0);//Ϊ0�������г���
		// ����ϵͳ���� �� ����name����
		// ���������Ҫ������ֻ����ʾϵͳӦ�ã��������г�������Ӧ�ó���
		Collections.sort(resolveInfos,
				new ResolveInfo.DisplayNameComparator(pm));
		if (mlistAppInfo != null) {
			mlistAppInfo.clear();
			for (ResolveInfo reInfo : resolveInfos) {
				String activityName = reInfo.activityInfo.name; // ��ø�Ӧ�ó��������Activity��name
				String pkgName = reInfo.activityInfo.packageName; // ���Ӧ�ó���İ���
				String appLabel = (String) reInfo.loadLabel(pm); // ���Ӧ�ó����Label
				Drawable icon = reInfo.loadIcon(pm); // ���Ӧ�ó���ͼ��
				// ΪӦ�ó��������Activity ׼��Intent
				Intent launchIntent = new Intent();
				launchIntent.setComponent(new ComponentName(pkgName,
						activityName));
				// ����һ��AppInfo���󣬲���ֵ
				AppInfo appInfo = new AppInfo();
				appInfo.setAppLabel(appLabel);
				appInfo.setPkgName(pkgName);
				appInfo.setAppIcon(icon);
				appInfo.setIntent(launchIntent);
				mlistAppInfo.add(appInfo); // ������б���
				System.out.println(appLabel + " activityName---" + activityName
						+ " pkgName---" + pkgName);
			}
		}
	}
}
