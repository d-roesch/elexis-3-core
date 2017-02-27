/*******************************************************************************
 * Copyright (c) 2017 vality GmbH
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Daniel Rösch, vality GmbH - initial implementation
 *     
 ******************************************************************************/

package ch.elexis.core.print.sourceprovider;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

import ch.elexis.core.data.activator.CoreHub;

/**
 * Provides the current active print plugin to
 * distinguish, which handler is executed.
 * 
 * @author Daniel Rösch, vality GmbH
 *
 */
public class PrintSourceProvider extends AbstractSourceProvider {
	public final static String PRINT_PLUGIN =
		"ch.elexis.core.print.sourceprovider.printplugin";
	
	@Override
	public void dispose(){
		// Auto-generated method stub to fulfill the contract
	}
	
	@Override
	public Map getCurrentState(){
		String printingPlugin = CoreHub.localCfg.get("print/plugin", null);
		Map<String, Object> map = new HashMap<String, Object>(1);
		
		if (printingPlugin == null) {
			map.put(PRINT_PLUGIN, "Default");
			return map;
		} else if (printingPlugin.isEmpty()) {
			map.put(PRINT_PLUGIN, "Default");
			return map;
		}
		map.put(PRINT_PLUGIN, printingPlugin);
		return map;
	}
	
	@Override
	public String[] getProvidedSourceNames(){
		return new String[] {
			PRINT_PLUGIN
		};
	}
	
	public void togglePlugin() {
		String value = CoreHub.localCfg.get("print/plugin", null);
		fireSourceChanged(ISources.WORKBENCH, PRINT_PLUGIN, value);
		
	}
	
}
