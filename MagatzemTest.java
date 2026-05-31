import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MagatzemTest {

    @Test
    void testDegradacioNormal() {
        Article[] art = { new Article("Poma", 10, 20) };
        new Magatzem(art).actualitzarEstat();
        assertEquals(9, art[0].getDiesPerVendre());
        assertEquals(19, art[0].getQualitat());
    }

    @Test
    void testFormatgeMilloraAmbElTemps() {
        Article[] art = { new Article("Formatge Gidurat", 10, 20) };
        new Magatzem(art).actualitzarEstat();
        assertTrue(art[0].getQualitat() > 20);
    }

    @Test
    void testEntradesCaducadesValenZero() {
        Article[] art = { new Article("Entrades per al Concert del Trobador", 0, 40) };
        new Magatzem(art).actualitzarEstat();
        assertEquals(0, art[0].getQualitat());
    }

    @Test
    void testMartellDeThorImmutable() {
        Article[] art = { new Article("Martell de Thor (Llegendari)", 10, 80) };
        new Magatzem(art).actualitzarEstat();
        assertEquals(80, art[0].getQualitat());
        assertEquals(10, art[0].getDiesPerVendre());
    }
}