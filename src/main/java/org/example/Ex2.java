package org.example;
import java.util.*;

class Ex2 {
    public static void main(String[] args) {
        int V = 11;
        Grafo grafo = new Grafo(V);
        grafo.addAresta('O', 'R', 4);
        grafo.addAresta('O', 'K', 1);
        grafo.addAresta('R', 'K', 2);
        grafo.addAresta('R', 'G', 0);
        grafo.addAresta('R', 'E', 0);
        grafo.addAresta('K', 'G', 1);
        grafo.addAresta('G', 'E', 3);
        grafo.addAresta('E', 'A', 2);
        grafo.addAresta('G', 'Q', 5);
        grafo.addAresta('Q', 'A', 4);
        grafo.addAresta('Q', 'M', 2);
        grafo.addAresta('M', 'A', 2);
        grafo.addAresta('A', 'D', 1);
        grafo.addAresta('A', 'T', 3);
        grafo.addAresta('T', 'X', 2);
        grafo.addAresta('T', 'D', 4);
        grafo.addAresta('D', 'X', 3);

        //grafo.menorCaminhoDjikstra('G');//calcula todos

        grafo.menorCaminhoDijkstra('G', 'O');
        grafo.menorCaminhoDijkstra('G', 'E');
        grafo.menorCaminhoDijkstra('G', 'X');
        grafo.menorCaminhoDijkstra('G', 'T');
        grafo.mostrarCaminho('G', 'T');//esse metodo realiza a buscaDijkstra, mas tbm armazena o caminho
    }
}
