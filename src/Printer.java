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
    //Calculam les posicions que haurà d'avançar per seguir construint la fila
    private int letterChange;
    //Els caràcters extranys seran reemplasats per un interrogant però hem de saber a quina posició es troba
    private int questionPos;
    //Depenguent de la caligrafía hem de saber les files que tendrà cada lletra
    private int rows;

    // Constructor: accepta un String amb la representació de tot l'alphabet
    // Hi haurà 27 lletres en total, separades per un space en blanc.
    Printer(String alphabet) {
        this.alphabet = alphabet;
        //Per calcular les fileres que tendrà
        this.rows = alphabet.length() / 27 - 14;
        //Calculam la longitud de la lletra i el la posició de la lletra en la següent filera
        this.letterLength = (alphabet.length() / 27) / this.rows;
        this.letterChange = alphabet.length() / rows + 1;
        //Miram el tipus de caligrafía i li donam un espai entre lletres
        if (this.rows == 5) {
            this.space = "    ";
        } else {
            this.space = "      ";
        }
        //Calculam on es troba l'interrogant
        this.questionPos = letterChange - space.length();
    }

    // Mètode render: accepta un String amb el text a representar i torna
    // també un String amb el text en forma d'ASCII Art.
    // Només es consideren les lletres majúscules de la A a la Z. Les
    // minúscules es passen a majúscules, i els altres caràctes es tradueixen
    // a «?» (manco l'space en blanc).
    public String render(String text) {
        //Tenim el text ficat dins un array de lletres i un StringBuilder que serà l'impres
        char[] letters = text.toUpperCase().toCharArray();
        StringBuilder rendering = new StringBuilder();
        //Hem d'escriure per tantes fileres que tengui l'alfabet de la caligrafía
        for (int i = 0; i < this.rows; i++) {
            //Cridam una funció que ens escriurà una línia sensera
            rendering.append(buildLine(i, letters));
            //Miram si estam a la darrera línia i si no li posam un salt de línia
            if (i < this.rows - 1) {
                rendering.append("\n");
            }
        }
        return rendering.toString();
    }

    //Funció que ens dirà a quina posició hem d'anar a cercar la lletra que correspon dins l'alfabet
    private int getPos(char letter, int currentRow) {
        //Per saber la posició on hem d'anar a cercar la lletra hem de saber a la linia que ens trobam
        int letterPos = currentRow * this.letterChange;
        //Si tenim un caràcter hem de tornar la posició que li correspon a cada caràcter, si nó vol dir que el
        //Nostre alfabet no ho podrà representar i ho farem amb un interrogant
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
        //Recorrem l'array de lletres que tenim
        for (int i = 0; i < letters.length; i++) {
            //Miram si es un espai, si ho és simplement li posam un espai que li correspon depenent de la caligrafía
            if (letters[i] == ' ') {
                row.append(this.space);
                continue;
            }
            //Calculam la posició on hem d'anar a cercar la lletra
            int actualPos = getPos(letters[i], currentRow);
            //Li sumam a la fila la fila corresponent de la lletra
            row.append(this.alphabet.substring(actualPos, actualPos + this.letterLength));
            //Si tenim una paraula per exemple que la separi per espais
            if (letters.length > 1 && i < letters.length - 1) {
                row.append(" ");
            }
        }
        return row;
    }
}
