
import java.awt.*;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;

import static java.lang.Thread.sleep;


public class Tutorial3 {

    private static Graph graph = new SingleGraph("Tutorial 1");

    public static class MyFrame extends JFrame {

        private static final long serialVersionUID = 8394236698316485656L;

        //private Graph graph = new MultiGraph("embedded");
        private SwingViewer viewer = new SwingViewer(graph, SwingViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        //private Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_SWING_THREAD);
        private View view = viewer.addDefaultView(false);
        private View defaultView  = viewer.getDefaultView();

        public MyFrame() {

            setLayout(new BorderLayout());

            //add( new JScrollPane(defaultView), BorderLayout.CENTER);

            add((Component) defaultView, (Object) BorderLayout.CENTER);

            setDefaultCloseOperation(EXIT_ON_CLOSE);

        }

    }

    public static void main(String args[]) throws IOException, InterruptedException{
        System.setProperty("org.graphstream.ui", "swing");
        Random random = new Random();
        MyFrame frame = new MyFrame();

        frame.setSize(320, 240);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // a layout algorithm instance plugged to the graph
        Layout layout = new SpringBox(false);
        graph.addSink(layout);
        layout.addAttributeSink(graph);
        graph.display();
        graph.addNode("0");

        for(int i = 1; i < 200; i++){
            String n1 = Integer.toString(i);
            String n2 = Integer.toString(random.nextInt(i));
            //Integer.toString(i % 20);
            graph.addNode(n1);
            //layout.compute();
            sleep(100);

            graph.addEdge(n1 + n2, n1, n2);
            //layout.compute();
            sleep(100);
        }


        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        // iterate the compute() method a number of times
        while(layout.getStabilization() < 0.9){
            layout.compute();
        }
        graph.write("tutorial3_graph.dgs");
    }
}