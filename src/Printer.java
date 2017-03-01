/**
 * Created by pizajoan on 23/2/17.
 */
public class Printer {
    //Declaram les variables que tendrà la un objecte de la clase Printer
    //Primer de tot tindrà l'alfabet de la caligrafia corresponent
    private String alphabet;
    //Seguidament tendrà l'espai entre lletres que toca tenir depenguent de la caligrafia que sigui
    private String space;
    //Cada lletra tendrà una longitud
    private int letterLength;
    //També amb el tipus de caligrafía hi haurà un espai entre lletres
    private int letterChange;
    //Els caràcters extranys seran reemplasats per un interrogant però hem de saber a quina posició es troba
    private int questionPos;
    //Depenguent de la caligrafía hem de saber les files que tendrà cada lletra
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
        StringBuilder rendering = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {
            rendering.append(buildLine(i, letters));
            if (i < this.rows - 1) {
                rendering.append("\n");
            }
        }
        return rendering.toString();
    }

    //Funció que ens dirà a quina posició hem d'anar a cercar la lletra que correspon dins l'alfabet
    private int getPos(char letter, int currentRow) {
        int letterPos = currentRow * this.letterChange;
        if (letter < 'A' || letter > 'Z') {
            letterPos += this.questionPos;
        } else {
            letterPos += this.letterLength * (letter - 'A') + (letter - 'A');
        }
        return letterPos;
    }

    //Fuunció que ens va creant línia a línia la lletra o frase que volguem imprimir
    private StringBuilder buildLine(int currentRow, char[] letters) {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == ' ') {
                row.append(this.space);
                continue;
            }
            int actualPos = getPos(letters[i], currentRow);
            row.append(this.alphabet.substring(actualPos, actualPos + this.letterLength));
            if (letters.length > 1 && i < letters.length - 1) {
                row.append(" ");
            }
        }
        return row;
    }
}
