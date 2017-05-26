package my.sandbox.report.svgtest.png;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.renderers.DataRenderable;
import net.sf.jasperreports.renderers.SimpleDataRenderer;

@Component(service = Object.class, immediate = true)
public class TestSvgCreator {
	public TestSvgCreator() {

	}

	@Activate
	private void test() {
		System.out.println("run - png");

		try {
			InputStream is = TestSvgCreator.class.getResourceAsStream("test.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);

			JasperPrint jp = JasperFillManager.fillReport(jasperReport, null, new JRDataSource() {

				int i = 0;

				@Override
				public boolean next() throws JRException {
					i++;
					return i < 2;
				}

				@Override
				public DataRenderable getFieldValue(JRField arg0) throws JRException {
					InputStream inputStream = TestSvgCreator.class.getResourceAsStream("png.png");

					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int read = 0;
					try {
						while ((read = inputStream.read(buffer, 0, buffer.length)) != -1) {
							baos.write(buffer, 0, read);
						}
						baos.flush();
					} catch (IOException e) {

						e.printStackTrace();
					}

					return SimpleDataRenderer.getInstance(baos.toByteArray());
				}
			});

			byte[] data = JasperExportManager.exportReportToPdf(jp);
			Path p = Files.createTempFile("filesImage", ".pdf");
			FileOutputStream fos = new FileOutputStream(p.toFile());
			fos.write(data);
			fos.flush();
			fos.close();
			Desktop.getDesktop().open(p.toFile());

			System.out.println("end - png");
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}
}
