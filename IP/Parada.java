package onibus;
import java.util.Random;

public class Parada {
    
    //atributos:
    
    private int ponto; //localizacao na linha
    private int terminal; //1 = terminal e 0 = ponto comum
    private int tempoParada; //tempo de parada obrigatoria em segundos
    
    /*
    Tempo de parada:
    ponto comum = 20 a 90 segundos
    terminal = 180 a 420 segundos
    */
    
    //métodos:

    //getters e setters
    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public int getTempoParada() {
        return tempoParada;
    }

    public void setTempoParada(int tempoParada) {
        this.tempoParada = tempoParada;
    }
    
    //mostra o status da parada
    public void status() {
        if(getTerminal() == 1) {
            System.out.println(": terminal com espera de " + getTempoParada() + " segundos.");
        } else {
            System.out.println(": espera de " + getTempoParada() + " segundos.");
        }
        System.out.println("PONTO NA RETA REAL: " + getPonto());
    }
}

//classe auxiliar: nó para a lista ligada
class NoParada {
    
    //atributos:
    
    NoParada prox; //referencia para o proximo nó
    Parada parada; //referencia para sua parada
    int posicao; //posicao na lista
    int dist; //distancia entre a parada atual e a anterior
    
    //métodos:
    
    //construtor do nó
    NoParada(Parada parada) { //recebe a parada como parametro
        this.parada = parada;
        prox = null; //aponta para null
    }
}

//lista ligada de nós de paradas
class ListaParada {
    
    //instâncias de objetos:
    
    Random n = new Random(); //para gerar numeros aleatórios
    
    //atributos:
    
    NoParada inicio = null; //nó inicial (null quando a lista está vazia)
    int totalParadas; //total de nós de parada
    int tamanho; //tamanho total da linha
    
    //métodos:
    
    //construtor da lista
    ListaParada(int totalParadas) { //recebe o total de paradas como parametro
        this.totalParadas = totalParadas;
        
        for(int i = 0; i < totalParadas; i++) { //insere i paradas
            this.tamanho += inserirNoFinal();
        }
        
        //agora, já sabemos o tamanho total da linha, e podemos definir quais são os terminais.
        
        inicio.parada.setTerminal(1); //o inicio é agora um terminal
        
        NoParada atual = inicio;
        
        while(atual.prox != null) { //enquanto ainda não tivermos alcançado o último ítem da lista
            atual.parada.setTempoParada(n.nextInt(70) + 20); //ajustamos o tempo de parada dos pontos comuns
            atual = atual.prox; //passar para o próximo
        }
        
        //atual é o último ítem da lista
        
        atual.parada.setTerminal(1); //o final é agora um terminal
        atual.parada.setTempoParada(n.nextInt(240) + 180); //portanto, seu tempo de parada será de 180 a 420 segundos
        inicio.parada.setTempoParada(n.nextInt(240) + 180); //acontecerá o mesmo com inicio
    }
    
    //metodo para inserir nova parada no fim da lista: retorna a distancia entre a parada anterior e a atual
    public int inserirNoFinal() {
        Parada parada = new Parada(); //instancia nova parada
        NoParada novo = new NoParada(parada); //instancia novo nó que armazena a referência para a parada de cima
        novo.dist = 0; //a distancia da parada anterior até a atual é inicializada como 0m
        
        if(inicio == null) { //se a lista estiver vazia
            inicio = novo; //o novo nó será o primeiro da lista
            novo.posicao = 0; //e sua posição será a 0
            novo.parada.setTerminal(1); //portanto, é um terminal
            return novo.dist; //como é a primeira parada, dista 0 da parada anterior (pois não há parada anterior)
        }
        
        //agora, encontraremos o final da lista
        
        //"atual" será o nó que está sendo analisado no momento
        
        NoParada atual = inicio; //começaremos analisando a lista do início
        
        while(atual.prox != null) { //enquanto ainda não tivermos alcançado o último ítem da lista
            atual = atual.prox; //passar para o próximo
        }
        
        //agora, atual é o último ítem da lista
        
        atual.prox = novo; //então atribuímos ao próximo o novo nó criado
        novo.prox = null; //para termos certeza de que este será o último ítem da lista
        novo.posicao = atual.posicao + 1; //atualizamos a posição do último na lista
        
        //agora, definiremos a distância entre a parada anterior e a atual
        
        /*
            Para isso, usaremos numeros aleatórios para uma das cinco possibilidades:
            
            0 = 50 metros
            1 = 75 metros
            2 = 100 metros
            3 = 125 metros
            4 = 150 metros
        
        */
        
        int num = n.nextInt(5); //num gera um numero aleatório
        
        //retornaremos diferentes valores para cada distância
        switch(num) {
            case 0:
                novo.dist =  50;
                break;
            case 1:
                novo.dist =  75;
                break;
            case 2:
                novo.dist = 100;
                break;
            case 3:
                novo.dist = 125;
                break;
            case 4:
                novo.dist = 150;
        }
        
        NoParada atual2 = inicio;
        int distAteAgora = 0;
        
        while(atual2.prox != null) {
            distAteAgora += atual2.dist;
            atual2 = atual2.prox;
        }
        
        novo.parada.setPonto(distAteAgora + novo.dist);
        
        return novo.dist;
    }
    
    //método para imprimir a lista
    public void imprimeLista() {
        System.out.println("(Legenda: cada '-' vale 5 metros)");
        System.out.println("");
        NoParada atual = inicio;
        while(atual.prox != null) { //enquanto ainda não tivermos alcançado o último ítem da lista
            switch(atual.dist) {
            case 50:
                for(int i = 0; i < 10; i++) {
                    System.out.println("-");
                }
                break;
            case 75:
                for(int i = 0; i < 15; i++) {
                    System.out.println("-");
                }
                break;
            case 100:
                for(int i = 0; i < 20; i++) {
                    System.out.println("-");
                }
                break;
            case 125:
                for(int i = 0; i < 25; i++) {
                    System.out.println("-");
                }
                break;
            case 150:
                for(int i = 0; i < 30; i++) {
                    System.out.println("-");
                }
            }
            System.out.print("PARADA " + (atual.posicao + 1));
            atual.parada.status(); //imprimir o status da parada
            atual = atual.prox; //passar para o próximo
        }
        switch(atual.dist) {
            case 50:
                for(int i = 0; i < 10; i++) {
                    System.out.println("-");
                }
                break;
            case 75:
                for(int i = 0; i < 15; i++) {
                    System.out.println("-");
                }
                break;
            case 100:
                for(int i = 0; i < 20; i++) {
                    System.out.println("-");
                }
                break;
            case 125:
                for(int i = 0; i < 25; i++) {
                    System.out.println("-");
                }
                break;
            case 150:
                for(int i = 0; i < 30; i++) {
                    System.out.println("-");
                }
            }
        System.out.print("PARADA " + (atual.posicao + 1));
        atual.parada.status(); //imprime o status do ultimo
    }
}
