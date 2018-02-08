package com.tema6.biblioteca.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.tema6.biblioteca.BibliotecaProvider.LibrosColumns;

public class DataBaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_TABLE = "libros";

	private static final String DATABASE_CREATE = "create table "
			+ DATABASE_TABLE + "(" + LibrosColumns._ID
			+ " integer primary key autoincrement, " + LibrosColumns.KEY_ISBN
			+ " text not null, " + LibrosColumns.KEY_TITULO + " text not null,"
			+ LibrosColumns.KEY_AUTOR + " text not null,"
			+ LibrosColumns.KEY_IDIOMA + " text not null);";

	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia SQL de creación de la tabla
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior,
			int versionNueva) {
		// NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la
		// opción de
		// eliminar la tabla anterior y crearla de nuevo vacía con el nuevo
		// formato.
		// Sin embargo lo normal será que haya que migrar datos de la tabla
		// antigua
		// a la nueva, por lo que este método debería ser más elaborado.

		// Se elimina la versión anterior de la tabla
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

		// Se crea la nueva versión de la tabla
		db.execSQL(DATABASE_CREATE);
	}
}
