package onibus;

public class Linha {
    
    Passageiro passageiro = new Passageiro();
    Veiculo onibus = new Veiculo();
    Parada parada = new Parada();
    
    private int tamanho; //em km
    private int paradas; //numero de paradas

    public Linha() {
        
    }

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
    
}
