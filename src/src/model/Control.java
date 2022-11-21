package model;

public class Control {


    private Graph<String> grafo;


    public Control()throws Exception{
        grafo = new Graph<>();
    }

    public void addVertex(String value, int key)throws Exception{
        grafo.addVertex(value,key);
    }

    public void addAdj(int keyVertex, int keyAdj){
        grafo.addAdjacent(keyVertex,keyAdj);
    }

    public String proveConex(){
        String out = "";
        int count = grafo.proveConex();
        int real = grafo.getHashSize();
        if(count == real){
            out = "Is strongly conex";
        }else {
            out= "Is not strongly conex";
        }
        return out;

    }

}
