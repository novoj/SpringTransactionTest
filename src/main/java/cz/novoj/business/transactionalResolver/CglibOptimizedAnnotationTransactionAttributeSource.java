package cz.novoj.business.transactionalResolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.lang.reflect.Method;

/**
 * Description
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class CglibOptimizedAnnotationTransactionAttributeSource extends AnnotationTransactionAttributeSource {
	private static final Log log = LogFactory.getLog(CglibOptimizedAnnotationTransactionAttributeSource.class);

	@Override
	protected TransactionAttribute findTransactionAttribute(Method method) {
		Class<?> declaringClass = method.getDeclaringClass();
		if (AopUtils.isCglibProxyClass(declaringClass)) {
			try {
				//find appropriate method on parent class
				Method superMethod = declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes());
				return super.findTransactionAttribute(superMethod);
			} catch (Exception ex) {
				if(log.isWarnEnabled()) {
					log.warn("Cannot find superclass method for Cglib method: " + method.toGenericString());
				}
				return super.findTransactionAttribute(method);
			}
		} else {
			return super.findTransactionAttribute(method);
		}
	}

}
