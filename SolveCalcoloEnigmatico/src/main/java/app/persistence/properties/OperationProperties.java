package app.persistence.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:operation.properties")
@ConfigurationProperties(prefix="operation")
public class OperationProperties {

	private String property1;
	
	private String property2;
	
	public OperationProperties() {
		
	}

	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}

	public String getProperty2() {
		return property2;
	}

	public void setProperty2(String property2) {
		this.property2 = property2;
	}

	@Override
	public String toString() {
		return "OperationProperties [property1=" + property1 + ", property2=" + property2 + "]";
	}

}
