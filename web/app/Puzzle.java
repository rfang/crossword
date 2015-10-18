package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Bryan on 10/18/2015.
 */
public class Puzzle {

    public final char width;
    public final char height;
    public final String solution;
    public final String fill;
    public final ArrayList<String> clues = new ArrayList<>();
    public String line;

    public Puzzle(URL url) {
        load(url);
        line = line.substring(line.indexOf("ACROSS&DOWN") - 2);
        width = line.charAt(44);
        height = line.charAt(45);
        char numClues = line.charAt(46);
        line = line.substring(52);
        solution = line.substring(0, width * height);
        line = line.substring(width * height);
        fill = line.substring(0, width * height);
        line = line.substring(width * height);
        line = line.split("\0", 2)[1];
        line = line.split("\0", 2)[1];
        line = line.split("\0", 2)[1];
        for (int i = 0; i < numClues; i++) {
            String[] split = line.split("\0", 2);
            clues.add(split[0]);
            line = split[1];
        }
    }

    private void load(URL url) {
        Charset charset = Charset.forName("ISO-8859-1");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), charset))) {
            line = reader.readLine();
            System.err.format("%s\n", line);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
