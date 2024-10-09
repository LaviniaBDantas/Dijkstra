package org.example;
import java.util.*;

public class Grafo {
    private int V; // Número de vértices
    private List<List<Pair>> adj; // Lista de adjacência
    private Map<Character, Integer> vertexMap; // Mapeamento de char para int
    private Map<Integer, Character> reverseMap; // Mapeamento reverso de int para char
    private int index; // Índice para atribuir aos vértices

    class Pair implements Comparable<Pair> { //permite que a fila de prioridade ordene os pares com base no peso.
        int vertice, peso;
        Pair(int v, int p) {
            vertice = v;
            peso = p;
        }
        public int compareTo(Pair other) {
            return Integer.compare(this.peso, other.peso);
        }
    }

    public Grafo(int V) {
        this.V = V;
        adj = new ArrayList<>(V); //lista de listas List<List<Pair>>
        for (int i = 0; i < V; i++) { //cada vertice tem sua lista de adj que é uma sublista, que armazenará os pares(Vertice e Custo) dos vesrtices q conecta
            adj.add(new ArrayList<>());
        }
        vertexMap = new HashMap<>();
        reverseMap = new HashMap<>();
        index = 0;
    }

    private int getIndex(char c) {
        if (!vertexMap.containsKey(c)) {
            vertexMap.put(c, index);
            reverseMap.put(index, c);
            return index++;
        }
        return vertexMap.get(c);
    }

    public void addAresta(char origem, char destino, int p) {
        int indiceO = getIndex(origem);
        int indiceD = getIndex(destino);
        adj.get(indiceO).add(new Pair(indiceD, p));
        adj.get(indiceD).add(new Pair(indiceO, p));
    }
    //realiza a buscaDijkstra, mas armazena tbm o caminho(Para o EX.2)
    public void mostrarCaminho(char partida, char destino) {
        PriorityQueue<Pair> fila = new PriorityQueue<>();
        int[] dist = new int[V];
        int[] pai = new int[V]; ////agora precisa de um vetor de pais
        Arrays.fill(dist, Integer.MAX_VALUE);//todas as dist como infinito no incio
        Arrays.fill(pai, -1); //preenche inicialmente como -1 os valores do pai

        int indiceP = getIndex(partida);
        fila.add(new Pair(indiceP, 0));
        dist[indiceP] = 0;

        while (!fila.isEmpty()) {
            int u = fila.poll().vertice;

            for (Pair vizinho : adj.get(u)) {
                int v = vizinho.vertice;
                int peso = vizinho.peso;

                if (dist[v] > dist[u] + peso) { //se a distancia atual for maior do que a distancia de ir para o vizinho+o custo, entao atualiza a distancia e acrescenta o novo par na fila
                    dist[v] = dist[u] + peso;
                    fila.add(new Pair(v, dist[v]));
                    pai[v] = u; //salva no vetor de pais
                }
            }
        }
        List<Character> caminho = new ArrayList<>(); //lista para pegar os caracters dos pais nos indices diferentes de -1
        for (int iAtual = getIndex(destino); iAtual != -1; iAtual = pai[iAtual]) {
            caminho.add(reverseMap.get(iAtual));//guarda o caracter do pai
        }
        Collections.reverse(caminho); //para o caminho ficar ordenado corretamente

        System.out.print("Caminho de " + partida + " até " + destino + ": ");
        for (char v : caminho) {
            System.out.print(v + " ");
        }
        System.out.println("\nDistância total: " + dist[getIndex(destino)]);
    }

    public int menorCaminhoDijkstra(char partida, char destino) {
        PriorityQueue<Pair> fila = new PriorityQueue<>();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE); //inicialmente preenche as distancias com infinito
        dist[getIndex(partida)] = 0;

        fila.add(new Pair(getIndex(partida), 0));

        while (!fila.isEmpty()) {
            int u = fila.poll().vertice;

            if (u == getIndex(destino)) { // verifica se o indice do destino é igual ao indice atual, se for, encontrou, retorna
                System.out.println("Menor distancia saindo de " + partida + " para " + destino + ": " + dist[u]);
                return dist[u];
            }
            for (Pair vizinho : adj.get(u)) { //para cada vizinho dos adjacentes
                int v = vizinho.vertice;
                int peso = vizinho.peso;
                if (dist[v] > dist[u] + peso) { //se a distancia atual for maior do que a distancia de ir para o vizinho+o custo, entao atualiza a distancia e acrescenta o novo par na fila
                    dist[v] = dist[u] + peso;
                    fila.add(new Pair(v, dist[v]));
                }
            }
        }
        return -1; // Retorna -1 se não tiver caminho da origem para o destino
    }
}
