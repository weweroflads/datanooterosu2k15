import java.io.IOException;

import org.jfree.chart.demo.LineChartDemo1;

import components.map.Map;
import components.map.Map.Pair;
import components.queue.Queue;
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

        Set<String> irrelevantWords = Comp.populateSet('w');

        //The keywords comparison
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
        Queue<Pair<String, Double>> relGradStudent = Comp
                .relativeFrequency(tenGradStudent);
        Queue<Pair<String, Double>> relNonGradStudent = Comp
                .relativeFrequency(tenNonGradStudent);
        Queue<Pair<String, Double>> relGradJob = Comp
                .relativeFrequency(tenGradJob);
        Queue<Pair<String, Double>> relNonGradJob = Comp
                .relativeFrequency(tenNonGradJob);
        LineChartDemo1.urmums(relGradJob, relGradStudent);
        LineChartDemo1.urmums(relNonGradJob, relNonGradStudent);

        Set<String> expLevels = Comp.populateSet('e');
        //The experience comparison
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
        Queue<Pair<String, Double>> relGradStudentExp = Comp
                .relativeFrequency(tenGradExp);
        Queue<Pair<String, Double>> relNonGradStudentExp = Comp
                .relativeFrequency(tenNonGradExp);
        Queue<Pair<String, Double>> relGradJobExp = Comp
                .relativeFrequency(tenGradJobExp);
        Queue<Pair<String, Double>> relNonGradJobExp = Comp
                .relativeFrequency(tenNonGradJobExp);
        LineChartDemo1.urmums(relGradJobExp, relGradStudentExp);
        LineChartDemo1.urmums(relNonGradJobExp, relNonGradStudentExp);

        Set<String> courses = Comp.populateSet('c');
        //The courses comparison
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
        Queue<Pair<String, Double>> relGradStudentCourses = Comp
                .relativeFrequency(tenGradCourses);
        Queue<Pair<String, Double>> relNonGradStudentCourses = Comp
                .relativeFrequency(tenNonGradCourses);
        Queue<Pair<String, Double>> relGradJobCourses = Comp
                .relativeFrequency(tenGradJobCourses);
        Queue<Pair<String, Double>> relNonGradJobCourses = Comp
                .relativeFrequency(tenNonGradJobCourses);
        LineChartDemo1.urmums(relGradJobCourses, relGradStudentCourses);
        LineChartDemo1.urmums(relNonGradJobCourses, relNonGradStudentCourses);

        Set<String> skills = Comp.populateSet('s');
        //Skills comparison
        Map<String, Integer> gradStudentSkill = Comp
                .skillFrequencyStudent(students, gradStudents, skills);
        Map<String, Integer> nonGradStudentSkill = Comp
                .skillFrequencyStudent(students, nonGradStudents, skills);
        Map<String, Integer> gradJobSkill = Comp.skillFrequencyStudent(jobs,
                gradJobs, skills);
        Map<String, Integer> nonGradJobSkill = Comp.skillFrequencyStudent(jobs,
                nonGradJobs, skills);
        Queue<Pair<String, Integer>> tenGradSkill = Comp
                .topTen(gradStudentSkill);
        Queue<Pair<String, Integer>> tenNonGradSkill = Comp
                .topTen(nonGradStudentSkill);
        Queue<Pair<String, Integer>> tenGradJobSkill = Comp
                .topTen(gradJobSkill);
        Queue<Pair<String, Integer>> tenNonGradJobSkill = Comp
                .topTen(nonGradJobSkill);
        Queue<Pair<String, Double>> relGradSkill = Comp
                .relativeFrequency(tenGradSkill);
        Queue<Pair<String, Double>> relNonGradSkill = Comp
                .relativeFrequency(tenNonGradSkill);
        Queue<Pair<String, Double>> relGradJobSkill = Comp
                .relativeFrequency(tenGradJobSkill);
        Queue<Pair<String, Double>> relNonGradJobSkill = Comp
                .relativeFrequency(tenNonGradJobSkill);
        LineChartDemo1.urmums(relGradJobSkill, relGradSkill);
        LineChartDemo1.urmums(relNonGradJobSkill, relNonGradSkill);
    }

}
