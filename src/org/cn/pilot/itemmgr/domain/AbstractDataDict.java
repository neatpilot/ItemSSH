package org.cn.pilot.itemmgr.domain;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:40:41 PM ] -->
 */
public abstract class AbstractDataDict {
	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
