package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Babysitter;
import model.Contrato;

public class BabysitterDAO implements DAO<Babysitter>{

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
	public void addObj(Babysitter obj) {
		connection = new Conexao().getConexao();
		
		String sql = "INSERT INTO babysitters VALUES(default,?,default,?,?);";
		
		if(obj != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, obj.getDescricao());
				statement.setInt(2, obj.getHoraExtra());
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
	public void atualizarObj(Babysitter obj) {
		if (obj != null) {
            connection = new Conexao().getConexao();
            String sql = "UPDATE babysitters SET descricao = ?, horaextra = ?, valortotal = ? WHERE id = ?;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getDescricao());
				statement.setInt(2, obj.getHoraExtra());
				statement.setDouble(3, obj.getValorTotal());
                
                statement.setInt(4, obj.getId());

                statement.execute();

                // Fecha as conexões abertas
                closeConnections();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        }

	}
	
	@Override
	public Babysitter getObj(int key) {
		connection = new Conexao().getConexao();
        String sql = "SELECT * WHERE id = ?;";
        Babysitter babysitter = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, key);
            resultSet = statement.executeQuery();

            /**
             * Verifica se a consulta retornou o dado e popula o objeto
             */
            if (resultSet.next()) {
            	babysitter = new Babysitter();
            	babysitter.setId(resultSet.getInt(1));
            	babysitter.setDescricao(resultSet.getString(2));
            	babysitter.setHoraExtra(resultSet.getInt(3));
            	babysitter.setValorTotal(resultSet.getDouble(4));
            
            	
            	Contrato c = new ContratoDAO().getObj(resultSet.getInt(5));
            	babysitter.setContrato(c);
            	
            }

            // Fecha as conexões abertas
           closeConnections();
        } catch (SQLException e) {
        	e.printStackTrace();
        }

        return babysitter;
	}

	@Override
	public void removerObj(int key) {
		if (key > 0) {
			connection = new Conexao().getConexao();
			String sql = "DELETE FROM babysitters WHERE id = ?;";
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
	public ArrayList<Babysitter> getAllObj() {
		connection = new Conexao().getConexao();
		ArrayList<Babysitter> listaBabysitter = new ArrayList<Babysitter>();
        String sql = "SELECT * FROM babysitters;";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Babysitter babysitter = new Babysitter();
            	babysitter = new Babysitter();
            	babysitter.setId(resultSet.getInt(1));
            	babysitter.setDescricao(resultSet.getString(2));
            	babysitter.setHoraExtra(resultSet.getInt(3));
            	babysitter.setValorTotal(resultSet.getDouble(4));
            
            	
            	Contrato c = new ContratoDAO().getObj(resultSet.getInt(5));
            	babysitter.setContrato(c);

                listaBabysitter.add(babysitter);
            }

            // Fecha as conexões abertas
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaBabysitter;
	}

}
