package org.soundofheart.galacticfreepressreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpStatus;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Xml;
import android.widget.EditText;
import android.widget.TextView;

public class FetchXmlTask extends AsyncTask<Void, Void, Void>{
	
	boolean finished = false;
	static ArrayList<Product> products = null;
//	public final static String EXTRA_MESSAGE = "org.soundofheart.galacticfreepressreader.MESSAGE";
    Context context;
    
    public FetchXmlTask(Context context) {
        this.context = context;
    }

	@Override
	protected Void doInBackground(Void... params) {
		System.out.println("doInBackGround");
		try {
			loadXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		pullParserFactory = XmlPullParserFactory.newInstance();
//	    FileInputStream fIn = (FileInputStream) activity.getApplicationContext().getAssets().open("temp.xml");
//	    InputStreamReader isr = new InputStreamReader(fIn);
//		URL url = new URL("http://soundofheart.org/galacticfreepress/app.xml");
//		URLConnection connection = url.openConnection();
//		HttpURLConnection httpConn = (HttpURLConnection)connection;
//		httpConn.setDoInput(true);
//		httpConn.setRequestProperty("charset", "utf-8");
//		int responseCode = httpConn.getResponseCode();
//		if(responseCode != HttpStatus.SC_OK) {
//			
//			connected = true;
//			InputStream xmlStream = httpConn.getInputStream();
////		    InputStream in_s = activity.getApplicationContext().getAssets().open("temp.xml");
//		    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//            parser.setInput(xmlStream, null);
//
//            parseXML(parser);
//		}
//		else
//		{
//			connected = false;
//			TextView display = (TextView)activity.findViewById(R.id.node1);
//			display.setText("Unable to Connect");
//		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		System.out.println("onPostExecute");
		finished = true;
	    Intent intent = new Intent(context, DisplayNodesActivity.class);
//	    EditText editText = (EditText) findViewById(R.id.edit_message);
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
	    context.startActivity(intent);

	}
	
	public boolean isReady()
	{
		System.out.println("isReady");
		return finished;
	}
	
	public ArrayList<Product> getData()
	{
		System.out.println("getData");
		return products;
	}
	
	private void loadXML() throws IOException, XmlPullParserException
	{
		System.out.println("loadXML");
//		pullParserFactory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = Xml.newPullParser();

		URL url = new URL("http://soundofheart.org/galacticfreepress/app.xml");
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection)connection;
		httpConn.setDoInput(true);
		httpConn.setRequestProperty("charset", "utf-8");
		int responseCode = httpConn.getResponseCode();
//		System.out.println("ResponseCode: " + responseCode);
		if(responseCode == HttpStatus.SC_OK) {
			
			InputStream xmlStream = httpConn.getInputStream();
//		    InputStream in_s = activity.getApplicationContext().getAssets().open("temp.xml");
		    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(xmlStream, null);

            parseXML(parser);
		}

	}
	

	private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
	{
//		System.out.println("parseXML");
//		ArrayList<Product> products = null;
        int eventType = parser.getEventType();
        Product currentProduct = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	products = new ArrayList<Product>();
//                	System.out.println("START_DOCUMENT");
                    break;
                case XmlPullParser.START_TAG:
//                	System.out.println("START_TAG");
                    name = parser.getName();
//                  System.out.println(name);
                    if (name.equals("node")){
//                    	System.out.println("name == nodes");
            			currentProduct = new Product();
//                    } else if (currentProduct != null){
                    }
                    if (currentProduct != null){
                        if (name.equals("title")){
                        	System.out.println("TAG = title");
                            currentProduct.title = parser.nextText();
//                          System.out.println(currentProduct.name);
                        } 
                        
                        if (name.equals("nid")){
                        	System.out.println("TAG = nid");
                        	currentProduct.nid = parser.nextText();
                        	System.out.println(currentProduct.nid);
                        } 
                        if (name.equals("date")){
                            currentProduct.date= parser.nextText();
//                            display.setText("test");
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
