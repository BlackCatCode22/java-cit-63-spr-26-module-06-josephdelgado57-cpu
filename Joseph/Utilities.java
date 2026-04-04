package Joseph.zoo.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Utilities {

    public static String calcAnimalID(String animalSpecies) {
        String myID = "";
        int nextNum = 0;
        if (animalSpecies.equalsIgnoreCase("hyena")) {
            myID = "Hy";
            nextNum = Hyena.numOfHyenas + 1;
        } else if (animalSpecies.equalsIgnoreCase("lion")) {
            myID = "Li";
            nextNum = Lion.numOfLions + 1;
        } else if (animalSpecies.equalsIgnoreCase("tiger")) {
            myID = "Ti";
            nextNum = Tiger.numOfTigers + 1;
        } else if (animalSpecies.equalsIgnoreCase("bear")) {
            myID = "Be";
            nextNum = Bear.numOfBears + 1;
        }
        return myID + String.format("%02d", nextNum);
    }

    public static String arrivalDate() {
        java.util.Date today = new java.util.Date();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(today);
    }

    public static String calcAnimalBirthDate(int age, String theSeason) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int currentYear = cal.get(java.util.Calendar.YEAR);

        int birthYear = currentYear - age;
        String monthDay = "";

        String season = theSeason.toLowerCase();

        if (season.contains("spring")) {
            monthDay = "03-21";
        } else if (season.contains("summer")) {
            monthDay = "06-21";
        } else if (season.contains("fall")) {
            monthDay = "09-21";
        } else if (season.contains("winter")) {
            monthDay = "12-21";
        } else {
            monthDay = "01-01";
        }

        return birthYear + "-" + monthDay;

    }

    public static AnimalNameListsWrapper createAnimalNameLists(String filePath) {
        ArrayList<String> hyenaNameList = new ArrayList<>();
        ArrayList<String> lionNameList = new ArrayList<>();
        ArrayList<String> tigerNameList = new ArrayList<>();
        ArrayList<String> bearNameList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<String> currentList = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();  // Trim any leading or trailing spaces

                if (line.equals("Hyena Names:")) {
                    currentList = hyenaNameList;
                } else if (line.equals("Lion Names:")) {
                    currentList = lionNameList;
                } else if (line.equals("Tiger Names:")) {
                    currentList = tigerNameList;
                } else if (line.equals("Bear Names:")) {
                    currentList = bearNameList;
                } else if (!line.isEmpty()) {
                    // Add names to the current list
                    String[] names = line.split(",\\s*");
                    for (String name : names) {
                        if (currentList != null) {
                            currentList.add(name);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        return new AnimalNameListsWrapper(hyenaNameList, lionNameList, tigerNameList, bearNameList);
    }
}