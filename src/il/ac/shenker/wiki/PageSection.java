package il.ac.shenker.wiki;

import java.util.ArrayList;

/**
 * Created by Amsalem on 30/01/14.
 */
public class PageSection
{
    private String sectionName;
    private String secrionContentInHtml;
    private String pageTitle;
    private String sectionNumber;
    private int indexforQuery;
    private ArrayList<PageSection> subSections = null;

    public PageSection(String sectionName, String secrionContentInHtml, String pageTitle, String sectionNumber, int indexforQuery, ArrayList<PageSection> subSections) {
        this.sectionName = sectionName;
        this.secrionContentInHtml = secrionContentInHtml;
        this.pageTitle = pageTitle;
        this.sectionNumber = sectionNumber;
        this.indexforQuery = indexforQuery;
        this.subSections = subSections;
    }

    public PageSection() {
    }

    public String getSecrionContentInHtml() {
        return secrionContentInHtml;
    }

    public void setSecrionContentInHtml(String secrionContentInHtml) {
        this.secrionContentInHtml = secrionContentInHtml;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public int getIndexforQuery() {
        return indexforQuery;
    }

    public void setIndexforQuery(int indexforQuery) {
        this.indexforQuery = indexforQuery;
    }

    public ArrayList<PageSection> getSubSections() {
        return subSections;
    }

    public void setSubSections(ArrayList<PageSection> subSections) {
        this.subSections = subSections;
    }

    public void appendSubSection(PageSection pageSection)
    {
        if (subSections == null)
            subSections = new ArrayList<PageSection>();
        subSections.add(pageSection);
    }
}
