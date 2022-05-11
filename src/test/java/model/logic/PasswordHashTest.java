package model.logic;

import org.junit.jupiter.api.Test;
import ua.omelchenko.cinema.util.PasswordHash;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {
    @Test
    void encryptionTest() {
        String passwordBefore1 = "thePassword45";
        String passwordBefore2 = "thePassword45";
        String passwordBefore3 = "thePassword46";
        String passwordAfter1 = PasswordHash.encryption(passwordBefore1);
        String passwordAfter2 = PasswordHash.encryption(passwordBefore2);
        String passwordAfter3 = PasswordHash.encryption(passwordBefore3);

        assertEquals(passwordAfter1, passwordAfter2);
        assertNotEquals(passwordAfter1, passwordAfter3);
        assertNotEquals(passwordBefore1, passwordAfter1);
    }
}