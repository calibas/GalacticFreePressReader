package org.soundofheart.galacticfreepressreader;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;

public class ListNodesActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_nodes);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_nodes, menu);
		return true;
	}

}
