package com.CyanD.T9searcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

public class Utils {
	
	public static ArrayList<AppInfo> getAppInfo(Context context) {
		PackageManager pm = context.getPackageManager(); // ���PackageManager����
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		// ͨ����ѯ���������ResolveInfo����.
		// List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent,
		// PackageManager.MATCH_DEFAULT_ONLY);//PackageManager.MATCH_DEFAULT_ONLY���ϵͳ����
		List<ResolveInfo> resolveInfos = pm
				.queryIntentActivities(mainIntent, 0);// Ϊ0�������г���
		// ����ϵͳ���� �� ����name����
		// ���������Ҫ������ֻ����ʾϵͳӦ�ã��������г�������Ӧ�ó���
		Collections.sort(resolveInfos,
				new ResolveInfo.DisplayNameComparator(pm));
		ArrayList<AppInfo> apps = new ArrayList<AppInfo>();
		for (ResolveInfo reInfo : resolveInfos) {
			String activityName = reInfo.activityInfo.name; // ��ø�Ӧ�ó��������Activity��name
			String pkgName = reInfo.activityInfo.packageName; // ���Ӧ�ó���İ���
			String appLabel = (String) reInfo.loadLabel(pm); // ���Ӧ�ó����Label
			Drawable icon = reInfo.loadIcon(pm); // ���Ӧ�ó���ͼ��
			// ΪӦ�ó��������Activity ׼��Intent
			Intent launchIntent = new Intent();
			launchIntent.setComponent(new ComponentName(pkgName, activityName));
			// ����һ��AppInfo���󣬲���ֵ
			AppInfo appInfo = new AppInfo();
			appInfo.setAppLabel(appLabel);
			appInfo.setPkgName(pkgName);
			appInfo.setAppIcon(icon);
			appInfo.setIntent(launchIntent);
			apps.add(appInfo); // ������б���
			System.out.println(appLabel + " activityName---" + activityName
					+ " pkgName---" + pkgName);
		}
		return apps;
	}
}
