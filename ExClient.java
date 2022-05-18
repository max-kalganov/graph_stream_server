import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.stream.binary.ByteProxy;
import org.graphstream.stream.netstream.NetStreamUtils;
import org.graphstream.util.VerboseSink;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

import static java.lang.Thread.sleep;

public class ExClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("client...");

        String id = "NEW----ME";
        String label = "Mr or Ms ME";

        ByteProxy client = null;
        try {
            client = new ByteProxy(NetStreamUtils.getDefaultNetStreamFactory(), ByteProxy.Mode.CLIENT,
                    InetAddress.getLocalHost(), 2001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.start();

        Graph graphClient = new DefaultGraph("client");

        graphClient.addSink(client);

        //VerboseSink clientVSink = new VerboseSink();
        //clientVSink.setPrefix("client graph logout");
        //graphClient.addSink(clientVSink);

        Node n = graphClient.addNode(id);
        n.setAttribute( "ui.label", label);
        n.setAttribute( "nope", "ok", "not ok");

        graphClient.addNode("0");
        Random random = new Random();
        for(int i = 1; i < 200; i++){
            String n1 = Integer.toString(i);
            String n2 = Integer.toString(random.nextInt(i));
            //Integer.toString(i % 20);
            graphClient.addNode(n1);
            //layout.compute();
            sleep(100);

            graphClient.addEdge(n1 + n2, n1, n2);
            //layout.compute();
            sleep(100);
        }
        client.stop();

    }
}
