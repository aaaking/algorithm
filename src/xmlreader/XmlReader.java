package xmlreader;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XmlReader {
    private static final String ELE_ATTR = "attr";
    private static final String ELE_DECLARE_STYLEABLE = "declare-styleable";

    private XmlReader() {
    }

    private static class Holder {
        private static XmlReader INSTANCE = new XmlReader();
    }

    public static XmlReader get() {
        return XmlReader.Holder.INSTANCE;
    }

    public void formatRepeatAttr(String filepath) {
        try {
            File file = new File("/Users/aaaking/codebase/gitProjects/algorithm/src/xmlreader/attrs.xml");
            File dst = new File(file.getParent(), "new-" + file.getName());
            System.out.println("start format repeat attr file=" + file + " dstexist=" + dst.exists());
            if (dst.exists() && dst.length() > 0) {
                System.out.println("dst file exist no need parse");
                return;
            }
            SAXReader xmlReader = new SAXReader();

            Document doc = xmlReader.read(file);

            final Element rootEle = doc.getRootElement();

            if (rootEle == null) {
                return;
            }

            List<Element> rootAttr = rootEle.elements(ELE_ATTR);
            List<Element> styleableElements = rootEle.elements(ELE_DECLARE_STYLEABLE);

            final Set<StyleAttr> repeatStyleAttr = new HashSet<>();
            final Set<StyleAttr> styleAttrs = new HashSet<>();

            //先查找根节点<attr
            for (Element attr : rootAttr) {
                StyleAttr styleAttr = getStyleAttr(attr);
                if (styleAttr != null) {
                    if (styleAttrs.contains(styleAttr)) {
                        repeatStyleAttr.add(styleAttr);
                    } else {
                        styleAttrs.add(styleAttr);
                    }
                    if (repeatStyleAttr.contains(styleAttr)) {
                        Attribute format = attr.attribute(StyleAttr.ATTR_FORMAT);
                        attr.remove(format);
                    }
                }
            }

            // 查找子节点<declare-styleable
            for (Element declareStyle : styleableElements) {
                List<Element> attrs = declareStyle.elements(ELE_ATTR);
                for (Element attr : attrs) {
                    StyleAttr styleAttr = getStyleAttr(attr);
                    if (styleAttr != null) {
                        if (styleAttrs.contains(styleAttr)) {
                            repeatStyleAttr.add(styleAttr);
                        } else {
                            styleAttrs.add(styleAttr);
                        }
                        if (repeatStyleAttr.contains(styleAttr)) {
                            Attribute format = attr.attribute(StyleAttr.ATTR_FORMAT);
                            attr.remove(format);
                        }
                    }
                }
            }

            XMLWriter writer = new XMLWriter(new FileOutputStream(dst));
            writer.write(doc);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  StyleAttr getStyleAttr(Element element) {
        Attribute name = element.attribute(StyleAttr.ATTR_NAME);
        Attribute format = element.attribute(StyleAttr.ATTR_FORMAT);
        if (name != null && format != null) {
            return new StyleAttr(name.getValue(), format.getValue());
        }
        return null;
    }
}
