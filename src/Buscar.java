import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Buscar {
    public int buscarPessoa(String nomePessoa) {
        String url = "SELECT id_pessoa FROM pessoa WHERE nome = ?";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            stmt.setString(1, nomePessoa);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                int idEncontrado = resultado.getInt("id_pessoa");
                return idEncontrado;
            } else {
                System.out.println("Pessoa não encontrada! Confira a digitação ou cadastre");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
            return -1;
        }
    }

    public void somaValor() {
        String url = "SELECT SUM(valor) FROM gastos";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("sum");
                System.out.println(valorTotal);

            } else {
                System.out.println("valor não encontrado! Confira a digitação ou cadastre");

            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }
    }
}
