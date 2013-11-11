package org.soundofheart.galacticfreepressreader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NodeListAdapter extends BaseAdapter{

	Context listContext;
	ArrayList<Node> arrayList;
	
	public NodeListAdapter(Context context, ArrayList<Node> nodeList)
	{
		listContext = context;
		arrayList = nodeList;
	}
	
	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public Node getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        
		// here you inflate the layout you want for the row
        final View view = View.inflate(listContext, R.layout.activity_display_nodes, null);

        // you bind the layout with the content of your list
        // for each element of your list of notes, the adapter will create a row and affect the right title
        final TextView noteTitle= (TextView)view.findViewById(R.id.nodes);
        Node node = arrayList.get(position);
        long timeStamp = Long.valueOf(node.date) * 1000;
        Date d = new Date(timeStamp);
        Locale current = listContext.getResources().getConfiguration().locale;;
        String date = new SimpleDateFormat("MMM d, hh:mma", current).format(d);
        noteTitle.setText(Html.fromHtml("<big><b>" + node.title + "</b></big>" + "<br />" + date));
//      System.out.println(arrayList.get(position));

        return view;
	}

}
