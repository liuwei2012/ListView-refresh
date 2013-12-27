package wht.listview.refresh;
import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LvAdapter extends BaseAdapter {
  private List<String> list;
  private Context context;

  public LvAdapter(List<String> list, Context context) {
    this.list = list;
    this.context = context;
  }


	public int getCount() 
	{
		return list.size();
	}
	public Object getItem(int position) 
	{
		return list.get(position);
	}
	public long getItemId(int position) 
	{
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
		TextView textView = (TextView) convertView.findViewById(R.id.textView_item);
		textView.setText(list.get(position));
		return convertView;
	}


}