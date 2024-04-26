import java.util.HashSet;
import java.util.Set;

public class MockBookTitleAPI implements BookTitleAPI {
    @Override
    public Set<String> getBookTitles(String author, int year) {
        // Simulate dynamic mock data generation based on author and year
        Set<String> bookTitles = generateMockBookTitles(author, year);
        return bookTitles;
    }

    private Set<String> generateMockBookTitles(String author, int year) {
        Set<String> bookTitles = new HashSet<>();

        // Generate mock data based on author and year
        // let's assume the author's name is incorporated into the book titles
        for (int i = 1; i <= 10; i++) {
            String title = author + "'s Book " + i + " - " + year;
            bookTitles.add(title);
        }

        return bookTitles;
    }
}
