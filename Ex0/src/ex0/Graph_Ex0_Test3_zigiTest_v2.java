package ex0;

/**
 * This is a edge test class to test Ex0 functionality & performance.
*/
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Graph_Ex0_Test3_zigiTest_v2 {
    private static Random _rnd = null;
    private static int _errors = 0, _tests = 0;
    private static String _log = "";

    /**
     * Simple main, run all the tests.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\n\n\n  (c) AchiyaZigi 2020 8)");
        System.out.println("Running tests for Ex0 - this may take up to 7 seconds!");
        long start = new Date().getTime();
        test0();
        test1();
        test2();
        test2a();
        test3();
        test9();


        long end = new Date().getTime();
        double dt = (end-start)/1000.0;
        boolean t = dt<7;
        test("runtime test: ",t, true);
        System.out.println(_log);
        double g = 100.0*(_tests-_errors)/_tests;
        System.out.println("number of Errors: "+_errors+" of "+_tests+" tests, time: "+dt+" seconds");
        System.out.println("grade: "+(int)g);
    }



    /**
     * Generate a random graph with v_size nodes and e_size edges
     * @param v_size
     * @param e_size
     * @param seed
     * @return
     */
    public static graph graph_creator(int v_size, int e_size, int seed) {
        graph g = new Graph_DS();
        _rnd = new Random(seed);
        for(int i=0;i<v_size;i++) {
            node_data n = new NodeData();
            g.addNode(n);
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important
        node_data[] nodes = nodes(g);
        while(g.edgeSize() < e_size) {
            int a = nextRnd(0,v_size);
            int b = nextRnd(0,v_size);
            int i = nodes[a].getKey();
            int j = nodes[b].getKey();
            g.connect(i,j);
        }
        return g;
    }

    /**
     * Graph_Algo.copy() test: coloring node
     */
    public static void test0() {
        graph g0 = graph_creator(2, 1, 1);
        graph_algorithms ga = new Graph_Algo();
        ga.init(g0);
        graph g1 = ga.copy();
        g1.getNode(1).setTag(1);;
        boolean b = (g1.getNode(0).getNi().iterator().next().getTag() == 1);
        test("1st copy test- coloring node", b, true);
    }

    /**
     * Graph_Algo.copy() test: checking dependency
     */
    public static void test1() {
        graph g0 = graph_creator(2, 1, 1);
        graph_algorithms ga = new Graph_Algo();
        ga.init(g0);
        graph g1 = ga.copy();
        g0.removeNode(g0.getV().iterator().next().getKey());
        g1.addNode(new NodeData());
        boolean b = (g1.getV().size() == 3) && (g0.getV().size() == 1);
        test("2nd copy test- removing and adding nodes", b, true);
    }

    /**
     * performance test: computing O(1) operations 1,000,000 times
     */
    public static void test2() {
        int times = 1000000;
        long start_local = new Date().getTime();
        graph g0 = graph_creator(0, 0, 1);
        for (int i = 0; i < times; i++) {
            g0.addNode(new NodeData());
        }
        _rnd = new Random(31);
        node_data[] nodes = nodes(g0);
        for (int i = 0; i < times; i++) {
            int a = nextRnd(0,times);
            int b = nextRnd(0,times);
            int k = nodes[a].getKey();
            int j = nodes[b].getKey();
            g0.connect(k,j);
        }
        Collection<node_data> trash;
        for (int i = 0; i < times; i++) {
             trash = g0.getV();
        }
        node_data temp;
        for (int i = 0; i < times; i++) {
            temp = g0.getNode(nodes[nextRnd(0,times)].getKey());
            trash = temp.getNi();
        }
        long end_local = new Date().getTime();
        double dt_local = (end_local-start_local)/1000.0;
        boolean t_local = dt_local<4;
        test("performance of O(1) methods: took "+dt_local+"s'",t_local, true);

    }
    /**
     * checking if a node has itself as a neighbor
     */
    public static void test2a() {
        graph g0 = graph_creator(7, 13, 1);
        node_data nodes[] = nodes(g0);
        g0.connect(nodes[0].getKey(), nodes[0].getKey());
        Collection<node_data> col = g0.getV();
        boolean b = true;
        for (node_data n : col) {
            Collection<node_data> neighbors = n.getNi();
            int nkey = n.getKey();
            for (node_data connected : neighbors) {
                b &= (!connected.equals(n)) && connected.getKey() != nkey;
            }
        }
        test("self neighbor test ", b, true);
    }

/**
     * shortestPath from node to itself (should be 1 node in the list)
     */
    public static void test3() {
        graph g0 = graph_creator(7, 13, 1);
        graph_algorithms ga = new Graph_Algo();
        ga.init(g0);
        Collection<node_data> col = g0.getV();
        boolean b = true;
        List<node_data> lst;
        for (node_data n : col) {
            lst = ga.shortestPath(n.getKey(), n.getKey());
            b &= (lst.contains(n) && lst.size() == 1 && ga.shortestPathDist(n.getKey(), n.getKey()) == 0);
        }
        test("shortestPath from node to itself ", b, true);
    }

    /**
     * connecting with none existed node
     */
    public static void test9() {
        graph g0 = graph_creator(1,0,1);
        int n = g0.getV().iterator().next().getKey();
        g0.connect(0, n);
        g0.connect(0, 0);
        g0.connect(n, 0);
        g0.connect(n, n);
        boolean b = g0.edgeSize() == 0 && g0.getMC() == 1;
        test("messing with not valid edges ", b, true);
    }

   
    ////////////////////// Private Functions /////////////////////
    private static void test(String test, boolean val, boolean req) {
        test(test, ""+val, ""+req);
    }

    private static void test(String test, String val, String req) {
        boolean ans = true;
        ans = val.equals(req);
        String tt = _tests+") "+test+"  pass: "+ans;
        _log += "\n"+tt;
        if(!ans) {
            _errors++;
            String err = "  ERROR("+_errors+") "+val+"!="+req;
            System.err.println(tt+err);
            _log += err;

        }
        _tests++;
    }
    private static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    private static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }
    /**
     * Simple method for returning an array with all the node_data of the graph,
     * Note: this should be using an  Iterator<node_edge> to be fixed in Ex1
     * @param g
     * @return
     */
    private static node_data[] nodes(graph g) {
        int size = g.nodeSize();
        Collection<node_data> V = g.getV();
        node_data[] nodes = new node_data[size];
        V.toArray(nodes); // O(n) operation
        return nodes;
    }
}
