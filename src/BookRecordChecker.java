import java.util.Set;

public class BookRecordChecker {
    private static final int CURRENT_RECORD = 23; // Initial record set by Barbara Cartland

    private final BookTitleAPI bookTitleAPI;

    public BookRecordChecker(BookTitleAPI bookTitleAPI) {
        this.bookTitleAPI = bookTitleAPI;
    }

    public void checkBookRecord(String author, int year) {
        // Validate input parameters
        if (author == null || author.isEmpty() || year <= 0) {
            System.err.println("Invalid input parameters. Author and year must be specified.");
            return;
        }

        Set<String> bookTitles = bookTitleAPI.getBookTitles(author, year);
        if (bookTitles == null) {
            System.err.println("Error retrieving book titles from the API.");
            return;
        }

        int uniqueTitlesCount = bookTitles.size();
        compareRecordAndUpdate(uniqueTitlesCount);
    }

    private void compareRecordAndUpdate(int uniqueTitlesCount) {
        if (uniqueTitlesCount > CURRENT_RECORD) {
            System.out.println("New record achieved! Number of unique titles: " + uniqueTitlesCount);
            // Update current record with uniqueTitlesCount
        } else {
            System.out.println("Record not broken. Number of unique titles: " + uniqueTitlesCount);
            // No need to update current record, as it's not broken
        }
    }
}
