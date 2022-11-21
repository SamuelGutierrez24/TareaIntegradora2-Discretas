package model;

public class Edge<T>{

    Vertex<T> from;
    Vertex<T> to;
    private int weight;

    public Edge(Vertex<T> vfrom, Vertex<T> vto, int weight) {

        this.from=vfrom;
        this.to=vto;
        this.weight = weight;
    }
}
