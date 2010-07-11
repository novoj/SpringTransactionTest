package cz.novoj.spring.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class HostDependentPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private static Log log = LogFactory.getLog(HostDependentPropertyPlaceholderConfigurer.class);
	private String classPathBase ="conf/";
	private String resourceLocationPrefix = "populate";

	public HostDependentPropertyPlaceholderConfigurer() {
		super();
		Resource resource = getPropertyResource();
		if (resource != null) {
			super.setLocation(resource);
		}
	}

	public HostDependentPropertyPlaceholderConfigurer(String classPathBase, String resourceLocationPrefix) {
		this.classPathBase = classPathBase;
		this.resourceLocationPrefix = resourceLocationPrefix;
		Resource resource = getPropertyResource();
		if (resource != null) {
			super.setLocation(resource);
		}
	}

	private Resource getPropertyResource() {
		String[] configNames = getAllPossibleConfigNames(resourceLocationPrefix, "properties");
		for(String configName : configNames) {
			//get full name
			String customFileName = classPathBase + configName;
			Resource resource = new ClassPathResource(customFileName);

			if(log.isDebugEnabled()) {
				log.debug("Trying to load property configuration source from classpath: " + customFileName);
			}

			//if found return
			if(resource.exists()) {
				if(log.isInfoEnabled()) {
					log.info("Found property configuration source " + customFileName + " in classpath!");
				}
				return resource;
			}
			else {
				if(log.isInfoEnabled()) {
					log.info("Property configuration source " + customFileName + " not found in classpath!");
				}
			}
		}

		if(log.isErrorEnabled()) {
			log.error("No property configuration source found in classpath!");
		}

		return null;
	}

	private static String[] getAllPossibleConfigNames(String baseName, String suffix) {
		String[] result = new String[2];

		result[0] = baseName + "-" + getLocalHostname() + "." + suffix;
		result[1] = baseName + "." + suffix;

		return result;
	}

	/**
	 * Get machine hostname.
	 *
	 * @return
	 */
	private static String getLocalHostname() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostName();
		}
		catch(UnknownHostException e) {
			return null;
		}
	}

}
