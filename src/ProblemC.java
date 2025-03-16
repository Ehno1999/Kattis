import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ProblemC {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;

            // Läs varje rad tills "0" hittas
            while ((line = br.readLine()) != null && !line.equals("0")) {
                String[] splited = line.split(" "); // Dela raden i delar
                int trips = Integer.parseInt(splited[0]); // Antalet resor (första talet)

                int totalWeight = 0;
                // Summera vikterna från resorna
                for (int i = 1; i <= trips; i++) {
                    totalWeight += Integer.parseInt(splited[i]);
                }

                int targetWeight = totalWeight / 2; // Halva totala vikten, målet för jämn fördelning
                boolean[] isSumAchievable = new boolean[targetWeight + 1]; // Spårar vilka viktsummor som kan uppnås med en delmängd av föremålen, där isSumAchievable[i] är true om summan i kan uppnås.

                isSumAchievable[0] = true; // Det är alltid möjligt att uppnå en vikt på 0

                // Uppdatera möjliga summor baserat på vikterna
                for (int i = 1; i <= trips; i++) {
                    int weight = Integer.parseInt(splited[i]);
                    // Uppdaterar möjliga summor: Om currentSum kan nås genom att lägga till weight till en tidigare summa
                    for (int currentSum = targetWeight; currentSum >= weight; currentSum--) {
                        isSumAchievable[currentSum] = isSumAchievable[currentSum] || isSumAchievable[currentSum - weight];
                    }
                }

                int closestSum = 0;
                // Hitta den största möjliga summan som är mindre än eller lika med targetWeight
                for (int i = targetWeight; i >= 0; i--) {
                    if (isSumAchievable[i]) {
                        closestSum = i;
                        break;
                    }
                }

                // Dela upp vikten mellan kattis och kitten
                int kattis = Math.max(closestSum, totalWeight - closestSum);
                int kitten = Math.min(closestSum, totalWeight - closestSum);

                // Skriv ut resultatet: först den större vikten, sedan den mindre
                System.out.println(kattis + " " + kitten);
            }
        }
    }
}
