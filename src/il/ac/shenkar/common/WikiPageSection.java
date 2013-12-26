package il.ac.shenkar.common;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 12/26/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class WikiPageSection
{
    private String sectionTitle;
    private String sectionContent;

    public WikiPageSection(String sectionContent, String sectionTitle)
    {
        this.sectionContent = sectionContent;
        this.sectionTitle = sectionTitle;
    }

    public WikiPageSection()
    {
    }

    public String getSectionTitle()
    {
        return sectionTitle;
    }

    public String getSectionContent()
    {
        return sectionContent;
    }

    public void setSectionContent(String sectionContent)
    {
        this.sectionContent = sectionContent;
    }

    public void setSectionTitle(String sectionTitle)
    {
        this.sectionTitle = sectionTitle;
    }
}
