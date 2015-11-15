import components.map.Map;
import components.map.Map4;
import components.set.Set;
import components.set.Set2;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Comp {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Comp() {
    }

    /**
     * Separators.
     */
    private static final String SEPARATORS = " \t\n\r,/\"\\?<>:;{}[]|()!@#$%^&*_-+=~.0123456789";

    /**
     *
     * @param c
     *            character to compare.
     * @return if a character is in the separator strin.
     */
    private static boolean isSep(char c) {
        return SEPARATORS.indexOf(c) != -1;
    }

    /**
     *
     * @param text
     *            contains information to search.
     * @param startIndex
     *            must be updated to move through the string.
     * @return the next string of all separators or all non-separators.
     */
    public static String nextWordOrSeparator(String text, int startIndex) {
        boolean firstIsSep = isSep(text.charAt(startIndex));
        int checkIndex = startIndex + 1;
        while (checkIndex < text.length()
                && firstIsSep == isSep(text.charAt(checkIndex))) {
            checkIndex++;
        }
        return text.substring(startIndex, checkIndex);
    }

    /**
     * Generates a mapping of words used by employers to their frequencies among
     * a set of employers.
     *
     * @param jobs
     *            array of jobs from file
     * @param indices
     *            indices of jobs to be examined
     * @param irrelevantWords
     *            set of words which will not have frequency counts
     * @return map of words to frequency
     */
    public static Map<String, Integer> wordFrequencyJob(Job[] jobs,
            Set<Integer> indices, Set<String> irrelevantWords) {
        Map<String, Integer> frequency = new Map4<String, Integer>();

        for (Integer index : indices) {
            //get words from description
            int i = 0;
            String section = jobs[index].description() + " "
                    + jobs[index].requirements();
            while (i < section.length()) {
                String word = nextWordOrSeparator(section, i);
                i += word.length();

                if (!isSep(word.charAt(0)) && !irrelevantWords.contains(word)) {
                    //word is actually a relevant word
                    word = word.toLowerCase();
                    if (frequency.hasKey(word)) {
                        frequency.replaceValue(word, frequency.value(word) + 1);
                    } else {
                        frequency.add(word, 1);
                    }

                }
            }
        }
        return frequency;

    }

    /**
     * Generates a mapping of words used by students to their frequencies among
     * a set of students.
     *
     * @param students
     *            array of students from file
     * @param indices
     *            indices of students to be examined
     * @param irrelevantWords
     *            set of words which will not have frequency counts
     * @return map of words to frequency
     */
    public static Map<String, Integer> wordFrequencyStudent(Student[] students,
            Set<Integer> indices, Set<String> irrelevantWords) {
        Map<String, Integer> frequency = new Map4<String, Integer>();

        for (Integer index : indices) {
            //get words from description
            int i = 0;
            String section = students[index].getComputerSkills() + " "
                    + students[index].getLanguageSkills() + " "
                    + students[index].getCourses() + " "
                    + students[index].getPersonalSkills() + " "
                    + students[index].getProjects() + " "
                    + students[index].getJobTitle(0) + " "
                    + students[index].getJobDescription(0) + " "
                    + students[index].getJobTitle(1) + " "
                    + students[index].getJobDescription(1) + " "
                    + students[index].getJobTitle(2) + " "
                    + students[index].getJobDescription(2) + " ";
            while (i < section.length()) {
                String word = nextWordOrSeparator(section, i);
                i += word.length();

                if (!isSep(word.charAt(0)) && !irrelevantWords.contains(word)) {
                    //word is actually a relevant word
                    word = word.toLowerCase();
                    if (frequency.hasKey(word)) {
                        frequency.replaceValue(word, frequency.value(word) + 1);
                    } else {
                        frequency.add(word, 1);
                    }

                }
            }

        }
        return frequency;

    }

    /**
     *
     * @param graduating
     *            boolean describing whether to return gradutating or
     *            nongraduating students.
     * @param search
     *            the array of Student to search
     * @param currentYear
     *            the current calendar year.
     * @return the indices of either graduating or nongraduating students.
     */
    public static Set<Integer> studentsToSearch(boolean graduating,
            Student[] search, int currentYear) {
        Set<Integer> ind = new Set2<Integer>();
        if (graduating) {
            for (int i = 0; i < search.length; i++) {
                int temp = search[i].getGradYear();
                if (temp == currentYear) {
                    ind.add(i);
                }
            }
        } else {
            for (int i = 0; i < search.length; i++) {
                int temp = search[i].getGradYear();
                if (temp != currentYear) {
                    ind.add(i);
                }
            }
        }
        return ind;
    }

    /**
     *
     * @param graduating
     *            boolean describing whether to return gradutating or
     *            nongraduating career options.
     * @param search
     *            the array of Job to search
     * @return the indices of either graduating or nongraduating careers.
     */
    public static Set<Integer> jobsToSearch(boolean graduating, Job[] search) {
        Set<Integer> ind = new Set2<Integer>();
        if (graduating) {
            for (int i = 0; i < search.length; i++) {
                String temp = search[i].type();
                if (temp.indexOf("Career") > -1) {
                    ind.add(i);
                }
            }
        } else {
            for (int i = 0; i < search.length; i++) {
                String temp = search[i].type();
                if (temp.indexOf("Career") == -1) {
                    ind.add(i);
                }
            }
        }
        return ind;
    }
}
