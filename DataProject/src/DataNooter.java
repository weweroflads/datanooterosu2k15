import java.io.IOException;
import java.util.Iterator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map4;
import components.queue.Queue;
import components.set.Set;
import components.set.Set1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */

public final class DataNooter {

    private static final String SEPARATORS = " \t\n\r,/\"\\?<>:;{}[]|()!@#$%^&*_-+=~.'0123456789";

    private static boolean isSep(char c) {
        return SEPARATORS.indexOf(c) != -1;
    }

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
     * Counts the frequency of major requests among an array of Jobs.
     *
     * @param jobs
     *            array of jobs from file
     * @param indices
     *            indices of jobs to be examined
     * @return Map of major to frequency in data sheet
     */
    public static Map<String, Integer> majorFrequencyJob(Job[] jobs,
            Set<Integer> indices) {
        Map<String, Integer> frequency = new Map4<String, Integer>();

        for (Integer index : indices) {
            //get majors from set
            int i = 0;
            Map<String, Integer> majors = jobs[index].majors();
            Iterator<Pair<String, Integer>> jobMajors = majors.iterator();

            while (jobMajors.hasNext()) {
                Pair<String, Integer> item = jobMajors.next();

                if (!frequency.hasKey(item.key())) {
                    //word is actually a relevant word
                    frequency.add(item.key(), item.value());

                } else {
                    frequency.replaceValue(item.key(),
                            frequency.value(item.key()) + item.value());

                }

            }
        }
        return frequency;

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

    /**
     * Returns a set of Strings relevant to a user-input character: 'e' returns
     * a set of experience levels 'c' returns a set of courses 'w' returns a set
     * of irrelevant words default returns the empty set.
     *
     * @param contents
     *            character value that determines input
     * @return a set with keywords relevant to user input
     */
    public static Set<String> populateSet(char contents) {
        Set<String> set = new Set1L<String>();

        switch (contents) {
            case 'e':
                set.add("phd");
                set.add("master");
                set.add("bachelor");
                set.add("year");
                set.add("years");
                set.add("associate");
                set.add("graduation");
                set.add("undergrad");
                set.add("undergraduate");
                set.add("freshman");
                set.add("sophomore");
                set.add("junior");
                set.add("senior");
                //set.add("standing");
                set.add("ms");
                set.add("ma");
                set.add("experience");
                set.add("bs");
                set.add("ba");
                set.add("certified");
                set.add("degree");
                set.add("training");
                set.add("trained");
                set.add("enrollment");
                set.add("enrolled");
                set.add("internship");
                set.add("intern");
                set.add("honors");
                set.add("leadership");
                set.add("leader");
                set.add("lead");
                set.add("student");
                set.add("professional");
                set.add("previous");
                set.add("staff");
                set.add("manager");
                break;

            case 'c':
                set.add("economics");
                set.add("economy");
                set.add("economic");
                set.add("actuarial");
                set.add("science");
                set.add("physics");
                set.add("engineering");
                set.add("mathematics");
                set.add("data");
                set.add("analytics");
                set.add("analysis");
                set.add("statistics");
                set.add("statistical");
                set.add("linear");
                set.add("algebra");
                set.add("calculus");
                set.add("differential");
                set.add("agriculture");
                set.add("french");
                set.add("spanish");
                set.add("german");
                set.add("japanese");
                set.add("chinese");
                set.add("arabic");
                set.add("language");
                set.add("linguistics");
                set.add("design");
                set.add("electrical");
                set.add("biology");
                set.add("biological");
                set.add("chemistry");
                set.add("chemical");
                set.add("software");
                set.add("programming");
                set.add("english");
                set.add("written");
                set.add("writing");
                set.add("finance");
                set.add("financial");
                set.add("international");
                set.add("studies");
                set.add("sociology");
                set.add("psychology");
                set.add("health");
                set.add("business");
                set.add("government");
                set.add("political");
                set.add("politics");
                set.add("communication");
                set.add("communications");
                set.add("anthropology");
                set.add("information");
                set.add("law");
                set.add("accounting");
                set.add("aeronautical");
                set.add("astronomy");
                set.add("astronomical");
                set.add("matlab");
                set.add("java");
                set.add("c");
                set.add("r");
                set.add("microsoft");
                set.add("excel");
                set.add("powerpoint");
                set.add("access");
                set.add("solidworks");
                set.add("autocad");
                set.add("spreadsheets");
                set.add("html");
                set.add("xml");
                set.add("web");

                break;

            case 'w':

                set.add("and");
                set.add("a");
                set.add("an");
                set.add("apply");
                set.add("above");
                set.add("at");
                set.add("about");
                set.add("around");
                set.add("additional");
                set.add("are");
                set.add("as");
                set.add("associated");
                set.add("along");
                set.add("alongside");

                set.add("be");
                set.add("basis");
                set.add("based");
                set.add("base");
                set.add("before");
                set.add("below");
                set.add("between");
                set.add("by");

                set.add("can");
                set.add("could");
                set.add("com");
                set.add("create");

                set.add("date");
                set.add("duties");
                set.add("during");
                set.add("department");
                set.add("demonstrate");

                set.add("edu");
                set.add("each");
                set.add("examine");
                set.add("examined");
                set.add("event");
                set.add("events");
                set.add("ensure");

                set.add("for");
                set.add("from");

                set.add("go");
                set.add("given");
                set.add("get");
                set.add("gov");

                set.add("high");
                set.add("have");
                set.add("happen");
                set.add("http");
                set.add("https");

                set.add("i");
                set.add("is");
                set.add("in");
                set.add("inc");
                set.add("including");

                set.add("job");
                set.add("jobs");

                set.add("low");

                set.add("may");
                set.add("my");
                set.add("meet");
                set.add("month");
                set.add("might");
                set.add("must");
                set.add("member");
                set.add("members");

                set.add("no");
                set.add("not");
                set.add("non");
                set.add("new");
                set.add("next");

                set.add("of");
                set.add("off");
                set.add("on");
                set.add("out");
                set.add("or");
                set.add("ohio");
                set.add("our");
                set.add("over");
                set.add("obtain");
                set.add("organization");
                set.add("opportunity");

                set.add("part");
                set.add("particular");
                set.add("particularly");
                set.add("please");
                set.add("profile");
                set.add("program");

                set.add("quickly");

                set.add("re");
                set.add("result");
                set.add("results");
                set.add("related");

                set.add("s");//contractions
                set.add("state");
                set.add("such");

                set.add("t");//contractions
                set.add("th");
                set.add("to");
                set.add("the");
                set.add("then");
                set.add("than");
                set.add("that");
                set.add("there");
                set.add("their");
                set.add("they");
                set.add("this");
                set.add("time");
                set.add("through");
                set.add("towards");

                set.add("up");
                set.add("upon");
                set.add("use");
                set.add("used");
                set.add("uses");

                set.add("was");
                set.add("worked");
                set.add("we");
                set.add("what");
                set.add("with");
                set.add("within");
                set.add("without");
                set.add("will");
                set.add("www");

                set.add("you");
                set.add("your");

                break;

            default:
                break;
        }

        return set;
    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private DataNooter() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Student[] students = Data.processStudentData();
        Job[] jobs = Employer.employer();
        Set<Integer> nonGradStudents = Comp.studentsToSearch(false, students,
                2015);
        Set<Integer> gradStudents = Comp.studentsToSearch(true, students, 2015);
        Set<Integer> nonGradJobs = Comp.jobsToSearch(false, jobs);
        Set<Integer> gradJobs = Comp.jobsToSearch(true, jobs);

        Set<String> irrelevantWords = populateSet('w');

        //This shit does the keywords comparison
        Map<String, Integer> gradStudentFrequency = Comp
                .wordFrequencyStudent(students, gradStudents, irrelevantWords);
        Map<String, Integer> nonGradStudentFrequency = Comp
                .wordFrequencyStudent(students, nonGradStudents,
                        irrelevantWords);
        Map<String, Integer> gradJobFrequency = Comp.wordFrequencyJob(jobs,
                gradJobs, irrelevantWords);
        Map<String, Integer> nonGradJobFrequency = Comp.wordFrequencyJob(jobs,
                nonGradJobs, irrelevantWords);
        Queue<Pair<String, Integer>> tenGradStudent = Comp
                .topTen(gradStudentFrequency);
        Queue<Pair<String, Integer>> tenNonGradStudent = Comp
                .topTen(nonGradStudentFrequency);
        Queue<Pair<String, Integer>> tenGradJob = Comp.topTen(gradJobFrequency);
        Queue<Pair<String, Integer>> tenNonGradJob = Comp
                .topTen(nonGradJobFrequency);
        Set<String> commonGradKey = Comp.commonItems(tenGradStudent,
                tenGradJob);
        Set<String> commonNonGradKey = Comp.commonItems(tenNonGradStudent,
                tenNonGradJob);
        //Put In Drawing Here

        Set<String> expLevels = populateSet('e');
        //This shit does the experience comparison
        Map<String, Integer> gradStudentExp = Comp
                .experienceFrequencyStudent(students, gradStudents, expLevels);
        Map<String, Integer> nonGradStudentExp = Comp
                .experienceFrequencyStudent(students, nonGradStudents,
                        expLevels);
        Map<String, Integer> gradJobExp = Comp.experienceFrequencyJob(jobs,
                gradJobs, expLevels);
        Map<String, Integer> nonGradJobExp = Comp.experienceFrequencyJob(jobs,
                nonGradJobs, expLevels);
        Queue<Pair<String, Integer>> tenGradExp = Comp.topTen(gradStudentExp);
        Queue<Pair<String, Integer>> tenNonGradExp = Comp
                .topTen(nonGradStudentExp);
        Queue<Pair<String, Integer>> tenGradJobExp = Comp.topTen(gradJobExp);
        Queue<Pair<String, Integer>> tenNonGradJobExp = Comp
                .topTen(nonGradJobExp);
        Set<String> commonGradExp = Comp.commonItems(tenGradExp, tenGradJobExp);
        Set<String> commonNonGradExp = Comp.commonItems(tenNonGradExp,
                tenNonGradJobExp);
        //Put in drawing here

        Set<String> courses = populateSet('c');
        //This does the courses comparison
        Map<String, Integer> gradStudentCourses = Comp
                .courseFrequencyStudent(students, gradStudents, courses);
        Map<String, Integer> nonGradStudentCourses = Comp
                .courseFrequencyStudent(students, nonGradStudents, courses);
        Map<String, Integer> gradJobCourses = Comp.courseFrequencyJob(jobs,
                gradJobs, courses);
        Map<String, Integer> nonGradJobCourses = Comp.courseFrequencyJob(jobs,
                nonGradJobs, courses);
        Queue<Pair<String, Integer>> tenGradCourses = Comp
                .topTen(gradStudentCourses);
        Queue<Pair<String, Integer>> tenNonGradCourses = Comp
                .topTen(nonGradStudentCourses);
        Queue<Pair<String, Integer>> tenGradJobCourses = Comp
                .topTen(gradJobCourses);
        Queue<Pair<String, Integer>> tenNonGradJobCourses = Comp
                .topTen(nonGradJobCourses);
        Set<String> commonGradCourses = Comp.commonItems(tenGradCourses,
                tenGradJobCourses);
        Set<String> commonNonGradCourses = Comp.commonItems(tenNonGradCourses,
                tenNonGradJobCourses);
        //Put in drawing here
    }

}
