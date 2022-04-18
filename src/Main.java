//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//
//public class Main
//{
//    public Main() {
//    }
//
//    public static void main(String[] args)
//    {
//        List <Athlete> aAthletes  = new LinkedList<>(loadAthletes());
//        loadAthletes();
//        printStuff(aAthletes);
//    }
//
//    protected static LinkedList<Athlete> loadAthletes()
//    {
//        File file = new File("res/database.csv");
//        Scanner reader;
//
//        try
//        {
//            reader = new Scanner(file);
//        }
//        catch (FileNotFoundException e)
//        {
//            System.err.println("Could not find file: " + file.toPath());
//            return null;
//        }
//
//        LinkedList<Athlete> aAthletes = new LinkedList<>();
//
//        reader.nextLine();
//        while (reader.hasNext())
//        {
//            String line = reader.nextLine();
//            StringTokenizer tokenizer = new StringTokenizer(line, ",");
//
//            String ID = String.valueOf(tokenizer.nextToken());
//            String FirstName = tokenizer.nextToken();
//            String LastName = tokenizer.nextToken();
//            int age = Integer.parseInt((tokenizer.nextToken()));
//            String race = String.valueOf(tokenizer.nextToken());
//            String PB = String.valueOf(tokenizer.nextToken());
//            String SB = String.valueOf(tokenizer.nextToken());
//
//            Athlete a = new Athlete(ID, FirstName, LastName, age, race, PB, SB);
//            aAthletes.add(a);
//        }
//
//        return aAthletes;
//    }
//
//    public static <E> void printStuff(List<E> stuff)
//    {
//        for (E element : stuff)
//            System.out.println(element);
//    }
//}
