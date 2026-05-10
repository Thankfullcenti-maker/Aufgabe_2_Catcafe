package catcafe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test; // Wichtig: Import für @Test

class MainTest {

    @Test
    // Sagt JUnit: Das hier ist ein Test-Kapitel
    void meinErsterTest() {
        assertNotNull(1); // Jetzt ist es gültig!
    }
}
