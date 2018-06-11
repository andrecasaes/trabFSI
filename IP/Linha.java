package onibus;
import java.util.Random;

public class Linha {
    
    //instâncias de objetos:
    
    Random n = new Random(); //sera usado para gerar numeros aleatorios
    
    //atributos:
    
    private int tamanho; //em metros: depende da distância entre as paradas e do numero de paradas
    private int paradas; //numero de paradas
    ListaParada paradasDaLinha;
    
    //métodos:
    
    //getters e setters
    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getParadas() {
        return paradas;
    }

    public void setParadas(int paradas) {
        this.paradas = paradas;
    }
    
    //mostra o status da linha
    public void status() {
        System.out.println("");
        System.out.println("Esta linha tem " + getParadas() + " paradas.");
        System.out.println("");
    }
    
    //construtor da linha
    public Linha(int numParadas) {
        setParadas(numParadas);
        paradasDaLinha = new ListaParada(getParadas());
        setTamanho(paradasDaLinha.tamanho);
    }
    
    public void imprimeLinha() {
        System.out.println("");
        System.out.println("Nova linha criada!");
        status();
        System.out.println("");
        paradasDaLinha.imprimeLista();
        System.out.println("");
        System.out.println("Esta linha tem " + getTamanho() + "m.");
        System.out.println("");
    }
    
}
