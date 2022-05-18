
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Tutorial1 {
	public static void main(String args[]) throws IOException, InterruptedException {
		System.setProperty("org.graphstream.ui", "swing");
		Random random = new Random();
		Graph graph = new SingleGraph("Tutorial 1");
		Path f = new File("layout.css").toPath();
		graph.setAttribute("ui.stylesheet", Files.readString(f));
		graph.setAttribute("ui.quality");
		graph.setAttribute("ui.antialias");

		graph.display(true);
		//graph.read("tutorial2.dgs");

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
	}
}
