package junit.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author GovindSingh
 * @category Test
 * @since 2017 This is test class for Constants which is used to test number of constants and private constructor
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test-context.xml" })
public class ConstantsTest {
	private final int TOTAL_NUMBER_OF_CONSTANT = 7;
	@Test
	public void testConstructorIsPrivate() throws Exception {
		Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testNumberOfConstants() throws Exception {
		Field[] fieldList = Constants.class.getDeclaredFields();
		assertThat("Total number of constant does not match", fieldList.length, is(TOTAL_NUMBER_OF_CONSTANT));
	}
}
