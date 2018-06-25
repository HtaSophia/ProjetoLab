package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AluguelCarro;
import model.AluguelCarro.Tipos;
import model.Contrato;

public class AluguelCarroDAO implements DAO<AluguelCarro>{

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
	public void addObj(AluguelCarro obj) {
		connection = new Conexao().getConexao();
		
		String sql = "INSERT INTO aluguelcarros VALUES(default,?,?,?,?,?,?,?);";
		
		if(obj != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, obj.getDescricao());
				statement.setInt(2, obj.getQuantiaDiarias());
				statement.setBoolean(3, obj.isTanqueCheio());
				statement.setBoolean(4, obj.isSeguroCarro());
				statement.setString(5, obj.getTipoCarro().toString());
				statement.setDouble(6, obj.getValorTotal());
				statement.setInt(7, obj.getContrato().getId());
		
				statement.execute();
				closeConnections();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void atualizarObj(AluguelCarro obj) {
		if (obj != null) {
            connection = new Conexao().getConexao();
            String sql = "UPDATE aluguelcarros SET descricao = ?, quantiadiarias = ?, tanquecheio = ?, segurocarro = ?, tipocarro = ?, valortotal = ? WHERE id = ?;";
            try {
            	statement = connection.prepareStatement(sql);
            	statement.setString(1, obj.getDescricao());
				statement.setInt(2, obj.getQuantiaDiarias());
				statement.setBoolean(3, obj.isTanqueCheio());
				statement.setBoolean(4, obj.isSeguroCarro());
				statement.setString(5, obj.getTipoCarro().toString());
				statement.setDouble(6, obj.getValorTotal());
                
                statement.setInt(7, obj.getId());

                statement.execute();

                // Fecha as conexões abertas
                closeConnections();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        }

	}
	
	@Override
	public AluguelCarro getObj(int key) {
		connection = new Conexao().getConexao();
        String sql = "SELECT * WHERE id = ?;";
        AluguelCarro aluguelCarro = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, key);
            resultSet = statement.executeQuery();

            /**
             * Verifica se a consulta retornou o dado e popula o objeto
             */
            if (resultSet.next()) {
            	aluguelCarro = new AluguelCarro();
            	aluguelCarro.setId(resultSet.getInt(1));
            	aluguelCarro.setDescricao(resultSet.getString(2));
            	aluguelCarro.setQuantiaDiarias(resultSet.getInt(3));
            	aluguelCarro.setTanqueCheio(resultSet.getBoolean(4));
            	aluguelCarro.setSeguroCarro(resultSet.getBoolean(5));
            	switch (resultSet.getString(6)) {
				case "AUTOMOVEL_LUXO":
					aluguelCarro.setTipoCarro(Tipos.AUTOMOVEL_LUXO);
					break;
				case "AUTOMOVEL_EXECUTIVO":
					aluguelCarro.setTipoCarro(Tipos.AUTOMOVEL_EXECUTIVO);
				default:
					break;
				}
            	aluguelCarro.setValorTotal(resultSet.getDouble(7));
            	
            	
            	Contrato c = new ContratoDAO().getObj(resultSet.getInt(8));
            	aluguelCarro.setContrato(c);
            	
            }

            // Fecha as conexões abertas
           closeConnections();
        } catch (SQLException e) {
        	e.printStackTrace();
        }

        return aluguelCarro;
	}

	@Override
	public void removerObj(int key) {
		if (key > 0) {
			connection = new Conexao().getConexao();
			String sql = "DELETE FROM aluguelcarros WHERE id = ?;";
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
	public ArrayList<AluguelCarro> getAllObj() {
		connection = new Conexao().getConexao();
		ArrayList<AluguelCarro> listaAluguelCarro = new ArrayList<AluguelCarro>();
        String sql = "SELECT * FROM aluguelcarros;";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	AluguelCarro aluguelCarro = new AluguelCarro();
            	aluguelCarro.setId(resultSet.getInt(1));
            	aluguelCarro.setDescricao(resultSet.getString(2));
            	aluguelCarro.setQuantiaDiarias(resultSet.getInt(3));
            	aluguelCarro.setTanqueCheio(resultSet.getBoolean(4));
            	aluguelCarro.setSeguroCarro(resultSet.getBoolean(5));
            	switch (resultSet.getString(6)) {
				case "AUTOMOVEL_LUXO":
					aluguelCarro.setTipoCarro(Tipos.AUTOMOVEL_LUXO);
					break;
				case "AUTOMOVEL_EXECUTIVO":
					aluguelCarro.setTipoCarro(Tipos.AUTOMOVEL_EXECUTIVO);
				default:
					break;
				}
            	aluguelCarro.setValorTotal(resultSet.getDouble(7));
            	
            	
            	Contrato c = new ContratoDAO().getObj(resultSet.getInt(9));
            	aluguelCarro.setContrato(c);
            	
                listaAluguelCarro.add(aluguelCarro);
            }

            // Fecha as conexões abertas
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAluguelCarro;
	}


}
