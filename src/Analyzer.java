import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Analyzer {
    static Character[] winners = new Character[5];
    static Character[] doublePointWinners = new Character[2];
    static Character[] speedWinners = new Character[2];
    static Character[] streakWinners = new Character[2];
    static ArrayList<Character> people = new ArrayList<>();
    static ArrayList<Character> alreadyWon = new ArrayList<>();
    static final int questions = 6;
    static final int personCount = 1;
    public static void main(String[] args) {
        for (int i = 0; i < personCount; i++) {
            people.add(new Character(i));
        }
        if (personCount < 11){
            throw new NotEnoughPeopleException("Not enough people!");
        }
        handleWinners();
        handleDoubleWinners();
        handleSpeedWinners();
        handleStreakWinners();
        printWinners();
    }
    public static String readCellData(int vRow, int vColumn, int vSheet){
        String value;
        Workbook wb=null;
        try
        {
            FileInputStream fis=new FileInputStream("./Book1.xlsx");
            wb= new XSSFWorkbook(fis);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        Sheet sheet=wb.getSheetAt(vSheet);
        Row row=sheet.getRow(vRow);
        Cell cell=row.getCell(vColumn);
        value=cell.getStringCellValue();
        return value;
    }
    public static int readCellNumber(int vRow, int vColumn, int vSheet){
        int value;
        Workbook wb=null;
        try
        {
            FileInputStream fis=new FileInputStream("./Book1.xlsx");
            wb= new XSSFWorkbook(fis);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        Sheet sheet=wb.getSheetAt(vSheet);
        Row row=sheet.getRow(vRow);
        Cell cell=row.getCell(vColumn);
        value= (int) cell.getNumericCellValue();
        return value;
    }
    public static void printWinners(){
        System.out.println("Streak winners: ");
        System.out.println(streakWinners[0].name);
        System.out.println(streakWinners[1].name);
        System.out.println("Speed winners: ");
        System.out.println(speedWinners[0].name);
        System.out.println(speedWinners[1].name);
        System.out.println("Double point winners: ");
        System.out.println(doublePointWinners[0].name);
        System.out.println(doublePointWinners[1].name);
        for (int i = 4; i >= 0; i--) {
            System.out.println((i+1) + "th:");
            System.out.println(winners[i].name);
        }
        System.out.println("!!!");
    }
    public static void handleWinners(){
        for (int i = 0; i < 5; i++) {
            winners[i] = people.get(i);
        }
        alreadyWon.addAll(Arrays.asList(winners));
    }
    public static void handleDoubleWinners(){
        people.sort(Comparator.comparingInt(o -> o.doubleQuestions));
        int z = 0;
        for (int i = 0; i < people.size() && z < 2; i++) {
            if (!alreadyWon.contains(people.get(i))){
                doublePointWinners[z] = people.get(i);
                z++;
            }
        }
        alreadyWon.addAll(Arrays.asList(doublePointWinners));
    }
    public static void handleSpeedWinners(){
        people.sort(Comparator.comparingInt(o -> o.fastQuestions));
        int z = 0;
        for (int i = 0; i < people.size() && z < 2; i++) {
            if (!alreadyWon.contains(people.get(i))){
                speedWinners[z] = people.get(i);
                z++;
            }
        }
        alreadyWon.addAll(Arrays.asList(speedWinners));
    }
    public static void handleStreakWinners(){
        people.sort(Comparator.comparingInt(o -> o.maxStreak));
        int z = 0;
        for (int i = 0; i < people.size() && z < 2; i++) {
            if (!alreadyWon.contains(people.get(i))){
                streakWinners[z] = people.get(i);
                z++;
            }
        }
        alreadyWon.addAll(Arrays.asList(streakWinners));
    }

}
