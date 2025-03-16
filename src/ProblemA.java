import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ProblemA {
    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            StringBuilder result = new StringBuilder();

            while ((line = br.readLine()) != null) { // Läser indata rad för rad ifrån buffer
                char prevChar = '\0';
                result.setLength(0);

                for (int i = 0; i < line.length(); i++) {
                    char currentChar = line.charAt(i);
                    if (currentChar != prevChar) { // Lägg till om inte samma som föregående
                        result.append(currentChar);
                        prevChar = currentChar; // Uppdaterar föregående tecken
                    }
                }
                System.out.println(result); // Skriver ut det bearbetade resultatet
            }
        }
    }
}
