package model;

public class Restaurante extends Servico {
	
	private int id;
	private String data, pedido;

	public Restaurante() {
		
	}
	
	public Restaurante(double valorTotal, Contrato contrato, String data, String pedido) {
		super(valorTotal, contrato);
		this.data = data;
                this.pedido = pedido;
	}

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }
	
	public Restaurante(int id, String tipo, double valorTotal, Contrato contrato, String data, String pedido) {
		super(valorTotal, contrato);
		this.id = id;
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}