import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.stream.binary.ByteProxy;
import org.graphstream.stream.netstream.NetStreamUtils;
import org.graphstream.util.VerboseSink;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("org.graphstream.ui", "swing");
        int port = Integer.parseInt(args[0]);

        Graph g = new MultiGraph("G",false,true);
        Path f = new File("layout.css").toPath();
        g.setAttribute("ui.stylesheet", Files.readString(f));
        g.setAttribute("ui.quality");
        g.setAttribute("ui.antialias");

        VerboseSink logout = new VerboseSink();
        logout.setPrefix("server");
        g.addSink(logout);

        ByteProxy server = null;
        try {
            server = new ByteProxy(NetStreamUtils.getDefaultNetStreamFactory(), port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.addSink(g);
        g.display(true);
        server.start();

    }
}
