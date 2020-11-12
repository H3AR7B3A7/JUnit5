package be.dog.d.steven;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    // SETUP

    @BeforeAll
    static void beforeAll(){
        System.out.println("MathUtils tests starting!");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        mathUtils = new MathUtils();
        this.testInfo = testInfo;
        this.testReporter = testReporter;
    }

    @AfterEach
    void cleanUp(){
        System.out.println("Cleaning up...");
    }


    @Tag("math")
    public @interface Math {
        // You can add more @annotations to this to group to @Math
    }


    // TESTS

    @Test
    @Tag("area")
    @DisplayName("Testing circle area calculation \uD83D\uDE08")
    void mathUtilsComputeCircleAreaTest(){
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags: " + testInfo.getTags());

        assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return the surface area of a circle.");
    }

    @RepeatedTest(3)
    @Math
    @DisplayName("Testing add method 3 times \ud83d\udc3b")
    void mathUtilsAddTest(RepetitionInfo repetitionInfo){
        assertEquals(2,mathUtils.add(1,1),"The add method should add two numbers.");
        System.out.println(repetitionInfo.getCurrentRepetition());
    }

    @Test
    @Math
    @DisplayName("Testing divide method")
    void mathUtilsDivideTest() {
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(1,0), "Divide by 0 should throw.");
    }

    @Test
    @Math
    @DisplayName("Testing multiply method")
    void mathUtilsMultiplyTest(){
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2,2)),
                () -> assertEquals(0, mathUtils.multiply(0,2)),
                () -> assertEquals(-2, mathUtils.multiply(2,-1))
        );
    }

    @Nested
    @Math
    @DisplayName("Testing subtract method")
    class SubtractTests{

        @Test
        void mathUtilsSubtractionPositiveTest(){
            assertEquals(5, mathUtils.subtract(10,5));
        }

        @Test
        void mathUtilsSubtractionNegativeTest(){
            assertEquals(15, mathUtils.subtract(10,-5));
        }
    }

    @ParameterizedTest
    @ValueSource(ints={-5,-3,3,5,8,14})
    @DisplayName("Declaring integers")
    void shouldDeclareInteger(int integer){
        Integer i = integer; // POC for parameterized test, no1 should ever test if declaring an integer works
        assertEquals(integer, i);
    }


    // IGNORED TESTS

    @Test
    @Disabled
    void dontRun(){
        System.out.println("You will never see this string.");
    }

    @Test
    @EnabledOnOs({OS.MAC})
    void dontRunButOnMac(){
        System.out.println("You will never see this string.");
    }

    @Test
    void assume(){
        boolean serverUp = false; // Not hardcoded IRL
        assumeTrue(serverUp, "Server has to be running for this test.");
        System.out.println("You will never see this string.");
    }

}