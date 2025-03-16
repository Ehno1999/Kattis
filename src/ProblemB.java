import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class ProblemB {

    private static HashMap<String, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("def")) {
                    def(line);
                } else if (line.startsWith("calc")) {
                    calc(line);
                } else if (line.startsWith("clear")) {
                    hashMap.clear();
                }
            }
        }
    }

    private static void def(String line) {
        int firstSpace = line.indexOf(' ');
        int secondSpace = line.indexOf(' ', firstSpace + 1);
        String key = line.substring(firstSpace + 1, secondSpace); // Variabelnamn
        int value = Integer.parseInt(line.substring(secondSpace + 1)); // Värde
        hashMap.put(key, value); // Lägg till i hashkartan
    }

    private static void calc(String line) {
        String[] split = line.split(" ");
        int result = 0;
        boolean valid = true;

        if (hashMap.containsKey(split[1])) { // [1] innehåller första variabeln
            result = hashMap.get(split[1]);
        } else {
            // Fel om första variabeln saknas
            printResult(line, result, false);
        }

        for (int i = 2; i < split.length - 1 && valid; i += 2) { // [2], [4], osv. är operatorer, [3], [5], osv. är variabler
            String operator = split[i];
            String key = split[i + 1];
            if (hashMap.containsKey(key)) {
                int value = hashMap.get(key);
                result = operator.equals("+") ? result + value : result - value;
            } else {
                printResult(line, result, false);
            }
        }

        printResult(line, result, valid);
    }

    private static void printResult(String line, int result, boolean valid) {
        if (valid) {
            for (String key : hashMap.keySet()) {
                if (hashMap.get(key).equals(result)) {
                    System.out.println(line.substring(5) + " " + key);
                    return;
                }
            }
            System.out.println(line.substring(5) + " unknown");
        } else {
            System.out.println(line.substring(5) + " unknown");
        }
    }

}
