import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.Thread.sleep;

public class Tutorial2 {
    public static void main(String args[]) throws IOException {
        System.setProperty("org.graphstream.ui", "swing");

        Graph graph = new SingleGraph("Tutorial2");
        Path f = new File("layout.css").toPath();
        graph.setAttribute("ui.stylesheet", Files.readString(f));
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        //Layout layout = new SpringBox(false);
        //graph.addSink(layout);
        //layout.addAttributeSink(graph);

        graph.display(true);
        //graph.addAttribute("ui.antialias");
        try {
            FileSource source = FileSourceFactory.sourceFor("tutorial3_graph.dgs");
            source.addSink(graph);
            source.begin("tutorial3_graph.dgs");
            while(source.nextEvents()){
                sleep(100);
                // iterate the compute() method a number of times
                //layout.compute();

            }
            source.end();
        } catch(Exception e) { e.printStackTrace(); }


    }

}