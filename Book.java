import java.util.*;
// Name: Alec Huang
// Date: 01/11/26
// TA: ISHITA MUNDRA
// CSE 123
// C0

// Class Ccommenting: Book object that has aspects from Media 
// and is comparable to other books.
public class Book implements Media, Comparable<Book> {

    private final String title;
    private final List<String> authors;
    private final List<String> content;

    private int numberOfRatings;
    private int sumOfRatings;

    // BEHAVIOR: constructs book with given author, title, and stores 
    // words as content.
    // EXCEPTION: none
    // RETURNS: none
    // PARAMETERS:
    //      title - title of book
    //      authors - authors of book
    //      content - content of the book
    public Book(String title, List<String> authors, Scanner content) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.content = new ArrayList<>();

        while (content.hasNext()) {
            this.content.add(content.next());
        }

        this.numberOfRatings = 0;
        this.sumOfRatings = 0;
    }

    // BEHAVIOR: returns title
    // EXCEPTIONS: none
    // RETURNS: string title
    // PARAMETERS: none
    @Override
    public String getTitle() {
        return this.title;
    }

    // BEHAVIOR: returns book authors
    // EXCEPTIONS: none
    // RETURNS: List of authors
    // PARAMETERS: none
    @Override
    public List<String> getArtists() {
        return new ArrayList<>(this.authors);
    }

    // BEHAVIOR: adds a rating, IllegalArgumentException
    // if the rating is under 0.0
    // EXCEPTIONS: Illegal Argument Exception
    // RETURNS: none
    // PARAMETERS: 
    //      score - the rating for the book
    @Override
    public void addRating(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Rating must be non-negative");
        }

        this.numberOfRatings++;
        this.sumOfRatings = this.sumOfRatings + score;
    }

    // BEHAVIOR: returns number of ratings
    // EXCEPTIONS: none
    // RETURNS: int number of ratings
    // PARAMETERS: none
    @Override
    public int getNumRatings() {
        return this.numberOfRatings;
    }

    // BEHAVIOR: returns the average rating of the book
    // EXCEPTIONS: none
    // RETURNS: double rating
    // PARAMETERS: none
    @Override
    public double getAverageRating() {
        if (numberOfRatings == 0) {
            return 0;

        } else {
            return (double) this.sumOfRatings / this.numberOfRatings;
        }
    }

    // BEHAVIOR: returns book content
    // EXCEPTIONS: none
    // RETURNS: list of content
    // PARAMETERS: none
    @Override
    public List<String> getContent() {
        return new ArrayList<>(this.content);
    }

    // BEHAVIOR: returns string representation of book. Only returns authors
    //          if book has no reviews, otherwise includes reviews.
    // EXCEPTIONS: none
    // RETURNS: string representation of book
    // PARAMETERS: none
    @Override
    public String toString() {
        double average = getAverageRating();
        average = Math.round(average * 100.0) / 100.0;
        if (this.numberOfRatings == 0) {
            return this.title + " by " + this.authors;

        } else {
            return this.title + " by " + this.authors + ": " + average + 
                " (" + this.numberOfRatings + " ratings)";
        }
    }

    // BEHAVIOR: returns comparison between books by alphabetical order.
    // EXCEPTIONS: none
    // RETURNS: int; negative if before other title, 0 if same
    //      positive if this title comes out after other.
    // PARAMETERS: 
    //      other - book being compared to current book
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }
}  
