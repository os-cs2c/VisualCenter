/**
 * 
 */
package org.cs2c.vcenter.views.models;

import java.util.List;

import org.cs2c.nginlib.MiddlewareFactory;

/**
 * @author Administrator
 *
 */
public class ModuleElement extends TreeElement implements IModules {

	/**
	 * @param parent
	 */
	public ModuleElement(TreeElement parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TreeElement> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return false;
	}


}
