package exemploAcessoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	public static void main(String[] args) {
		try {
			/*
			 * localhost ->  Máquina onde está hospedado o banco, pode ser o IP da máquina, para isto é necessário habilitar o acesso
			 * 						no arquivo de configuração pg_hba.conf (no caso do postgres)
			 * :5432     ->  Porta habilitada para conexões no postgres
			 * 
			 */
			String url = "jdbc:postgresql://localhost:5432/agenda";
			String usuario = "postgres";
			String senha = "local";
			
			//-- nome da classe que "conhece" o processo de comunicação com o postgres
			Class.forName("org.postgresql.Driver");
			//-- objeto que estabelece a conexão com o banco, se passar daqui, tem chances de funcionar ....
			Connection con = DriverManager.getConnection(url, usuario, senha); 
			System.out.println("conseguiu acessar o banco!!!");
			
			/*
			 *  Statement -> interface que possui as declarações de métodos utilizados na manipulação de dados
			 *  				utilizar de preferência 
			 *  				executeQuery(sql) para consultas
			 *  				execute(sql) para inserts, deletes e updates (possui retorno boolean)
			 */
			
			Statement st = con.createStatement(); //importar de java.sql
			
			//-- ResultSet -> é o objeto que possui o resultado de uma instrução sql
			ResultSet resultado = st.executeQuery("select * from pessoa");
			
			/*
			 * next() movimenta para o próximo registro
			 * 
			 * instancia_resultset.getString(coluna) devolve o conteúdo do campo. o Nome da coluna pode ser expressa pelo seu nome entre aspas ou por um número
			 * 		onde 1 é a 1ª coluna, 2 a 2ª e assim por diante ... 
			 */
			while (resultado.next()) {
				System.out.print(resultado.getString(1) + " - ");
				System.out.println(resultado.getString("nome"));
				
			}
			
			resultado.close(); //--fecha a conexão --

			
        } catch (ClassNotFoundException e1) {
            System.out.println("Classe não encontrada, verifique se o jar foi adicionado ao projeto");
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}