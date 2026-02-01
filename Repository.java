import java.util.*;
import java.text.SimpleDateFormat;

public class Repository {
    private String name;
    private Commit head;
    private int size;

    public Repository(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.head = null;
        this.size = 0;
    }

    public String getRepoHead() {
        if (head == null) {
            return null;
        }

        return head.id;
    }

    public int getRepoSize() {
        return size;
    }

    public String toString() {
        if (head != null) {
            return name + " - Current head: " + head.toString();
        }

        return name + " - Current head: No commits";
    }
   
    public boolean contains(String targetId) {
        if (targetId == null) {
            throw new IllegalArgumentException();
        }

        Commit current = head;

        while (current != null) {
            if (current.id.equals(targetId)) {
                return true;

            } else {
                current = current.past;
            }
        }
        return false;
    }

    public String getHistory(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        Commit current = head;
        int count = 0;
        String result = "";

        while (current != null && count < n) {
            if (current.past == null || count == n - 1) {
                result = result + current.toString();

            } else {
                result = result + current.toString() + "\n";
            }

            current = current.past;
            count += 1;
        }

        return result;
    }

    public String commit(String message) {
        if (message == null) {
            throw new IllegalArgumentException();
        }

        Commit newCommit = new Commit(message, head);
        head = newCommit;
        size += 1;

        return newCommit.id;
    }

    public boolean drop(String targetId) {
        if (targetId == null) {
            throw new IllegalArgumentException();
        }

        if (head == null) {
            return false;
        }

        if (head.id.equals(targetId)) {
            head = head.past;
            size -= 1;
            return true;
        }

        Commit previous = head;
        Commit current = head.past;

        while (current != null) {
            if (current.id.equals(targetId)) {
                previous.past = current.past;
                size -= 1;
                return true;
            }

            previous = current;
            current = current.past;
        }
        return false;
    }

    public void synchronize(Repository other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }

        if (other.head == null) {
            other.size = 0;
            return;
        }

        if (this.head == null) {
            this.head = other.head;
            this.size += other.size;
            other.head = null;
            other.size = 0;
            return;
        }

        Commit refA = this.head;
        Commit refB = other.head;
        Commit mergedHead = null;

        if (refA.timeStamp > refB.timeStamp) {
            mergedHead = refA;
            refA = refA.past;

        } else {
            mergedHead = refB;
            refB = refB.past;
        }

        Commit mergedEnd = mergedHead;

        while (refA != null && refB != null) {
            if (refA.timeStamp > refB.timeStamp) {
                mergedEnd.past = refA;
                mergedEnd = refA;
                refA = refA.past;

            } else {
                mergedEnd.past = refB;
                mergedEnd = refB;
                refB = refB.past;
            }
        }

        if (refA != null) {
            mergedEnd.past = refA;

        } else {
            mergedEnd.past = refB;
        }

        this.head = mergedHead;
        this.size = this.size + other.size;
        other.head = null;
        other.size = 0;
    }

    /**
     * DO NOT MODIFY
     * A class that represents a single commit in the repository.
     * Commits are characterized by an identifier, a commit message,
     * and the time that the commit was made. A commit also stores
     * a reference to the immediately previous commit if it exists.
     *
     * Staff Note: You may notice that the comments in this 
     * class openly mention the fields of the class. This is fine 
     * because the fields of the Commit class are public. In general, 
     * be careful about revealing implementation details!
     */
    public static class Commit {

        private static int currentCommitID;

        /**
         * The time, in milliseconds, at which this commit was created.
         */
        public final long timeStamp;

        /**
         * A unique identifier for this commit.
         */
        public final String id;

        /**
         * A message describing the changes made in this commit.
         */
        public final String message;

        /**
         * A reference to the previous commit, if it exists. Otherwise, null.
         */
        public Commit past;

        /**
         * Constructs a commit object. The unique identifier and timestamp
         * are automatically generated.
         * @param message A message describing the changes made in this commit. Should be non-null.
         * @param past A reference to the commit made immediately before this
         *             commit.
         */
        public Commit(String message, Commit past) {
            this.id = "" + currentCommitID++;
            this.message = message;
            this.timeStamp = System.currentTimeMillis();
            this.past = past;
        }

        /**
         * Constructs a commit object with no previous commit. The unique
         * identifier and timestamp are automatically generated.
         * @param message A message describing the changes made in this commit. Should be non-null.
         */
        public Commit(String message) {
            this(message, null);
        }

        /**
         * Returns a string representation of this commit. The string
         * representation consists of this commit's unique identifier,
         * timestamp, and message, in the following form:
         *      "[identifier] at [timestamp]: [message]"
         * @return The string representation of this collection.
         */
        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timeStamp);

            return id + " at " + formatter.format(date) + ": " + message;
        }

        /**
        * Resets the IDs of the commit nodes such that they reset to 0.
        * Primarily for testing purposes.
        */
        public static void resetIds() {
            Commit.currentCommitID = 0;
        }
    }
}
