import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DayOne {
    public static void main(String[] args) {

        String fileName = "DayOneTest";
        ArrayList<Integer> firstList = new ArrayList<>();
        ArrayList<Integer> secondList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    firstList.add(Integer.parseInt(parts[0]));
                    secondList.add(Integer.parseInt(parts[1]));
                } else {
                    System.out.println("Line does not contain two integers: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integers: " + e.getMessage());
        }


        HashMap<Integer, Integer> firstSim = new HashMap<>();
        HashMap<Integer, Integer> secondSim = new HashMap<>();
        Collections.sort(firstList);
        Collections.sort(secondList);
        int sum = 0;
        for(int i = 0; i < firstList.size(); i++) {
            sum += Math.abs(firstList.get(i) - secondList.get(i));
            firstSim.put(firstList.get(i), firstSim.getOrDefault(firstList.get(i), 0) + 1);
            secondSim.put(secondList.get(i), secondSim.getOrDefault(secondList.get(i), 0) + 1);
        }
        System.out.println(sum);
        int similarityScore = 0;
        for(Map.Entry<Integer, Integer> entry : firstSim.entrySet()) {
            similarityScore += entry.getKey() * entry.getValue() * secondSim.getOrDefault(entry.getKey(), 0);
        }

        System.out.println(similarityScore);

    }
}