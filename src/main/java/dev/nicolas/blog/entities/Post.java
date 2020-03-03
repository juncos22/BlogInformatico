package dev.nicolas.blog.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "TITULO", nullable = false)
	private String titulo;
	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;
	@Column(name = "CONTENIDO", length = 500, nullable = false)
	private String contenido;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Categoria categoria;
	@Column(name = "FECHA_CREACION", updatable = false)
	private Date fechaCreacion;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Usuario usuario;
	
	public Post() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
