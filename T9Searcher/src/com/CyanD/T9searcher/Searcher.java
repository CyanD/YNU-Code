package com.CyanD.T9searcher;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Searcher extends Activity {

	private GridView gridView;
	/*// 图片的文字标题
	private String[] titles = new String[] { "pic1", "pic2", "pic3", "pic4",
			"pic5", "pic6", "pic7", "pic8", "pic9", "Baidu", "QQ", "W", "JFE",
			"FEWS" };
	// 图片ID数组
	private int[] images = new int[] { R.drawable.testpic1,
			R.drawable.testpic2, R.drawable.testpic3, R.drawable.testpic4,
			R.drawable.testpic5, R.drawable.testpic6, R.drawable.testpic1,
			R.drawable.testpic5, R.drawable.testpic4, R.drawable.testpic2,
			R.drawable.testpic4, R.drawable.testpic3, R.drawable.testpic1 };
*/
	// 标签动态显示按键内容，成员变量
	private TextView textview;
	private StringBuilder mMathString;
	private ButtonClickListener mClickListener;
	private List<AppInfo> mlistAppInfo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searcher);
		gridView = (GridView) findViewById(R.id.gridview);
		mlistAppInfo = Utils.getAppInfo(this);
		AppsAdaprter adapter = new AppsAdaprter(mlistAppInfo, this);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent intent = mlistAppInfo.get(position).getIntent();
				startActivity(intent);
			}
		});

		// 按键后，用for整体委托事件
		mMathString = new StringBuilder();
		textview = (TextView) findViewById(R.id.textview);
		mClickListener = new ButtonClickListener();
		int idList[] = { R.id.button1, R.id.button2, R.id.button3,
				R.id.button4, R.id.button5, R.id.button6, R.id.button7,
				R.id.button8, R.id.button9, R.id.button_return, R.id.button0,
				R.id.button_backspace };
		for (int id : idList) {
			View v = findViewById(id);
			v.setOnClickListener(mClickListener);
		}
	}
	
	

	// 更新标签
	private void updateTextView() {
		StringBuilder builder = new StringBuilder();
		builder.append(mMathString.toString());
		if (mMathString.length() == 0)
			textview.setText(R.string.frequent_app);
		else
			textview.setText(builder);
	}

	// 按钮点击监听
	private class ButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button_backspace:
				if (mMathString.length() > 0)
					mMathString.deleteCharAt(mMathString.length() - 1);
				break;
			case R.id.button_return:
				if (mMathString.length() > 0)
					mMathString.delete(0, mMathString.length());
				break;
			default:
				mMathString.append(((Button) v).getText().charAt(0));
			}
			updateTextView();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searcher, menu);
		return true;
	}

}
