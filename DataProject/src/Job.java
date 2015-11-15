import components.map.Map;
import components.map.Map4;
import components.set.Set;
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
     * Relevant majors for Job as a string.
     */
    private String majorsString;

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

        this.majorsString = lineIn;
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

    //------------------- Constructor -----------------------------

    /**
     * constructor for Job Object.
     *
     * @param fileIn
     *            source of Job information
     */
    public Job() {

    }

    //----------------- useful methods ----------------------------

    //------------------ getter methods ---------------------------
    /**
     * Getter method for Job title.
     *
     * @return this.title
     */
    public String title() {
        return this.title;
    }

    /**
     * Getter method for Job description.
     *
     * @return this.description
     */
    public String description() {
        return this.description;
    }

    /**
     * Getter method for Job type.
     *
     * @return this.type
     */
    public String type() {
        return this.type;
    }

    /**
     * Getter method for majors relevant to Job.
     *
     * @return this.majors
     */
    public Map<String, Integer> majors() {
        return this.majors;
    }

    /**
     * Getter method for majors relevant to Job.
     *
     * @return this.majors
     */
    public String majorsString() {
        return this.majorsString;
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
