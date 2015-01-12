package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AbstractMultiActionController extends MultiActionController {
	protected CustomDateEditor dateEditor = new CustomDateEditor(
			new SimpleDateFormat("MM/dd/yyyy"), true);

	protected CustomNumberEditor numberEditor = new CustomNumberEditor(
			Long.class, true);
	
	protected BindException bindObject(HttpServletRequest request,
			Object command) throws Exception {
		return bindObject(request, command, null);
	}

	protected BindException bindObject(HttpServletRequest request,
			Object command, Validator validator) throws Exception {
		ServletRequestDataBinder binder = createBinder(request, command);
		initBinder(request, binder);
		binder.bind(request);

		BindException errors = new BindException(command,
				getCommandName(command));
		/*
		if (validator.supports(command.getClass())) {
			ValidationUtils.invokeValidator(validator, command, errors);
		}
		*/

		return errors;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, this.dateEditor);
		binder.registerCustomEditor(Long.class, this.numberEditor);
	}

}
