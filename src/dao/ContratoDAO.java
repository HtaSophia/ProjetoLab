package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Contrato;
import model.Hospede;

public class ContratoDAO implements DAO<Contrato>{

	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	public void closeConnections() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

	@Override
	public void addObj(Contrato obj) {
		connection = new Conexao().getConexao();
		
		String sql = "INSERT INTO contratos VALUES(default,?,?,?,?,?,?);";
		
		if(obj != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, obj.getDataEntrada());
				statement.setInt(2, obj.getPeriodo());
				statement.setBoolean(3, obj.isTarifacao());
				statement.setBoolean(4, obj.isStatus());
				statement.setDouble(5, obj.getTotalPagar());
				statement.setInt(6, obj.getHospede().getCpf());
		
				statement.execute();
				closeConnections();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void atualizarObj(Contrato obj) {
		if (obj != null) {
            connection = new Conexao().getConexao();
            String sql = "UPDATE contratos SET dataentrada = ?, periodo = ?, tarifacao = ?, estado = ?, totalpagar = ? WHERE id = ?;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getDataEntrada());
				statement.setInt(2, obj.getPeriodo());
				statement.setBoolean(3, obj.isTarifacao());
				statement.setBoolean(4, obj.isStatus());
				statement.setDouble(5, obj.getTotalPagar());
                
                statement.setInt(6, obj.getId());

                statement.execute();

                // Fecha as conexões abertas
                closeConnections();
            } catch (SQLException e) {
               
            }
        }

	}
	
	@Override
	public Contrato getObj(int key) {
		connection = new Conexao().getConexao();
        String sql = "SELECT * WHERE id = ?;";
        Contrato contrato = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, key);
            resultSet = statement.executeQuery();

            /**
             * Verifica se a consulta retornou o dado e popula o objeto
             */
            if (resultSet.next()) {
            	contrato = new Contrato();
            	contrato.setId(resultSet.getInt(1));
            	contrato.setDataEntrada(resultSet.getString(2));
            	contrato.setPeriodo(resultSet.getInt(3));
            	contrato.setTarifacao(resultSet.getBoolean(4));
            	contrato.setStatus(resultSet.getBoolean(5));
            	contrato.setTotalPagar(resultSet.getDouble(6));
            
            	
            	Hospede h = new HospedeDAO().getObj(resultSet.getInt(7));
            	contrato.setHospede(h);
            	
            }

            // Fecha as conexões abertas
           closeConnections();
        } catch (SQLException e) {
           
        }

        return contrato;
	}

	@Override
	public void removerObj(int key) {
		if (key > 0) {
			connection = new Conexao().getConexao();
			String sql = "DELETE FROM contratos WHERE id = ?;";
			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, key);
				
				statement.execute();
				closeConnections();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public ArrayList<Contrato> getAllObj() {
		connection = new Conexao().getConexao();
		ArrayList<Contrato> listaContrato = new ArrayList<Contrato>();
        String sql = "SELECT * FROM contratos;";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Contrato contrato = new Contrato();
            	contrato.setId(resultSet.getInt(1));
            	contrato.setDataEntrada(resultSet.getString(2));
            	contrato.setPeriodo(resultSet.getInt(3));
            	contrato.setTarifacao(resultSet.getBoolean(4));
            	contrato.setStatus(resultSet.getBoolean(5));
            	contrato.setTotalPagar(resultSet.getDouble(6));
            
            	
            	Hospede h = new HospedeDAO().getObj(resultSet.getInt(7));
            	contrato.setHospede(h);

                listaContrato.add(contrato);
            }

            // Fecha as conexões abertas
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContrato;
	}
}
