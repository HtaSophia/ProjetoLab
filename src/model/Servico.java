package model;

public abstract class Servico {
	
	private String descricao;
	private double valorTotal;
	private Contrato contrato;
	
	public Servico() {
		
	}
	
	public Servico(double valorTotal, Contrato contrato) {
		this.valorTotal = valorTotal;
		this.contrato = contrato;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
}
