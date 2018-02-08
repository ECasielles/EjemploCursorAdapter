package com.tema6.biblioteca.modelo;

public class Libro {
	private String isbn;
	private String titulo;
	private String autor;
	private String idioma;

	public Libro(String isbn, String titulo, String autor, String idioma) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor=autor;
		this.idioma=idioma;
	}

	public Libro() {
		// TODO Auto-generated constructor stub
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	

}
