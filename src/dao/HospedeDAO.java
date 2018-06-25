package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Hospede;

public class HospedeDAO implements DAO<Hospede> {
	
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
	public void addObj(Hospede obj) {
		connection = new Conexao().getConexao();
		
		String sql = "INSERT INTO hospedes VALUES(?,?,?,?,?);";
		
		if(obj != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, obj.getCpf());
				statement.setString(2, obj.getNome());
				statement.setString(3, obj.getDataNascimento());
				statement.setString(4, obj.getEndereco());
				statement.setInt(5, obj.getCartaoCredito());
				
				statement.execute();
				closeConnections();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void atualizarObj(Hospede obj) {
		if (obj != null) {
            connection = new Conexao().getConexao();
            String sql = "UPDATE hospedes SET nome = ?, datanascimento = ?, endereco = ?, cartao = ? WHERE cpf = ? LIMIT 1;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getNome());
                statement.setString(2, obj.getDataNascimento());
                statement.setString(3, obj.getEndereco());
                statement.setInt(4, obj.getCartaoCredito());
                
                statement.setInt(5, obj.getCpf());

                statement.execute();

                // Fecha as conexões abertas
                closeConnections();
            } catch (SQLException e) {
               
            }
        }

	}
	
	@Override
	public Hospede getObj(int key) {
		connection = new Conexao().getConexao();
        String sql = "SELECT * WHERE cpf = ?;";
        Hospede hospede = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, key);
            resultSet = statement.executeQuery();

            /**
             * Verifica se a consulta retornou o dado e popula o objeto
             */
            if (resultSet.next()) {
            	hospede = new Hospede();
            	hospede.setCpf(resultSet.getInt(1));
            	hospede.setNome(resultSet.getString(2));
            	hospede.setEndereco(resultSet.getString(3));
            	hospede.setCartaoCredito(resultSet.getInt(4));
            
            }

            // Fecha as conexões abertas
           closeConnections();
        } catch (SQLException e) {
           
        }

        return hospede;
	}

	@Override
	public void removerObj(int key) {
		if (key > 0) {
			connection = new Conexao().getConexao();
			String sql = "DELETE FROM hospedes WHERE cpf = ?;";
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
	public ArrayList<Hospede> getAllObj() {
		connection = new Conexao().getConexao();
		ArrayList<Hospede> listaHospede = new ArrayList<Hospede>();
        String sql = "SELECT * FROM hospedes;";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Hospede h = new Hospede(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),  resultSet.getString(4), resultSet.getInt(5));

                listaHospede.add(h);
            }

            // Fecha as conexões abertas
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaHospede;
	}

}
