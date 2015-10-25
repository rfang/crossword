package bubbles.crossword;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Rachel Fang on 10/18/2015.
 */
public class CrosswordData {
    private String sourceURL;
    private int height;
    private int width;
    private char[] solution;
    private char[] fill;
    private ArrayList<String> clues;

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
        String[] clues = {"Stars and Stripes, e.g.","Admit (to), with \"up\"","Reindeer herder","Geometry calculation","\"My pleasure\"","Places where lines meet","Black power hairdo, for short","Dunderpate","The \"C\" in N.Y.C.","Divided 50/50","French greeting","Dirge","Schooner fill","Billet-doux","Join","Old message system","___ of Sandwich","Cause of a game cancellation","Unaccompanied","\"Here he is now!\"","Wails","Dueling sword","Black card","Talks one's head off","French summer","Twinings selections","Perched on","Deposed Iranian","Sign before Virgo","Big Apple ave.","Be undecided","\"Roots,\" for one","___ of Wight","Message on a shipping crate","Geologic time unit","Pigeon's sound","___ Wednesday","Suds maker","Mounted, as a horse","Big elevator manufacturer","___ too soon","Talking maybe a little too fast","Droid","Snapshot","Wedding cake feature","Killer whale","Sgt. Snorkel's dog","Fable writer","Republican, Democratic, Green, etc.","No. on which a magazine's ad rates are based","\"Luann\" or \"Blondie\"","Freudian one","Persist to completion","Knight time?","A score","End result","Prefix with center","French place of learning","Mail receiver, in brief","Column's counterpart","Interstate entrance or exit","Fish after which a cape is named","Logic diagram","Repeat","Long Island airfield town","___ Ness monster","Itsy-bitsy bit","Winter truck attachment","Command center? ... or where you might hear the starts of 17-, 28- and 49-Across","God, in Italy","Brain scan, for short","Bounding main","Nephew of Donald Duck","For whom the bell tolls, in a John Donne poem","Numerical prefix with -ber","Bygone Montreal ball club","Quiet exercise","Remove from the freezer"};
        sampleData.setClues(new ArrayList<>(Arrays.asList(clues)));
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

    public char[] getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution.toCharArray();
    }

    public char[] getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill.toCharArray();
    }

    public ArrayList<String> getClues() {
        return clues;
    }

    public void setClues(ArrayList<String> clues) {
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

    public ArrayList<PuzzleGridSquare> getPuzzleSquares(Context context) {
        char[] directions = getDirections();
        ArrayList<PuzzleGridSquare> views = new ArrayList<>();
        int numberCount = 1;
        int size = getHeight() * getWidth();
        for (int i = 0; i < size; i++) {
            PuzzleGridSquare view = new PuzzleGridSquare(context);

            if (fill[i] == '.') {
                view.setIsBlack();
            } else {
                view.setHighlight(PuzzleGridSquare.HIGHLIGHT_NONE);
            }

            if (directions[i] != '.' && directions[i] != '-') {
                view.setSquareNumber(""+numberCount);
                numberCount++;
            }

            //TODO: stop showing solution once we have actual fill data
            view.setSquareLetter(""+solution[i]);
            views.add(view);
        }

        return views;
    }
}
