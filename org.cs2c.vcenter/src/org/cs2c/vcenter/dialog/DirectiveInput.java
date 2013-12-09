package org.cs2c.vcenter.dialog;

import java.util.ArrayList;
import java.util.List;

import org.cs2c.nginlib.config.Directive;
import org.cs2c.nginlib.config.Parameter;
import org.cs2c.vcenter.composites.IntParamInput;
import org.cs2c.vcenter.composites.OptionIntParamInput;
import org.cs2c.vcenter.composites.OptionStringParamInput;
import org.cs2c.vcenter.composites.ParamInput;
import org.cs2c.vcenter.composites.SelectParamInput;
import org.cs2c.vcenter.composites.StringParamInput;
import org.cs2c.vcenter.composites.TagParamInput;
import org.cs2c.vcenter.metadata.DirectiveMeta;
import org.cs2c.vcenter.metadata.ParameterMeta;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class DirectiveInput extends Dialog {
	
	private List<ParamInput> listParamInput = new ArrayList<ParamInput>();
	private List<Parameter> valueParams = new ArrayList<Parameter>();
	
	private Directive dirct = null;
	private DirectiveMeta dMeta = null;
	
	private static Shell newShell = new Shell();

	private Label dirctString = null;
	
	/**
	 * @wbp.parser.constructor
	 */
	
	public DirectiveInput(Shell parent, Directive direct, DirectiveMeta dMeta) {
		super(parent);
		setShellStyle(SWT.BORDER | SWT.MIN | SWT.MAX | SWT.RESIZE);
		
		this.dirct = direct;
		
		this.dMeta = dMeta;
		if(dMeta == null)
		{
			MessageDialog.openError(parent, "Error", "Directive Meta is null!");
		}
		
		if(this.dirct != null)
		{
			List<Parameter> params = new ArrayList<Parameter>();
			params = this.dirct.getParameters();
			for(Parameter param : params)
			{
				valueParams.add(param);
			}
			
			//Unknown the Parameter TypeŁĄŁĄŁĄ
		}
		
	}
	
	protected Control createDialogArea(Composite parent) {
		
		getShell().setText(this.dMeta.getName());
		
		Composite composite = (Composite)super.createDialogArea(parent);

		composite.layout(true);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout(1,false));
		
		List<ParameterMeta> listParamMetas = new ArrayList<ParameterMeta>();
		listParamMetas = dMeta.getOptions();
		if(listParamMetas!=null && !listParamMetas.isEmpty())
		{
			int count = listParamMetas.size();
			int i = 0;
			while(i < count)
			{
				ParameterMeta pMeta = listParamMetas.get(i);
				String strClassName = pMeta.getClassName();
				if(strClassName.equals("OptionIntParamInput"))
				{
					OptionIntParamInput oppInput = new OptionIntParamInput(composite, SWT.NONE, pMeta, this);
					oppInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
					GridData gridDataList_opp=new GridData(GridData.FILL_BOTH);
					gridDataList_opp.verticalSpan=1;
				    oppInput.setLayoutData(gridDataList_opp);
				    
				    if(this.dirct != null)
					{
				    	this.dirct.getParameters();
					}
				    
				    listParamInput.add(oppInput);
				}
				else if(strClassName.equals("OptionStringParamInput"))
				{
					OptionStringParamInput ospInput = new OptionStringParamInput(composite, SWT.NONE, pMeta, this);
				    ospInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
					GridData gridDataList_osp=new GridData(GridData.FILL_BOTH);
					gridDataList_osp.verticalSpan=1;
				    ospInput.setLayoutData(gridDataList_osp);
				    
				    if(this.dirct != null)
					{
					}
				    
				    listParamInput.add(ospInput);
				}
				else if(strClassName.equals("IntParamInput"))
				{
					IntParamInput ipInput = new IntParamInput(composite, SWT.NONE, pMeta, this);
				    ipInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
					GridData gridDataList_ip=new GridData(GridData.FILL_BOTH);
					gridDataList_ip.verticalSpan=1;
					ipInput.setLayoutData(gridDataList_ip);
					
					if(this.dirct != null)
					{
					}
					
					listParamInput.add(ipInput);
				}
				else if(strClassName.equals("StringParamInput"))
				{
					StringParamInput strpInput = new StringParamInput(composite, SWT.NONE, pMeta, this);
					strpInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
					GridData gridDataList_strp=new GridData(GridData.FILL_BOTH);
					gridDataList_strp.verticalSpan=1;
					strpInput.setLayoutData(gridDataList_strp);
					
					if(this.dirct != null)
					{
					}
					
					listParamInput.add(strpInput);
				}
				else if(strClassName.equals("SelectParamInput"))
				{
					SelectParamInput slctpInput = new SelectParamInput(composite, SWT.NONE, pMeta, this);
					slctpInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
					GridData gridDataList_slctp=new GridData(GridData.FILL_BOTH);
					gridDataList_slctp.verticalSpan=1;
					slctpInput.setLayoutData(gridDataList_slctp);
					
					if(this.dirct != null)
					{
					}
					
					listParamInput.add(slctpInput);
				}
				else if(strClassName.equals("TagParamInput"))
				{
					TagParamInput tagpInput = new TagParamInput(composite, SWT.NONE, pMeta, this);
					tagpInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
					GridData gridDataList_tagp=new GridData(GridData.FILL_BOTH);
					gridDataList_tagp.verticalSpan=1;
					tagpInput.setLayoutData(gridDataList_tagp);
					
					if(this.dirct != null)
					{
					}
					
					listParamInput.add(tagpInput);
				}
				
				i++;
			}
			
		}
		
		Label tm = new Label(composite, SWT.NONE);
		tm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		GridData gridDataList_tm=new GridData(GridData.FILL_BOTH);
		gridDataList_tm.verticalSpan=1;
		tm.setLayoutData(gridDataList_tm);
		
		dirctString = new Label(composite, SWT.BORDER);
		dirctString.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		GridData gridDataList_drctstr=new GridData(GridData.FILL_BOTH);
		gridDataList_drctstr.verticalSpan=1;
		dirctString.setLayoutData(gridDataList_drctstr);
		
		if(dirct != null)
		{
			dirctString.setText("Directive: " + (dirct.toString()).trim());
		}
		else
		{
			dirctString.setText("Directive: " + dMeta.getName() + " ;");
		}
		
	    return composite;
   }
	
	protected void okPressed()
	{
		valueParams.clear();
		
		if(listParamInput==null || listParamInput.isEmpty())
		{
			return;
		}
		
		for(ParamInput pinpt : listParamInput)
		{
			valueParams.add(pinpt.getParameter());
		}
		
		super.okPressed();
	}
	
	public List<Parameter> getParams()
	{
		return valueParams;
	}
	
	public void updateDirctString()
	{
		valueParams.clear();
		if(listParamInput==null || listParamInput.isEmpty())
		{
			return;
		}
		for(ParamInput pinpt : listParamInput)
		{
			Parameter tmParam = pinpt.getParameter();
			if(tmParam != null)
			{
				valueParams.add(tmParam);
			}
		}
		
		String dirctStr = dMeta.getName();//dirct.getName();
		if(valueParams!=null && !valueParams.isEmpty())
		{
			Parameter param = null;
			int count = valueParams.size();
			int i = 0;
			while(i < count)
			{
				param = valueParams.get(i);
				dirctStr = dirctStr + " " + param.toString();
				i++;
			}
			dirctStr = dirctStr + ";";
		}
		dirctStr = dirctStr.trim();
		if(!dirctStr.endsWith(";"))
		{
			dirctStr = dirctStr + " ;";
		}
		if(dirctString!=null)
		{
			dirctString.setText("Directive: " + dirctStr);
		}
	}
	
}
