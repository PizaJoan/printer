/**
 * Created by pizajoan on 23/2/17.
 */
public class Printer {
    private String alphabet;
    private String space;
    private int letterLength;
    private int letterChange;
    private int questionPos;
    private int rows;

    // Constructor: accepta un String amb la representació de tot l'alphabet
    // Hi haurà 27 lletres en total, separades per un space en blanc.
    Printer(String alphabet) {
        this.alphabet = alphabet;
        this.rows = alphabet.length() / 27 - 14;
        this.letterLength = (alphabet.length() / 27) / this.rows;
        this.letterChange = alphabet.length() / rows + 1;
        if (this.rows == 5) {
            this.space = "    ";
        } else {
            this.space = "      ";
        }
        this.questionPos = letterChange - space.length();
    }

    // Mètode render: accepta un String amb el text a representar i torna
    // també un String amb el text en forma d'ASCII Art.
    // Només es consideren les lletres majúscules de la A a la Z. Les
    // minúscules es passen a majúscules, i els altres caràctes es tradueixen
    // a «?» (manco l'space en blanc).
    public String render(String text) {
        char[] letters = text.toUpperCase().toCharArray();
        String rendering = "";
        for (int i = 0; i < this.rows; i++) {
            rendering += buildLine(i, letters);
            if (i < this.rows - 1) {
                rendering += "\n";
            }
        }
        return rendering;
    }

    private int getPos(char letter, int currentRow) {
        int letterPos = currentRow * this.letterChange;
        if (letter < 'A' || letter > 'Z') {
            letterPos += this.questionPos;
        } else {
            letterPos += this.letterLength * (letter - 'A') + (letter - 'A');
        }
        return letterPos;
    }

    private String buildLine(int currentRow, char[] letters) {
        String s = "";
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == ' ') {
                s += this.space;
                continue;
            }
            int actualPos = getPos(letters[i], currentRow);
            s += this.alphabet.substring(actualPos, actualPos + this.letterLength);
            if (letters.length > 1 && i < letters.length - 1) {
                s += " ";
            }
        }
        return s;
    }
}
