package wht.listview.refresh;


import java.util.ArrayList;
import java.util.List;
import wht.listview.refresh.MyListView.OnRefreshListener;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.BaseAdapter;

public class MainActivity extends Activity 
{

	private List<String> list;
	private BaseAdapter adapter;
	private MyListView listView;

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ȡ����Ļ�ߴ��С
		displayScreenSize();
		
		list = new ArrayList<String>();
	    for(int i=0;i<3;i++)
	    {
	    	 list.add("���Ƕ��ǿ�����");

	    }

		listView = (MyListView) findViewById(R.id.listView);
		adapter= new LvAdapter(list,getApplicationContext());	
		listView.setAdapter(adapter);
		listView.setOnRefreshListener(new OnRefreshListener() 
		{
			public void onRefresh() 
			{
				RefreshTask rTask = new RefreshTask();
				rTask.execute(1500);
			}
		});
	}
	
	// AsyncTask�첽����
	class RefreshTask extends AsyncTask<Integer, Integer, String>
    {    
    	@Override
		protected String doInBackground(Integer... params) 
		{
			try 
			{
				// �¿��߳� ���� listview
				Thread.sleep(params[0]);
				// ��data��ǰ�������
				list.add("ˢ�º������");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			return null;
		}	

		@Override
		protected void onPostExecute(String result) 
		{
			super.onPostExecute(result);
			adapter.notifyDataSetChanged();
			listView.onRefreshComplete();
		}    	
    }
	
	// ȡ����Ļ�ߴ��С
	public void displayScreenSize()
	{
		// ��Ļ�����л�ʱ��÷���
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) 
		{		
			setTitle("landscape");
		}
		
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) 
		{
			setTitle("portrait");
		}
		
		// �����Ļ��С1
		WindowManager manager = getWindowManager();
		int width = manager.getDefaultDisplay().getWidth();
		int height = manager.getDefaultDisplay().getHeight();
		
		Log.v("am10", "width: " + width + " height: " + height);
		
		// �����Ļ��С2
		DisplayMetrics dMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
		int screenWidth = dMetrics.widthPixels;
		int screenHeight = dMetrics.heightPixels;
		
		Log.v("am10", "screenWidth: " + screenWidth + " screenHeight: " + screenHeight);
	}
}
