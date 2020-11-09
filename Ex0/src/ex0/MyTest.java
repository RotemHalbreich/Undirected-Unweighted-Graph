package ex0;

public class MyTest {

    public static void main(String[] args) {
//
//        graph g = new Graph_DS();
//
//        node_data n = new NodeData();
//        node_data n1 = new NodeData();
//        node_data n2 = new NodeData();
//        node_data n3 = new NodeData();
//        node_data n4 = new NodeData();
//
//        g.addNode(n);
//        g.addNode(n1);
//        g.addNode(n2);
//        g.addNode(n3);
//        g.addNode(n4);
//
//
//        g.connect(n.getKey(), n2.getKey());
//        g.connect(n.getKey(), n1.getKey());
//        g.connect(n2.getKey(), n4.getKey());
//        g.connect(n.getKey(), n3.getKey());
//        g.connect(n1.getKey(), n4.getKey());
//
//        System.out.println(g.toString());
//        System.out.println(g.getV());
//        System.out.println(g.getV(n.getKey()));
//
//        System.out.println(g.hasEdge(n.getKey(), n4.getKey()));
//        System.out.println(g.hasEdge(n.getKey(), n2.getKey()));
//        System.out.println(g.hasEdge(n2.getKey(), n.getKey()));
//        System.out.println(g.hasEdge(n1.getKey(), n4.getKey()));
//
//
//        g.removeEdge(n.getKey(), n2.getKey());
//
//        System.out.println(g.toString());
//        System.out.println("all V:" + g.getV());
//        System.out.println("v in n:" + g.getV(n.getKey()));
//        System.out.println("v in n2" + g.getV(n2.getKey()));
//        System.out.println("v in n1 before delete:" + g.getV(n1.getKey()));
//        g.removeNode(n.getKey());
//        System.out.println("v in n1 after delete:" + g.getV(n1.getKey()));
//        System.out.println("after delete" + g.toString());


        node_data n=new NodeData();
        node_data n1=new NodeData();
        node_data n2=new NodeData();
        node_data n3=new NodeData();
        node_data n4=new NodeData();

        graph g=new Graph_DS();
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);


        g.connect(n.getKey(),n1.getKey());
        g.connect(n1.getKey(),n2.getKey());
        g.connect(n2.getKey(),n3.getKey());
        g.connect(n2.getKey(),n3.getKey());
        g.connect(n2.getKey(),n3.getKey());
        g.connect(n2.getKey(),n3.getKey());
        g.connect(n2.getKey(),n3.getKey());
        System.out.println(g.edgeSize());
        graph_algorithms gg=new Graph_Algo();

        gg.init(g);

        graph g2 = gg.copy();
        System.out.println("this is g:" + g.toString());
        System.out.println("this is g2:" + g2.toString());

        g2.removeNode(n3.getKey());
        System.out.println("this is g:" + g.toString());
        System.out.println("this is g2:" + g2.toString());


        System.out.println(gg.shortestPath(n.getKey(),n3.getKey()));


        System.out.println(gg.shortestPathDist(n.getKey(),n3.getKey()));


    }
}

