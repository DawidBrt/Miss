import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Log {
    private ArrayList<String> lines = new ArrayList<>();
    private File simLog = null;

    public Log(String path) {
        simLog = new File(path);
    }

    public void getLogLine(String line) {
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
