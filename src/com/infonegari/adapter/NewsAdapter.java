package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.NewsLetter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter{
	private Context context;
	private List<NewsLetter> newsLetters;
	
	public NewsAdapter(Context context, List<NewsLetter> newsLetters){
		this.context = context;
		this.newsLetters = newsLetters;
	}
	
	@Override
	public int getCount() {
		return newsLetters.size();
	}

	@Override
	public Object getItem(int position) {
		return newsLetters.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_news, null);
        }
		
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtDate = (TextView) convertView.findViewById(R.id.date);
        TextView txtNews = (TextView) convertView.findViewById(R.id.news);
        
        txtTitle.setText(newsLetters.get(position).getTitle());
        txtDate.setText(newsLetters.get(position).getRequestedDate());
        txtNews.setText(newsLetters.get(position).getDetail());
        
        return convertView;
	}

}
