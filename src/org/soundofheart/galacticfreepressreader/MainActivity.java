package org.soundofheart.galacticfreepressreader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnItemClickListener{

	FetchXmlTask task;
	ArrayList<Node> arrayList = null;
	public int page = 0;
	int position = 0;
	String type = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate");
    	if (arrayList != null)
    	{
    		display(arrayList);
    		
//    		ListView nodeList = (ListView) findViewById(R.id.node_list);
//    		nodeList.setClickable(true);
//    		nodeList.setOnItemClickListener(this);
//    		NodeListAdapter listAdapter = new NodeListAdapter(this, arrayList);
//    		nodeList.setAdapter(listAdapter);
//    		if (page > 0) {
//    			prevPage.setVisibility(View.VISIBLE);
//    		}
//            nextPage.setVisibility(View.VISIBLE);
//            TextView pageNumber = (TextView) findViewById(R.id.page);
//            int displayPage = page + 1;
//            pageNumber.setText("Page:" + displayPage);
    	}
    	else {
            View prevPage = findViewById(R.id.prevPage);
            prevPage.setVisibility(View.GONE);
            View nextPage = findViewById(R.id.nextPage);
            nextPage.setVisibility(View.GONE);
    	}

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
//            case R.id.news_button:
////              openSearch();
//                return true;
            case R.id.action_settings:
    		    AlertDialog.Builder ad = new AlertDialog.Builder(this);  
    		    ad.setCancelable(false); // This blocks the 'BACK' button  
    		    ad.setMessage("No settings yet");  
    		    ad.setNegativeButton("Close", new DialogInterface.OnClickListener() {  
    		        @Override  
    		        public void onClick(DialogInterface dialog, int which) {  
    		            dialog.dismiss();                      
    		        }  
    		    });  
    		    AlertDialog alert = ad.create();
    		    alert.show();
//              openSettings();
                return true;
//            case R.id.videos_button:
//            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
        System.out.println("onResume");

        setContentView(R.layout.activity_main);
    	if (arrayList != null)
    	{
    		display(arrayList);

//    		ListView nodeList = (ListView) findViewById(R.id.node_list);
//    		nodeList.setClickable(true);
//    		nodeList.setOnItemClickListener(this);
//    		NodeListAdapter listAdapter = new NodeListAdapter(this, arrayList);
//    		nodeList.setAdapter(listAdapter);
//    		nodeList.setSelection(position);
//    		System.out.println("page " + page);
//    		if (page > 0) {
//    			View prevPage = findViewById(R.id.prevPage);
//                prevPage.setVisibility(View.VISIBLE);    			
//    		}
//    		View nextPage = findViewById(R.id.nextPage);
//            nextPage.setVisibility(View.VISIBLE);
//            TextView pageNumber = (TextView) findViewById(R.id.page);
//            int displayPage = page + 1;
//            pageNumber.setText("Page:" + displayPage);
    	}
    	else
    	{
            View prevPage = findViewById(R.id.prevPage);
            prevPage.setVisibility(View.GONE);
            View nextPage = findViewById(R.id.nextPage);
            nextPage.setVisibility(View.GONE); 
    	}
    }
    
    public void openNews(View view) throws MalformedURLException
    {
    	position = 0;
    	page = 0;
        final TextView noteTitle = (TextView)this.findViewById(R.id.message);
        noteTitle.setText("Loading...");
        final Button newsButton = (Button)this.findViewById(R.id.buttonNews);
        newsButton.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
    	String url = "http://soundofheart.org/galacticfreepress/app-news.xml?page=";
        task = (FetchXmlTask) new FetchXmlTask(this, url, 0);
        task.execute();
        type = "news";
    }
    
    public void openVideos(View view) throws MalformedURLException
    {
    	position = 0;
    	page = 0;
        final TextView noteTitle = (TextView)this.findViewById(R.id.message);
        noteTitle.setText("Loading...");
        final Button videosButton = (Button)this.findViewById(R.id.buttonVideos);
//        videosButton.setBackgroundColor(Color.BLUE);
        videosButton.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
    	String url = "http://soundofheart.org/galacticfreepress/app-videos.xml?page=";
        task = (FetchXmlTask) new FetchXmlTask(this, url, 0);
        task.execute();
        type = "videos";
    }
    
    public void prevPage(View view) throws MalformedURLException
    {
//    	page--;
    	position = 0;
        final TextView noteTitle = (TextView)this.findViewById(R.id.message);
        noteTitle.setText("Loading...");
        String url;
        if (type.equals("videos")) {
        	url = "http://soundofheart.org/galacticfreepress/app-videos.xml?page=";
        } else {
        	url = "http://soundofheart.org/galacticfreepress/app-news.xml?page=";
        }
        final Button prevButton = (Button)this.findViewById(R.id.prevPage);
        prevButton.setEnabled(false);
        final Button nextButton = (Button)this.findViewById(R.id.nextPage);
        nextButton.setEnabled(false);
        task = (FetchXmlTask) new FetchXmlTask(this, url, -1);
        task.execute();

    }
    
    public void nextPage(View view) throws MalformedURLException
    {
//    	page++;
    	position = 0;
        final TextView noteTitle = (TextView)this.findViewById(R.id.message);
        noteTitle.setText("Loading...");
        String url;
        if (type.equals("videos")) {
        	url = "http://soundofheart.org/galacticfreepress/app-videos.xml?page=";
        } else {
        	url = "http://soundofheart.org/galacticfreepress/app-news.xml?page=";
        }
        final Button prevButton = (Button)this.findViewById(R.id.prevPage);
        prevButton.setEnabled(false);
        final Button nextButton = (Button)this.findViewById(R.id.nextPage);
        nextButton.setEnabled(false);
        task = (FetchXmlTask) new FetchXmlTask(this, url, 1);
        task.execute();
    }
    
    public void display(ArrayList<Node> nodes)
    {
    	arrayList = nodes;
    	setContentView(R.layout.activity_main);
//		ArrayList<Node> nodes = FetchXmlTask.products;
		ListView nodeList = (ListView) findViewById(R.id.node_list);
		nodeList.setClickable(true);
		nodeList.setOnItemClickListener(this);
		NodeListAdapter listAdapter = new NodeListAdapter(this, arrayList);
		nodeList.setAdapter(listAdapter);
		nodeList.setSelection(position);
        View prevPage = findViewById(R.id.prevPage);
        if (page <= 0) {
        	prevPage.setVisibility(View.GONE);  
        }
        TextView pageNumber = (TextView) findViewById(R.id.page);
        int displayPage = page + 1;
        pageNumber.setText("Page " + displayPage);
        if (type.equals("news"))
        {
            final Button newsButton = (Button)this.findViewById(R.id.buttonNews);
            newsButton.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
        }
        if (type.equals("videos"))
        {
            final Button videosButton = (Button)this.findViewById(R.id.buttonVideos);
            videosButton.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
        }
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		System.out.println("clicked");
//		ArrayList<Node> nodes = FetchXmlTask.products;
		Node node = arrayList.get(position);
		
//		WebView webview = (WebView) findViewById(R.id.webview);
//		WebSettings webSettings = webview.getSettings();
//		webSettings.setJavaScriptEnabled(true);
//		webview.setWebViewClient(new WebViewClient());
//		setContentView(webview);
//		webview.loadUrl("http://soundofheart.org/galacticfreepress/node/" + node.nid);
		Uri uri = Uri.parse("http://soundofheart.org/galacticfreepress/node/" + node.nid);

		System.out.println(node.title + node.nid + node.type + node.date + node.videoUrl);
		
		if (node.type.equals("Video"))
		{
			uri = Uri.parse(node.videoUrl);
		}
		
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);


	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  super.onSaveInstanceState(savedInstanceState);
		
	  //Convert arrayList into individual strings
	  if (arrayList != null)
	  {
		int i = 0;
		Iterator<Node> it = arrayList.iterator();
		while(it.hasNext())
		{
			Node currentNode = it.next();
			savedInstanceState.putString("title" + i, currentNode.title);
			savedInstanceState.putString("nid" + i, currentNode.nid);
			savedInstanceState.putString("date" + i, currentNode.date);
			savedInstanceState.putString("type" + i, currentNode.type);
			savedInstanceState.putString("videoUrl" + i, currentNode.videoUrl);

			i++;
		}
		
		ListView nodeList = (ListView) findViewById(R.id.node_list);
		position = nodeList.getFirstVisiblePosition();
		savedInstanceState.putInt("position", position);
		savedInstanceState.putInt("page", page);
		savedInstanceState.putString("type", type);
		savedInstanceState.putInt("count", i);
	  }
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
	  super.onRestoreInstanceState(savedInstanceState);
	  
	  //Reassemble arrayList 
	  if (savedInstanceState.containsKey("count"))
	  {
		ArrayList<Node> newList = new ArrayList<Node>();;
		int count = savedInstanceState.getInt("count");
		int i = 0;
		while(count > i)
		{
			Node current = new Node();
			current.title = savedInstanceState.getString("title" + i);
			current.nid = savedInstanceState.getString("nid" + i);
			current.date = savedInstanceState.getString("date" + i);
			current.type = savedInstanceState.getString("type" + i);
			current.videoUrl = savedInstanceState.getString("videoUrl" + i);
			newList.add(current);
			i++;
		}
		
		arrayList = newList;
		
		position = savedInstanceState.getInt("position");
		page = savedInstanceState.getInt("page");
		type = savedInstanceState.getString("type");
	  }
	}
		
}