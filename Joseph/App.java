package Joseph.zoo.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        System.out.println("Welcome to the Zookeeper's Challenge!");

        String namesFilePath = "animalNames.txt";
        AnimalNameListsWrapper animalLists = Utilities.createAnimalNameLists(namesFilePath);

        ArrayList<Hyena> hyenaHabitat = new ArrayList<>();
        ArrayList<Lion> lionHabitat = new ArrayList<>();
        ArrayList<Tiger> tigerHabitat = new ArrayList<>();
        ArrayList<Bear> bearHabitat = new ArrayList<>();

        String arrivingAnimalsPath = "arrivingAnimals.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(arrivingAnimalsPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                String[] descParts = parts[0].split(" ");
                int age = Integer.parseInt(descParts[0]);
                String sex = descParts[3];
                String species = descParts[4];

                String birthSeason = parts[1]; // e.g., "born in spring"
                String color = parts[2];        // e.g., "tan color"
                String weightStr = parts[3];   // e.g., "70 pounds"
                int weight = Integer.parseInt(weightStr.split(" ")[0]);
                String origin = parts[4] + ", " + parts[5]; // e.g., "from Friguia Park, Tunisia"

                String animalID = Utilities.calcAnimalID(species);
                String birthDate = Utilities.calcAnimalBirthDate(age, birthSeason);
                String arrivalDate = Utilities.arrivalDate();
                String name = "Unknown";

                if (species.equalsIgnoreCase("hyena")) {
                    name = animalLists.getHyenaNameList().remove(0);
                    hyenaHabitat.add(new Hyena(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate));
                } else if (species.equalsIgnoreCase("lion")) {
                    name = animalLists.getLionNameList().remove(0);
                    lionHabitat.add(new Lion(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate));
                } else if (species.equalsIgnoreCase("tiger")) {
                    name = animalLists.getTigerNameList().remove(0);
                    tigerHabitat.add(new Tiger(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate));
                } else if (species.equalsIgnoreCase("bear")) {
                    name = animalLists.getBearNameList().remove(0);
                    bearHabitat.add(new Bear(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading arrivingAnimals.txt: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("zooPopulation.txt"))) {

            writer.println("Hyena Habitat:");
            writer.println();
            for (Hyena h : hyenaHabitat) {
                writer.println(formatOutput(h));
            }
            writer.println();

            writer.println("Lion Habitat:");
            writer.println();
            for (Lion l : lionHabitat) {
                writer.println(formatOutput(l));
            }
            writer.println();

            writer.println("Tiger Habitat:");
            writer.println();
            for (Tiger t : tigerHabitat) {
                writer.println(formatOutput(t));
            }
            writer.println();

            writer.println("Bear Habitat:");
            writer.println();
            for (Bear b : bearHabitat) {
                writer.println(formatOutput(b));
            }

            System.out.println("Report 'zooPopulation.txt' has been successfully generated.");

        } catch (IOException e) {
            System.out.println("Error writing to zooPopulation.txt: " + e.getMessage());
        }
    }

    private static String formatOutput(Animal a) {
        return String.format("%s; %s; birth date: %s; %s; %s; %d pounds; %s; arrived %s",
                a.getAnimalID(), a.getAnimalName(), a.getAnimalBirthDate(),
                a.getAnimalColor(), a.getSex(), a.getWeight(),
                a.getAnimalOrigin(), a.getAnimalArrivalDate());
    }
}