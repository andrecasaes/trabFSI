package onibus;

public class Parada {
    
    Passageiro passageiro = new Passageiro();
    Veiculo onibus = new Veiculo();
    Linha linha = new Linha();
    
    private int ponto; //localizacao na linha
    private boolean terminal; //true = terminal e false = ponto comum
    private int tempoParada; //tempo de parada obrigatoria

    public Parada() {
        
    }

    public Parada(int ponto, boolean terminal, int tempoParada) {
        this.ponto = ponto;
        this.terminal = terminal;
        this.tempoParada = tempoParada;
    }

    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public int getTempoParada() {
        return tempoParada;
    }

    public void setTempoParada(int tempoParada) {
        this.tempoParada = tempoParada;
    }
    
    
}
