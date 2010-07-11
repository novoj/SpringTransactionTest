package cz.novoj.dao.mysql;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import cz.novoj.spring.utils.HostConfigurableContextLoader;

/**
 * Description
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
				"classpath:spring/spring-datasource.xml",
				"classpath:spring/spring-dao.xml"
				},
		loader = HostConfigurableContextLoader.class
)
@Transactional
public abstract class AbstractDaoTest {

}
