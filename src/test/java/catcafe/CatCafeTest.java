package catcafe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CatCafeTest {

    @Test
    void addCat_increasesCount() {
        // Given
        CatCafe cafe = new CatCafe();
        FelineOverLord cat = new FelineOverLord("Minka", 4000);

        // When
        cafe.addCat(cat);

        // Then
        assertEquals(1, cafe.getCatCount(), "Anzahl der Katzen sollte 1 sein.");
    }

    @Test
    void addCat_throwsExceptionOnNull() {
        // Given
        CatCafe cafe = new CatCafe();

        // Then
        assertThrows(
                NullPointerException.class,
                () -> {
                    cafe.addCat(null);
                },
                "Hinzufügen von null sollte eine Exception werfen.");
    }

    @Test
    void getCatByName_returnsCorrectCat() {
        // Given
        CatCafe cafe = new CatCafe();
        FelineOverLord expectedCat = new FelineOverLord("Garfield", 8000);
        cafe.addCat(expectedCat);

        // When
        FelineOverLord actualCat = cafe.getCatByName("Garfield");

        // Then
        assertNotNull(actualCat);
        assertEquals("Garfield", actualCat.name());
    }

    @Test
    void getCatByName_returnsNullIfNotFound() {
        // Given
        CatCafe cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Luna", 3000));

        // When
        FelineOverLord result = cafe.getCatByName("Unbekannt");

        // Then
        assertNull(result, "Sollte null zurückgeben, wenn der Name nicht existiert.");
    }

    @Test
    void getCatByName_returnsNullOnNullInput() {
        // Given
        CatCafe cafe = new CatCafe();

        // When
        FelineOverLord result = cafe.getCatByName(null);

        // Then
        assertNull(result, "Suche nach null-Name sollte null zurückgeben.");
    }

    // --- TESTS FÜR GET BY WEIGHT ---

    @Test
    void getCatByWeight_findsCatInLowerBound() {
        // Given
        CatCafe cafe = new CatCafe();
        FelineOverLord cat = new FelineOverLord("Fluffy", 5000);
        cafe.addCat(cat);

        // When
        // Bereich 5000 (inklusive) bis 6000 (exklusive)
        FelineOverLord found = cafe.getCatByWeight(5000, 6000);

        // Then
        assertNotNull(found);
        assertEquals("Fluffy", found.name());
    }

    @Test
    void getCatByWeight_returnsNullIfWeightIsExclussive() {
        // Given
        CatCafe cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Heavy", 7000));

        // When
        // Obergrenze ist exklusiv laut Javadoc
        FelineOverLord found = cafe.getCatByWeight(6000, 7000);

        // Then
        assertNull(found, "Obergrenze sollte exklusiv sein (liefert null).");
    }

    @Test
    void getCatByWeight_returnsNullOnInvalidRange() {
        // Given
        CatCafe cafe = new CatCafe();

        // When (Min > Max)
        FelineOverLord result = cafe.getCatByWeight(500, 100);

        // Then
        assertNull(result, "Ungültige Range sollte null liefern.");
    }

    @Test
    void getCatByWeight_returnsNullOnNegativeWeight() {
        // Given
        CatCafe cafe = new CatCafe();

        // When
        FelineOverLord result = cafe.getCatByWeight(-1, 1000);

        // Then
        assertNull(result, "Negatives Gewicht sollte null liefern.");
    }

    // --- TEST FÜR MEHRERE KATZEN ---

    @Test
    void multipleCats_allAreCounted() {
        // Given
        CatCafe cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Cat1", 3000));
        cafe.addCat(new FelineOverLord("Cat2", 4000));
        cafe.addCat(new FelineOverLord("Cat3", 5000));

        // Then
        assertEquals(3, cafe.getCatCount(), "Es sollten 3 Katzen im Cafe sein.");
    }
}
