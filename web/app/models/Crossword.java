package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import play.libs.F;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

@Entity
public class Crossword extends Model {

    public static Finder<Long, Crossword> find = new Finder<>(Crossword.class);
    public final String url;
    public final int width;
    public final int height;
    @Column(columnDefinition = "TEXT")
    public final String solution;
    @Column(columnDefinition = "TEXT")
    public final String fill;
    @JsonIgnore
    @Column(columnDefinition = "TEXT")
    public final String clues;
    @Id
    public Long id;

    public Crossword(String url) {
        this.url = url;
        String line = load();
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
        ArrayList<String> clues = new ArrayList<>();
        for (int i = 0; i < numClues; i++) {
            String[] split = line.split("\0", 2);
            clues.add(split[0]);
            line = split[1];
        }
        this.clues = String.join(";", clues);
    }

    private String load() {
        Charset charset = Charset.forName("ISO-8859-1");
        String line = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), charset))) {
            line = reader.readLine();
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return line;
    }

    @JsonProperty("across")
    public ArrayList<String> getAcross() {
        return number()._1;
    }

    @JsonProperty("down")
    public ArrayList<String> getDown() {
        return number()._2;
    }

    private F.Tuple<ArrayList<String>, ArrayList<String>> number() {
        ArrayList<String> across = new ArrayList<>(), down = new ArrayList<>();

        int c = 0;
        String[] clues = this.clues.split(";");
        for (int i = 0; i < solution.length(); i++) {
            if (solution.charAt(i) != '.') {
                boolean isAcross = (i % width == 0) || (solution.charAt(i - 1) == '.');
                if (isAcross) {
                    across.add(clues[c]);
                    c += 1;
                }
                boolean isDown = (i % height == 0) || (i > width && solution.charAt(i - width) == '.');
                if (isDown) {
                    down.add(clues[c]);
                    c += 1;
                }
            }
        }

        return new F.Tuple<>(across, down);
    }

}
