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
package ch.elexis.core.print.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Just a simple test handler for testing.
 *
 */
public class TestHandler extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException{
		System.out.println("Test Handler fired!!!!");
		return null;
	}	
}
