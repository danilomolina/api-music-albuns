package com.music.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity iteminvoice
 * @author danilo
 *
 */

@Entity
@Table(name = "item_invoice")
public class ItemInvoice implements Serializable{

	private static final long serialVersionUID = 1L;	
	
	public ItemInvoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int quant;
	private Double cashback;
	private Double value;

	@Column(name="id_catalog_disk")
	private Long disk;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getCashback() {
		return cashback;
	}
	public void setCashback(Double cashback) {
		this.cashback = cashback;
	}
	public int getQuant() {
		return quant;
	}
	public void setQuant(int quant) {
		this.quant = quant;
	}
	public Long getDisk() {
		return disk;
	}
	public void setDisk(Long disk) {
		this.disk = disk;
	}
	
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "ItemInvoice [id=" + id + ", quant=" + quant + ", cashback=" + cashback + ", value=" + value + ", disk="
				+ disk + "]";
	}

	
	
	
}
