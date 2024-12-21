/*
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;
*/
/**
 * Team Project -- RunLocalTest
 *
 * Local Test for databases covered in the project.
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 *
 * @version Apr 1, 2024
 *
 *//*

@RunWith(Enclosed.class)
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        @Test(timeout = 1000)
        public void OutputTest() {
            String firstUser = "James";
            String secondUser = "Evan";
            int age = 18;
            String email = "evan@purdue.edu";
            String password = "12345";
            String bio = "Freshman Undergrad at Purdue University";
            String profilePicture = "duration_boxplot.png";
            String friendName = "Josh";
            String firstMessage = "This is the first message";
            String secondMessage = "This is the second message";
            String thirdMessage = "This is the third message";
            String newSecondMessage = "this is the second message after edit";
            // Arrange
            String input = firstUser + "\n" +
                    secondUser + "\n" +
                    age + "\n" +
                    email + "\n" +
                    password + "\n" +
                    bio + "\n" +
                    profilePicture + "\n" +
                    friendName + "\n" +
                    firstMessage + "\n" +
                    secondMessage + "\n" +
                    thirdMessage + "\n" +
                    newSecondMessage + "\n";
            String expectedOutput = String.format("Set name for the first user: \n" +
                            "Set name for new user: \n" +
                            "What's your age?\n" +
                            "What's your email?\n" +
                            "What's your password?\n" +
                            "What would you like your bio to be?\n" +
                            "Type in a jpg for your profile picture:\n" +
                            "Give a name of a friend: \n" +
                            "This is your friend: \n%s\n" +
                            "All users: %s %s \n" +
                            "Search for Jam: \n" +
                            "User searched: %s\n" +
                            "Removed the first user: \n" +
                            "All users: %s\n" +
                            "Give me content for the first message: \n" +
                            "Give me content for the second message: \n" +
                            "Give me content for the third message: \n" +
                            "Sent\nSent\nSent\n" +
                            "Here is all the message currently sent: \n%s\n%s\n%s\n" +
                            "Give me new content for the second message: \n" +
                            "Message successfully edited.\n" +
                            "Here is all the message currently sent: \n%s\n%s\n%s\n" +
                            "Result of deleting first message: \n" +
                            "Here is all the message currently sent: \n%s\n%s\n", friendName, firstUser, secondUser, firstUser, secondUser,
                    firstMessage, secondMessage, thirdMessage, firstMessage, newSecondMessage, thirdMessage, newSecondMessage,
                    thirdMessage); // Expected output

            // Normalize line separators
            //expectedOutput = expectedOutput.replace("\n", System.lineSeparator());

            // Capture system output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));

            // Provide input to the program
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            try {
                // Act
                MainForTest.main(new String[0]);

                // Assert
                String actualOutput = outputStream.toString().trim();
                actualOutput = actualOutput.replace("\r\n", "\n").replace("\r", "\n"); // Normalize line separators
                assertEquals(expectedOutput.trim(), actualOutput);
            } catch (AssertionError e) {
                throw e; // Rethrow the AssertionError to ensure the test fails
            } finally {
                // Clean up
                System.setOut(originalOut);
                System.setIn(System.in);
            }
        }

        @Test(timeout = 1000)
        public void ProfileClassDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = Profile.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            assertTrue("Ensure that `Profile` is `public`!",
                    Modifier.isPublic(modifiers));
            assertFalse("Ensure that `Profile` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            assertEquals("Ensure that `Profile` extends `Object`!",
                    Object.class, superclass);
            assertNotEquals("Ensure that `Profile` implements interfaces!",
                    0, superinterfaces.length);
        }
    }


}
*/
