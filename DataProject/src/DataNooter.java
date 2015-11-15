import components.map.Map;
import components.set.Set;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class DataNooter {

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
     */
    public static void main(String[] args) {
        Student[] students = Data.XXX();
        Job[] jobs = Data.XXX();
        Set<Integer> nonGradStudents = Comp.studentsToSearch(false, students,
                2015);
        Set<Integer> gradStudents = Comp.studentsToSearch(true, students, 2015);
        Set<Integer> nonGradJobs = Comp.jobsToSearch(false, jobs);
        Set<Integer> gradJobs = Comp.jobsToSearch(true, jobs);
        Map<String, Integer> gradStudentFrequency = Comp
                .wordFrequencyStudent(students, gradStudents, irrelevantWords);
        Map<String, Integer> nongradStudentFrequency = Comp
                .wordFrequencyStudent(students, nonGradStudents,
                        irrelevantWords);
        Map<String, Integer> gradJobFrequency = Comp.wordFrequencyJob(jobs,
                gradJobs, irrelevantWords);
        Map<String, Integer> nongradJobFrequency = Comp.wordFrequencyJob(jobs,
                nonGradJobs, irrelevantWords);

    }

}
