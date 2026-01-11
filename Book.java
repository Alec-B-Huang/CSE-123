import java.util.*;

public class Book implements Media, Comparable<Book> {

    private final String title;
    private final List<String> authors;
    private final List<String> content;

    private int numberOfRatings;
    private int sumOfRatings;

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

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public List<String> getArtists() {
        return new ArrayList<>(this.authors);
    }

    @Override
    public void addRating(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Rating must be non-negative");
        }

        this.numberOfRatings++;
        this.sumOfRatings = this.sumOfRatings + score;
    }

    @Override
    public int getNumRatings() {
        return this.numberOfRatings;
    }

    @Override
    public double getAverageRating() {
        if (numberOfRatings == 0) {
            return 0;

        } else {
            return (double) this.sumOfRatings / this.numberOfRatings;
        }
    }

    @Override
    public List<String> getContent() {
        return new ArrayList<>(this.content);
    }

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

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }
}  
