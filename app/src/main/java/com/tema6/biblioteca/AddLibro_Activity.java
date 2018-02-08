package com.tema6.biblioteca;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddLibro_Activity extends Activity {
	/** Called when the activity is first created. */

	private EditText isbn;
	private EditText titulo;
	private EditText autor;
	private EditText idioma;
	private Button agregar;
	private Button cancelar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_libro);

		// almacenando valores ingresados por el usuario
		isbn = (EditText) findViewById(R.id.etIsbn);
		titulo = (EditText) findViewById(R.id.etTitulo);
		autor = (EditText) findViewById(R.id.etAutor);
		idioma = (EditText) findViewById(R.id.etIdioma);

		// declarando a los botones
		agregar = (Button) findViewById(R.id.btnAgregar);
		cancelar = (Button) findViewById(R.id.btnCancelar);

		// acción cuando le dan click al boton agregar
		agregar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// almacenamiento temporal
				String sisbn = String.valueOf(isbn.getText());
				String stitulo = String.valueOf(titulo.getText());
				String sautor = String.valueOf(autor.getText());
				String sidioma = String.valueOf(idioma.getText());

				// se realiza el intento de enviar el isbn del lisbro a
				// la clase VerLibro
				/*
				 * Bundle b = new Bundle(); b.putString("isbn", sisbn); Intent
				 * intent = new Intent(AddLibro_Activity.this,
				 * VerLibro_Activity.class); intent.putExtras(b);
				 */
				// llamada al metodo para insertar los datos.
				ContentValues contentvalues = new ContentValues();
				contentvalues.put(BibliotecaProvider.LibrosColumns.KEY_ISBN,
						sisbn);
				contentvalues.put(BibliotecaProvider.LibrosColumns.KEY_AUTOR,
						sautor);
				contentvalues.put(BibliotecaProvider.LibrosColumns.KEY_TITULO,
						stitulo);
				contentvalues.put(BibliotecaProvider.LibrosColumns.KEY_IDIOMA,
						sidioma);
				getContentResolver().insert(BibliotecaProvider.CONTENT_URI,
						contentvalues);
				finish();

			}
		});
		// acción cuando le dan click al boton agregar
		cancelar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				isbn.setText(" ");
				titulo.setText(" ");
				autor.setText(" ");
				idioma.setText(" ");

			}
		});
	}

}
