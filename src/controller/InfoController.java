/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AluguelCarroDAO;
import dao.BabysitterDAO;
import dao.QuartoDAO;
import dao.RestauranteDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.AluguelCarro;
import model.Babysitter;
import model.Contrato;
import model.Hospede;
import model.Quarto;
import model.Restaurante;

public class InfoController {

    static public Contrato contrato;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text nome;

    @FXML
    private Text cpf;

    @FXML
    private Text end;
    @FXML
    private Text cartao;

    @FXML
    private Text entrada;

    @FXML
    private Text quartos;

    @FXML
    private Text servicos;

    @FXML
    private Text estado;

    @FXML
    private Text valor;

    @FXML
    void btCancel(ActionEvent event) {

    }

    @FXML
    void btOk(ActionEvent event) {
        contrato.setStatus(false);
                estado.setText(contrato.getStatus());

    }

    @FXML
    void initialize() {
        
        nome.setText(contrato.getHospede().getNome());
        cpf.setText(String.valueOf(contrato.getHospede().getCpf()));
        end.setText(contrato.getHospede().getEndereco());
        cartao.setText(String.valueOf(contrato.getHospede().getCartaoCredito()));
        entrada.setText(contrato.getDataEntrada());
        estado.setText(contrato.getStatus());
        valor.setText("R$"+String.valueOf(contrato.getTotalPagar()));
        
        ArrayList<Quarto> q = new QuartoDAO().getAllObj();
        ArrayList<Babysitter> b = new BabysitterDAO().getAllObj();
        ArrayList<Restaurante> r = new RestauranteDAO().getAllObj();
        ArrayList<AluguelCarro> a = new AluguelCarroDAO().getAllObj();
        
        String quarto = "";
        String servico = "";

       
        for(Quarto q1: q){
            if(q1.getContrato().getHospede().getCpf() == contrato.getHospede().getCpf()){
                quarto += q1.getTipoQuarto().name()+" - R$"+q1.getValorTotal()+" ";
                quartos.setText(quarto);
            }
            
        }
        for(Babysitter q1: b){
            if(q1.getContrato().getHospede().getCpf() == contrato.getHospede().getCpf()){
                servico += "Babysitter - R$"+q1.getValorTotal()+" ";
            }
            
        }
        for(Restaurante q1: r){
            if(q1.getContrato().getHospede().getCpf() == contrato.getHospede().getCpf()){
                servico += "Restaurante - R$"+q1.getValorTotal()+" ";
            }
            
        }
        for(AluguelCarro q1: a){
            if(q1.getContrato().getHospede().getCpf() == contrato.getHospede().getCpf()){
                servico += "AluguelCarro - R$"+q1.getValorTotal()+" ";
            }
            
        }
        servicos.setText(servico);
    }
}
