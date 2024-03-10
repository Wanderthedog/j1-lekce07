package cz.czechitas.lekce7;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Filip Jirsák
 */
class SvatkyTest {

    /**
     * Testuje metodu {@link Svatky#vratKdyMaSvatek(String)}
     */
    @Test
    void kdyMaSvatek() {
        Svatky svatky = new Svatky();
        assertEquals(MonthDay.of(5, 18), svatky.vratKdyMaSvatek("Nataša"));
        assertNull(svatky.vratKdyMaSvatek("Eva"));
    }

    /**
     * Testuje metodu {@link Svatky#jeVSeznamu(String)}
     */
    @Test
    void jeVSeznamu() {
        Svatky svatky = new Svatky();
        String jmenoJeVseznamu = "Zikmund";
        String jmenoNeniVseznamu = "Petr";

        //act
        boolean resultJevVeznamu = svatky.jeVSeznamu(jmenoJeVseznamu);
        boolean resultNeniVseznamu = svatky.jeVSeznamu(jmenoNeniVseznamu);
        //assert

        assertTrue(resultJevVeznamu, "Je v seznamu");
        assertFalse(resultNeniVseznamu, "Neni v seznamu");

    }

    /**
     * Testuje metodu {@link Svatky#getPocetJmen()}
     */
    @Test
    void getPocetJmen() {
        Svatky svatky = new Svatky();
        int pocetJmen = svatky.getPocetJmen();
        System.out.println(pocetJmen);
        assertEquals(37, pocetJmen, "Počet jmen.");
        svatky.pridejSvatek("Jarmil", 2, Month.JUNE);
        svatky.pridejSvatek("Tamara", MonthDay.of(6, 3));
        svatky.pridejSvatek("Kevin", MonthDay.of(Month.JUNE, 3));
        int pocetJmenPoPridaniJmen = svatky.getPocetJmen();
        System.out.println(pocetJmenPoPridaniJmen);
        assertEquals(40, pocetJmenPoPridaniJmen, "Počet jmen.");

        // Ověření, že počet jmen po přidání nových jmen odpovídá očekávanému počtu (40)
        //TODO Otestovat, že vrací počet jmen, která máme v seznamu
    }

    /**
     * Testuje metodu {@link Svatky#getSeznamJmen()}
     */
    @Test
    void getSeznamJmen() {
        Svatky svatky = new Svatky();
        Set<String> ocekavanaJmena = new HashSet<>(Arrays.asList("Radoslav", "Stanislav", "Svatava"));
        Set<String> seznamJmen = svatky.getSeznamJmen();
        assertTrue(seznamJmen.containsAll(ocekavanaJmena), "Jména odpovídají seznamu");
        assertFalse(seznamJmen.contains("Petr"), "Jméno není v seznamu");
    }

    /**
     * Testuje metodu {@link Svatky#pridejSvatek(String, int, int)}
     */
    @Test
    void pridejSvatekDenMesicInt() {
        //TODO Otestuje, že je jméno v seznamu svátků a že má přiřazen správný den
        Svatky svatky = new Svatky();
        svatky.pridejSvatek("Wander", 2, 5);
        assertTrue(svatky.jeVSeznamu("Wander"));
        MonthDay kdyMaSvatekWander = svatky.vratKdyMaSvatek("Wander");
        System.out.println(kdyMaSvatekWander);
        assertEquals(MonthDay.of(2, 5), kdyMaSvatekWander);
    }

    /**
     * Testuje metodu {@link Svatky#pridejSvatek(String, int, Month)}
     */
    @Test
    void pridejSvatekDenMesicMonth() {
        Svatky svatky = new Svatky();
        svatky.pridejSvatek("Pepina", 28, Month.MAY);
        MonthDay kdyMaSvatekPepina = svatky.vratKdyMaSvatek("Pepina");
        System.out.println(kdyMaSvatekPepina);
        assertEquals(Month.MAY, kdyMaSvatekPepina.getMonth());
        assertEquals(28, kdyMaSvatekPepina.getDayOfMonth());

        //TODO Otestuje, že je jméno v seznamu svátků a že má přiřazen správný den
    }

    /**
     * Testuje metodu {@link Svatky#pridejSvatek(String, MonthDay)}
     */
    @Test
    void pridejSvatekMonthDay() {
        Svatky svatky = new Svatky();
        String jmenoSvatku = "Jaryn";
        svatky.pridejSvatek("Jaryn", MonthDay.of(1, 2));
        assertEquals(MonthDay.of(1, 2), svatky.vratKdyMaSvatek("Jaryn"));
        //TODO Otestuje, že je jméno v seznamu svátků a že má přiřazen správný den
    }

    @Test
    void testKdyMaSvatekMonika() {
        Svatky svatky = new Svatky();
        MonthDay kdyMaSvatekMonika = svatky.vratKdyMaSvatek("Monika");
        System.out.println(kdyMaSvatekMonika);
        assertEquals(MonthDay.of(5, 21), kdyMaSvatekMonika);
        assertNull(svatky.vratKdyMaSvatek("Eva"));
    }

    @Test
    void testKdyMaSvatekEva() {
        Svatky svatky = new Svatky();
        MonthDay kdyMaSvatekMonika = svatky.vratKdyMaSvatek("Monika");
        System.out.println(kdyMaSvatekMonika);
        assertEquals(MonthDay.of(5, 21), kdyMaSvatekMonika);
        assertNull(svatky.vratKdyMaSvatek("Eva"));
    }

    /**
     * Testuje metodu {@link Svatky#smazSvatek(String)}
     */
    @Test
    void smazSvatek() {
        Svatky svatky = new Svatky();
        int pocetJmen = svatky.getPocetJmen();
        System.out.println(pocetJmen);
        assertEquals(37, pocetJmen, "Počet jmen.");
        svatky.smazSvatek("Přemysl");
        int pocetJmenPoSmazaniJmen = svatky.getPocetJmen();
        System.out.println(pocetJmenPoSmazaniJmen);
        assertEquals(36, pocetJmenPoSmazaniJmen, "Počet jmen po smazani jmena.");
        //nebo
        assertEquals(pocetJmen - 1, pocetJmenPoSmazaniJmen, "Počet jmen po smazání jména.");
        // nebo
        assertFalse(false, "Přemysl");
        //TODO Zkontrolovat, že po smazání bude počet svátků odpovídat novému počtu.
    }
}
