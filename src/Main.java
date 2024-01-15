import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static List<Fish> list = new CopyOnWriteArrayList<>();
    public static List<String> name = Arrays.asList("Nemo", "Marlin", "Bruce", "Anchor", "Chum", "Gill", "Bloat", "Bubbles",
            "Peach", "Gurgle", "Jacques", "Nigel", "Crush", "Squirt", "Mr. Ray", "Tad", "Pearl", "Sheldon",
            "Dory", "Deb");

    public static int aquariumSize = 10;
    public static int fishId = 1;

    public static void main(String[] args) {

        Random random = new Random();

        int maleFish = random.nextInt(10) + 1;
        int femaleFish = random.nextInt(10) + 1;
        System.out.println("male = " + maleFish + ", female = " + femaleFish);
        aquariumSize += femaleFish + maleFish;
        for (int i = 0; i < maleFish; i++) {
            Fish fish = new Fish();
            setDataFish(fish);
            fish.setType(Type.MALE.toString());
            list.add(fish);
        }
        for (int i = 0; i < femaleFish; i++) {
            Fish fish = new Fish();
            setDataFish(fish);
            fish.setType(Type.FEMALE.toString());
            list.add(fish);
        }
        list.forEach(System.out::println);
        for (Fish fish1 : list) {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            new Thread(() -> checkFishLife(fish1)).start();
        }


    }

    public static void checkFishLife(Fish fish) {
        Random random = new Random();
        long currentTimeMillis = System.currentTimeMillis();
        long lifeDate = fish.getLifeDate();
        while (currentTimeMillis <= lifeDate) {
            List<Fish> collect = list.stream().filter(fish1 -> !fish1.getType().equals(fish.getType()) && fish1.getX().equals(fish.getX()) && fish1.getY().equals(fish.getY())).toList();
            if (!collect.isEmpty()) {
                if (list.size() != aquariumSize){
                    collect.forEach(fish1 -> {
                        Fish fish2 = new Fish();
                        setDataFish(fish2);
                        fish2.setType(setFishType(fish2));
                        list.add(fish2);
                        printGreen("Fish " + fish.getName() + " is mating with " + fish1.getName() + " and create " + fish2.getName());
                        System.out.println(fish2);
                        if (list.size() == aquariumSize) {
                            printYellow("Aquarium is full");
                            list.forEach(System.out::println);
                            System.exit(0);
                        }
                        new Thread(() -> checkFishLife(fish2)).start();
                    });
                }

            }
            if (fish.getStatus()) System.out.println("Fish " + fish.getName() + " is sleeping");
            else System.out.println("Fish " + fish.getName() + " is eating");
            fish.setStatus(!fish.getStatus());
            fish.setX(random.nextInt(1,10) + 1);
            fish.setY(random.nextInt(1,3) + 1);
            try {
                Thread.sleep(getRandomNumber(20000,30000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentTimeMillis = System.currentTimeMillis();
        }

        printRed("Fish " + fish.getName() + " is dead");
        System.out.println(fish);
        list.remove(fish);
        if (list.isEmpty()) {
            printYellow("Aquarium is empty");
            System.exit(0);
        }
    }

    public static String setFishType(Fish fish) {
        Random random = new Random();
        int i = random.nextInt(2);
        if (i == 0) {
            fish.setType(Type.MALE.toString());
        } else {
            fish.setType(Type.FEMALE.toString());
        }
        return fish.getType();
    }

    public static void setDataFish(Fish fish) {
        Random random = new Random();
        long currentTimeMillis = System.currentTimeMillis();

        long randomMilliseconds = currentTimeMillis + random.nextInt(600000);
        fish.setLifeDate(randomMilliseconds);
        fish.setX(random.nextInt(10) + 1);
        fish.setY(random.nextInt(3) + 1);
        fish.setId(fishId++);
        fish.setName(name.get(random.nextInt(name.size())));

    }
    public static void printRed(String text) {
        System.out.println("\u001B[31m" + text + "\u001B[0m");
    }

    public static void printGreen(String text) {
        System.out.println("\u001B[32m" + text + "\u001B[0m");
    }
    public static void printYellow(String text) {
        System.out.println("\u001B[33m" + text + "\u001B[0m");
    }
    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }



}
