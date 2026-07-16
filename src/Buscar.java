import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Buscar {

    Scanner sc = new Scanner(System.in);
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

                System.out.println("Total de gastos já registrados: R$" + valorTotal);


            } else {
                System.out.println("valor não encontrado! Confira a digitação ou cadastre");

            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }
    }
    public BigDecimal filtraPorPeriodo(int id, int dias) {
        String url = "SELECT SUM(valor) FROM gastos WHERE dia >= now() - (INTERVAL '1days' * ?) and id_pessoa = ?;";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            stmt.setInt(1, dias);
            stmt.setInt(2,id);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("sum");
                return valorTotal;
            } else {
                System.out.println("valor não encontrado! Confira a digitação e tente novamente.");
                return null;

            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
            return null;
        }
    }

    public BigDecimal filtraPorFinalidade(int id, String finalidade) {
        String url = "SELECT SUM(valor) FROM gastos WHERE finalidade = ? and id_pessoa = ?;";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            stmt.setString(1,finalidade);
            stmt.setInt(2,id);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("sum");
               return valorTotal;
            } else {
                System.out.println("valor não encontrado! Confira a digitação ou cadastre");
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
            return null;
        }
    }

    public BigDecimal filtraPorPagamento(int id, String formaPagamento) {
        String url = "SELECT SUM(valor) FROM gastos WHERE forma = ? and id_pessoa = ?;";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            stmt.setString(1,formaPagamento);
            stmt.setInt(2,id);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("sum");
                return valorTotal;
            } else {
                System.out.println("valor não encontrado! Confira a digitação ou cadastre");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
            return  null;
        }
    }

    public void Exibindo(int id, int dias) {
        String url = "SELECT valor, forma, finalidade, dia FROM gastos WHERE dia >= now() - (INTERVAL '1days' * ?) and id_pessoa = ?";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            stmt.setInt(1,dias);
            stmt.setInt(2,id);

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                BigDecimal valor = resultado.getBigDecimal("valor");
                String forma = resultado.getString("forma");
                String finalidade = resultado.getString("finalidade");
                LocalDate dataBD = resultado.getObject("dia", LocalDate.class);

                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String data = dataBD.format(formatar);

                System.out.println("Valor: R$" + valor + "|\tForma de pagamento: " + forma + "|\tFinalidade: "+ finalidade+ "|\tDia: "+data);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }
    }

    public void filtraPorPercentual(int id) {

        BigDecimal gasto = filtraPorPeriodo(id, 30);

        String url = "SELECT metagasto FROM pessoa WHERE id_pessoa = ?";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            stmt.setInt(1,id);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("metagasto");
                BigDecimal cem = new BigDecimal(100);

                BigDecimal media = gasto.multiply(cem).divide(valorTotal, RoundingMode.HALF_UP);

                System.out.println("Meta de gastos: "+ valorTotal+"\nValor gasto nos ultimos 30 dias: " + gasto + "\n% da meta gasta: %" + media);


            } else {
                System.out.println("valor não encontrado! Confira a digitação ou cadastre");

            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }
    }

    public void filtraPorTotalIndividual(int id) {
        String url = "SELECT SUM(valor) FROM gastos WHERE id_pessoa = ?;";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            stmt.setInt(1,id);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("sum");

                System.out.println("Total de gastos registrados na pessoa: R$" + valorTotal);


            } else {
                System.out.println("valor não encontrado! Confira a digitação ou cadastre");

            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }
    }
    public void TodasFormas(int id, String formaPagamento) {
        String url = "SELECT valor, finalidade, dia FROM gastos WHERE id_pessoa = ? AND forma = ?;";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            stmt.setInt(1,id);
            stmt.setString(2,formaPagamento);

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                BigDecimal valor = resultado.getBigDecimal("valor");
                String finalidade = resultado.getString("finalidade");
                LocalDate dataBD = resultado.getObject("dia", LocalDate.class);

                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String data = dataBD.format(formatar);

                System.out.println("Valor: R$" + valor + "|\tFinalidade: "+ finalidade+ "|\tDia: "+data);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }
    }

    public void TodasFinalidades(int id, String formaPagamento) {
        String url = "SELECT valor, forma, dia FROM gastos WHERE id_pessoa = ? AND finalidade = ?;";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            stmt.setInt(1,id);
            stmt.setString(2,formaPagamento);

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                BigDecimal valor = resultado.getBigDecimal("valor");
                String forma = resultado.getString("forma");
                LocalDate dataBD = resultado.getObject("dia", LocalDate.class);

                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String data = dataBD.format(formatar);

                System.out.println("Valor: R$" + valor + "|\tForma: "+ forma+ "|\tDia: "+data);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }
    }

}
