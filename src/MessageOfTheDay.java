import java.net.*;
import java.io.*;

public class MessageOfTheDay {

    public void getMessage() throws Exception {
        URL url = new URL("http://cswebcat.swansea.ac.uk/puzzle");
        try {
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine = in.readLine();
            in.close();
            solvePuzzle(inputLine);
        } catch (Exception e) {
            System.out.println("wrong");
        }
    }

    public void solvePuzzle(String msg) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String puzzleString = msg;
        char[] puzzleStringChar = puzzleString.toCharArray();
        boolean forward = false;
        int i;
        for(i = 0; i < puzzleStringChar.length; i++) {
            if(forward) {
                puzzleStringChar[i] =  alphabet[(findLetterPos(alphabet, puzzleStringChar[i]) + i + 1) % alphabet.length] ;
            } else {
                if (i >= findLetterPos(alphabet, puzzleStringChar[i])) {
                    puzzleStringChar[i] = alphabet[alphabet.length - (i - findLetterPos(alphabet, puzzleStringChar[i])) - 1];
                }
                else {
                    puzzleStringChar[i] = alphabet[findLetterPos(alphabet, puzzleStringChar[i]) - i - 1];
                }
            }
            forward = !forward;
        }
        puzzleString = new String(puzzleStringChar);
        puzzleString = "CS-230" +   puzzleString;
        puzzleString = puzzleString + puzzleString.length();
        System.out.println(puzzleString);;
        try {
            completeMessage(puzzleString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public int findLetterPos(char[] alphabet, char c) {
        for(int i=0; i<alphabet.length; i++) {
            if (alphabet[i] == c) {
                return i;
            }
        }
        return 0;
    }

    public String completeMessage(String msg) throws MalformedURLException {
        URL url = new URL("http://cswebcat.swansea.ac.uk/message?solution=" + msg);
        try {
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine = in.readLine();
            in.close();
            System.out.println(inputLine);
            return inputLine;
        } catch (Exception e) {
            return null;
        }
    }

}
