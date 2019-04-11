package exemploAcessoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao2 {
    private static Connection conexao = null;
    
	public static Connection obterConexao() {
		String url = "jdbc:postgresql://localhost:5432/agenda";
		String usuario = "postgres";
		String senha = "local";

		if (conexao == null){
            try {
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(url, usuario, senha);
            } catch (ClassNotFoundException e1) {
                System.out.println("Classe não encontrada");
                System.exit(0);
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
            
        }
    	return conexao;		
	}
	
	public static void close(){
        try {
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conexao = null;
    }
	
	public static Statement obterStatement() {
		try {
			return obterConexao().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws SQLException {
		ResultSet resultado  = obterStatement().executeQuery("select * from pessoa");
		
		while (resultado.next()) {
			System.out.print(resultado.getString(1) + " - ");
			System.out.println(resultado.getString("nome"));
			
		}
		
		resultado.close(); //--fecha a conexão --

	}
}