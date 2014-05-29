package com.CyanD.T9searcher;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppsAdaprter extends BaseAdapter{
	
	private List<AppInfo> mlistAppInfo = null;
	LayoutInflater infater = null;
	
	
	public AppsAdaprter(List<AppInfo> mlistAppInfo, Context context) {
		this.mlistAppInfo = mlistAppInfo;
		infater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if(mlistAppInfo != null){
			return mlistAppInfo.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		if(mlistAppInfo != null){
			return mlistAppInfo.get(arg0);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = infater.inflate(R.layout.picture_item, null);
		}
		ViewHolder viewholder =  getHolder(convertView);
		viewholder.ivIcon.setImageDrawable(mlistAppInfo.get(position).getAppIcon());
		viewholder.tvName.setText(mlistAppInfo.get(position).getAppLabel());
		return convertView;
	}
	
	private ViewHolder getHolder(View view){
		ViewHolder viewholder = (ViewHolder)view.getTag();
		if(viewholder == null){
			return new ViewHolder(view);
		}else{
			return viewholder;
		}
	}
	
	class ViewHolder{
		private ImageView ivIcon;
		private TextView tvName;
		
		public ViewHolder(View view) {
			this.ivIcon = (ImageView)view.findViewById(R.id.image);
			this.tvName = (TextView)view.findViewById(R.id.appName);
		}
		
		
	}

}
