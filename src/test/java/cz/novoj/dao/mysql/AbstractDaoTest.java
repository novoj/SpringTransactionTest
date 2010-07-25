package cz.novoj.dao.mysql;

import cz.novoj.spring.utils.HostConfigurableContextLoader;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

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
	@Autowired(required = true)
	@Qualifier("dataSource")
	DataSource dataSource;

	@Before
	public void init() throws IOException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Resource res = new ClassPathResource("/sqldump.sql");
		InputStream is = res.getInputStream();
		try {
			String script = IOUtils.toString(is, "utf-8");
			String[] sqls = StringUtils.tokenizeToStringArray(script, ";");
			for(String sql : sqls) {
				jdbcTemplate.execute(sql);
			}
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

}
