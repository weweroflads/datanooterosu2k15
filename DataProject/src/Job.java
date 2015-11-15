import components.map.Map;
import components.map.Map;
import components.map.Map4;
import components.set.Set;
import components.set.Set;
import components.set.Set1L;
import components.set.Set1L;

/**
 * Data class to hold information about job positions
 *
 * @author Adam Ovak
 */
public class Job {

    private static final String SEPARATORS = " \t\n\r,/\"\\?<>:;{}[]|()!@#$%^&*_-+=~.0123456789";

    /**
     * Title of Job.
     */
    private String title;

    /**
     * Description of Job.
     */
    private String description;

    /**
     * Job Type.
     */
    private String type;

    /**
     * Relevant majors for Job.
     */
    private Map<String, Integer> majors = new Map4<String, Integer>();

    /**
     * 'Required' Job Qualifications.
     */
    private String requirements;

    /**
     * 'Not Required' Job Qualifications.
     */
    private String preferences;

    /**
     * Set of keywords/phrase that indicate a qualification is 'required'.
     */
    private Set<String> requiredIndicators = new Set1L<String>();

    /**
     * Set of keywords/phrase that indicate a qualification is 'not required'.
     */
    private Set<String> preferredIndicators = new Set1L<String>();

    /**
     * Set of 'Significant' title keywords
     */
    private Set<String> titleIndicators = new Set1L<String>();

    //----------------------- Helper Methods -------------------------

    /**
     * Fills in data members relevant to Job object
     *
     * @param dataIn
     *            Array of 5 Strings containing data relevant to 'columns'
     *
     * @requires dataIn.length >= 5;
     *
     */
    public void populateMembers(String[] dataIn) {
        this.populateTitle(dataIn[0]);
        this.populateDescription(dataIn[1]);
        this.populateType(dataIn[2]);
        this.populateMajors(dataIn[3]);
        this.populateQualifications(dataIn[4]);
    }

    /**
     * Fills in title with relevant information.
     *
     * @param lineIn
     *            String containing job title
     */
    private void populateTitle(String lineIn) {
        // TODO Auto-generated method stub
        this.title = lineIn;
    }

    /**
     * Fills in description with relevant information.
     *
     * @param lineIn
     *            String containing job description
     */
    private void populateDescription(String lineIn) {
        // TODO Auto-generated method stub
        this.description = lineIn;
    }

    /**
     * Fills in type with relevant information.
     *
     * @param lineIn
     *            String containing job type
     */
    private void populateType(String lineIn) {
        // TODO Auto-generated method stub

        this.type = lineIn;

    }

    /**
     * Fills in Majors with relevant information.
     *
     *
     * @param lineIn
     *            source of Job information
     */
    private void populateMajors(String lineIn) {
        // TODO Auto-generated method stub
        int i = 0;
        while (i < lineIn.length() && lineIn.indexOf(",") != -1) {
            String major = lineIn.substring(i, lineIn.indexOf(","));

            this.majors.add(major, 1);

            i = lineIn.indexOf(",") + 1;
        }
    }

    /**
     * Fills in qualifications (requirements and preferences) with relevant
     * information.
     *
     *
     * @param lineIn
     *            source of Job qualifications
     */
    private void populateQualifications(String lineIn) {
        // TODO Auto-generated method stub

        this.requirements = lineIn;
        this.preferences = "";
    }

    /**
     * Populates indicator sets.
     */
    private void establishIndicators() {
        this.requiredIndicators.add("MUST BE");
        this.requiredIndicators.add("ARE REQUIRED");
        this.requiredIndicators.add("NEED");
        this.requiredIndicators.add("REQUIRED");
        this.requiredIndicators.add("MINIMUM");
        this.requiredIndicators.add("BASIC");

        this.preferredIndicators.add("PREFERRED");
        this.preferredIndicators.add("NOT REQUIRED");

        this.titleIndicators.add("Analyst");
        this.titleIndicators.add("Scientist");

    }

    //------------------- Constructor -----------------------------

    /**
     * constructor for Job Object.
     *
     * @param fileIn
     *            source of Job information
     */
    public Job() {
        this.establishIndicators();
    }

    //----------------- useful methods ----------------------------

    //------------------ getter methods ---------------------------
    /**
     * Getter method for Job title.
     *
     * @return this.title
     */
    public final String title() {
        return this.title;
    }

    /**
     * Getter method for Job description.
     *
     * @return this.description
     */
    public final String description() {
        return this.description;
    }

    /**
     * Getter method for Job type.
     *
     * @return this.type
     */
    public final String type() {
        return this.type;
    }

    /**
     * Getter method for majors relevant to Job.
     *
     * @return this.majors
     */
    public final Map<String, Integer> majors() {
        return this.majors;
    }

    /**
     * Getter method for Job requirements.
     *
     * @return this.requirements
     */
    public String requirements() {
        return this.requirements;
    }

    /**
     * Getter method for Job preferences.
     *
     * @return this.preferences
     */
    public String preferences() {
        return this.preferences;
    }

}