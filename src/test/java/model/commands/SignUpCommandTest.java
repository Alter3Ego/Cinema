package model.commands;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SignUpCommandTest {
    @InjectMocks
    SignUpCommand signUpCommand;


    @ParameterizedTest
    @MethodSource("generateData")
    void wrongInputCheckTesting(String firstName, String lastName, String email, String password,
                                boolean expected) {
        boolean actual = signUpCommand.wrongInputCheck(firstName, lastName, email, password);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("Taras", "Shevchenko", "sheva@gmail.com", "passworD22",
                        false),
                Arguments.of("", "Shevchenko", "sheva@gmail.com", "passworD22",
                        true),
                Arguments.of("Taras", null, "sheva@gmail.com", "passworD22",
                        true),
                Arguments.of("Taras", null, "sheva@gmail.com", "passworD22",
                        true),
                Arguments.of("Taras", "Shevchenko", "shevagmail.com", "passworD22",
                        true),
                Arguments.of("Taras", "Shevchenko", "sheva@gmail.com", "passwor22",
                        true),
                Arguments.of("Тарас", "Shevchenko", "sheva@gmail.com", "paSSword22",
                        false),
                Arguments.of("тарас", "Shevchenko", "sheva@gmail.com", "paSSword22",
                        true)
        );
    }
}