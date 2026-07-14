import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        Registro registro = new Registro();
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
                   registro.registraDados();
                   break;
               case 2:
                   System.out.println("Qual dado consultar?");
                   System.out.println("[1] = Listar todos os gastos");
                   int opDado = sc.nextInt();

                   switch (opDado){
                       case 1:
                           registro.listarGastos();
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