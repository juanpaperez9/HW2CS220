import java.util.Set;

public interface BookTitleAPI {
    /**
     * Retrieves a set of book titles written by the specified author during the given year.
     *
     * @param author the name of the author
     * @param year   the year when the books were published
     * @return a set of book titles
     * @throws IllegalArgumentException if the author is null or empty, or if the year is invalid
     */
    Set<String> getBookTitles(String author, int year) throws IllegalArgumentException;
}
