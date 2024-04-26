import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookrecordCheckerTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;
    private BookRecordChecker recordChecker;

    @Before
    public void setUp() {
        // Redirect System.out to capture printed output
        System.setOut(new PrintStream(outputStream));
        // Redirect System.err to capture error output
        System.setErr(new PrintStream(errorStream));

        // Create a stub BookTitleAPI instance for testing
        BookTitleAPI bookTitleAPI = new StubBookTitleAPI();
        recordChecker = new BookRecordChecker(bookTitleAPI);
    }

    @After
    public void tearDown() {
        // Restore original System.out
        System.setOut(originalOut);
        // Restore original System.err
        System.setErr(originalErr);
    }

    @Test
    public void testRecordBroken() {
        // Check the book record
        recordChecker.checkBookRecord("AuthorName", 1976);

        // Verify that the record is broken
        // Assuming the current record is 23, and the stub provides 25 titles
        assertEquals("New record achieved! Number of unique titles: 25", systemOut());
    }

    @Test
    public void testEmptyBookTitles() {
        // Stub the BookTitleAPI to return an empty set of book titles
        BookTitleAPI emptyAPI = new BookTitleAPI() {
            @Override
            public Set<String> getBookTitles(String author, int year) {
                return Collections.emptySet();
            }
        };

        BookRecordChecker emptyChecker = new BookRecordChecker(emptyAPI);
        emptyChecker.checkBookRecord("AuthorName", 1976);

        // Verify that the record is not broken
        assertEquals("Record not broken. Number of unique titles: 0", systemOut());
    }

    @Test
    public void testInvalidInputParameters() {
        // Check the book record with null author and negative year
        recordChecker.checkBookRecord(null, -1);

        // Verify that an error message is printed
        assertEquals("Invalid input parameters. Author and year must be specified.", systemErr());
    }

    @Test
    public void testBookTitleAPIError() {
        // Stub the BookTitleAPI to return null, simulating an error condition
        BookTitleAPI errorAPI = new BookTitleAPI() {
            @Override
            public Set<String> getBookTitles(String author, int year) {
                return null;
            }
        };

        BookRecordChecker errorChecker = new BookRecordChecker(errorAPI);
        errorChecker.checkBookRecord("AuthorName", 1976);

        // Verify that an error message is printed
        assertEquals("Error retrieving book titles from the API.", systemErr());
    }

    // Helper method to capture System.out.println output
    private String systemOut() {
        return outputStream.toString().trim();
    }

    // Helper method to capture System.err.println output
    private String systemErr() {
        return errorStream.toString().trim();
    }
}
