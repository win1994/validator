package test.bean;

import test.annotation.TestAnnotation;

/**
 * @author XiaoFan
 *
 */
public class TestBean1 {

	@TestAnnotation
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
