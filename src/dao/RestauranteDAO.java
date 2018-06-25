package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Restaurante;
import model.Contrato;

public class RestauranteDAO implements DAO<Restaurante>{

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
	public void addObj(Restaurante obj) {
		connection = new Conexao().getConexao();
		
		String sql = "INSERT INTO restaurante VALUES(default,?,?,?,?;";
		
		if(obj != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, obj.getDescricao());
				statement.setString(2, obj.getData());
				statement.setDouble(3, obj.getValorTotal());
				statement.setInt(4, obj.getContrato().getId());
		
				statement.execute();
				closeConnections();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void atualizarObj(Restaurante obj) {
		if (obj != null) {
            connection = new Conexao().getConexao();
            String sql = "UPDATE restaurante SET descricao = ?, valortotal = ? WHERE id = ?;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getDescricao());
				statement.setDouble(2, obj.getValorTotal());
                
                statement.setInt(3, obj.getId());

                statement.execute();

                // Fecha as conexões abertas
                closeConnections();
            } catch (SQLException e) {
               
            }
        }

	}
	
	@Override
	public Restaurante getObj(int key) {
		connection = new Conexao().getConexao();
        String sql = "SELECT * WHERE id = ?;";
        Restaurante restaurante = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, key);
            resultSet = statement.executeQuery();

            /**
             * Verifica se a consulta retornou o dado e popula o objeto
             */
            if (resultSet.next()) {
            	restaurante = new Restaurante();
            	restaurante.setId(resultSet.getInt(1));
            	restaurante.setDescricao(resultSet.getString(2));
            	restaurante.setData(resultSet.getString(3));
            	restaurante.setValorTotal(resultSet.getDouble(4));
            
            	
            	Contrato c = new ContratoDAO().getObj(resultSet.getInt(5));
            	restaurante.setContrato(c);
            	
            }

            // Fecha as conexões abertas
           closeConnections();
        } catch (SQLException e) {
           
        }

        return restaurante;
	}

	@Override
	public void removerObj(int key) {
		if (key > 0) {
			connection = new Conexao().getConexao();
			String sql = "DELETE FROM restaurante WHERE id = ?;";
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
	public ArrayList<Restaurante> getAllObj() {
		connection = new Conexao().getConexao();
		ArrayList<Restaurante> listaRestaurante = new ArrayList<Restaurante>();
        String sql = "SELECT * FROM Restaurante;";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Restaurante restaurante = new Restaurante();
            	restaurante.setId(resultSet.getInt(1));
            	restaurante.setDescricao(resultSet.getString(2));
            	restaurante.setData(resultSet.getString(3));
            	restaurante.setValorTotal(resultSet.getDouble(4));
            
            	
            	Contrato c = new ContratoDAO().getObj(resultSet.getInt(5));
            	restaurante.setContrato(c);

                listaRestaurante.add(restaurante);
            }

            // Fecha as conexões abertas
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaRestaurante;
	}

}
