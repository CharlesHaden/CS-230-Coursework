import java.net.*;
import java.io.*;

public class MessageOfTheDay {

    public String getMessage() throws IOException{
        URL url = new URL("http://cswebcat.swansea.ac.uk/puzzle");
        try {
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine = in.readLine();
            in.close();
            return solvePuzzle(inputLine);
        } catch (IOException e) {
            System.out.println (e.toString());
            System.out.println("Could not connect to URL to obtain puzzle");
            return null;
        }
    }

    public String solvePuzzle(String msg) throws IOException{
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
        try {
            return completeMessage(puzzleString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
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

    public String completeMessage(String msg) throws IOException {
        URL url = new URL("http://cswebcat.swansea.ac.uk/message?solution=" + msg);
        try {
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine = in.readLine();
            in.close();
            return inputLine;
        } catch (IOException e) {
            System.out.println (e.toString());
            System.out.println("Could not connect to URL to obtain message");
        }
        return null;
    }

}
