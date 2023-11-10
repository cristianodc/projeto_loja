package br.com.loja_v_ment.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "nota_fiscal_venda")
@SequenceGenerator(name = "seq_nota_fiscal_venda", sequenceName = "seq_nota_fiscal_venda", allocationSize = 1, initialValue = 1)
public class NotaFiscalVenda implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_venda")
	private Long id;
	@Column(nullable = false)
	private String numero;
	@Column(nullable = false)
	private String serie;
	@Column(nullable = false)
	private String tipo;
	
	@Column(columnDefinition = "text",nullable = false)
	private String xml;
	
	@Column(columnDefinition = "text",nullable = false)
	private String pdf;
	
	@OneToOne
	@JoinColumn(name = "vendaCompraLoja_Virtual_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "vendaCompraLoja_Virtual_fk"))
	private VendaCompraLojaVirtual vendaCompraLojaVirtual;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getSerie() {
		return serie;
	}


	public void setSerie(String serie) {
		this.serie = serie;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getXml() {
		return xml;
	}


	public void setXml(String xml) {
		this.xml = xml;
	}


	public String getPdf() {
		return pdf;
	}


	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public void setVendaCompraLojaVirtual(VendaCompraLojaVirtual vendaCompraLojaVirtual) {
		this.vendaCompraLojaVirtual = vendaCompraLojaVirtual;
	}
	public VendaCompraLojaVirtual getVendaCompraLojaVirtual() {
		return vendaCompraLojaVirtual;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaFiscalVenda other = (NotaFiscalVenda) obj;
		return Objects.equals(id, other.id);
	} 

	
}
