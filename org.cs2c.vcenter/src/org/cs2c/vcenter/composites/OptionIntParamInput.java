package org.cs2c.vcenter.composites;

import java.util.ArrayList;
import java.util.List;

import org.cs2c.nginlib.config.Option;
import org.cs2c.nginlib.config.Parameter;
import org.cs2c.nginlib.config.RecOption;
import org.cs2c.vcenter.dialog.DirectiveInput;
import org.cs2c.vcenter.metadata.ParameterMeta;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class OptionIntParamInput extends BaseParamInput {

	private String strParamName = "";
	private List<String> strUnit = new ArrayList<String>();
	private int max = 999999999;
	private int min = -999999999;
	private String tips = "";
	
	private int selValue = 0;
	private String selUnit = "";
	
	private ParameterMeta pMeta;
	private Option opt = new RecOption();
	
	private Button ctlCheckButton;
	private Label ctlLabel;
	private Spinner ctlSpinner;
	private Combo ctlCombo;
	
	private boolean isChecked = false;
	
	private DirectiveInput parentDialog = null;
	
	public OptionIntParamInput(Composite parent, int style, ParameterMeta meta, DirectiveInput parentDlg) {
		super(parent, style);
		
		this.layout(true);
		this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		this.setLayout(new GridLayout(10,true));
		
		ctlCheckButton = new Button(this,SWT.CHECK);
		GridData gridDataListCB = new GridData(GridData.FILL_BOTH);
		gridDataListCB.verticalAlignment = SWT.CENTER;
		gridDataListCB.horizontalAlignment = SWT.CENTER;
		gridDataListCB.horizontalSpan=1;
		ctlCheckButton.setLayoutData(gridDataListCB);
		ctlCheckButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(isChecked)
				{
					isChecked = false;
					ctlSpinner.setEnabled(false);
					ctlCombo.setEnabled(false);
				}
				else
				{
					isChecked = true;
					ctlSpinner.setEnabled(true);
					if(strUnit!=null && !strUnit.isEmpty())
					{
						ctlCombo.setEnabled(true);
					}
					else
					{
						ctlCombo.setEnabled(false);
					}
				}
				
				UpdateParam();
				parentDialog.updateDirctString();
			}
		});
		ctlCheckButton.setText("");
		
		ctlLabel = new Label(this,SWT.NONE);
		GridData gridDataListLB = new GridData(GridData.FILL_BOTH);
		gridDataListLB.horizontalSpan=3;
		ctlLabel.setLayoutData(gridDataListLB);
		
		ctlSpinner = new Spinner(this,SWT.NONE);
		ctlSpinner.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				UpdateParam();
				parentDialog.updateDirctString();
			}
		});
		GridData gridDataListSP = new GridData(GridData.FILL_BOTH);
		gridDataListSP.horizontalSpan=4;
		ctlSpinner.setLayoutData(gridDataListSP);
		
		ctlCombo = new Combo(this,SWT.NONE);
		ctlCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				UpdateParam();
				parentDialog.updateDirctString();
			}
		});
		GridData gridDataListCMB = new GridData(GridData.FILL_BOTH);
		gridDataListCMB.horizontalSpan=2;
		ctlCombo.setLayoutData(gridDataListCMB);
		
		this.pMeta = meta;
		this.parentDialog = parentDlg;
		
		strParamName = this.pMeta.getName();
		selValue = 0;
		max = (int) this.pMeta.getMax();
		min = (int) this.pMeta.getMin();
		strUnit = this.pMeta.getUnit();
		tips = this.pMeta.getTips();
		
		ctlSpinner.setMaximum(max);
		ctlSpinner.setMinimum(min);
		ctlSpinner.setSelection(0);
		
		if(strUnit!=null && !strUnit.isEmpty())
		{
			int i = strUnit.size();
			while(i>0)
			{
				ctlCombo.add(strUnit.get(i-1));
				i--;
			}
		}
		else
		{
			ctlCombo.setEnabled(false);
		}
		if(ctlCombo.getItemCount() > 0)
		{
			ctlCombo.select(0);
		}
		
		ctlLabel.setText(strParamName+" = ");

		isChecked = false;
		ctlSpinner.setEnabled(false);
		ctlCombo.setEnabled(false);
			
		if(tips!=null && !tips.isEmpty())
		{
			ctlLabel.setToolTipText(tips);
			ctlCheckButton.setToolTipText(tips);
			ctlSpinner.setToolTipText(tips);
			ctlCombo.setToolTipText(tips);
		}

	}
	
	@Override
	public void setMeta(ParameterMeta meta) {
		this.pMeta = meta;
		
		strParamName = this.pMeta.getName();
		selValue = 0;
		max = (int) this.pMeta.getMax();
		min = (int) this.pMeta.getMin();
		strUnit = this.pMeta.getUnit();
		tips = this.pMeta.getTips();
		
		ctlSpinner.setMaximum(max);
		ctlSpinner.setMinimum(min);
		ctlSpinner.setSelection(0);
		
		if(strUnit!=null && !strUnit.isEmpty())
		{
			int i = strUnit.size();
			ctlCombo.add(" ");
			while(i>0)
			{
				ctlCombo.add(strUnit.get(i-1));
				i--;
			}
		}
		else
		{
			ctlCombo.setEnabled(false);
		}
		if(ctlCombo.getItemCount() > 0)
		{
			ctlCombo.select(0);
		}
		
		ctlLabel.setText(strParamName+" = ");
		
		if(!isChecked)
		{
			isChecked = false;
			ctlSpinner.setEnabled(false);
			ctlCombo.setEnabled(false);
		}
		else
		{
			isChecked = true;
			ctlSpinner.setEnabled(true);
			if(strUnit!=null && !strUnit.isEmpty())
			{
				ctlCombo.setEnabled(true);
			}
			else
			{
				ctlCombo.setEnabled(false);
			}
		}
		
		if(tips!=null && !tips.isEmpty())
		{
			ctlLabel.setToolTipText(tips);
			ctlCheckButton.setToolTipText(tips);
			ctlSpinner.setToolTipText(tips);
			ctlCombo.setToolTipText(tips);
		}

	}
	
	public void UpdateParam()
	{
		selValue = ctlSpinner.getSelection();
		
		opt.setName(strParamName);
		if(ctlCombo.getItemCount() > 0 && ctlCombo.getSelectionIndex()!=-1)
		{
			selUnit = ctlCombo.getItem(ctlCombo.getSelectionIndex());
			opt.setValue((Integer.toString(selValue)+selUnit).trim());
		}
		else
		{
			opt.setValue(Integer.toString(selValue));
		}
	}
	
	@Override
	public Parameter getParameter() {
		if(isChecked)
		{
			return opt;
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void setInputData(List<Parameter> params)
	{
		for(Parameter param : params)
		{
			String paramStr = deleteExtraSpace(param.toString());
			paramStr = paramStr.replaceAll(" ", "");
			String[] paramStrSlip = paramStr.split("=");
			if(paramStrSlip[0].equals(strParamName))
			{
				isChecked = true;
				ctlCheckButton.setSelection(true);
				ctlSpinner.setEnabled(true);
				
				if(strUnit!=null && !strUnit.isEmpty())
				{
					ctlCombo.setEnabled(true);
					
					int i;
					for (i = paramStrSlip[1].length();--i>=0;)
					{
						if (Character.isDigit(paramStrSlip[1].charAt(i)))
						{
							break;
						}
					}
					String substr = paramStrSlip[1].substring(0, i+1);
					int nvalue = Integer.parseInt(substr);
					ctlSpinner.setSelection(nvalue);
					ctlCombo.setText(paramStrSlip[1].substring(i+1, paramStrSlip[1].length()));
				}
				else
				{
					ctlCombo.setEnabled(false);
					
					ctlSpinner.setSelection(Integer.parseInt(paramStrSlip[1]));
				}
				
				params.remove(param);
				break;
			}
		}
	}

}
