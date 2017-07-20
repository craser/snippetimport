package kukido.snips;

import org.xml.sax.SAXException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by craser on 7/19/17.
 */
public class Main
{
    static public void main(String[] args) throws IOException, SAXException, InterruptedException {
        FileInputStream in = new FileInputStream("/Users/craser/Downloads/Settings.textexpander");
        TextExpanderParser parser = new TextExpanderParser();
        List<Snippet> snips = parser.parse(in);
        for (Snippet snip : snips) {
            String[] command = new String[] {
                    "albind",
                    snip.getAbbreviation(),
                    snip.getExpansion()
            };
            Runtime.getRuntime().exec(command).waitFor();
        }
    }
}
