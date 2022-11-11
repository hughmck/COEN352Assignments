import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
    private static HashMap<Node, LinkedList<Node>> adjacencyMap = new HashMap<Node, LinkedList<Node>>();
    private static Node course;
    static int V = 34;
    static Node coen212 = new Node(0, "COEN212");
    static Node coen231 = new Node(1, "COEN231");
    static Node coen243 = new Node(2, "COEN243");
    static Node coen244 = new Node(3, "COEN244");
    static Node coen311 = new Node(4, "COEN311");
    static Node coen313 = new Node(5, "COEN313");
    static Node coen316 = new Node(6, "COEN316");
    static Node coen317 = new Node(7, "COEN317");
    static Node coen320 = new Node(8, "COEN320");
    static Node coen346 = new Node(9, "COEN346");
    static Node coen352 = new Node(10, "COEN352");
    static Node coen366 = new Node(11, "COEN366");
    static Node coen390 = new Node(12, "COEN390");
    static Node coen413 = new Node(13, "COEN413");
    static Node coen415 = new Node(14, "COEN415");
    static Node coen421 = new Node(15, "COEN421");
    static Node coen422 = new Node(16, "COEN422");
    static Node coen424 = new Node(17, "COEN424");
    static Node coen432 = new Node(18, "COEN432");
    static Node coen433 = new Node(19, "COEN433");
    static Node coen434 = new Node(20, "COEN434");
    static Node coen446 = new Node(21, "COEN446");
    static Node coen447 = new Node(22, "COEN447");
    static Node coen448 = new Node(23, "COEN448");
    static Node coen451 = new Node(24, "COEN451");
    static Node coen490 = new Node(25, "COEN490");
    static Node coen498 = new Node(26, "COEN498");
    static Node math204 = new Node(27, "MATH204");
    static Node engr290 = new Node(28, "ENGR290");
    static Node elec311 = new Node(29, "ELEC311");
    static Node soen341 = new Node(30, "SOEN341");
    static Node elec372 = new Node(31, "ELEC372");
    static Node engr301 = new Node(32, "ENGR301");
    static Node engr371 = new Node(33, "ENGR371");


    public Graph(Node course) {
        Init(course);
    }

    public static void Init(Node course) {
        LinkedList<Node> prerequisites = new LinkedList<Node>();
        adjacencyMap.put(course, prerequisites);
    }

    public Graph() {
    }


    public static void main(String[] args) {

        for (int i = 0; i < V; i++) {
            Node course = new Node(i,null);
            Init(course);
        }


        addEdge(coen212, math204);
        addEdge(coen231, math204);
        addEdge(coen243, math204);
        addEdge(coen244, coen243);
        addEdge(coen311, coen212);
        addEdge(coen311, coen243);
        addEdge(coen313, coen212);
        addEdge(coen313, coen231);
        addEdge(coen316, coen311);
        addEdge(coen316, coen313);
        addEdge(coen317, coen311);
        addEdge(coen317, coen313);
        addEdge(coen320, coen346);
        addEdge(coen346, coen311);
        addEdge(coen346, coen352);
        addEdge(coen352, coen231);
        addEdge(coen352, coen244);
        addEdge(coen366, coen346);
        addEdge(coen390, coen311);
        addEdge(coen390, coen352);
        addEdge(coen390, engr290);
        addEdge(coen413, coen313);
        addEdge(coen415, elec311);
        addEdge(coen421, coen317);
        addEdge(coen421, coen320);
        addEdge(coen421, soen341);
        addEdge(coen422, coen346);
        addEdge(coen422, elec372);
        addEdge(coen424, coen346);
        addEdge(coen432, coen352);
        addEdge(coen433, coen212);
        addEdge(coen433, coen244);
        addEdge(coen434, coen244);
        addEdge(coen434, engr290);
        addEdge(coen446, coen366);
        addEdge(coen447, coen317);
        addEdge(coen447, coen366);
        addEdge(coen448, soen341);
        addEdge(coen451, coen212);
        addEdge(coen451, elec311);
        addEdge(coen490, engr301);
        addEdge(coen490, engr371);
        addEdge(coen490, coen390);
        addEdge(coen490, soen341);

        Graph.getPrerequisistePath("COEN490");
        Graph.getPrerequiste("COEN490");

    }

    public static void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> prerequisites = adjacencyMap.get(a);
        if (prerequisites != null) {
            prerequisites.remove(b);
        }
        else prerequisites = new LinkedList<>();
        prerequisites.add(b);
        adjacencyMap.put(a,prerequisites);
    }

    public static void addEdge(Node source, Node destination) {

        // We make sure that every used node shows up in our .keySet()
        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        addEdgeHelper(source, destination);
    }


    public static String getPrerequisistePath(String courseCode) {
        course.name = courseCode;
        Node ui = new Node();
        int o = 4; //Maximum number of prerequisites a course has

        for (int i = 0; i < o; i++) {
            assert adjacencyMap.get(course) != null : "No Prerequisites for this course";
            LinkedList<Node> prerequisites = adjacencyMap.get(course);
            ui = prerequisites.get(i);
        }

        return ui.name;
    }

    public static String[] getPrerequiste(String courseCode) {
        course.name = courseCode;
        int o = 4; //Maximum number of prerequisites a course has
        String[] y = new String[o];

        for (int i = 0; i < o; i++) {
            assert adjacencyMap.get(course) != null : "No Prerequisites for this course";
            LinkedList<Node> prerequisites = adjacencyMap.get(course);
            y[i] = (prerequisites.get(i).name);
        }

        return y;
    }


    public boolean hasEdge(Node source, Node destination) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source) != null && adjacencyMap.get(source).contains(destination);
    }
}
