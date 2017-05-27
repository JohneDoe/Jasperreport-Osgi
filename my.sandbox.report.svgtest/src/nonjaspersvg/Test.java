package nonjaspersvg;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.svg.SVGDocumentFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.w3c.dom.svg.SVGDocument;

@Component(service = Object.class, immediate = true)
public class Test {

	byte[] svg = "<svg height=\"100\" width=\"100\"><circle cx=\"50\" cy=\"50\" r=\"40\" fill=\"red\" /></svg>"
			.getBytes();

	@Activate
	private void runfirst() {
		System.out.println("first");

	}

	public void runBatik() {
		System.out.println("Start");

		UserAgent userAgent = new UserAgentAdapter();
		SVGDocumentFactory documentFactory = new SAXSVGDocumentFactory(userAgent.getXMLParserClassName(), true);
		documentFactory.setValidating(userAgent.isXMLParserValidating());

		try {
			SVGDocument document = documentFactory.createSVGDocument(null, new ByteArrayInputStream(svg));
			System.out.println(document.getInputEncoding());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("end");
	}
}
