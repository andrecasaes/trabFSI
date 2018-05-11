package onibus;

public class Veiculo {
    
    Passageiro passageiro = new Passageiro();
    Linha linha = new Linha();
    Parada parada = new Parada();
    
    //espaço e velocidade do veiculo
    private double velocidade; //em km/h
    private double espaco; //em km
    private int ponto; //ponto de onibus em que o onibus se encontra
    private double tempoViagem; //tempo em minutos
    
    //passageiros e capacidade do veiculo
    private String tamanho; //qualitativo
    private int passageiros; //numero de passageiros
    private int capacidadeMax; //quantos passageiros cabem
    private int numeroAssentos; //quantos assentos tem
    private boolean adaptacao; //true = tem e false = nao tem
    
    /*
    
    Tamanhos (utópicos):
    
    grande:
    capacidade maxima = 100
    assentos = 70
    
    medio: 
    capacidade maxima = 70
    assentos = 50
    
    pequeno:
    capacidade maxima = 50
    assentos = 30
    
    */

    public Veiculo() {
        
    }

    public Veiculo(String tamanho, boolean adaptacao) {
        
        this.tamanho = tamanho;
        this.adaptacao = adaptacao;
        
        switch(tamanho) {
            case "grande":
                this.capacidadeMax = 100;
                this.numeroAssentos = 70;
                break;
            case "medio":
                this.capacidadeMax = 70;
                this.numeroAssentos = 50;
                break;
            case "pequeno":
                this.capacidadeMax = 50;
                this.numeroAssentos = 30;
                break;
        }
    }

    public int getNumeroAssentos() {
        return numeroAssentos;
    }

    public void setNumeroAssentos(int numeroAssentos) {
        this.numeroAssentos = numeroAssentos;
    }

    public int getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(int passageiros) {
        this.passageiros = passageiros;
    }

    public int getCapacidadeMax() {
        return capacidadeMax;
    }

    public void setCapacidadeMax(int capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isAdaptacao() {
        return adaptacao;
    }

    public void setAdaptacao(boolean adaptacao) {
        this.adaptacao = adaptacao;
    }

    public double getEspaco() {
        return espaco;
    }

    public void setEspaco(double espaco) {
        this.espaco = espaco;
    }

    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

    public double getTempViagem() {
        return tempoViagem;
    }

    public void setTempoViagem(double tempoViagem) {
        this.tempoViagem = tempoViagem;
    }
    
    
}
