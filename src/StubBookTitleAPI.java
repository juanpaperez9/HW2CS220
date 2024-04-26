import java.util.Set;
import java.util.HashSet;

public class StubBookTitleAPI implements BookTitleAPI {
    @Override
    public Set<String> getBookTitles(String author, int year) {
        // Stub implementation: return a set of book titles that exceeds the current record
        Set<String> bookTitles = new HashSet<>();
        for (int i = 1; i <= 25; i++) {
            bookTitles.add("Book Title " + i);
        }
        return bookTitles;
    }
}
