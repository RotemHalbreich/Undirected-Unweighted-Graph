package ex0;

public class NodeDataTest {
    public static void main(String[] args) {

        node_data n1 = new NodeData();
        node_data n2 = new NodeData();
        node_data n3 = new NodeData();
        node_data n4 = new NodeData();
        node_data n5 = new NodeData();


        n1.getKey();
        n1.addNi(n2);
        n2.addNi(n5);
        n3.addNi(n2);
        n1.addNi(n3);

        System.out.println(n1.toString());
        n1.removeNode(n3);

        System.out.println(n1.toString());


        n1.getNi();




    }
}
