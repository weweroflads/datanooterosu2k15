import components.map.Map;
import components.map.Map.Pair;
import components.map.Map4;
import components.queue.Queue;
import components.queue.Queue2;
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

    public static Queue<Pair<String, Integer>> topTen(
            Map<String, Integer> values) {
        Queue<Pair<String, Integer>> top = new Queue2<Pair<String, Integer>>();
        for (int i = values.size(); i > values.size() - 10; i--) {
            int max = 0;
            String werd = "";
            for (Pair<String, Integer> x : values) {
                if (x.value() > max) {
                    max = x.value();
                    werd = x.key();
                }
            }
            Pair<String, Integer> temp = values.remove(werd);
            top.enqueue(temp);
        }
        return top;
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

    /**
     *
     * @param q1
     *            first queue
     * @param q2
     *            second queue
     * @updates q2 to disinclude keys in q1
     * @return the common keys
     */
    public static Set<String> commonItems(Queue<Pair<String, Integer>> q1,
            Queue<Pair<String, Integer>> q2) {
        Set<String> common = new Set2<String>();
        Set<String> keys = new Set2<String>();
        for (int i = 0; i < q1.length(); i++) {
            Pair<String, Integer> temp = q1.dequeue();
            keys.add(temp.key());
            q1.enqueue(temp);
        }
        for (int i = 0; i < q2.length(); i++) {
            Pair<String, Integer> temp = q2.dequeue();
            if (keys.contains(temp.key())) {
                common.add(temp.key());
            } else {
                q2.enqueue(temp);
            }
        }
        return common;
    }

    /**
     * Generates a mapping of experience levels required or desired by employers
     * to their frequencies.
     *
     * @param jobs
     *            array of jobs from file
     * @param indices
     *            indices of students to be examined
     * @param experienceLevels
     *            set of words which indicate experience levels
     * @return map of words to frequency
     */
    public static Map<String, Integer> experienceFrequencyJob(Job[] jobs,
            Set<Integer> indices, Set<String> experienceLevels) {
        Map<String, Integer> frequency = new Map4<String, Integer>();

        if (indices.size() > 0) {
            for (Integer index : indices) {
                //get words from description
                int i = 0;
                boolean isEntryLevel = true;
                String section = jobs[index].requirements();

                while (i < section.length()) {
                    String word = nextWordOrSeparator(section, i);
                    i += word.length();

                    if (!isSep(word.charAt(0))
                            && experienceLevels.contains(word)) {
                        //experience required is not entry-level
                        word = word.toLowerCase();
                        isEntryLevel = false;
                        if (frequency.hasKey(word)) {
                            frequency.replaceValue(word,
                                    frequency.value(word) + 1);
                        } else {
                            frequency.add(word, 1);
                        }

                    }
                }

                if (isEntryLevel) {
                    if (frequency.hasKey("entry level")) {
                        //experience required is not entry-level
                        frequency.replaceValue("entry level",
                                frequency.value("entry level") + 1);
                    } else {
                        frequency.add("entry level", 1);
                    }

                }
            }
        } else {
            int k = 0;
            while (k < jobs.length) {
                //get words from description
                int i = 0;
                boolean isEntryLevel = true;
                String section = jobs[k].requirements();
                while (i < section.length()) {
                    String word = nextWordOrSeparator(section, i);
                    i += word.length();

                    if (!isSep(word.charAt(0))
                            && experienceLevels.contains(word)) {
                        //experience required is not entry-level
                        word = word.toLowerCase();
                        isEntryLevel = false;
                        if (frequency.hasKey(word)) {
                            frequency.replaceValue(word,
                                    frequency.value(word) + 1);
                        } else {
                            frequency.add(word, 1);
                        }

                    }
                }

                if (isEntryLevel) {
                    if (frequency.hasKey("entry level")) {
                        //experience required is not entry-level
                        frequency.replaceValue("entry level",
                                frequency.value("entry level") + 1);
                    } else {
                        frequency.add("entry level", 1);
                    }

                }
                k++;
            }

        }
        return frequency;

    }

    /**
     * Generates a mapping of experience levels of students to their
     * frequencies.
     *
     * @param students
     *            array of students from file
     * @param indices
     *            indices of students to be examined
     * @param experienceLevels
     *            set of words which indicate experience levels
     * @return map of words to frequency
     */
    public static Map<String, Integer> experienceFrequencyStudent(
            Student[] students, Set<Integer> indices,
            Set<String> experienceLevels) {
        Map<String, Integer> frequency = new Map4<String, Integer>();

        if (indices.size() > 0) {
            for (Integer index : indices) {
                //get words from description
                int i = 0;
                boolean isEntryLevel = true;
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
                        + students[index].getJobDescription(2) + " "
                        + students[index].getDegrees() + " "
                        + students[index].getMinor() + " ";

                while (i < section.length()) {
                    String word = nextWordOrSeparator(section, i);
                    i += word.length();

                    if (!isSep(word.charAt(0))
                            && experienceLevels.contains(word)) {
                        //experience required is not entry-level
                        word = word.toLowerCase();
                        isEntryLevel = false;
                        if (frequency.hasKey(word)) {
                            frequency.replaceValue(word,
                                    frequency.value(word) + 1);
                        } else {
                            frequency.add(word, 1);
                        }

                    }
                }

                if (isEntryLevel) {
                    if (frequency.hasKey("entry level")) {
                        //experience required is not entry-level
                        frequency.replaceValue("entry level",
                                frequency.value("entry level") + 1);
                    } else {
                        frequency.add("entry level", 1);
                    }

                }
            }
        } else {
            int k = 0;
            while (k < students.length) {
                //get words from description
                int i = 0;
                boolean isEntryLevel = true;
                String section = students[k].getComputerSkills() + " "
                        + students[k].getLanguageSkills() + " "
                        + students[k].getCourses() + " "
                        + students[k].getPersonalSkills() + " "
                        + students[k].getProjects() + " "
                        + students[k].getJobTitle(0) + " "
                        + students[k].getJobDescription(0) + " "
                        + students[k].getJobTitle(1) + " "
                        + students[k].getJobDescription(1) + " "
                        + students[k].getJobTitle(2) + " "
                        + students[k].getJobDescription(2) + " "
                        + students[k].getDegrees() + " "
                        + students[k].getMinor() + " ";
                while (i < section.length()) {
                    String word = nextWordOrSeparator(section, i);
                    i += word.length();

                    if (!isSep(word.charAt(0))
                            && experienceLevels.contains(word)) {
                        //experience required is not entry-level
                        word = word.toLowerCase();
                        isEntryLevel = false;
                        if (frequency.hasKey(word)) {
                            frequency.replaceValue(word,
                                    frequency.value(word) + 1);
                        } else {
                            frequency.add(word, 1);
                        }

                    }
                }

                if (isEntryLevel) {
                    if (frequency.hasKey("entry level")) {
                        //experience required is not entry-level
                        frequency.replaceValue("entry level",
                                frequency.value("entry level") + 1);
                    } else {
                        frequency.add("entry level", 1);
                    }

                }
                k++;
            }

        }
        return frequency;

    }

    /**
     * Generates a mapping of course listings as listed by employers.
     *
     * @param jobs
     *            array of jobs from file
     * @param indices
     *            indices of jobs to be examined
     * @param courses
     *            set of words which indicate a course
     * @return map of words to frequency
     */
    public static Map<String, Integer> courseFrequencyJob(Job[] jobs,
            Set<Integer> indices, Set<String> courses) {
        Map<String, Integer> frequency = new Map4<String, Integer>();

        if (indices.size() > 0) {
            for (Integer index : indices) {
                //get words from description
                int i = 0;
                String section = jobs[index].requirements() + " "
                        + jobs[index].preferences() + " "
                        + jobs[index].description() + " "
                        + jobs[index].majorsString() + " ";

                while (i < section.length()) {
                    String word = nextWordOrSeparator(section, i);
                    i += word.length();

                    if (!isSep(word.charAt(0)) && courses.contains(word)) {
                        //experience required is not entry-level
                        word = word.toLowerCase();
                        if (frequency.hasKey(word)) {
                            frequency.replaceValue(word,
                                    frequency.value(word) + 1);
                        } else {
                            frequency.add(word, 1);
                        }

                    }
                }

            }
        } else {
            int k = 0;
            while (k < jobs.length) {
                //get words from description
                int i = 0;
                String section = jobs[k].requirements() + " "
                        + jobs[k].preferences() + " " + jobs[k].description()
                        + " " + jobs[k].majorsString() + " ";
                while (i < section.length()) {
                    String word = nextWordOrSeparator(section, i);
                    i += word.length();

                    if (!isSep(word.charAt(0)) && courses.contains(word)) {
                        //experience required is not entry-level
                        word = word.toLowerCase();
                        if (frequency.hasKey(word)) {
                            frequency.replaceValue(word,
                                    frequency.value(word) + 1);
                        } else {
                            frequency.add(word, 1);
                        }

                    }
                }
                k++;
            }
        }
        return frequency;

    }

    /**
     * Generates a mapping of course listings from students to their frequencies
     *
     * @param students
     *            array of students from file
     * @param indices
     *            indices of students to be examined
     * @param courses
     *            set of courses to search for
     * @return map of words to frequency
     */
    public static Map<String, Integer> courseFrequencyStudent(
            Student[] students, Set<Integer> indices, Set<String> courses) {
        Map<String, Integer> frequency = new Map4<String, Integer>();

        if (indices.size() > 0) {
            for (Integer index : indices) {
                //get words from description
                int i = 0;
                String section = students[index].getMajor() + " "
                        + students[index].getMinor() + " "
                        + students[index].getCourses() + " "
                        + students[index].getPersonalSkills() + " "
                        + students[index].getProjects() + " "
                        + students[index].getComputerSkills() + " "
                        + students[index].getLanguageSkills() + " "
                        + students[index].getDegrees() + " ";

                while (i < section.length()) {
                    String word = nextWordOrSeparator(section, i);
                    i += word.length();

                    if (!isSep(word.charAt(0)) && courses.contains(word)) {
                        //experience required is not entry-level
                        word = word.toLowerCase();
                        if (frequency.hasKey(word)) {
                            frequency.replaceValue(word,
                                    frequency.value(word) + 1);
                        } else {
                            frequency.add(word, 1);
                        }

                    }
                }

            }
        } else {
            int k = 0;
            while (k < students.length) {
                //get words from description
                int i = 0;
                String section = students[k].getMajor() + " "
                        + students[k].getMinor() + " "
                        + students[k].getCourses() + " "
                        + students[k].getPersonalSkills() + " "
                        + students[k].getProjects() + " "
                        + students[k].getComputerSkills() + " "
                        + students[k].getLanguageSkills() + " "
                        + students[k].getDegrees() + " ";
                while (i < section.length()) {
                    String word = nextWordOrSeparator(section, i);
                    i += word.length();

                    if (!isSep(word.charAt(0)) && courses.contains(word)) {
                        //experience required is not entry-level
                        word = word.toLowerCase();
                        if (frequency.hasKey(word)) {
                            frequency.replaceValue(word,
                                    frequency.value(word) + 1);
                        } else {
                            frequency.add(word, 1);
                        }

                    }
                }
                k++;
            }
        }
        return frequency;

    }

    public static Queue<Pair<String, Double>> relativeFrequency(
            Queue<Pair<String, Integer>> dataIn) {

        Map<String, Double> relativeFreqMap = new Map4<String, Double>();
        Queue<Pair<String, Double>> dataOut = new Queue2<Pair<String, Double>>();

        int i = 0;
        int dataLength = dataIn.length();
        double sum = 0.0;
        while (i < dataLength) {
            Pair<String, Integer> item = dataIn.dequeue();
            sum = sum + item.value();

            relativeFreqMap.add(item.key(), (double) item.value());
            dataIn.enqueue(item);
            i++;
        }

        i = 0;
        while (i < dataLength) {
            Pair<String, Integer> item = dataIn.dequeue();
            relativeFreqMap.replaceValue(item.key(),
                    relativeFreqMap.value(item.key()) / sum);
            dataOut.enqueue(relativeFreqMap.remove(item.key()));
            dataIn.enqueue(item);
            i++;
        }

        return dataOut;
    }

}
