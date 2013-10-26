package org.soundofheart.galacticfreepressreader;

import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	FetchXmlTask task = (FetchXmlTask) new FetchXmlTask(this);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task.execute();
        System.out.println("onCreate");
        
//        GfpXmlParser parser = new GfpXmlParser();
//        parser.start(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        System.out.println("onCreateOptionMenu");
        return true;
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
//    	FetchXmlTask task = (FetchXmlTask) new FetchXmlTask().execute();
//		TextView display = (TextView)this.findViewById(R.id.node2);
//		display.setText("onResume");
//		if (task.isReady())
//		{
//			System.out.println("if (task.isReady)");
//			ArrayList<Product> nodes = task.getData();
//			printProducts(nodes, this);
//		}
//		System.out.println("onResume");
    }


//    private void printProducts(ArrayList<Product> products, Activity activity)
//    {
//    	String content = "";
//    	if (products != null)
//    	{
//    		Iterator<Product> it = products.iterator();
//    		System.out.println("printProducts");
//    		while(it.hasNext())
//    		{
//    			Product currProduct  = it.next();
//    			content = content + "Title: " +  currProduct.name + "\n";
////    			content = content + "Quantity :" + currProduct.quantity + "n";
////    			content = content + "Color :" +  currProduct.color + "n";
//
//    		}
//    	
//    		TextView display = (TextView)activity.findViewById(R.id.node1);
//    		display.setText(content);
//    	}
//    }
}