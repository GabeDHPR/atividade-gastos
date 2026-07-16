import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        Registro registro = new Registro();
        Buscar buscar = new Buscar();
        int op;

       do {
           Scanner sc = new Scanner(System.in);
           System.out.println("===============================");
           System.out.println("\t\tMENU INICIAL!");
           System.out.println("===============================");
           System.out.println("[0] - Cadastrar pessoa\n[1] - Inserir gasto\n[2] - Realizar consultas\n[3] - Sair");
           System.out.print("Digite a opção desejada: ");
           op = sc.nextInt();

           switch (op) {
               case 0:
                   registro.registrarPessoa();
                   break;
               case 1:
                   sc.nextLine();
                   System.out.print("Digite o nome da pessoa que irá registar o gasto: ");
                   String nome = sc.nextLine();
                  int id_pessoa = buscar.buscarPessoa(nome);
                    if(id_pessoa!=-1) {
                      registro.registraDados(id_pessoa);
                     }
                   break;
               case 2:
                   System.out.println("\n\n=========================================");
                   System.out.println("\t\tMENU DE CONSULTAS");
                   buscar.somaValor();
                   System.out.println("=========================================\n");
                   sc.nextLine();

                   System.out.println("====================================================================");
                   System.out.print("Digite o nome do responsável pelos gastos que deseja filtrar: ");
                   String user = sc.nextLine();
                   int id = buscar.buscarPessoa(user);
                   if(id!=-1) {
                       System.out.println("====================================================================");
                       int opDado;
                       do {
                           System.out.println("\nFiltar por:");
                           System.out.println("[1] - Periodo \n[2] - Por finalidade\n[3] - Por formas de pagamento\n[4] - Por % do sálario e meta  \n[5] - Por valor acumulado \n[6] - sair");
                           System.out.print("Digite sua opção: ");

                           opDado = sc.nextInt();
                           sc.nextLine();

                           switch (opDado) {
                               case 1:
                                   System.out.print("Digite a quantidade de dias que deseja filtrar: ");
                                   int dias = sc.nextInt();
                                   sc.nextLine();
                                   BigDecimal valor = buscar.filtraPorPeriodo(id, dias);
                                   System.out.println("Total de gastos registrados no periodo de " + dias + " dias : R$" + valor);
                                   if(valor!=null){
                                       System.out.println("Exibir todos os gastos do periodo? [1] - SIM [2] - NÃO");
                                       int resposta = sc.nextInt();
                                       if(resposta == 1){
                                           buscar.Exibindo(id, dias);
                                       }
                                   }
                                   break;
                               case 2:
                                   System.out.print("Digite a finalidade do gasto: ");
                                   String finalidade = sc.nextLine();
                                   valor = buscar.filtraPorFinalidade(id, finalidade);
                                   if(valor!=null){
                                       System.out.println("Exibir todos os gastos registrados nessa finalidade? [1] - SIM [2] - NÃO");
                                       int resposta = sc.nextInt();
                                       if(resposta == 1){
                                           buscar.TodasFinalidades(id, finalidade);
                                       }
                                   }

                                   break;
                               case 3:
                                   System.out.print("Digite a forma de pagamento: ");
                                   String formaPagamento = sc.nextLine();
                                   valor = buscar.filtraPorPagamento(id, formaPagamento);

                                   if(valor!=null){
                                       System.out.println("Exibir todos os gastos dessa forma de pagamento? [1] - SIM [2] - NÃO");
                                       int resposta = sc.nextInt();
                                       if(resposta == 1){
                                           buscar.TodasFormas(id, formaPagamento);
                                       }
                                   }
                                   break;
                               case 4:
                                   buscar.filtraPorPercentual(id);
                                   break;
                               case 5:
                                   buscar.filtraPorTotalIndividual(id);
                                   break;
                               case 6:
                                   System.out.println("SAINDO!....");
                                   break;
                               default:
                                   System.out.println("Digite uma opção valida seu besta!");
                           }
                       }while (opDado!=6);
                   }

                   break;
               case 3:
                   System.out.println("Saindo...");
                   break;
               default:
                   System.out.println("op invalido");
           }
       }while(op!=3);


    }
}