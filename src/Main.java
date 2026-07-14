import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        Registro registro = new Registro();
        Buscar buscar = new Buscar();
        int op;

       do {
           Scanner sc = new Scanner(System.in);
           System.out.println("Qual operação deseja realizar?");
           System.out.println("[0] - Cadastrar pessoa\n[1] - Inserir gasto\n[2] - Realizar consultas\n[3] - Sair");
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
                   System.out.println("Qual dado consultar?");
                   System.out.println("[1] - Valor gasto no mês \n[2] - Valor em cada finalidade\n[3] - Valor por formas de pagamento\n[4] - % do sálario e meta  \n[5] - Valor acumulado ");


                   int opDado = sc.nextInt();

                   switch (opDado){
                       case 1:
                           buscar.somaValor();
                           break;
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