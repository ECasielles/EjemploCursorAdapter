package com.tema6.biblioteca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Biblioteca_Activity extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void btListLibro(View v){
		Intent i = new Intent(getApplicationContext(), ListLibro_Activity.class);
		startActivity(i);
	}
	public void btAddLibro(View v){
		Intent i = new Intent(getApplicationContext(), AddLibro_Activity.class);
		startActivity(i);
	}
}
