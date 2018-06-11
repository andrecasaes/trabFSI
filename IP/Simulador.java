package onibus;
import java.util.Random;
import java.util.Scanner;

public class Simulador {

    public static void main(String[] args) {
        
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        int comando = 0;
        boolean existeLinha = false;
        int paradas = 0;
        Linha linha;
        
        while(!existeLinha) {
            System.out.println("Comandos: ");
            System.out.println("0 - Iniciar uma nova simulação.");
            System.out.println("1 - Encerrar o programa.");
            System.out.print("Digite um comando: ");
            comando = sc.nextInt();
            if(comando == 1) {
                System.exit(0);
            }
            System.out.println("");
            System.out.println("Deseja que a nova linha tenha uma quantidade específica de paradas?");
            System.out.println("1 - Sim, digitarei um tamanho para a nova linha.");
            System.out.println("2 - Não, a linha pode ter tamanho aleatório.");
            System.out.print("Digite um comando: ");
            int resp = sc.nextInt();
            if(resp == 1) {
                System.out.print("Digite a quantidade de paradas: ");
                paradas = sc.nextInt();
            } else {
                paradas = random.nextInt(25) + 5;
            }
            
            existeLinha = true;
        }
        
        comando = 0;
        linha = new Linha(paradas);
        
        while(comando != 1) {
            linha.imprimeLinha();
            System.out.println("Comandos: ");
            System.out.println("0 - Prosseguir com a simulação.");
            System.out.println("1 - Encerrar a simulação.");
            System.out.print("Digite um comando: ");
            comando = sc.nextInt();
        }
        
        System.out.println("");
        System.out.println("Simulação encerrada.");
    }
    
}
