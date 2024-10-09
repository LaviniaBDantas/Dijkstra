package org.example;
public class Ex1 {

    public static void main(String[] args) {
        int V = 12;
        Grafo grafo = new Grafo(V);

        grafo.addAresta('A', 'B', 61);
        grafo.addAresta('A', 'C', 50);
        grafo.addAresta('A', 'D', 42);
        grafo.addAresta('B', 'D', 32);
        grafo.addAresta('B', 'F', 29);
        grafo.addAresta('B', 'J', 17);
        grafo.addAresta('C', 'D', 56);
        grafo.addAresta('C', 'E', 67);
        grafo.addAresta('D', 'E', 45);
        grafo.addAresta('D', 'F', 62);
        grafo.addAresta('D', 'G', 85);
        grafo.addAresta('E', 'G', 72);
        grafo.addAresta('E', 'I', 73);
        grafo.addAresta('F', 'J', 30);
        grafo.addAresta('F', 'L', 45);
        grafo.addAresta('F', 'H', 35);
        grafo.addAresta('G', 'F', 20);
        grafo.addAresta('G', 'H', 40);
        grafo.addAresta('G', 'M', 32);
        grafo.addAresta('H', 'L', 50);
        grafo.addAresta('H', 'M', 21);
        grafo.addAresta('I', 'M', 50);
        grafo.addAresta('J', 'L', 30);


        grafo.menorCaminhoDijkstra('A', 'M');
    }
}
