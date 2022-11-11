import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class GraphTest {


    @SuppressWarnings("resource")
    public void testgetPrerequisitePath(String coursecode) throws FileNotFoundException {
        BufferedReader f;
        f = new BufferedReader(new InputStreamReader(new FileInputStream("coen_course.gph")));
        Node coen316 = new Node(6, "COEN316");
        coursecode = coen316.name;
        String r = Graph.getPrerequisistePath(coursecode);
        assertEquals("COEN311 COEN316", r);
    }


    @SuppressWarnings("resource")
    public void testgetPrerequisite(String coursecode) throws FileNotFoundException {
        BufferedReader f;
        f = new BufferedReader(new InputStreamReader(new FileInputStream("coen_course.gph")));
        Node coen316 = new Node(6, "COEN316");
        coursecode = coen316.name;
        String[] r = Graph.getPrerequiste(coursecode);
        assertEquals("COEN311 COEN316", r.toString());

    }

    private void assertEquals(String string, String r) {
        // TODO Auto-generated method stub

    }
		