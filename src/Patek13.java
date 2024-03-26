import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * @author itnetwork.cz
 */

public class Patek13 {
    public static void main(String[] args) {
        // Zadání dat
        Scanner scanner = new Scanner(System.in, "Windows-1250");
        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("d.M.yyyy");
        System.out.println("Pro zadaný interval datumů vypíše všechny pátky 13.:");
        System.out.println("Zadejte 1. datum od kdy chcete najít pátky 13.: ");
        LocalDate zacatek;
        // výjimka (try-catch) při chybě parsování nesprávného data. Pro splnění úlohy to není potřeba
        try {
            zacatek = LocalDate.parse(scanner.nextLine(), formatData);
        } catch (DateTimeParseException ex) {
            System.out.println("Nezadali jste datum ve správném formátu!" + ex.getMessage());
            return;
        }
        System.out.println("Zadejte 2. datum do kdy chcete najít pátky 13. (max dnešní datum): ");
        LocalDate konec;
        try {
            konec = LocalDate.parse(scanner.nextLine(), formatData);
        } catch (DateTimeParseException ex) {
            System.out.println("Nezadali jste datum ve správném formátu!" + ex.getMessage());
            return;
        }
        System.out.println("Pátky 13.: ");

        // Posunutí prvního data na pátek
        while (zacatek.getDayOfWeek() != DayOfWeek.FRIDAY) {
            zacatek = zacatek.plusDays(1);
        }
        // Skákání po 7 dnech, dokud jsme stále v intervalu
        while (zacatek.isBefore(konec)) {
            // Je číslovka dne 13?
            if (zacatek.getDayOfMonth() == 13) {
                System.out.printf("%s, ", formatData.format(zacatek));
            }
            // Posun o týden dopředu
            zacatek = zacatek.plusDays(7);
        }
    }
}