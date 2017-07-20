package kukido.snips;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by craser on 7/19/17.
 */
public class TextExpanderParser extends DefaultHandler
{
    private Stack<String> state;
    private StringBuffer chars;
    private List<Snippet> snippets;
    private Snippet snippet;
    private String key;

    public TextExpanderParser() {
        this.state = state = new Stack<String>();
        this.chars = chars = new StringBuffer();
        this.snippets = new ArrayList<Snippet>();
    }

    public List<Snippet> parse(InputStream in) throws IOException, SAXException {
        XMLReader reader = new SAXParser();
        reader.setContentHandler(this);
        reader.parse(new InputSource(in));

        return snippets;
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        state.push(name);
        if ("dict".equals(name)) {
            snippet = new Snippet();
        }
    }

    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        String currentState = state.pop();
        String val = StringEscapeUtils.unescapeXml(chars.toString().trim());
        chars = new StringBuffer();

        if ("dict".equals(currentState)) {
            if (snippet != null) {
                if (!snippet.isEmpty()) {
                    snippets.add(snippet);
                }
                snippet = null;
            }
        }
        else if ("key".equals(currentState)) {
            key = val;
        }
        else if ("string".equals(currentState)) {
            if ("abbreviation".equals(key)) {
                if (snippet != null) {
                    snippet.setAbbreviation(val);
                }
            }
            else if ("plainText".equals(key)) {
                if (snippet != null) {
                    snippet.setExpansion(val);
                }
            }
        }
    }

    @Override
    public void characters(char[] buff, int start, int len) throws SAXException {
        chars.append(buff, start, len);
    }
}

