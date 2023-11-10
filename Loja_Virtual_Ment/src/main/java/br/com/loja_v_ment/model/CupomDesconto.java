package br.com.loja_v_ment.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cup_desconto")
@SequenceGenerator(name = "seq_cup_desconto", sequenceName = "seq_cup_desconto", allocationSize = 1, initialValue = 1)
public class CupomDesconto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cup_desconto")
	private Long id;
	
	@Column(nullable = false)
	private String codDescricao;
	
	private BigDecimal valorRealDesc;
	
	private BigDecimal valorPorcentDescricao;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataValidadeCupom;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodDescricao() {
		return codDescricao;
	}
	public void setCodDescricao(String codDescricao) {
		this.codDescricao = codDescricao;
	}
	public BigDecimal getValorRealDesc() {
		return valorRealDesc;
	}
	public void setValorRealDesc(BigDecimal valorRealDesc) {
		this.valorRealDesc = valorRealDesc;
	}
	public BigDecimal getValorPorcentDescricao() {
		return valorPorcentDescricao;
	}
	public void setValorPorcentDescricao(BigDecimal valorPorcentDescricao) {
		this.valorPorcentDescricao = valorPorcentDescricao;
	}
	public Date getDataValidadeCupom() {
		return dataValidadeCupom;
	}
	public void setDataValidadeCupom(Date dataValidadeCupom) {
		this.dataValidadeCupom = dataValidadeCupom;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CupomDesconto other = (CupomDesconto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
