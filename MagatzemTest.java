import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * XARXA DE SEGURETAT: Suite de proves unitàries automatitzades amb JUnit 5.
 * Compleix la regla d'or de la refactorització: garanteix que el comportament extern
 * de l'aplicació roman idèntic abans, durant i després d'aplicar els patrons.
 */
class MagatzemTest {

    @Test
    void testDegradacioNormal() {
        Article[] art = { new Article("Poma", 10, 20) };
        new Magatzem(art).actualitzarEstat();
        assertEquals(9, art[0].diesPerVendre, "Els dies haurien de baixar en 1");
        assertEquals(19, art[0].qualitat, "La qualitat hauria de baixar en 1");
    }

    @Test
    void testFormatgeMilloraAmbElTemps() {
        Article[] art = { new Article("Formatge Gidurat", 10, 20) };
        new Magatzem(art).actualitzarEstat();
        assertTrue(art[0].qualitat > 20, "El formatge hauria d'augmentar la seva qualitat");
    }

    @Test
    void testEntradesCaducadesValenZero() {
        Article[] art = { new Article("Entrades per al Concert del Trobador", 0, 40) };
        new Magatzem(art).actualitzarEstat();
        assertEquals(0, art[0].qualitat, "Després del concert la qualitat ha de ser 0");
    }

    @Test
    void testMartellDeThorImmutable() {
        Article[] art = { new Article("Martell de Thor (Llegendari)", 10, 80) };
        new Magatzem(art).actualitzarEstat();
        assertEquals(80, art[0].qualitat, "La qualitat del Martell no ha de canviar");
        assertEquals(10, art[0].diesPerVendre, "Els dies del Martell no han de canviar");
    }
}