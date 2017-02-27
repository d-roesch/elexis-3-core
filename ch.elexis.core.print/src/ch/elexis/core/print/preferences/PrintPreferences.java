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

package ch.elexis.core.print.preferences;

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.services.ISourceProviderService;

import ch.elexis.core.data.activator.CoreHub;
import ch.elexis.core.data.util.Extensions;
import ch.elexis.core.print.Messages;
import ch.elexis.core.print.sourceprovider.PrintSourceProvider;
import ch.elexis.core.ui.preferences.SettingsPreferenceStore;

/**
 * A preference page for the administration of the print plugin settings.
 * 
 * @author Daniel Rösch, vality GmbH
 *
 */
public class PrintPreferences extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {
	
	public PrintPreferences(){
		super(GRID);
		// Make sure we persist the configuration 
		setPreferenceStore(new SettingsPreferenceStore(CoreHub.localCfg));
		setDescription("\n" + Messages.getString("PreferencePage.title.description") + "\n"); //$NON-NLS-1$
	}
	
	@Override
	protected void createFieldEditors(){
		
		List<IConfigurationElement> list = Extensions.getExtensions("ch.elexis.core.print"); //$NON-NLS-1$
		String[][] rows = new String[list.size() + 1][];
		int i = 1;
		
		rows[0] = new String[2];
		rows[0][1] = "Default"; //$NON-NLS-1$
		rows[0][0] = "0 : Default"; //$NON-NLS-1$
		
		for (IConfigurationElement ice : list) {
			rows[i] = new String[2];
			//Set the value of the Radio Button
			rows[i][1] = ice.getAttribute("name"); //$NON-NLS-1$
			//Set the label of the Radio Button
			rows[i][0] = Integer.toString(i) + " : " + rows[i][1]; //$NON-NLS-1$
			i += 1;
		}
		addField(new RadioGroupFieldEditor("print/plugin",
			Messages.getString("PreferencePage.RadioGroup.description"), 2, rows,
			getFieldEditorParent())); //$NON-NLS-1$
	}
	
	@Override
	public boolean performOk(){
		super.performOk();
		ISourceProviderService service = (ISourceProviderService) PlatformUI.getWorkbench()
			.getService(ISourceProviderService.class);
		PrintSourceProvider printingSourceProvider =
			(PrintSourceProvider) service.getSourceProvider(PrintSourceProvider.PRINT_PLUGIN);
		printingSourceProvider.togglePlugin();
		
		return true;
	}
	
	public void init(IWorkbench workbench){}
}