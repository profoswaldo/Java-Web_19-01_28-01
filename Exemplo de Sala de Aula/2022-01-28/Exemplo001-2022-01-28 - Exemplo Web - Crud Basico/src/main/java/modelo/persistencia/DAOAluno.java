package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.pessoal.Aluno;

public class DAOAluno {
	
	private final String url = "jdbc:postgresql://localhost:5432/postgres";
	private final String usuario = "postgres"; //verificar o seu usuario do banco
	private final String senha = "aluno123"; //verificar a sua senha do banco
	private final String driverBanco = "org.postgresql.Driver";
	private Connection conexao = null;
	private PreparedStatement preparedStatement = null;

	public DAOAluno() throws Exception {
		Class.forName(driverBanco);
		conexao = DriverManager.getConnection(url, usuario, senha);
	}
	
	public void incluir(Aluno aluno) throws Exception {
		
		final String instrucao_sql = "INSERT INTO alunos (id, matricula, nome, teste, prova) VALUES(?,?,?,?,?)";
		
			preparedStatement = conexao.prepareStatement(instrucao_sql);
			preparedStatement.setInt(1, aluno.getId());
			preparedStatement.setString(2, aluno.getMatricula());
			preparedStatement.setString(3, aluno.getNome());
			preparedStatement.setDouble(4, aluno.getTeste());
			preparedStatement.setDouble(5, aluno.getProva());
			
			preparedStatement.executeUpdate();
			
		
	}
	
	
	public void excluir(Aluno aluno) throws Exception  {
		
		final String instrucao_sql = "DELETE FROM alunos WHERE matricula = ?";
		
			preparedStatement = conexao.prepareStatement(instrucao_sql);
			
			preparedStatement.setString(1, aluno.getMatricula());
			
			preparedStatement.executeUpdate();
			
			
	}
	
	
	public void alterar(Aluno aluno) throws Exception {
		
		final String instrucao_sql = "UPDATE alunos set id = ?, nome = ?, teste = ?, prova = ? WHERE matricula = ?";

			preparedStatement = conexao.prepareStatement(instrucao_sql);
			preparedStatement.setInt(1, aluno.getId());
			preparedStatement.setString(5, aluno.getMatricula());
			preparedStatement.setString(2, aluno.getNome());
			preparedStatement.setDouble(3, aluno.getTeste());
			preparedStatement.setDouble(4, aluno.getProva());
			
			preparedStatement.executeUpdate();
			

	}
	
	public Aluno consultar(Aluno aluno) throws Exception {
		final String instrucao_sql = "SELECT * FROM alunos WHERE matricula = ?";

			preparedStatement = conexao.prepareStatement(instrucao_sql);
			preparedStatement.setString(1, aluno.getMatricula());
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				aluno.setId(resultSet.getInt("id"));
				aluno.setMatricula(resultSet.getString("matricula"));
				aluno.setNome(resultSet.getString("nome"));
				aluno.setTeste(resultSet.getDouble("teste"));
				aluno.setProva(resultSet.getDouble("prova"));
				
			}

		
		return aluno;
	}
}

