package Joseph.zoo.com;

public class Hyena extends Animal {
    static int numOfHyenas = 0;

    public Hyena(){
        super();
        numOfHyenas++;
    }

    public Hyena(String sex, int age, int weight, String animalName,
                 String animalID, String animalBirthDate, String animalColor,
                 String animalOrigin, String animalArrivalDate){
        super(sex, age, weight, animalName, animalID, animalBirthDate, animalColor, animalOrigin, animalArrivalDate);
        numOfHyenas++;
    }



}
