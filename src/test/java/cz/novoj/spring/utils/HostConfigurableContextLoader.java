package cz.novoj.spring.utils;

import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Applies host dependent properties to loaded application context.
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class HostConfigurableContextLoader extends GenericXmlContextLoader {

	protected void customizeContext(GenericApplicationContext context) {
		super.customizeContext(context);
		context.addBeanFactoryPostProcessor(new HostDependentPropertyPlaceholderConfigurer());
	}

}
