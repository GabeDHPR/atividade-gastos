import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

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
    public BigDecimal filtraPorPeriodo(int id) {
        String url = "SELECT SUM(valor) FROM gastos WHERE dia >= now() - (INTERVAL '1days' * ?) and id_pessoa = ?;";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            System.out.print("Digite a quantidade de dias que deseja filtrar: ");
            int dias = sc.nextInt();
            sc.nextLine();

            stmt.setInt(1, dias);
            stmt.setInt(2,id);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("sum");

                System.out.println("Total de gastos registrados no periodo de " + dias + " dias : R$" + valorTotal);

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

    public void filtraPorFinalidade(int id) {
        String url = "SELECT SUM(valor) FROM gastos WHERE finalidade = ? and id_pessoa = ?;";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            System.out.print("Digite a finalidade: ");
            String finalidade = sc.nextLine();

            stmt.setString(1,finalidade);
            stmt.setInt(2,id);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("sum");

                System.out.println("Total de gastos registrados na finalidade [" + finalidade + "]: R$" + valorTotal);


            } else {
                System.out.println("valor não encontrado! Confira a digitação ou cadastre");

            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }
    }

    public void filtraPorPagamento(int id) {
        String url = "SELECT SUM(valor) FROM gastos WHERE forma = ? and id_pessoa = ?;";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            System.out.print("Digite a forma de pagamento: ");
            String formaPagamento = sc.nextLine();

            stmt.setString(1,formaPagamento);
            stmt.setInt(2,id);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("sum");

                System.out.println("Total de gastos registrados na finalidade [" + formaPagamento + "]: R$" + valorTotal);


            } else {
                System.out.println("valor não encontrado! Confira a digitação ou cadastre");

            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }
    }

    public void Exibindo(int id) {
        String url = "SELECT valor, forma, finalidade, dia FROM gastos WHERE forma = ? and id_pessoa = ?;";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            System.out.print("Digite a forma de pagamento: ");
            String formaPagamento = sc.nextLine();

            stmt.setString(1,formaPagamento);
            stmt.setInt(2,id);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("sum");

                System.out.println("Total de gastos registrados na finalidade [" + formaPagamento + "]: R$" + valorTotal);


            } else {
                System.out.println("valor não encontrado! Confira a digitação ou cadastre");

            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }
    }

    public void filtraPorPercentual(int id) {

        BigDecimal gasto = filtraPorPeriodo(id);

        String url = "SELECT metagasto FROM pessoa WHERE id_pessoa = ?";
        try (Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {

            stmt.setInt(1,id);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                BigDecimal valorTotal = resultado.getBigDecimal("metagasto");
                BigDecimal cem = new BigDecimal(100);

                BigDecimal media = gasto.multiply(cem).divide(valorTotal, RoundingMode.HALF_UP);

                System.out.println("Meta de gastos: "+ valorTotal+"\nValor gasto: " + gasto + "\n% da meta gasta: %" + media);


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

}
