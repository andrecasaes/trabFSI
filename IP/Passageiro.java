package onibus;

//Andre Biondi Casaes - 10391184
//Karina Huh - 10723822
//Lais Cavalheiro - 10687638

public class Passageiro {
    
    Veiculo onibus = new Veiculo();
    Linha linha = new Linha();
    Parada parada = new Parada();
    
    //atributos
    
    private boolean necessidadeEspecial; //true = sim e false = nao
    private boolean sentado; //true = sentado e nao = em pe
    
    private int tempoAdicional; //inteiro para medir tempo em minutos
    private int tempoViagem; //inteiro para medir tempo minutos
    private int tempoEspera; //inteiro para medir tempo minutos
    private int origem; //ponto de partida
    private int destino; //ponto de chegada
    
    //construtores

    public Passageiro() {
        
    }

    public Passageiro(boolean necessidadeEspecial, int origem, int destino) {
        
        //atribui os valores passados como parametro para os atributos do passageiro
        
        this.necessidadeEspecial = necessidadeEspecial;
        this.origem = origem;
        this.destino = destino;
        
        //no caso de o passageiro ser portador de necessidades especiais
        
        if(necessidadeEspecial) {
            this.sentado = true; //ele precisara ir sentado
            this.tempoAdicional += 2 * 2; //atribuimos um valor arbitrario por ora para o tempo adicional de embarque/desembarque (por isso multiplicamos por 2)
        }
    }
    
    //getters e setters
    
    public boolean isNecessidadeEspecial() {
        return necessidadeEspecial;
    }

    public void setNecessidadeEspecial(boolean necessidadeEspecial) {
        this.necessidadeEspecial = necessidadeEspecial;
    }

    public int getTempoAdicional() {
        return tempoAdicional;
    }

    public void setTempoAdicional(int tempoAdicional) {
        this.tempoAdicional = tempoAdicional;
    }

    public int getTempoViagem() {
        return tempoViagem;
    }

    public void setTempoViagem(int tempoViagem) {
        this.tempoViagem = tempoViagem;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public int getOrigem() {
        return origem;
    }

    public void setOrigem(int origem) {
        this.origem = origem;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }
    
    //metodos dos passageiros 
    
    public void embarcar() {
        if(onibus.getCapacidadeMax() != onibus.getPassageiros()) {
            onibus.setPassageiros(onibus.getPassageiros() + 1);
        }
    }
            
    //pretendemos linkar com o veiculo (ex.: quando um passageiro embarcar, o numero de passageiros no onibus aumentara)
    public void desembarcar() {
        if(onibus.getPassageiros() > 0) {
            onibus.setPassageiros(onibus.getPassageiros() - 1);
        }
    }
    
}