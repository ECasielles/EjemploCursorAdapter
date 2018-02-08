package com.tema6.biblioteca;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.tema6.biblioteca.contentprovider.DataBaseHelper;

public class BibliotecaProvider extends ContentProvider {

	// Errores
	// private static final String TAG = "Biblioteca_Provider";

	// Clase que contiene las columnas o datos del ContentProvider
	public static final class LibrosColumns implements BaseColumns {

		// Nombres de columnas
		public static final String KEY_ISBN = "isbn";
		public static final String KEY_TITULO = "titulo";
		public static final String KEY_AUTOR = "autor";
		public static final String KEY_IDIOMA = "idioma";

	}

	public static final String[] TABLE_COLUMN = { LibrosColumns._ID,
			LibrosColumns.KEY_ISBN, LibrosColumns.KEY_TITULO,
			LibrosColumns.KEY_AUTOR, LibrosColumns.KEY_IDIOMA };

	private static final String DATABASE_NAME = "biblioteca";
	public static final String DATABASE_TABLE = "libros";
	private static final int DATABASE_VERSION = 1;

	// UriMatcher
	private static final int TABLE = 1;
	private static final int TABLE_ID = 2;
	private static final UriMatcher uriMatcher;

	// Inicializamos el UriMatcher, con los patrones
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.tema6.biblioteca ", "libros", TABLE);
		uriMatcher.addURI("com.tema6.biblioteca ", "libros/#", TABLE_ID);
	}
	private DataBaseHelper dataBaseHelper;
	private SQLiteDatabase db;

	// Constantes que construyen el URI del Content Provider,
	// URI única que lo representa y a través de la cual los otros
	// componentes de una aplicación pueden acceder a él.

	public static final String AUTHORITY = "com.tema6.biblioteca";
	public static final String BASE_PATH = "libros";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + BASE_PATH);

	/* MÉTODOS */

	@Override
	public boolean onCreate() {
		dataBaseHelper = new DataBaseHelper(getContext(), DATABASE_NAME, null,
				DATABASE_VERSION);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// Si es una consulta a un ID concreto construimos el WHERE
		String where = selection;
		if (uriMatcher.match(uri) == TABLE_ID) {
			where = "_id=" + uri.getLastPathSegment();
		}

		db = dataBaseHelper.getWritableDatabase();

		//TODO: Como se cierre el cursor, se peta la BD
        //Lo bueno es que puedo notificar los cambios
		Cursor c = db.query(DATABASE_TABLE, projection, where, selectionArgs,
				null, null, sortOrder);

		return c;

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int cont;
		// Si es una consulta a un ID concreto construimos el WHERE
		String where = selection;
		if (uriMatcher.match(uri) == TABLE_ID) {
			where = "_id=" + uri.getLastPathSegment();
		}
		db = dataBaseHelper.getWritableDatabase();
		cont = db.update(DATABASE_TABLE, values, where, selectionArgs);
		return cont;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int cont;
		// Si es una consulta a un ID concreto construimos el WHERE
		String where = selection;
		if (uriMatcher.match(uri) == TABLE_ID) {
			where = "_id=" + uri.getLastPathSegment();
		}
		db = dataBaseHelper.getWritableDatabase();
		cont = db.delete(DATABASE_TABLE, where, selectionArgs);
		return cont;
	}

	@Override
	public String getType(Uri uri) {

		int match = uriMatcher.match(uri);

		switch (match) {
		case TABLE:
			return "vnd.android.cursor.dir/vnd.tema6.com.biblioteca.cliente";
		case TABLE_ID:
			return "vnd.android.cursor.item/vnd.tema6.com.biblioteca.cliente";
		default:
			return null;
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		long regId = 1;
		db = dataBaseHelper.getWritableDatabase();
		regId = db.insert(DATABASE_TABLE, null, values);
		Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
		return newUri;
	}

}
