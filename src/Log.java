import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Log {
    ArrayList<String> lines = new ArrayList<>();
    File simLog = new File("log/sim.log");

    public void getLine(String line) {
        lines.add(line);
    }
    public void makeLogFile() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(simLog), StandardCharsets.UTF_8))) {
            for(int i = 0; i < lines.size(); i++)
                writer.write(lines.get(i)+"\n");

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
