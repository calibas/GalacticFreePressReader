package org.soundofheart.galacticfreepressreader;

import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.widget.TextView;

public class DisplayNodesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_nodes);
		System.out.println("DisplayNodes onCreate");
		ArrayList<Product> nodes = FetchXmlTask.products;
		printProducts(nodes, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_nodes, menu);
		return true;
	}
	
    private void printProducts(ArrayList<Product> products, Activity activity)
    {
    	String content = "";
    	if (products != null)
    	{
    		Iterator<Product> it = products.iterator();
    		System.out.println("printProducts");
    		while(it.hasNext())
    		{
    			Product currProduct  = it.next();
    			content = content + "<h2>" +  currProduct.title + "</h2>\n";
    			content = content + "<a href='http://soundofheart.org/galacticfreepress/node/" + currProduct.nid + "'>Link</a>";
//    			content = content + "Color :" +  currProduct.color + "n";

    		}
    	
    		TextView display = (TextView)activity.findViewById(R.id.nodes);
    		display.setMovementMethod(LinkMovementMethod.getInstance());
    		display.setText(Html.fromHtml(content));
    	}
    }

}
