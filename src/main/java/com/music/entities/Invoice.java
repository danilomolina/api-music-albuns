package com.music.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity invoice
 * @author danilo
 *
 */

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable{

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String date;
	
	private Double value;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_invoice")
	private List<ItemInvoice> itens;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}	
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public List<ItemInvoice> getItens() {
		return itens;
	}
	public void setItens(List<ItemInvoice> itens) {
		this.itens = itens;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", date=" + date + ", value=" + value + ", itens=" + itens + "]";
	}
	


	

}
