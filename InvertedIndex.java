import java.util.*;

// Name: Alec Huang
// Date: 01/11/26
// TA: ISHITA MUNDRA
// CSE 123
// C0

// Class Comment: Represents a search tool that builds an inverted index 
// that maps words to the media items whose content contains those words.
public class InvertedIndex {
    public static void main(String[] args) {
        List<Media> docs = List.of(
            new Book("Mistborn", List.of("Brandon Sanderson"),
                     new Scanner("Epic fantasy worldbuildling content")),
            new Book("Fahrenheit 451", List.of("Ray Bradbury"),
                     new Scanner("Realistic \"sci-fi\" content")),
            new Book("The Hobbit", List.of("J.R.R. Tolkein"),
                     new Scanner("Epic fantasy quest content"))
        );
        
        Map<String, Set<Media>> result = createIndex(docs);
        System.out.println(docs);
        System.out.println();
        System.out.println(result);
    }

    // BEHAVIOR: 
    //      creates a map of items in which each word is matched to the media titles
    //      that they appear in.
    // EXCEPTIONS: None
    // RETURNS: 
    //      invertedIndex - a map from each word to the set of media whose content contains it.
    // PARAMETERS: 
    //      docs - a list of media titles.
    public static Map<String, Set<Media>> createIndex(List<Media> docs) {
        Map<String, Set<Media>> invertedIndex = new TreeMap<>();

        for (Media doc : docs) {
            List<String> words = doc.getContent();

            for (String word : words) {

                String wordLower = word.toLowerCase();
                if (!invertedIndex.keySet().contains(wordLower)) {
                    invertedIndex.put(wordLower, new HashSet<>());
                    invertedIndex.get(wordLower).add(doc);

                } else {
                    invertedIndex.get(wordLower).add(doc);
                }
            }
        }

        return invertedIndex;
        
    }
}
