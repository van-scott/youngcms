package com.esen.youngcms.core.utils;

import java.util.List;

import com.esen.youngcms.vo.LabelValue;

public final class ListTypeParameter
{
	public final static List<LabelValue> yesOrNo = initYesOrNo();
	
	public final static List<LabelValue> friendLinkTypeList = initFriendLinkTypeList();
	
	public final static List<LabelValue> advertPositionTypeList = initAdvertPositionTypeList();
	
	public final static List<LabelValue> moduleTypeList = initModuleTypeList();
	
	public final static List<LabelValue> protocolList = initProtocolList();
	
	public final static List<LabelValue> postfixList = initPostfixList();
	
	public final static List<LabelValue> extendFieldTypeList = initExtendFieldTypeList();
	
	private static List<LabelValue> initYesOrNo() {
		return DictUtil.getDictsByEnName("yesOrNo");
	}

	private static List<LabelValue> initPostfixList() {
		return DictUtil.getDictsByEnName("postfix");
	}

	private static List<LabelValue> initProtocolList() {
		return DictUtil.getDictsByEnName("protocol");
	}

	private static List<LabelValue> initExtendFieldTypeList() {
		return DictUtil.getDictsByEnName("extendFieldType");
	}

	private static List<LabelValue> initFriendLinkTypeList() {
		return DictUtil.getDictsByEnName("friendLinkType");
	}
	
	private static List<LabelValue> initModuleTypeList() {
		return DictUtil.getDictsByEnName("moduleType");
	}

	private static List<LabelValue> initAdvertPositionTypeList() {
		return DictUtil.getDictsByEnName("advertPositionType");
	}

}
