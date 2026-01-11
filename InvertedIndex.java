import java.util.*;

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
