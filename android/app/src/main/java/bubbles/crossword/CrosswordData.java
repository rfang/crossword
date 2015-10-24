package bubbles.crossword;

import java.util.Vector;

/**
 * Created by Rachel Fang on 10/18/2015.
 */
public class CrosswordData {
    private String sourceURL;
    private int height;
    private int width;
    private String solution;
    private String fill;
    private Vector<String> clues;

    public static CrosswordData buildSampleData() {
        CrosswordData sampleData = new CrosswordData();
        sampleData.setSourceURL("https://github.com/alexdej/puzpy/raw/master/testfiles/av110622.puz");
        sampleData.setHeight(15);
        sampleData.setWidth(15);
        sampleData.setSolution("SATAN.TROI.LAMETRINA.RUNS.IHOPEMMET.ESCAPEHATTHEWICKEDWIT...TOM." +
                "OHS...KOTEXELAINE.MLLE.HEXDECCA.PLEA.VINO..HELLSKITTEN..QUIT.BAJA.RIGGSBAN.USSR" +
                ".DENALISWEAT...EON.MUG...PITTINGCOACHBITPLEASE.HOJOSORAL.TRAM.ELISASKYE.SONY." +
                "SAGET");
        sampleData.setFill("-----.----.---------.----.---------.---------------------...---.---." +
                "..-----------.----.--------.----.----..-----------..----.----.--------.----.---" +
                "--------...---.---...---------------------.---------.----.---------.----.-----");
        // ignoring clues because there's too many to make it worth it for sample data.
        return sampleData;
    }
    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public Vector<String> getClues() {
        return clues;
    }

    public void setClues(Vector<String> clues) {
        this.clues = clues;
    }

    public char[] getDirections() {
        //TODO: do this actually, and maybe save it?
        String directions =
               "bdddd.dddd.dddd" +
               "a----.----.----" +
               "a----.----d----" +
               "a----d------..." +
               "a--.a--...--ddd" +
               "a--d--.bdd-.a--" +
               "a----.b---.b---" +
               "..a--d----d--.." +
               "b---.a---.a--dd" +
               "a--.b---.b-----" +
               "a--d-...b--.a--" +
               "...a-ddd---d---" +
               "bdd------.a----" +
               "a---.a---.a----" +
               "a---.a---.a----";
        return directions.toCharArray();
    }
}
