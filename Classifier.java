// ALEC HUANG
// CSE 123
// P3
// 03/04/2025
// Ishita Mundra

import java.io.*;
import java.util.*;

public class Classifier {
    private ClassifierNode root;

    public Classifier(Scanner input) {
       if (input == null) {
        throw new IllegalArgumentException();
       }

       root = loadHelper(input);
       if (root == null) {
        throw new IllegalStateException();
       }
    }

    private ClassifierNode loadHelper(Scanner input) {
        if (!input.hasNextLine()) {
            throw new IllegalStateException();
        }

        String line = input.nextLine();

        if (line.startsWith("Feature: ")) {
            String featureWord = line.substring(9);
            String thresholdLine = input.nextLine();
            double threshold = Double.parseDouble(thresholdLine.substring(11));

            ClassifierNode left = loadHelper(input);
            ClassifierNode right = loadHelper(input);

            return new ClassifierNode(featureWord, left, right, threshold);
        }

        return new ClassifierNode(line, null);
    }

    public Classifier(List<TextBlock> data, List<String> labels) {
        if (data == null || labels == null || data.size() != labels.size() || data.isEmpty()) {
            throw new IllegalArgumentException();
        }

        root = new ClassifierNode(labels.get(0), data.get(0));

        for (int i = 1; i < data.size(); i++) {
            root = trainTree(root, data.get(i), labels.get(i));
        }
    }

    public String classify(TextBlock input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        ClassifierNode currentNode = root;
        String resultNode = classifyHelper(currentNode, input);

        return resultNode;
    }
    private String classifyHelper(ClassifierNode node, TextBlock input) {
        if (node.isLeaf()) {
            return node.label;
        }

        String word = node.featureWord;
        double threshold = node.threshold;
        double nodeValue = input.get(word);

        if (nodeValue < threshold) {
            node = node.left;
            return classifyHelper(node, input);

        } else {
            node = node.right;
            return classifyHelper(node, input);
        }
    }

    public void save(PrintStream output) {
        if (output == null) {
            throw new IllegalArgumentException();
        }

        saveHelper(root, output);
    }

    private void saveHelper(ClassifierNode node, PrintStream output) {
        if (node.isLeaf()) {
            output.println(node.label);

        } else {
            output.println("Feature: " + node.featureWord);
            output.println("Threshold: " + node.threshold);

            saveHelper(node.left, output);
            saveHelper(node.right, output);
        }
    }

    private ClassifierNode trainTree(ClassifierNode node, TextBlock block, String trueLabel) {
            if (node.isLeaf()) {
                if (node.label.equals(trueLabel)) {
                    return node;
                }

                TextBlock oldBlock = node.trainedExample;
                String oldLabel = node.label;
                String feature = oldBlock.findBiggestDifference(block);
                double oldFeature = oldBlock.get(feature);
                double newFeature = block.get(feature);
                double threshold = midpoint(oldFeature, newFeature);

                ClassifierNode oldLeaf = new ClassifierNode(oldLabel, oldBlock);
                ClassifierNode newLeaf = new ClassifierNode(trueLabel, block);

                double oldValue = oldFeature;
                double newValue = newFeature;

                ClassifierNode leftChild;
                ClassifierNode rightChild;

                if (newValue < threshold && oldValue >= threshold) {
                    leftChild = newLeaf;
                    rightChild = oldLeaf;

                } else if (oldValue < threshold && newValue >= threshold) {
                    leftChild = oldLeaf;
                    rightChild = newLeaf;

                } else {
                    if (newValue < threshold) {
                        leftChild = newLeaf;
                        rightChild = oldLeaf;

                    } else {
                        leftChild = oldLeaf;
                        rightChild = newLeaf;
                    }
                }

                return new ClassifierNode(feature, leftChild, rightChild, threshold);
            }

            double value = block.get(node.featureWord);
            if (value < node.threshold) {
                node.left = trainTree(node.left, block, trueLabel);

            } else {
                node.right = trainTree(node.right, block, trueLabel);
            }
            
            return node;
        }

    private static class ClassifierNode {
        public ClassifierNode left;
        public ClassifierNode right;
        public final String label;
        public final TextBlock trainedExample;
        public final String featureWord;
        public final double threshold;

        private ClassifierNode(String label, TextBlock trainedExample) {
            this.label = label;
            this.left = null;
            this.right = null;
            this.featureWord = null;
            this.trainedExample = trainedExample;
            this.threshold = 0;
        }

        private ClassifierNode(String featureWord, ClassifierNode left, ClassifierNode right, 
                                                                            double threshold) {
            this.featureWord = featureWord;
            this.threshold = threshold;
            this.left = left;
            this.right = right;
            this.label = null;
            this.trainedExample = null;
        }

        private boolean isLeaf() {
            return featureWord == null;
        }
    }
        
    ////////////////////////////////////////////////////////////////////
    // PROVIDED METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!** //
    ////////////////////////////////////////////////////////////////////

    // Helper method to calculate the midpoint of two provided doubles.
    private static double midpoint(double one, double two) {
        return Math.min(one, two) + (Math.abs(one - two) / 2.0);
    }

    // Behavior: Calculates the accuracy of this model on provided Lists of 
    //           testing 'data' and corresponding 'labels'. The label for a 
    //           datapoint at an index within 'data' should be found at the 
    //           same index within 'labels'.
    // Exceptions: IllegalArgumentException if the number of datapoints doesn't match the number 
    //             of provided labels
    // Returns: a map storing the classification accuracy for each of the encountered labels when
    //          classifying
    // Parameters: data - the list of TextBlock objects to classify. Should be non-null.
    //             labels - the list of expected labels for each TextBlock object. 
    //             Should be non-null.
    public Map<String, Double> calculateAccuracy(List<TextBlock> data, List<String> labels) {
        // Check to make sure the lists have the same size (each datapoint has an expected label)
        if (data.size() != labels.size()) {
            throw new IllegalArgumentException(
                    String.format("Length of provided data [%d] " +
                                    "doesn't match provided labels [%d]", 
                            data.size(), labels.size()));
        }

        // Create our total and correct maps for average calculation
        Map<String, Integer> labelToTotal = new HashMap<>();
        Map<String, Double> labelToCorrect = new HashMap<>();
        labelToTotal.put("Overall", 0);
        labelToCorrect.put("Overall", 0.0);

        for (int i = 0; i < data.size(); i++) {
            String result = classify(data.get(i));
            String label = labels.get(i);

            // Increment totals depending on resultant label
            labelToTotal.put(label, labelToTotal.getOrDefault(label, 0) + 1);
            labelToTotal.put("Overall", labelToTotal.get("Overall") + 1);
            if (result.equals(label)) {
                labelToCorrect.put(result, labelToCorrect.getOrDefault(result, 0.0) + 1);
                labelToCorrect.put("Overall", labelToCorrect.get("Overall") + 1);
            }
        }

        // Turn totals into accuracy percentage
        for (String label : labelToCorrect.keySet()) {
            labelToCorrect.put(label, labelToCorrect.get(label) / labelToTotal.get(label));
        }

        return labelToCorrect;
    }
}
