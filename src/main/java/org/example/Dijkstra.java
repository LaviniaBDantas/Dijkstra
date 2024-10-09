package org.example;

import java.util.*;

public class Dijkstra {

    static int V = 13;
    static int A = 23;

    static int dijkstra(Map<Integer, ArrayList<ArrayList<Integer>>> graph, int origem, int destino) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE); //infinito
        dist[origem] = 0; // origem p origem é 0

        PriorityQueue<int[]> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        filaPrioridade.offer(new int[]{origem, 0});

        // Conjunto de vértices já visitados
        boolean[] visitado = new boolean[V];

        while (!filaPrioridade.isEmpty()) {
            int[] node = filaPrioridade.poll();
            int u = node[0];
            int uDist = node[1];

            // Processa o nó somente se ainda não foi visitado
            if (!visitado[u]) {
                // Marca o nó como visitado
                visitado[u] = true;

                // Se encontrar o destino, para e retorna
                if (u == destino) {
                    return uDist;
                }

                // Processa todos os vizinhos do vértice atual
                for (ArrayList<Integer> vizinho : graph.getOrDefault(u, new ArrayList<>())) {
                    int v = vizinho.get(0); // Vértice vizinho
                    int peso = vizinho.get(1); // Peso da aresta (u -> v)

                    // Atualiza a distância se encontrar um caminho mais curto
                    if (!visitado[v] && dist[u] + peso < dist[v]) {
                        dist[v] = dist[u] + peso;
                        filaPrioridade.offer(new int[]{v, dist[v]});
                    }
                }
            }
        }


        return -1; // Retorna -1 se não tiver caminho da origem para o destino
    }

    public static void main(String[] args) {
        // Map para representar o grafo (índice de vértices -> lista de [vizinho, peso])
        Map<Integer, ArrayList<ArrayList<Integer>>> map = new HashMap<>();

        char[] o = {'a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'd', 'd', 'd', 'e', 'e', 'f', 'f', 'f', 'g', 'g', 'g', 'h', 'h', 'i', 'j'};
        char[] d = {'b', 'c', 'd', 'd', 'f', 'j', 'd', 'e', 'e', 'f', 'g', 'g', 'i', 'j', 'l', 'h', 'f', 'h', 'm', 'l', 'm', 'm', 'l'};
        int[] p = {61, 50, 42, 32, 29, 17, 56, 67, 45, 62, 85, 72, 73, 30, 45, 35, 20, 40, 32, 50, 21, 50, 30}; // pesos

        for (int i = 0; i < A; i++) {
            int origemIndex = o[i] - 'a'; // Converte 'a' a 'm' para índices de 0 a 12
            int destinoIndex = d[i] - 'a'; // Converte 'a' a 'm' para índices de 0 a 12

            // aresta de origem para destino
            ArrayList<Integer> arestaOrigem = new ArrayList<>();
            arestaOrigem.add(destinoIndex);
            arestaOrigem.add(p[i]);

            ArrayList<ArrayList<Integer>> listaAdjOrigem = map.getOrDefault(origemIndex, new ArrayList<>());
            listaAdjOrigem.add(arestaOrigem);
            map.put(origemIndex, listaAdjOrigem);

            // a volta, destino->origem, grafo não-direcionado
            ArrayList<Integer> arestaDestino = new ArrayList<>();
            arestaDestino.add(origemIndex);
            arestaDestino.add(p[i]);

            ArrayList<ArrayList<Integer>> listaAdjDestino = map.getOrDefault(destinoIndex, new ArrayList<>());
            listaAdjDestino.add(arestaDestino);
            map.put(destinoIndex, listaAdjDestino);
        }

        // vértices de origem e destino
        int origem = 'a' - 'a'; //  'a' para índice 0
        int destino = 'm' - 'a'; // 'm' para índice 12

        // Calcula o menor caminho usando o algoritmo de Dijkstra
        int menorCaminho = dijkstra(map, origem, destino);
        System.out.println("A distância menor de 'a' para 'm' é: " + menorCaminho);
    }
}
