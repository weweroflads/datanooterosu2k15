/**
 *
 * Class to store data about students.
 *
 * @author Michael Bayless
 */
public class Student {

    /**
     * The student's major.
     */
    private String major;

    /**
     * The student's minor or specialization.
     */
    private String minor;

    /**
     * The student's graduation year.
     */
    private int graduationYear;

    /**
     * The student's graduation month, of the specified year.
     */
    private int graduationMonth;

    /**
     * The student's prior degrees.
     */
    private String degrees;

    /**
     * An array of string. Even indexed entries contain position titles. Odd
     * indexed entries contain position descriptions.
     */
    private String[] employment;

    /**
     * The number of pairs of entries in employment field above.
     */
    private int jobs;

    /**
     * The student's computing experience.
     */
    private String computerSkills;

    /**
     * The student's language skills.
     */
    private String languageSkills;

    /**
     * The student's listed personal skills.
     */
    private String personalSkills;

    /**
     * The student's specified listed courses.
     */
    private String courses;

    /**
     * The student's listed projects.
     */
    private String projects;

    public Student() {
        this.employment = new String[6];
    }

    /**
     * To set major.
     *
     * @param x
     *            the major
     */
    public final void setMajor(String x) {
        this.major = x;
    }

    /**
     * To get major.
     *
     * @return the major of student.
     */
    public final String getMajor() {
        return this.major;
    }

    /**
     * To set minor.
     *
     * @param x
     *            the minor
     */
    public final void setMinor(String x) {
        this.minor = x;
    }

    /**
     * To get minor.
     *
     * @return the minor of student.
     */
    public final String getMinor() {
        return this.minor;
    }

    /**
     * To set the graduation year. Should be single integer year value.
     *
     * @param x
     *            the year
     */
    public final void setGradYear(int x) {
        this.graduationYear = x;
    }

    /**
     * To get graduation year.
     *
     * @return the graduation year.
     */
    public final int getGradYear() {
        return this.graduationYear;
    }

    /**
     * To set the graduation Month. Should be single integer Month value.
     *
     * @param x
     *            the Month
     */
    public final void setGradMonth(int x) {
        this.graduationMonth = x;
    }

    /**
     * To get graduation year.
     *
     * @return the graduation month.
     */
    public final int getGradMonth() {
        return this.graduationMonth;
    }

    /**
     * To set previous degrees.
     *
     * @param x
     *            the major
     */
    public final void setMajor(String x) {
        this.major = x;
    }

    /**
     * To get major.
     *
     * @return the major of student.
     */
    public final String getMajor() {
        return this.major;
    }

    /**
     * To add an entry in the employment array.
     *
     * @param
     */
    public final int setJobEntry(String title, String description) {
        int entered = 1;
        for (int i = 0; i < 4; i += 2) {
            if (!this.employment[i].equals("")) {
                this.employment[i] = title;
                this.employment[i + 1] = description;
                entered = 0;
                this.jobs++;
            }
        }
        return entered;
    }

    /**
     * Get a job title pertaining to a given number of job in the array.
     *
     * @param index
     *            0-2 possible.
     * @return The title of given job.
     */
    public final String getJobTitle(int index) {
        return this.employment[2 * index];
    }

    /**
     * Get a job description pertaining to a given number of job in the array.
     *
     * @param index
     *            0-2 possible.
     * @return The description of given job.
     */
    public final String getJobDescription(int index) {
        return this.employment[2 * index + 1];
    }

    /**
     * Get a number of jobs for the student.
     *
     * @return The number of jobs.
     */
    public final int getJobs() {
        return this.jobs;
    }

    /**
     *
     */

}
