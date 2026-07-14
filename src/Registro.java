import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Registro {
    private ArrayList<Gasto> listaGastos = new ArrayList<>();

    private ArrayList<Pessoa> listaPessoa = new ArrayList<>();

    public void registrarPessoa(){
       try{
           Scanner sc = new Scanner(System.in);
           System.out.print("Digite o nome: ");
           String nome = sc.nextLine();

           System.out.print("Digite o CPF: ");
           String cpf = sc.nextLine();

           System.out.print("Digite o sálario: ");
           BigDecimal salario = sc.nextBigDecimal();

           System.out.println("Digite a meta de gastos: ");
           BigDecimal metaGastos = sc.nextBigDecimal();

           Pessoa pessoa = new Pessoa(nome, cpf, salario, metaGastos);
           listaPessoa.add(pessoa);



       } catch (Exception e) {
       }
       }

    public void registraDados(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Digite o nome do responsável pelo gasto: ");
            String nome = sc.nextLine();
            System.out.print("Digite o valor gasto: ");
            BigDecimal valor = sc.nextBigDecimal();
            sc.nextLine();
            System.out.print("Digite a finalidade do gasto: ");
            String finalide = sc.nextLine();
            System.out.print("Digite a forma de pagamento: ");
            String forma = sc.nextLine();
            System.out.print("Digite a data que o gasto ocorreu (DD/MM/AAAA): ");
            String dataGasto = sc.nextLine();

            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataGasto, formatador);
            System.out.println("=================");
            System.out.println("REGISTRO SALVO!");
            System.out.println("=================");

            Gasto gasto = new Gasto(nome, valor, finalide, forma, data);
            listaGastos.add(gasto);
            salvarNobanco(gasto);
        }catch(Exception e){
            System.out.println("======================");
            System.out.println("ERRO! TENTE NOVAMENTE");
            System.out.println("======================");

        }
    }
    public void listarGastos(){
        for(Gasto g: listaGastos){
            System.out.println(g);
        }
    }

    public void salvarNobanco(Gasto gasto){
        String url = "INSERT INTO Gastos (pessoa, valor, finalidade, forma, dia) VALUES (?,?,?,?,?)";

        try(Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {
            stmt.setString(1, gasto.getPessoa());
            stmt.setBigDecimal(2, gasto.getValor());
            stmt.setString(3, gasto.getFinalidade());
            stmt.setString(4, gasto.getForma());
            stmt.setObject(5, gasto.getDia());

            stmt.executeUpdate();

            System.out.println("==================================");
            System.out.println(" GASTO SALVO NO BANCO DE DADOS!   ");
            System.out.println("==================================");

        } catch (Exception e) {
            System.out.println("Erro ao salvar no banco de dados!");
            e.printStackTrace();
        }

        }

        public void salvarPessoa(Pessoa pessoa){
            String url = "INSERT INTO pessoa (nome, cpf, forma, salario, metaGastos) VALUES (?,?,?,?)";
            try(Connection c = ConexaoDB.conectar(); PreparedStatement stmt = c.prepareStatement(url)) {
                stmt.setString(1, pessoa.getNome());
                stmt.setString(2, pessoa.getCpf());
                stmt.setBigDecimal(3, pessoa.getSalario());
                stmt.setBigDecimal(4,pessoa.getMetaGastos());

                stmt.executeUpdate();

                System.out.println("====================");
                System.out.println("Pessoa salva no BD");
                System.out.println("====================");
            } catch (Exception e) {
                System.out.println("Erro ao salvar no banco de dados!");
                e.printStackTrace();
            }
        }
    }

