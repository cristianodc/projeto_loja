package br.com.loja_v_ment.enums;

public enum StatusContaReceber {

	COBRANCA("Cobrança"),
	VENCIDA("Vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada");
	
	private String descricao;
	
	private StatusContaReceber(String descricao) {
		// TODO Auto-generated constructor stub
		this.descricao = descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
