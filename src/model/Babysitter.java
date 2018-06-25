package model;

public class Babysitter extends Servico {

	private int id;
	static private final double VALORHORA = 45;
	private int horaExtra;

	public Babysitter() {
		
	}
	
	public Babysitter(Contrato contrato, int horaExtra) {
		super(horaExtra*VALORHORA, contrato);
		this.horaExtra = horaExtra;
	}

	public Babysitter(int id, double valorTotal, Contrato contrato, int horaExtra) {
		super(valorTotal, contrato);
		this.id = id;
		this.horaExtra = horaExtra;
	}

	public int getHoraExtra() {
		return horaExtra;
	}

	public void setHoraExtra(int horaExtra) {
		this.horaExtra = horaExtra;
	}

	public double getVALORHORA() {
		return VALORHORA;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}