package model;

public class Contrato {
	
	private int id;
	private Hospede hospede;
	private String dataEntrada;
	private int periodo;
	private boolean tarifacao;
	private boolean status;
	private double totalPagar;
	
	public Contrato() {
		
	}
	
	public Contrato(Hospede hospede, String dataEntrada, int periodo, boolean tarifacao, boolean status, double totalPagar) {
		this.dataEntrada = dataEntrada;
		this.periodo = periodo;
		this.tarifacao = tarifacao;
		this.status = status;
		this.hospede = hospede;
		this.totalPagar = totalPagar;
	}
	
	public Contrato(int id, Hospede hospede, String dataEntrada, int periodo, boolean tarifacao, boolean status, double totalPagar) {
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.periodo = periodo;
		this.tarifacao = tarifacao;
		this.status = status;
		this.hospede = hospede;
		this.totalPagar = totalPagar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public boolean isTarifacao() {
		return tarifacao;
	}

	public void setTarifacao(boolean tarifacao) {
		this.tarifacao = tarifacao;
	}

	public boolean isStatus() {
		return status;
	}
	
	public String getStatus() {
		if(status) {
			return "Aberto";
		}
		else {
			return "Fechado";
		}
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(double totalPagar) {
		this.totalPagar = totalPagar;
	}
}