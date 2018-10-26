package com.esen.youngcms.core.freemarker.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.NumberUtils;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

/**
 * FreeMarker甯姪
 * 
 * 鍙俧reemarker瀹樻柟鏂囨。绀轰緥锛歨ttp://freemarker.org/docs/pgui_datamodel_directive.html
 * 
 * @author liufang
 * 
 */
public class Freemarker {
	public static final String KEY_PARAMETERS = "Param";
	public static final String KEY_PARAMETER_VALUES = "ParamValues";
	/**
	 * 绗竴鏉″紑濮嬩綅
	 */
	public static final String OFFSET = "offset";
	/**
	 * 澶ф潯
	 */
	public static final String LIMIT = "limit";
	/**
	 * 鎺掑簭
	 */
	public static final String SORT = "sort";
	/**
	 * 椤靛彿
	 */
	public static final String PAGE = "page";
	/**
	 * 姣忛〉鏉℃暟
	 */
	public static final String PAGE_SIZE = "pageSize";
	/**
	 * 鍦板潃
	 */
	public static final String URL = "url";
	/**
	 * 缈婚〉鍦板潃鐢熸垚瀵硅薄
	 */
	public static final String PAGE_URL_RESOLVER = "pageUrlResolver";
	/**
	 * 鍒嗛殧
	 */
	public static final String SPLIT = ",";
	
	private static final String REQUIRED = "The '%s' paramter is required";
	private static final String NOT_MATCH = "The '%s' parameter not a %s";

	public static final Integer getOffset(Map<String, TemplateModel> params)
			throws TemplateModelException {
		return getInteger(params, OFFSET);
	}

	public static final Integer getLimit(Map<String, TemplateModel> params)
			throws TemplateModelException {
		return getInteger(params, LIMIT);
	}


	public static final Integer getPageSize(Map<String, TemplateModel> params)
			throws TemplateModelException {
		Integer pageSize = Freemarker.getInteger(params, PAGE_SIZE);
		if (pageSize == null || pageSize < 1) {
			pageSize = 20;
		}
		return pageSize;
	}

	public static final int getPage(Map<String, TemplateModel> params,
			Environment env) throws TemplateModelException {
		Integer page = Freemarker.getInteger(params, PAGE);
		if (page == null) {
			page = Freemarker.getInteger(env.getDataModel().get(PAGE), PAGE);
		}
		if (page == null || page < 1) {
			page = 1;
		}
		return page;
	}


	public static String getUrl(Environment env) throws TemplateModelException {
		TemplateModel model = env.getDataModel().get(URL);
		return Freemarker.getString(model, URL);
	}



	@SuppressWarnings("unchecked")
	public static <T> T getObject(TemplateModel model, String name,
			Class<T> targetClass) throws TemplateModelException {
		if (model instanceof AdapterTemplateModel) {
			return (T) ((AdapterTemplateModel) model)
					.getAdaptedObject(targetClass);
		} else {
			throw new TemplateModelException(String.format(NOT_MATCH, name,
					targetClass.getName()));
		}
	}

	public static <T> T getObject(Map<String, TemplateModel> params,
			String name, Class<T> targetClass) throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getObject(model, name, targetClass);
	}

	public static String getString(TemplateModel model, String name, String def)
			throws TemplateModelException {
		String text;
		if (model == null) {
			text = def;
		} else if (model instanceof TemplateScalarModel) {
			TemplateScalarModel scalarModel = (TemplateScalarModel) model;
			text = scalarModel.getAsString();
		} else if ((model instanceof TemplateNumberModel)) {
			// 濡傛灉鏄暟瀛楋紝涔熻浆鎹㈡垚瀛楃锟�?
			TemplateNumberModel numberModel = (TemplateNumberModel) model;
			Number number = numberModel.getAsNumber();
			text = number.toString();
		} else {
			throw new TemplateModelException(String.format(NOT_MATCH, name,
					"string"));
		}
		return text;
	}

	public static String getString(TemplateModel model, String name)
			throws TemplateModelException {
		return getString(model, name, null);
	}

	public static String getString(Map<String, TemplateModel> params,
			String name, String def) throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getString(model, name, def);
	}

	public static String getString(Map<String, TemplateModel> params,
			String name) throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getString(model, name);
	}

	public static String getStringRequired(TemplateModel model, String name)
			throws TemplateModelException {
		String text = getString(model, name);
		if (StringUtils.isBlank(text)) {
			throw new TemplateModelException(String.format(REQUIRED, name));
		} else {
			return text;
		}
	}

	public static String getStringRequired(Map<String, TemplateModel> params,
			String name) throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getStringRequired(model, name);
	}

	public static String[] getStrings(TemplateModel model, String name)
			throws TemplateModelException {
		String text = getString(model, name);
		return StringUtils.split(text, SPLIT);
	}

	public static String[] getStrings(Map<String, TemplateModel> params,
			String name) throws TemplateModelException {
		String text = getString(params, name);
		return StringUtils.split(text, SPLIT);
	}

	public static String[] getStringsRequired(TemplateModel model, String name)
			throws TemplateModelException {
		String text = getString(model, name);
		String[] array = StringUtils.split(text, SPLIT);
		if (ArrayUtils.isEmpty(array)) {
			throw new TemplateModelException(String.format(REQUIRED, name));
		}
		return array;
	}

	public static String[] getStringsRequired(
			Map<String, TemplateModel> params, String name)
			throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getStringsRequired(model, name);
	}

	public static <T extends Number> T getNumber(TemplateModel model,
			String name, Class<T> targetClass) throws TemplateModelException {
		if (model == null) {
			return null;
		} else if (model instanceof TemplateNumberModel) {
			TemplateNumberModel numberModel = (TemplateNumberModel) model;
			Number number = numberModel.getAsNumber();
			return NumberUtils.convertNumberToTargetClass(number, targetClass);
		} else if (model instanceof TemplateScalarModel) {
			TemplateScalarModel scalarModel = (TemplateScalarModel) model;
			String text = scalarModel.getAsString();
			if (StringUtils.isNotBlank(text)) {
				try {
					return NumberUtils.parseNumber(text, targetClass);
				} catch (NumberFormatException e) {
					throw new TemplateModelException(String.format(NOT_MATCH,
							name, "number"), e);
				}
			} else {
				return null;
			}
		} else {
			throw new TemplateModelException(String.format(NOT_MATCH, name,
					"number"));
		}
	}

	public static <T extends Number> T getNumber(
			Map<String, TemplateModel> params, String name, Class<T> targetClass)
			throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getNumber(model, name, targetClass);
	}

	public static <T extends Number> T getNumberRequired(TemplateModel model,
			String name, Class<T> targetClass) throws TemplateModelException {
		T number = getNumber(model, name, targetClass);
		if (number == null) {
			throw new TemplateModelException(String.format(REQUIRED, name));
		} else {
			return number;
		}
	}

	public static <T extends Number> T getNumberRequired(
			Map<String, TemplateModel> params, String name, Class<T> targetClass)
			throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getNumberRequired(model, name, targetClass);
	}

	public static Long getLong(TemplateModel model, String name)
			throws TemplateModelException {
		return getNumber(model, name, Long.class);
	}

	public static Long getLong(TemplateModel model, String name, Long def)
			throws TemplateModelException {
		Long result = getNumber(model, name, Long.class);
		return result != null ? result : def;
	}

	public static Long getLong(Map<String, TemplateModel> params, String name)
			throws TemplateModelException {
		return getNumber(params, name, Long.class);
	}

	public static Long getLong(Map<String, TemplateModel> params, String name,
			Long def) throws TemplateModelException {
		Long result = getNumber(params, name, Long.class);
		return result != null ? result : def;
	}

	public static Long getLongRequired(TemplateModel model, String name)
			throws TemplateModelException {
		return getNumberRequired(model, name, Long.class);
	}

	public static Long getLongRequired(Map<String, TemplateModel> params,
			String name) throws TemplateModelException {
		return getNumberRequired(params, name, Long.class);
	}

	public static Integer getInteger(TemplateModel model, String name)
			throws TemplateModelException {
		return getNumber(model, name, Integer.class);
	}

	public static Integer getInteger(TemplateModel model, String name,
			Integer def) throws TemplateModelException {
		Integer result = getNumber(model, name, Integer.class);
		return result != null ? result : def;
	}

	public static Integer getInteger(Map<String, TemplateModel> params,
			String name) throws TemplateModelException {
		return getNumber(params, name, Integer.class);
	}

	public static Integer getInteger(Map<String, TemplateModel> params,
			String name, Integer def) throws TemplateModelException {
		Integer result = getNumber(params, name, Integer.class);
		return result != null ? result : def;
	}

	public static Integer getIntegerRequired(TemplateModel model, String name)
			throws TemplateModelException {
		return getNumberRequired(model, name, Integer.class);
	}

	public static Integer getIntegerRequired(Map<String, TemplateModel> params,
			String name) throws TemplateModelException {
		return getNumberRequired(params, name, Integer.class);
	}

	public static Integer[] getIntegers(TemplateModel model, String name)
			throws TemplateModelException {
		String text = getString(model, name);
		if (text == null) {
			return null;
		} else if (StringUtils.isBlank(text)) {
			return new Integer[0];
		}
		String[] stringArray = StringUtils.split(text, SPLIT);
		int length = stringArray.length;
		Integer[] numberArray = new Integer[length];
		try {
			for (int i = 0; i < length; i++) {
				numberArray[i] = Integer.valueOf(stringArray[i]);
			}
			return numberArray;
		} catch (NumberFormatException e) {
			throw new TemplateModelException(String.format(NOT_MATCH, name,
					"integer array"));
		}
	}

	public static Integer[] getIntegers(Map<String, TemplateModel> params,
			String name) throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getIntegers(model, name);
	}

	public static Integer[] getIntegersRequired(TemplateModel model, String name)
			throws TemplateModelException {
		Integer[] array = getIntegers(model, name);
		if (ArrayUtils.isEmpty(array)) {
			throw new TemplateModelException(String.format(REQUIRED, name));
		} else {
			return array;
		}
	}

	public static Integer[] getIntegersRequired(
			Map<String, TemplateModel> params, String name)
			throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getIntegersRequired(model, name);
	}

	public static Long[] getLongs(TemplateModel model, String name)
			throws TemplateModelException {
		String text = getString(model, name);

		if (text == null) {
			return null;
		} else if (StringUtils.isBlank(text)) {
			return new Long[0];
		}
		String[] stringArray = StringUtils.split(text, SPLIT);
		int length = stringArray.length;
		Long[] numberArray = new Long[length];
		try {
			for (int i = 0; i < length; i++) {
				numberArray[i] = Long.valueOf(stringArray[i]);
			}
			return numberArray;
		} catch (NumberFormatException e) {
			throw new TemplateModelException(String.format(NOT_MATCH, name,
					"long array"));
		}
	}

	public static Long[] getLongs(Map<String, TemplateModel> params, String name)
			throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getLongs(model, name);
	}

	public static Long[] getLongsRequired(TemplateModel model, String name)
			throws TemplateModelException {
		Long[] array = getLongs(model, name);
		if (ArrayUtils.isEmpty(array)) {
			throw new TemplateModelException(String.format(REQUIRED, name));
		} else {
			return array;
		}
	}

	public static Long[] getLongsRequired(Map<String, TemplateModel> params,
			String name) throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getLongsRequired(model, name);
	}

	public static Boolean getBoolean(TemplateModel model, String name,
			Boolean def) throws TemplateModelException {
		Boolean result;
		if (model == null) {
			result = null;
		} else if (model instanceof TemplateBooleanModel) {
			TemplateBooleanModel booleanModel = (TemplateBooleanModel) model;
			result = booleanModel.getAsBoolean();
		} else if (model instanceof TemplateScalarModel) {
			TemplateScalarModel scalarModel = (TemplateScalarModel) model;
			String text = scalarModel.getAsString();
			if (StringUtils.isNotBlank(text)) {
				result = Boolean.valueOf(text);
			} else {
				result = null;
			}
		} else {
			throw new TemplateModelException(String.format(NOT_MATCH, name,
					"boolean"));
		}
		return result != null ? result : def;
	}

	public static Boolean getBoolean(TemplateModel model, String name)
			throws TemplateModelException {
		return getBoolean(model, name, null);
	}

	public static Boolean getBoolean(Map<String, TemplateModel> params,
			String name) throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getBoolean(model, name);
	}

	public static Boolean getBoolean(Map<String, TemplateModel> params,
			String name, Boolean def) throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getBoolean(model, name, def);
	}

	public static Boolean getBooleanRequired(TemplateModel model, String name)
			throws TemplateModelException {
		Boolean bool = getBoolean(model, name);
		if (bool == null) {
			throw new TemplateModelException(String.format(REQUIRED, name));
		} else {
			return bool;
		}
	}

	public static Boolean getBooleanRequired(Map<String, TemplateModel> params,
			String name) throws TemplateModelException {
		TemplateModel model = params.get(name);
		return getBooleanRequired(model, name);
	}


	/**
	 * 
	 * @param freemarkerCfg
	 * @param context
	 * @param data
	 * @param templatePath
	 * @param targetHtmlPath
	 */
    public static void crateHTML(Configuration freemarkerCfg,Map<String,Object> data,String templatePath,String htmlPath) throws Exception{
        try {
            Template template = freemarkerCfg.getTemplate(templatePath,"UTF-8");
            File htmlFile = new File(htmlPath);
            if(!htmlFile.getParentFile().exists()) {
            	htmlFile.getParentFile().mkdirs();
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
            template.process(data, out);
            out.flush();
            out.close();
        } catch (Exception e) {
           throw new Exception("静态化失败");
        }
    }

}
