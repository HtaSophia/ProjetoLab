package model;

public class AluguelCarro extends Servico{
	
	private int id;
	private int quantiaDiarias;
	private boolean tanqueCheio;
	private boolean seguroCarro;
	private Tipos tipoCarro;
	
	public AluguelCarro() {
		
	}
	
	public AluguelCarro(double valorTotal, Contrato contrato, int quantiaDiarias, boolean tanqueCheio, boolean seguroCarro) {
		super(valorTotal, contrato);
		this.quantiaDiarias = quantiaDiarias;
		this.tanqueCheio = tanqueCheio;
		this.seguroCarro = seguroCarro;
	}
	
	public AluguelCarro(int id, double valorTotal, Contrato contrato, int quantiaDiarias, boolean tanqueCheio, boolean seguroCarro) {
		super(valorTotal, contrato);
		this.id = id;
		this.quantiaDiarias = quantiaDiarias;
		this.tanqueCheio = tanqueCheio;
		this.seguroCarro = seguroCarro;
	}

	public enum Tipos {
		AUTOMOVEL_LUXO(100), AUTOMOVEL_EXECUTIVO(60), TANQUE_CHEIO(250), SEGURO_CARRO(200);
		
		private final int valor;
		
	    Tipos(int valorOpcao){
	        valor = valorOpcao;
	    }

		public int getValor() {
			return valor;
		}
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantiaDiarias() {
		return quantiaDiarias;
	}

	public void setQuantiaDiarias(int quantiaDiarias) {
		this.quantiaDiarias = quantiaDiarias;
	}

	public boolean isTanqueCheio() {
		return tanqueCheio;
	}

	public void setTanqueCheio(boolean tanqueCheio) {
		this.tanqueCheio = tanqueCheio;
	}

	public boolean isSeguroCarro() {
		return seguroCarro;
	}

	public void setSeguroCarro(boolean seguroCarro) {
		this.seguroCarro = seguroCarro;
	}

	public Tipos getTipoCarro() {
		return tipoCarro;
	}

	public void setTipoCarro(Tipos tipoCarro) {
		this.tipoCarro = tipoCarro;
	}
}
