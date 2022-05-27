package br.com.loja.enums;

public enum StatusContaAPagar {


	COBRANCA("Pagar"),
	VENCIDA("Vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada"),
	NEGOCIADA("Renegociada");

private String descricao;

private StatusContaAPagar(String descricao) {
	this.descricao = descricao;
}

public String getDescricao() {
	return descricao;
}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return this.descricao;
}
}
