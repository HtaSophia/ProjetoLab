package model;

public class Quarto extends Servico {

	private int id;
	private int numPessoas;
	private Tipos tipoQuarto;

	public Quarto() {
		
	}
	
	public Quarto(double valorTotal, Contrato contrato, int numCamas, Tipos tipoQuarto) {
		super(valorTotal, contrato);
		this.numPessoas = numCamas;
		this.tipoQuarto = tipoQuarto;
	}
	
	public Quarto(int id, double valorTotal, Contrato contrato, int numCamas, Tipos tipoQuarto) {
		super(valorTotal, contrato);
		this.setId(id);
		this.numPessoas = numCamas;
		this.tipoQuarto = tipoQuarto;
	}

	public enum Tipos {
		PRESIDENCIAL(1200), LUXO_SIMPLES(520), LUXO_DUPLO(570), LUXO_TRIPLO(620), EXECUTIVO_SIMPLES(360) ,EXECUTIVO_DUPLO(385), EXECUTIVO_TRIPLO(440);
		
		private final int valor;
		
	    Tipos(int valorOpcao){
	        valor = valorOpcao;
	    }

		public int getValor() {
			return valor;
		}
		
	}

	public int getNumPessoas() {
		return numPessoas;
	}

	public void setNumPessoas(int numCamas) {
		this.numPessoas = numCamas;
	}

	public Tipos getTipoQuarto() {
		return tipoQuarto;
	}

	public void setTipoQuarto(Tipos tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}