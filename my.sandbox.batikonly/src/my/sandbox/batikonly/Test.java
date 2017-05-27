package my.sandbox.batikonly;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(service = Object.class, immediate = true)
public class Test {

	@Activate
	private void runfirst() {
		System.out.println("activate");

	}

}
