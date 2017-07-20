package kukido.snips;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by craser on 7/19/17.
 */
public class Snippet
{
    private String abbreviation;
    private String expansion;

    public Snippet() {
    }

    public Snippet(String abbreviation, String expansion) {
        this.abbreviation = abbreviation;
        this.expansion = expansion;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(abbreviation) || StringUtils.isEmpty(expansion);
    }

    @Override
    public String toString() {
        return "['" + abbreviation + "': '" + expansion + "']";
    }
}
