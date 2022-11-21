package model;

import java.util.*;

public class Graph<T> {
    private ArrayList<Vertex<T>> vertices;
    private HashMap<Integer, Vertex<T>>vertexes;

    private int time = 0;
    private int white = 1;
    private  int grey = 2;
    private int black = 3;

    public Graph() {
        this.vertexes = new HashMap<>();
        this.vertices = new ArrayList<>();
    }

    public void addVertex(T value, int key)throws Exception{
        if(vertexes.containsKey(key)){
            throw new Exception("The node reference with the key " + key + " already exist");
        }else {
            Vertex<T> vertex = new Vertex<>(value, key);
            vertexes.put(key, vertex);
            vertices.add(vertex);
        }
    }

    public void addAdjacent(int keyVertex,int keyAd){
        //hash map
        vertexes.get(keyVertex).addAdj(vertexes.get(keyAd));

        //ArrayList
        Vertex<T> vertex = new Vertex<>(null,-1);
        boolean flag = true;
        for(int i = 0; i<vertices.size()&& flag; i++){
            if(vertices.get(i).getKey()== keyVertex){
                vertex = vertices.get(i);
                flag = false;
            }
        }
        for(int i = 0; i<vertices.size()&& flag; i++){
            if(vertices.get(i).getKey()== keyAd){
                vertex.addAdj(vertices.get(i));
            }
        }

    }

    public void deleteVertex(int key){
        vertexes.remove(key);
    }

    public void deleteAllReference(int key){
        Vertex<T> vertex = vertexes.get(key);
        vertexes.remove(key);
        for(Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            c.getValue().deleteAd(vertex);
        }
    }



    public void BFS(int keyRoot){
        LinkedList<Vertex<T>> queue = new LinkedList<>();
        Vertex<T> vertex = new Vertex<>(null,-1);
        Vertex<T> root = vertexes.get(keyRoot);
        int distance = 0;

        for(Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            c.getValue().setColor(white);
            c.getValue().setDistance(distance);
        }
        root.setDistance(distance);
        root.setColor(grey);
        root.setPadre(null);
        queue.addLast(root);

        while (!queue.isEmpty()){
            vertex = queue.poll();
            for(int i = 0; i<vertex.getAdyacentes().size();i++){
                if(vertex.getAdyacentes().get(i).getColor() == white){
                    vertex.getAdyacentes().get(i).setColor(grey);
                    vertex.getAdyacentes().get(i).setPadre(vertex);
                    vertex.getAdyacentes().get(i).setDistance(vertex.getDistance()+1);
                    queue.addLast(vertex.getAdyacentes().get(i));
                }
            }
            vertex.setColor(black);
        }

    }

    public void DFS(){

        for(Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            c.getValue().setColor(white);
            c.getValue().setPadre(null);
        }

        for(Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            if(c.getValue().getColor() == white){
                dfsVisit(c.getValue());
            }
        }

    }

    public void dfsVisit(Vertex<T> vertex){
        time = time + 1;
        vertex.setTime1(time);
        vertex.setPadre(null);
        vertex.setColor(grey);
        for (int i = 0; i<vertex.getAdyacentes().size();i++){
            if (vertex.getAdyacentes().get(i).getColor() == white){
                vertex.getAdyacentes().get(i).setPadre(vertex);
                dfsVisit(vertex.getAdyacentes().get(i));
            }
        }
        vertex.setColor(black);
        time = time +1;
        vertex.setTime2(time);

    }

    public int getHashSize(){
        return vertexes.size();
    }
    public int proveConex(){
        int count = 0;
        for (Map.Entry<Integer, Vertex<T>> c : vertexes.entrySet()){
            BFS(c.getValue().getKey());
            boolean flag = true;
            for(Map.Entry<Integer, Vertex<T>> a : vertexes.entrySet()){
                if(a.getValue().getColor()==white){
                    flag = false;
                }
            }
            if (flag){
                count++;
            }

        }
        return count;
    }

}

