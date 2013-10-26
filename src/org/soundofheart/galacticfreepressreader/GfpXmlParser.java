package org.soundofheart.galacticfreepressreader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpStatus;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.util.Xml;
import android.widget.TextView;

public class GfpXmlParser {

	
	
	public void start(Activity activity){
//		XmlPullParserFactory pullParserFactory;
//		try {
////			pullParserFactory = XmlPullParserFactory.newInstance();
//			XmlPullParser parser = Xml.newPullParser();
//
////		    FileInputStream fIn = (FileInputStream) activity.getApplicationContext().getAssets().open("temp.xml");
////		    InputStreamReader isr = new InputStreamReader(fIn);
//			URL url = new URL("http://soundofheart.org/galacticfreepress/app.xml");
//			URLConnection connection = url.openConnection();
//			HttpURLConnection httpConn = (HttpURLConnection)connection;
//			httpConn.setDoInput(true);
//			httpConn.setRequestProperty("charset", "utf-8");
//			int responseCode = httpConn.getResponseCode();
//			if(responseCode != HttpStatus.SC_OK) {
//				
//				InputStream xmlStream = httpConn.getInputStream();
////			    InputStream in_s = activity.getApplicationContext().getAssets().open("temp.xml");
//			    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//	            parser.setInput(xmlStream, null);
//
//	            parseXML(parser, activity);
//			}
//			else
//			{
//				TextView display = (TextView)activity.findViewById(R.id.node1);
//				display.setText("Unable to Connect");
//			}
//		FetchXmlTask task = (FetchXmlTask) new FetchXmlTask().execute();
			
//		ArrayList nodes = 
			

//		} catch (XmlPullParserException e) {
//
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

//	private void parseXML(XmlPullParser parser, Activity activity) throws XmlPullParserException,IOException
//	{
//		ArrayList<Product> products = null;
//        int eventType = parser.getEventType();
//        Product currentProduct = null;
//
//        while (eventType != XmlPullParser.END_DOCUMENT){
//            String name = null;
//            switch (eventType){
//                case XmlPullParser.START_DOCUMENT:
//                	products = new ArrayList<Product>();
//                    break;
//                case XmlPullParser.START_TAG:
//                    name = parser.getName();
//                    if (name == "node"){
//            			currentProduct = new Product();
//                    } else if (currentProduct != null){
//                        if (name == "title"){
//                            currentProduct.name = parser.nextText();
//                        } else if (name == "productcolor"){
//                        	currentProduct.color = parser.nextText();
//                        } else if (name == "productquantity"){
//                            currentProduct.quantity= parser.nextText();
////                            display.setText("test");
//                        }  
//                    }
//                    break;
//                case XmlPullParser.END_TAG:
//                    name = parser.getName();
//                    if (name.equalsIgnoreCase("node") && currentProduct != null){
//                    	products.add(currentProduct);
//                    } 
//            }
//            eventType = parser.next();
//        }
//
//        printProducts(products, activity);
//	}

	private void printProducts(ArrayList<Product> products, Activity activity)
	{
		String content = "";
		Iterator<Product> it = products.iterator();
		while(it.hasNext())
		{
			Product currProduct  = it.next();
			content = content + "nnnProduct :" +  currProduct.title + "n";
			content = content + "Quantity :" +  currProduct.nid + "n";
			content = content + "Color :" +  currProduct.date + "n";

		}

		TextView display = (TextView)activity.findViewById(R.id.node1);
		display.setText(content);
	}

}