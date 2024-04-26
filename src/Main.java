public class Main {
    public static void main(String[] args) {
        BookTitleAPI bookTitleAPI = new MockBookTitleAPI();

        // Create a BookRecordChecker instance
        BookRecordChecker recordChecker = new BookRecordChecker(bookTitleAPI);

        // Check the book record for a specific author and year
        recordChecker.checkBookRecord("AuthorName", 1976);
    }
}
