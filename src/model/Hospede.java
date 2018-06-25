package model;

public class Hospede {

	private String nome;
	private int cpf;
	private String dataNascimento;
	private String endereco;
	private int cartaoCredito;
	
	public Hospede() {
		
	}
	
	public Hospede(int cpf, String nome, String dataNascimento, String endereco, int cartaoCredito ) {
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.cartaoCredito = cartaoCredito;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(int cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}	
}
