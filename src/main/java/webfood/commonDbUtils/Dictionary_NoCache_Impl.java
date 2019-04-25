package webfood.commonDbUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import webfood.mapper.DicItemMapper;
import webfood.model.DicItem;
import webfood.utils.SpringContextUtil;

public class Dictionary_NoCache_Impl implements Dictionary {
	private static final long serialVersionUID = 1723952685584490695L;
	private static final Logger log = Logger.getLogger(Dictionary_NoCache_Impl.class);
	private static Dictionary_NoCache_Impl instance;

	ApplicationContext beanFactory = SpringContextUtil.getApplicationContext();
	DicItemMapper mapper = beanFactory.getBean(DicItemMapper.class);

	public static Dictionary getInstance() {
		if (instance == null) {
			instance = new Dictionary_NoCache_Impl();
		}
		return new Dictionary_NoCache_Impl();
	}

	@Override
	public void clearForFinalize() {
		log.debug("准备销毁Dictionary!");
		instance = null;
	}

	@Override
	public Map<String, List<String>> getItems(String dicClassName) {

		if (StringUtils.isEmpty(dicClassName)) {
			log.warn("DicClassName为空！");
			return null;
		}
		Map<String, List<String>> map = null;
		try {

			List<DicItem> list = mapper.queryItem(dicClassName);
			if (list.size() > 0) {
				int i = 0;
				map = new LinkedHashMap<String, List<String>>();
				for (; i < list.size(); i++) {
					List<String> itemList = new ArrayList<String>();
					itemList.add(list.get(i).getItemName());
					itemList.add(list.get(i).getItemValue());
					itemList.add(String.valueOf(list.get(i).getItemState()));
					itemList.add(list.get(i).getRemark());
					map.put(list.get(i).getItemName(), itemList);
				}
			}
			log.info("list.size():" + list.size());
		} catch (Exception ex) {
			log.error("出错了！" + ex.getMessage());
			log.fatal(log, ex);
		}
		return map;
	}

	@Override
	public String getName(String dicClassName, String dicItemValue) {

		if (StringUtils.isEmpty(dicClassName)) {
			log.warn("DicClassName为空！");
			return null;
		}
		if (StringUtils.isEmpty(dicItemValue)) {
			log.warn("DicItemValue为空！");
			return null;
		}

		String result = null;
		try {
			String sql = "select item.item_Name, item.item_Value from hz_dic_class  dic, hz_dic_item  item where dic.id=item.class_Id and dic.class_name='"
					+ dicClassName + "' and item.item_value='" + dicItemValue + "' ";
			DicItem query = mapper.queryItemName(sql);
			if (query != null) {
				result = query.getItemName();
			} else {
				log.warn("未找到字典名称:dicClassName=" + dicClassName + "  dicItemValue:" + dicItemValue);
				result = dicItemValue;
			}
		} catch (Exception ex) {
			log.error("出错了！" + ex.getMessage());
			log.fatal(log, ex);
		}
		return result;
	}

	@Override
	public String getValue(String dicClassName, String dicItemName) {
		if (StringUtils.isEmpty(dicClassName)) {
			log.warn("DicClassName为空！");
			return null;
		}
		if (StringUtils.isEmpty(dicItemName)) {
			log.warn("DicItemName为空！");
			return null;
		}
		String result = null;

		try {
			String sql = "select item.item_Name, item.item_Value from HZ_DIC_CLASS  dic, HZ_DIC_ITEM  item where dic.class_Id=item.class_Id and dic.class_name='"
					+ dicClassName + "' and item.item_Name='" + dicItemName + "' ";
			DicItem query = mapper.queryItemName(sql);
			if (query != null) {
				result = query.getItemName();
			} else {
				log.warn("未找到字典名称:dicClassName=" + dicClassName + "  dicItemName:" + dicItemName);
				result = dicItemName;
			}
		} catch (Exception ex) {
			log.error("出错了！" + ex.getMessage());
			log.fatal(log, ex);
		}
		return result;
	}

	@Override
	public String getRemark(String dicClassName, String dicItemName) {
		return null;
	}

	@Override
	public String getState(String dicClassName, String dicItemName) {

		return null;
	}

	@Override
	public void refresh(boolean closeSession) {
	}

	@Override
	public String getJspCode(String style, String dicClassName, String inputName, String values, String desc) {
		return null;
	}

	@Override
	public String getHtmlCode(String style, String dicClassName, String inputName, String values, String desc) {

		if (StringUtils.isEmpty(style) || StringUtils.isEmpty(dicClassName)) {
			return "";
		}
		style = style.toUpperCase();
		Map items = getItems(dicClassName);
		if (items == null) {
			return "";
		}
		Iterator it = null;
		List item = null;
		String key = null;
		String value = null;
		String[] valueArray = StringUtils.split(values, ";");
		desc = StringUtils.trimToEmpty(desc);

		int count = 0;
		StringBuffer sb = new StringBuffer();
		if (style.equals("INPUT")) {
			String formName = "form1";
			if (inputName.indexOf(".") != -1) {
				formName = inputName.substring(0, inputName.indexOf("."));
				inputName = inputName.substring(inputName.indexOf(".") + 1);
			}
			sb.append("<input type=\"text\"  name=\"" + inputName + "\" id=\"" + inputName + "\" value=\""
					+ StringUtils.trimToEmpty(values) + "\" " + desc + "  >");
			sb.append("\r\n");
			it = items.keySet().iterator();
			while (it.hasNext()) {
				key = ((String) it.next()).toString();
				item = (List) items.get(key);
				value = ((String) item.get(1)).toString();
				if ("0".equals(((String) item.get(2)).toString())) {
					sb.append("      <a href=\"###\" onClick=\"setValue('" + formName + "','" + inputName + "', '"
							+ value + "');\">" + key + "</a> ");
				}
			}
		} else if (style.equals("RADIO")) {
			it = items.keySet().iterator();
			while (it.hasNext()) {
				key = ((String) it.next()).toString();
				item = (List) items.get(key);
				value = ((String) item.get(1)).toString();
				if ("0".equals(((String) item.get(2)).toString())) {
					count++;
					sb.append("<input type=\"radio\" name=\"" + inputName + "\"  id=\"" + inputName + "\" value=\""
							+ value + "\" " + desc);
					if (valueArray == null || valueArray.length == 0) {
						if (count == 1) {
							sb.append(" checked ");

						}
					} else {
						for (String element : valueArray) {
							if (element.equals(value)) {
								sb.append(" checked ");
							}
						}
					}

					sb.append(">" + key);
				}
			}
		} else if (style.equals("CHECKBOX")) {
			it = items.keySet().iterator();
			while (it.hasNext()) {
				key = ((String) it.next()).toString();
				item = (List) items.get(key);
				value = ((String) item.get(1)).toString();
				if ("0".equals(((String) item.get(2)).toString())) {
					count++;
					sb.append("<input type=\"checkbox\" name=\"" + inputName + "\" id=\"" + inputName + "\" value=\""
							+ value + "\" " + desc);
					if (valueArray != null && valueArray.length != 0) {
						for (String element : valueArray) {
							if (element.equals(value)) {
								sb.append(" checked ");
							}
						}
					}
					sb.append(">" + key);
				}
			}
		} else if (style.equals("LISTBOX")) {
			/* 546 */ it = items.keySet().iterator();
			/* 547 */ sb.append("<select name=\"" + inputName + "\" id=\"" +
			/* 548 */ inputName + "\" size=\"" +
			/* 549 */ items.size() + "\" " + desc + " multiple>");
			/* 550 */ sb.append("\r\n");
			/* 551 */ while (it.hasNext()) {
				/* 552 */ key = ((String) it.next()).toString();
				/* 553 */ item = (List) items.get(key);
				/* 554 */ value = ((String) item.get(1)).toString();
				/* 555 */ if ("0".equals(((String) item.get(2)).toString())) {
					/* 558 */ count++;
					/* 559 */ sb.append("      <option value=\"" + value + "\"");
					/* 560 */ if (valueArray != null && valueArray.length != 0) {
						/* 563 */ for (String element : valueArray) {
							/* 564 */ if (element.equals(value)) {
								/* 565 */ sb.append(" selected ");
							}
						}
					}

					/* 570 */ sb.append(">" + key + "</option>");
					/* 571 */ sb.append("\r\n");
				}
			}
			/* 574 */ sb.append("    </select>");
		}
		/* 576 */ else if (style.equals("DROPBOX")) {
			/* 577 */ it = items.keySet().iterator();
			/* 578 */ sb.append("<select name=\"" + inputName + "\"  id=\"" +
			/* 579 */ inputName + "\" " + desc + " >");
			/* 580 */ sb.append("\r\n");
			/* 581 */ sb.append("      <option value=\"\">--请选择--</option>");
			/* 582 */ while (it.hasNext()) {
				/* 583 */ key = ((String) it.next()).toString();
				/* 584 */ item = (List) items.get(key);
				/* 585 */ value = ((String) item.get(1)).toString();
				/* 586 */ if ("0".equals(((String) item.get(2)).toString())) {
					/* 589 */ count++;
					/* 590 */ sb.append("      <option value=\"" + value + "\"");
					/* 591 */ if (valueArray != null && valueArray.length != 0) {
						/* 594 */ for (String element : valueArray) {
							/* 595 */ if (element.equals(value)) {
								/* 596 */ sb.append(" selected ");
							}
						}
					}

					/* 601 */ sb.append(">" + key + "</option>");
					/* 602 */ sb.append("\r\n");
				}
			}
			/* 605 */ sb.append("    </select>");
		}

		/* 610 */ return sb.toString();

	}

	public static void main(String[] argv) {
		Dictionary dic = Dictionary_Cache_Impl.getInstance();
		log.debug(dic.getJspCode("CHECKBOX", "学历", "XULI", null, null));
	}
}
