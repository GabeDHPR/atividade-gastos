import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    public static Connection conectar(){
        String url = "jdbc:postgresql://localhost:5432/financeiro";
        String usuario = "postgres";
        String senha = "1381";

        try{
            Connection conexao = DriverManager.getConnection(url,usuario,senha);
            return conexao;
        } catch (SQLException e) {
            System.out.println("FALHA AO CONECTAR NO BD!!!");
            e.printStackTrace();
            return null;
        }
    }
}
