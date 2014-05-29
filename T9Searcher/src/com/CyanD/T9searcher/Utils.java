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
		PackageManager pm = context.getPackageManager(); // 获得PackageManager对象
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		// 通过查询，获得所有ResolveInfo对象.
		// List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent,
		// PackageManager.MATCH_DEFAULT_ONLY);//PackageManager.MATCH_DEFAULT_ONLY获得系统程序
		List<ResolveInfo> resolveInfos = pm
				.queryIntentActivities(mainIntent, 0);// 为0则获得所有程序
		// 调用系统排序 ， 根据name排序
		// 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
		Collections.sort(resolveInfos,
				new ResolveInfo.DisplayNameComparator(pm));
		ArrayList<AppInfo> apps = new ArrayList<AppInfo>();
		for (ResolveInfo reInfo : resolveInfos) {
			String activityName = reInfo.activityInfo.name; // 获得该应用程序的启动Activity的name
			String pkgName = reInfo.activityInfo.packageName; // 获得应用程序的包名
			String appLabel = (String) reInfo.loadLabel(pm); // 获得应用程序的Label
			Drawable icon = reInfo.loadIcon(pm); // 获得应用程序图标
			// 为应用程序的启动Activity 准备Intent
			Intent launchIntent = new Intent();
			launchIntent.setComponent(new ComponentName(pkgName, activityName));
			// 创建一个AppInfo对象，并赋值
			AppInfo appInfo = new AppInfo();
			appInfo.setAppLabel(appLabel);
			appInfo.setPkgName(pkgName);
			appInfo.setAppIcon(icon);
			appInfo.setIntent(launchIntent);
			apps.add(appInfo); // 添加至列表中
			System.out.println(appLabel + " activityName---" + activityName
					+ " pkgName---" + pkgName);
		}
		return apps;
	}
}
