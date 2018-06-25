package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Quarto;
import model.Contrato;
import model.Quarto.Tipos;

public class QuartoDAO implements DAO<Quarto>{

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
	public void addObj(Quarto obj) {
		connection = new Conexao().getConexao();
		
		String sql = "INSERT INTO quartos VALUES(default,?,?,?,?,?);";
		
		if(obj != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, obj.getDescricao());
				statement.setInt(2, obj.getNumPessoas());
				statement.setString(3, obj.getTipoQuarto().toString());
				statement.setDouble(4, obj.getValorTotal());
				statement.setInt(5, obj.getContrato().getId());
		
				statement.execute();
				closeConnections();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void atualizarObj(Quarto obj) {
		if (obj != null) {
            connection = new Conexao().getConexao();
            String sql = "UPDATE quartos SET descricao = ?, numcamas = ?, numpessoas = ?, tipoquarto = ?, valortotal = ? WHERE id = ?;";
            try {
            	statement = connection.prepareStatement(sql);
            	statement.setString(1, obj.getDescricao());
				statement.setInt(2, obj.getNumPessoas());
				statement.setString(3, obj.getTipoQuarto().toString());
				statement.setDouble(4, obj.getValorTotal());
       
                statement.setInt(6, obj.getId());

                statement.execute();

                // Fecha as conexões abertas
                closeConnections();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        }

	}
	
	@Override
	public Quarto getObj(int key) {
		connection = new Conexao().getConexao();
        String sql = "SELECT * WHERE id = ?;";
        Quarto quarto = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, key);
            resultSet = statement.executeQuery();

            /**
             * Verifica se a consulta retornou o dado e popula o objeto
             */
            if (resultSet.next()) {
            	quarto = new Quarto();
            	quarto.setId(resultSet.getInt(1));
            	quarto.setDescricao(resultSet.getString(2));
            	quarto.setNumPessoas(resultSet.getInt(3));
            	switch (resultSet.getString(4)) {
            	case "PRESIDENCIAL":
					quarto.setTipoQuarto(Tipos.PRESIDENCIAL);
					break;
				case "LUXO_SIMPLES":
					quarto.setTipoQuarto(Tipos.LUXO_SIMPLES);
					break;
				case "LUXO_DUPLO":
					quarto.setTipoQuarto(Tipos.LUXO_DUPLO);
					break;
				case "LUXO_TRIPLO":
					quarto.setTipoQuarto(Tipos.LUXO_TRIPLO);
					break;
				case "EXECUTIVO_SIMPLES":
					quarto.setTipoQuarto(Tipos.EXECUTIVO_SIMPLES);
					break;
				case "EXECUTIVO_DUPLO":
					quarto.setTipoQuarto(Tipos.EXECUTIVO_DUPLO);
					break;
				case "EXECUTIVO_TRIPLO":
					quarto.setTipoQuarto(Tipos.EXECUTIVO_TRIPLO);
					break;
				default:
					break;
				}
            	quarto.setValorTotal(resultSet.getDouble(5));
            	
            	
            	Contrato c = new ContratoDAO().getObj(resultSet.getInt(6));
            	quarto.setContrato(c);
            	
            }

            // Fecha as conexões abertas
           closeConnections();
        } catch (SQLException e) {
        	e.printStackTrace();
        }

        return quarto;
	}

	@Override
	public void removerObj(int key) {
		if (key > 0) {
			connection = new Conexao().getConexao();
			String sql = "DELETE FROM quartos WHERE id = ?;";
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
	public ArrayList<Quarto> getAllObj() {
		connection = new Conexao().getConexao();
		ArrayList<Quarto> listaQuarto = new ArrayList<Quarto>();
        String sql = "SELECT * FROM quartos;";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Quarto quarto = new Quarto();
            	quarto.setId(resultSet.getInt(1));
            	quarto.setDescricao(resultSet.getString(2));
            	quarto.setNumPessoas(resultSet.getInt(3));
            	switch (resultSet.getString(4)) {
            	case "PRESIDENCIAL":
					quarto.setTipoQuarto(Tipos.PRESIDENCIAL);
					break;
				case "LUXO_SIMPLES":
					quarto.setTipoQuarto(Tipos.LUXO_SIMPLES);
					break;
				case "LUXO_DUPLO":
					quarto.setTipoQuarto(Tipos.LUXO_DUPLO);
					break;
				case "LUXO_TRIPLO":
					quarto.setTipoQuarto(Tipos.LUXO_TRIPLO);
					break;
				case "EXECUTIVO_SIMPLES":
					quarto.setTipoQuarto(Tipos.EXECUTIVO_SIMPLES);
					break;
				case "EXECUTIVO_DUPLO":
					quarto.setTipoQuarto(Tipos.EXECUTIVO_DUPLO);
					break;
				case "EXECUTIVO_TRIPLO":
					quarto.setTipoQuarto(Tipos.EXECUTIVO_TRIPLO);
					break;
				default:
					break;
				}
            	quarto.setValorTotal(resultSet.getDouble(5));
            	
            	
            	Contrato c = new ContratoDAO().getObj(resultSet.getInt(6));
            	quarto.setContrato(c);
            	
                listaQuarto.add(quarto);
            }

            // Fecha as conexões abertas
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaQuarto;
	}
}
