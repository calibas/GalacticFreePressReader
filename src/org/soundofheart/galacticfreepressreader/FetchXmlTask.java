package org.soundofheart.galacticfreepressreader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.HttpStatus;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Xml;
import android.widget.Button;
import android.widget.TextView;

public class FetchXmlTask extends AsyncTask<Void, Void, Void>{
	
	static ArrayList<Node> products = null;
	boolean success = false;
//	public final static String EXTRA_MESSAGE = "org.soundofheart.galacticfreepressreader.MESSAGE";
    Context context;
    URL url;
    int shiftPage = 0;
    
    public FetchXmlTask(Context context, String urlString, int pageShift) throws MalformedURLException {
		MainActivity activity = (MainActivity) context;
        this.shiftPage = pageShift;
        int page = activity.page + shiftPage;
        this.url = new URL(urlString + page);
        this.context = context;
        System.out.println("page " + page);
        System.out.println(this.url);
        System.out.println("shiftPage " + this.shiftPage);
    }

	@Override
	protected Void doInBackground(Void... params) {
		try {
			loadXML();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		if (success)
		{
//			Intent intent = new Intent(context, DisplayNodesActivity.class);
//	    	EditText editText = (EditText) findViewById(R.id.edit_message);
//	    	String message = editText.getText().toString();
//	    	intent.putExtra(EXTRA_MESSAGE, message);
//			context.startActivity(intent);
			MainActivity activity = (MainActivity) context;
	        activity.page = activity.page + shiftPage;
			activity.display(getData());
	        final TextView noteTitle= (TextView)activity.findViewById(R.id.message);
	        noteTitle.setText("");
	        final Button prevButton = (Button)activity.findViewById(R.id.prevPage);
	        prevButton.setEnabled(true);
	        final Button nextButton = (Button)activity.findViewById(R.id.nextPage);
	        nextButton.setEnabled(true);

		}
		else
		{
			MainActivity activity = (MainActivity) context;
	        final TextView noteTitle= (TextView)activity.findViewById(R.id.message);
	        noteTitle.setText("Unable to connect");
	        
		    AlertDialog.Builder ad = new AlertDialog.Builder(context);  
		    ad.setCancelable(false); // This blocks the 'BACK' button  
		    ad.setMessage("Unable to connect");  
		    ad.setNegativeButton("Close", new DialogInterface.OnClickListener() {  
		        @Override  
		        public void onClick(DialogInterface dialog, int which) {  
		            dialog.dismiss();                      
		        }  
		    });  
		    AlertDialog alert = ad.create();
		    alert.show();  
		}
		success = false;
	}
	
	public ArrayList<Node> getData()
	{
		System.out.println("getData");
		return products;
	}
	
	private void loadXML() throws IOException, XmlPullParserException
	{
		System.out.println("loadXML");
		XmlPullParser parser = Xml.newPullParser();

//		URL url = new URL("http://soundofheart.org/galacticfreepress/app.xml");
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection)connection;
		httpConn.setDoInput(true);
		httpConn.setRequestProperty("charset", "utf-8");
		int responseCode = httpConn.getResponseCode();
		if(responseCode == HttpStatus.SC_OK) {
			
//			File xmlFile = getTempFile(context, url.toString());
			
			InputStream xmlStream = httpConn.getInputStream();
		    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(xmlStream, null);

            parseXML(parser);
		}

	}
	

	private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
	{
        int eventType = parser.getEventType();
        Node currentProduct = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	products = new ArrayList<Node>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("node")){
            			currentProduct = new Node();
            			success = true;
                    }
                    if (currentProduct != null){
                        if (name.equals("title")){
                            currentProduct.title = parser.nextText();
                        } 
                        if (name.equals("nid")){
                        	currentProduct.nid = parser.nextText();
                        } 
                        if (name.equals("created")){
                            currentProduct.date = parser.nextText();
                        }  
                        if (name.equals("type")){
                            currentProduct.type = parser.nextText();
                        }
                        if (name.equals("field_video_url")){
                        	currentProduct.videoUrl = parser.nextText();
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("node") && currentProduct != null){
                    	products.add(currentProduct);
                    } 
            }
            eventType = parser.next();
        }

 //       printProducts(products, activity);
	}

	
//	public File getTempFile(Context context, String url) {
//	    File file = null;
//	    try {
//	        String fileName = Uri.parse(url).getLastPathSegment();
//	        file = File.createTempFile(fileName, null, context.getCacheDir());
//	        System.out.println("createTempFile " + fileName);
//	    }
//	    catch (IOException e) {
//	        // Error while creating file
//	    }
//	    return file;
//	}

//	private void printProducts(ArrayList<Product> products, Activity activity)
//	{
//		String content = "";
//		Iterator<Product> it = products.iterator();
//		while(it.hasNext())
//		{
//			Product currProduct  = it.next();
//			content = content + "nnnProduct :" +  currProduct.name + "n";
//			content = content + "Quantity :" +  currProduct.quantity + "n";
//			content = content + "Color :" +  currProduct.color + "n";
//
//		}
//
//		TextView display = (TextView)activity.findViewById(R.id.node1);
//		display.setText(content);
//	}
}
