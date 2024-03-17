package cz.czechitas.lekce7;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.MonthDay;
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
        String jmenoJeVSeznamu = "Zikmund"; //najde v seznamu existující jméno
        String jmenoNeniVSeznamu = "Mirek";//nenajde neexistující jméno

        boolean resultJeVSeznamu = svatky.jeVSeznamu(jmenoJeVSeznamu);
        boolean resultNeniVSeznamu = svatky.jeVSeznamu(jmenoNeniVSeznamu);

        assertTrue(resultJeVSeznamu, jmenoJeVSeznamu + " má být v seznamu!");
        assertFalse(resultNeniVSeznamu, "Jméno " + jmenoNeniVSeznamu + " by nemělo být v seznamu.");
    }

    /**
     * Testuje metodu {@link Svatky#getPocetJmen()}
     */
    @Test
    void getPocetJmen() {
        Svatky svatky = new Svatky();
        int pocetJmen = svatky.getPocetJmen();
        assertEquals(37, pocetJmen, "Počet jmen.");
        svatky.pridejSvatek("Jarmil", 2, Month.JUNE);
        svatky.pridejSvatek("Tamara", MonthDay.of(6, 3));
        svatky.pridejSvatek("Kevin", MonthDay.of(Month.JUNE, 3));
        int pocetJmenPoPridaniJmen = svatky.getPocetJmen();
        assertEquals(40, pocetJmenPoPridaniJmen, "Počet jmen.");

        // Ověření, že počet jmen po přidání nových jmen odpovídá očekávanému počtu (40)
    }

    /**
     * Testuje metodu {@link Svatky#getSeznamJmen()}
     */
    @Test
    void getSeznamJmen() {
        Svatky svatky = new Svatky();
        Set<String> ocekavanaJmena = Set.of("Zikmund", "Stanislav", "Svatava");//toto tady možná ani nemusí být
        Set<String> seznamJmen = svatky.getSeznamJmen();
        int velikostSeznamu = svatky.getPocetJmen();
        // Assert
        assertTrue(seznamJmen.containsAll(ocekavanaJmena), "Jména by měla být v seznamu");//toto tady možná ani nemusí být
        assertFalse(seznamJmen.contains("Žába"), "Jméno nemá být v seznamu");//toto tady možná ani nemusí být
        assertEquals(velikostSeznamu, seznamJmen.size(), "Seznam má správný počet položek");
        //Zkontrolovat, že seznam jmen má správný počet položek.
    }

    /**
     * Testuje metodu {@link Svatky#pridejSvatek(String, int, int)}
     */
    @Test
    void pridejSvatekDenMesicInt() {
        //Otestuje, že je jméno v seznamu svátků a že má přiřazen správný den
        Svatky svatky = new Svatky();
        svatky.pridejSvatek("Wander", 2, 5);
        MonthDay kdyMaSvatekWander = svatky.vratKdyMaSvatek("Wander");
        assertTrue(svatky.jeVSeznamu("Wander"));
        assertEquals(kdyMaSvatekWander, MonthDay.of(2, 5)); // String, int, int
    }

    /**
     * Testuje metodu {@link Svatky#pridejSvatek(String, int, Month)}
     */
    @Test
    void pridejSvatekDenMesicMonth() {
        Svatky svatky = new Svatky();
        svatky.pridejSvatek("Pepina", 28, Month.MAY);
        MonthDay kdyMaSvatekPepina = svatky.vratKdyMaSvatek("Pepina");
        assertTrue(svatky.jeVSeznamu("Pepina"));
        assertEquals(Month.MAY, kdyMaSvatekPepina.getMonth());
        assertEquals(28, kdyMaSvatekPepina.getDayOfMonth());
        // Otestuje, že je jméno v seznamu svátků a že má přiřazen správný den
    }

    /**
     * Testuje metodu {@link Svatky#pridejSvatek(String, MonthDay)}
     */
    @Test
    void pridejSvatekMonthDay() {
        Svatky svatky = new Svatky();
        String jmenoSvatku = "Jaryn";
        MonthDay datumSvatku = MonthDay.of(1, 2);
        svatky.pridejSvatek(jmenoSvatku, datumSvatku); //tady jsem to pro sebe jen rozepsala trochu jinak
        assertEquals(MonthDay.of(1, 2), svatky.vratKdyMaSvatek("Jaryn")); //Je v seznamu a ma spravne daum
    }

    @Test
    void testKdyMaSvatekMonika() {
        Svatky svatky = new Svatky();
        MonthDay kdyMaSvatekMonika = svatky.vratKdyMaSvatek("Monika");
        assertEquals(MonthDay.of(5, 21), kdyMaSvatekMonika);
    }

    @Test
    void testKdyMaSvatekEva() {
        Svatky svatky = new Svatky();
        MonthDay kdyMaSvatekEva = svatky.vratKdyMaSvatek("Eva");
        assertNull(kdyMaSvatekEva, "Eva by neměla být v seznamu");
    }

    /**
     * Testuje metodu {@link Svatky#smazSvatek(String)}
     */
    @Test
    void smazSvatek() {
        Svatky svatky = new Svatky();
        int pocetJmen = svatky.getPocetJmen();
        assertEquals(37, pocetJmen, "Počet jmen.");
        svatky.smazSvatek("Přemysl");
        int pocetJmenPoSmazaniJmen = svatky.getPocetJmen();
        assertEquals(pocetJmen - 1, pocetJmenPoSmazaniJmen, "Počet jmen po smazání jména.");
        //Zkontrolovat, že po smazání bude počet svátků odpovídat novému počtu.
    }
}
