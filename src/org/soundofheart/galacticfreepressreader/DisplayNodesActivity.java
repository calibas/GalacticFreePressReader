package org.soundofheart.galacticfreepressreader;

import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayNodesActivity extends Activity implements OnItemClickListener{

	private ListView nodeList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_nodes);
		ArrayList<Node> nodes = FetchXmlTask.products;
		nodeList = (ListView) findViewById(R.id.node_list);
		nodeList.setClickable(true);
		nodeList.setOnItemClickListener(this);
//		nodeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//					long arg3) {
//				System.out.println("clicked");
//				WebView webview = new WebView(this.);
//				setContentView(webview);
//
//				
//			}
//		});
		
//		System.out.println("DisplayNodes onCreate");

//		ArrayList<String> output = formatNodes(nodes, this);
//		System.out.println(output);
//		System.out.println("doing something");
//		if (output == null)
//			System.out.println("null");
//		printProducts(nodes, this);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, output);
//        nodeList.setAdapter(arrayAdapter); 
		NodeListAdapter listAdapter = new NodeListAdapter(this, nodes);
		nodeList.setAdapter(listAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_nodes, menu);
		return true;
	}
	
    private ArrayList<String> formatNodes(ArrayList<Node> nodeArrayList, Activity activity)
    {
    	ArrayList<String> nodes = new ArrayList<String>();
    	String content;
    	if (nodeArrayList != null)
    	{
    		Iterator<Node> it = nodeArrayList.iterator();
//    		System.out.println("printProducts");
    		while(it.hasNext())
    		{
    			content = "";
    			Node currProduct  = it.next();
    			content = content + "<h2>" +  currProduct.title + "</h2>\n";
    			content = content + "<a href='http://soundofheart.org/galacticfreepress/node/" + currProduct.nid + "'>Link</a>";
//    			content = content + "Color :" +  currProduct.color + "n";
    			nodes.add(content);

    		}
    	
//    		TextView display = (TextView)activity.findViewById(R.id.nodes);
//    		display.setMovementMethod(LinkMovementMethod.getInstance());
//    		display.setText(Html.fromHtml(content));
    	}
    	
    	return nodes;
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		System.out.println("clicked");
		ArrayList<Node> nodes = FetchXmlTask.products;
		Node node = nodes.get(position);
		
		WebView webview = new WebView(this);
		setContentView(webview);
		webview.loadUrl("http://soundofheart.org/galacticfreepress/node/" + node.nid);

	}

}
