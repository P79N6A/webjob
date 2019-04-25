package webfood.commonDbUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class Dictionary_Cache_Impl implements Dictionary
{
	private static final long serialVersionUID = 1724139513882264816L;
	private static final Logger log = Logger.getLogger(Dictionary_Cache_Impl.class);
	private static final String RETN = "\r\n";
	private Map<String, Map<String, List<String>>> dic = new LinkedHashMap();
	private static Dictionary instance = null;

	private Dictionary_Cache_Impl()
	{
		refresh(true);
	}

	public static Dictionary getInstance()
	{
		if (instance == null)
		{
			instance = new Dictionary_Cache_Impl();
		}
		return instance;
	}

	@Override
	public synchronized void refresh(boolean closeSession)
	{
	}

	@Override
	public Map<String, List<String>> getItems(String dicClassName)
	{
		Map items = this.dic.get(dicClassName);
		if (items == null)
		{
			return null;
		}
		Map result = new LinkedHashMap();

		result.putAll(items);
		return result;
	}

	@Override
	public String getValue(String dicClassName, String dicItemName)
	{
		if (dicItemName == null || dicClassName == null)
		{
			return null;
		}
		Map items = this.dic.get(dicClassName);
		if (items == null)
		{
			return dicItemName;
		}
		List item = (List) items.get(dicItemName);
		return ((String) item.get(1)).toString();
	}

	@Override
	public String getName(String dicClassName, String dicItemValue)
	{
		if (dicItemValue == null || dicClassName == null)
		{
			return null;
		}
		Map items = this.dic.get(dicClassName);
		if (items == null)
		{
			return dicItemValue;
		}
		Iterator it = items.keySet().iterator();
		while (it.hasNext())
		{
			String name = (String) it.next();
			List item = (List) items.get(name);
			if (((String) item.get(1)).toString().equals(dicItemValue))
			{
				return name;

			}
		}
		return dicItemValue;
	}

	@Override
	public String toString()
	{
		return this.dic.toString();
	}

	@Override
	public void clearForFinalize()
	{
		instance = null;
	}

	@Override
	public String getState(String dicClassName, String dicItemName)
	{
		if (StringUtils.isEmpty(dicClassName))
		{
			log.warn("DicClassName为空！");
			return null;
		}
		if (StringUtils.isEmpty(dicItemName))
		{
			log.warn("DicItemName为空！");
			return null;
		}
		Map items = this.dic.get(dicClassName);
		if (items == null)
		{
			return null;
		}
		List item = (List) items.get(dicItemName);
		return ((String) item.get(2)).toString();
	}

	@Override
	public String getRemark(String dicClassName, String dicItemName)
	{
		/* 193 */ if (StringUtils.isEmpty(dicClassName))
		{
			/* 194 */ log.warn("DicClassName为空！");
			/* 195 */ return null;
		}
		/* 197 */ if (StringUtils.isEmpty(dicItemName))
		{
			/* 198 */ log.warn("DicItemName为空！");
			/* 199 */ return null;
		}
		/* 201 */ Map items = this.dic.get(dicClassName);
		/* 202 */ if (items == null)
		{
			/* 203 */ return null;
		}
		/* 204 */ List item = (List) items.get(dicItemName);
		/* 205 */ return ((String) item.get(3)).toString();
	}

	@Override
	public void finalize()
	{
		/* 209 */ log.info("被垃圾回收！");
		/* 210 */ System.out/* 211 */ .println(
				"\r\n\r\n------------------------------------\r\nDictionary对象被垃圾回收！\r\n\r\n------------------------------------\r\n");
	}

	@Override
	public String getJspCode(String style, String dicClassName, String inputName, String values, String desc)
	{
		/* 233 */ if (StringUtils.isEmpty(style) || StringUtils.isEmpty(dicClassName))
		{
			/* 234 */ return "";
		}
		/* 235 */ style = style.toUpperCase();
		/* 236 */ Map items = getItems(dicClassName);
		/* 237 */ if (items == null)
		{
			/* 238 */ return "";
		}

		/* 241 */ desc = StringUtils.trimToEmpty(desc);

		/* 245 */ StringBuffer sb = new StringBuffer();
		/* 246 */ if (style.equals("INPUT"))
		{
			/* 247 */ String formName = "form1";
			/* 248 */ if (inputName.indexOf(".") != -1)
			{
				/* 249 */ formName = inputName.substring(0, inputName.indexOf("."));
				/* 250 */ inputName = inputName.substring(inputName.indexOf(".") + 1);
			}
			/* 252 */ sb.append("      <input type=\"text\"  name=\"" + inputName +
			/* 253 */ "\" id=\"" + inputName + "\" value=\"" +
			/* 254 */ StringUtils.trimToEmpty(values) + "\" " + desc + ">");
			/* 255 */ sb.append("\r\n");
			/* 256 */ sb.append("<%");
			/* 257 */ sb.append("\r\n");
			/* 258 */ sb.append("    items = dic.getItems(\"" + dicClassName + "\");");
			/* 259 */ sb.append("\r\n");
			/* 260 */ sb.append("    it = items.keySet().iterator();");
			/* 261 */ sb.append("\r\n");
			/* 262 */ sb.append("    while(it.hasNext()){");
			/* 263 */ sb.append("\r\n");
			/* 264 */ sb.append("      String _k_name = it.next().toString();");
			/* 265 */ sb.append("\r\n");
			/* 266 */ sb.append("      item = (List)items.get(_k_name);");
			/* 267 */ sb.append("\r\n");
			/* 268 */ sb.append("      if(!\"0\".equals(item.get(2).toString()))");
			/* 269 */ sb.append("\r\n");
			/* 270 */ sb.append("        continue;");
			/* 271 */ sb.append("\r\n");
			/* 272 */ sb.append("%>");
			/* 273 */ sb.append("\r\n");
			/* 274 */ sb.append("      <a href=\"#\" onclick=\"setValue('" + formName +
			/* 275 */ "','" + inputName +
			/* 276 */ "','<%=item.get(1)%>');\"><%=_k_name%></a>");
			/* 277 */ sb.append("\r\n");
			/* 278 */ sb.append("<%");
			/* 279 */ sb.append("\r\n");
			/* 280 */ sb.append("    }");
			/* 281 */ sb.append("\r\n");
			/* 282 */ sb.append("%>");
			/* 283 */ sb.append("\r\n");
			/* 284 */ }
		else if (style.equals("RADIO"))
		{
			/* 285 */ sb.append("<%");
			/* 286 */ sb.append("\r\n");
			/* 287 */ sb.append("    items = dic.getItems(\"" + dicClassName + "\");");
			/* 288 */ sb.append("\r\n");
			/* 289 */ sb.append("    it = items.keySet().iterator(); _count = 0;");
			/* 290 */ sb.append("\r\n");
			/* 291 */ sb.append("    while(it.hasNext()){");
			/* 292 */ sb.append("\r\n");
			/* 293 */ sb.append("      String _k_name = it.next().toString();");
			/* 294 */ sb.append("\r\n");
			/* 295 */ sb.append("      item = (List)items.get(_k_name);");
			/* 296 */ sb.append("\r\n");
			/* 297 */ sb.append("      if(!\"0\".equals(item.get(2).toString()))");
			/* 298 */ sb.append("\r\n");
			/* 299 */ sb.append("        continue;");
			/* 300 */ sb.append("\r\n");
			/* 301 */ sb.append("      else{");
			/* 302 */ sb.append("\r\n");
			/* 303 */ sb.append("        _count++;");
			/* 304 */ sb.append("\r\n");
			/* 305 */ sb.append("%>");
			/* 306 */ sb.append("\r\n");
			/* 307 */ sb/* 308 */ .append("      <input type=\"radio\" name=\"" +
			/* 309 */ inputName +
			/* 310 */ "\" value=\"<%=item.get(1)%>\" <%=_count==1?\" checked \":\"\"%> " +
			/* 311 */ desc + "><%=_k_name%> ");
			/* 312 */ sb.append("\r\n");
			/* 313 */ sb.append("<%");
			/* 314 */ sb.append("\r\n");
			/* 315 */ sb.append("      }");
			/* 316 */ sb.append("\r\n");
			/* 317 */ sb.append("    }");
			/* 318 */ sb.append("\r\n");
			/* 319 */ sb.append("%>");
			/* 320 */ sb.append("\r\n");
		}
		/* 322 */ else if (style.equals("CHECKBOX"))
		{
			/* 323 */ sb.append("<%");
			/* 324 */ sb.append("\r\n");
			/* 325 */ sb.append("    items = dic.getItems(\"" + dicClassName + "\");");
			/* 326 */ sb.append("\r\n");
			/* 327 */ sb.append("    it = items.keySet().iterator();");
			/* 328 */ sb.append("\r\n");
			/* 329 */ sb.append("    while(it.hasNext()){");
			/* 330 */ sb.append("\r\n");
			/* 331 */ sb.append("      String _k_name = it.next().toString();");
			/* 332 */ sb.append("\r\n");
			/* 333 */ sb.append("      item = (List)items.get(_k_name);");
			/* 334 */ sb.append("\r\n");
			/* 335 */ sb.append("      if(!\"0\".equals(item.get(2).toString()))");
			/* 336 */ sb.append("\r\n");
			/* 337 */ sb.append("        continue;");
			/* 338 */ sb.append("\r\n");
			/* 339 */ sb.append("      else{");
			/* 340 */ sb.append("\r\n");
			/* 341 */ sb.append("%>");
			/* 342 */ sb.append("\r\n");
			/* 343 */ sb.append("      <input type=\"checkbox\" name=\"" + inputName +
			/* 344 */ "\" value=\"<%=item.get(1)%>\" " + desc +
			/* 345 */ " ><%=_k_name%> ");
			/* 346 */ sb.append("\r\n");
			/* 347 */ sb.append("<%");
			/* 348 */ sb.append("\r\n");
			/* 349 */ sb.append("      }");
			/* 350 */ sb.append("\r\n");
			/* 351 */ sb.append("    }");
			/* 352 */ sb.append("\r\n");
			/* 353 */ sb.append("%>");
			/* 354 */ sb.append("\r\n");
			/* 355 */ }
		else if (style.equals("LISTBOX"))
		{
			/* 356 */ sb.append("<%");
			/* 357 */ sb.append("\r\n");
			/* 358 */ sb.append("    items = dic.getItems(\"" + dicClassName + "\");");
			/* 359 */ sb.append("\r\n");
			/* 360 */ sb.append("    it = items.keySet().iterator();");
			/* 361 */ sb.append("\r\n");
			/* 362 */ sb.append("%>\r\n");
			/* 363 */ sb.append("    <select name=\"" + inputName +
			/* 364 */ "\" size=\"<%=items.size()%>\" multiple " + desc + ">");
			/* 365 */ sb.append("\r\n");
			/* 366 */ sb.append("<%");
			/* 367 */ sb.append("\r\n");
			/* 368 */ sb.append("    while(it.hasNext()){");
			/* 369 */ sb.append("\r\n");
			/* 370 */ sb.append("      String _k_name = it.next().toString();");
			/* 371 */ sb.append("\r\n");
			/* 372 */ sb.append("      item = (List)items.get(_k_name);");
			/* 373 */ sb.append("\r\n");
			/* 374 */ sb.append("      if(!\"0\".equals(item.get(2).toString()))");
			/* 375 */ sb.append("\r\n");
			/* 376 */ sb.append("        continue;");
			/* 377 */ sb.append("\r\n");
			/* 378 */ sb.append("%>");
			/* 379 */ sb.append("\r\n");
			/* 380 */ sb/* 381 */ .append("      <option value=\"<%=item.get(1)%>\"><%=_k_name%></option>");
			/* 382 */ sb.append("\r\n");
			/* 383 */ sb.append("<%");
			/* 384 */ sb.append("\r\n");
			/* 385 */ sb.append("    }");
			/* 386 */ sb.append("\r\n");
			/* 387 */ sb.append("%>");
			/* 388 */ sb.append("\r\n");
			/* 389 */ sb.append("    </select>");
			/* 390 */ sb.append("\r\n");
		}
		/* 392 */ else if (style.equals("DROPBOX"))
		{
			/* 393 */ sb.append("<%");
			/* 394 */ sb.append("\r\n");
			/* 395 */ sb.append("    items = dic.getItems(\"" + dicClassName + "\");");
			/* 396 */ sb.append("\r\n");
			/* 397 */ sb.append("    it = items.keySet().iterator();");
			/* 398 */ sb.append("\r\n");
			/* 399 */ sb.append("%>\r\n");
			/* 400 */ sb.append("    <select name=\"" + inputName + "\" " + desc + ">");
			/* 401 */ sb.append("\r\n");
			/* 402 */ sb.append("<%");
			/* 403 */ sb.append("\r\n");
			/* 404 */ sb.append("    while(it.hasNext()){");
			/* 405 */ sb.append("\r\n");
			/* 406 */ sb.append("      String _k_name = it.next().toString();");
			/* 407 */ sb.append("\r\n");
			/* 408 */ sb.append("      item = (List)items.get(_k_name);");
			/* 409 */ sb.append("\r\n");
			/* 410 */ sb.append("      if(!\"0\".equals(item.get(2).toString()))");
			/* 411 */ sb.append("\r\n");
			/* 412 */ sb.append("        continue;");
			/* 413 */ sb.append("\r\n");
			/* 414 */ sb.append("%>");
			/* 415 */ sb.append("\r\n");
			/* 416 */ sb/* 417 */ .append("      <option value=\"<%=item.get(1)%>\"><%=_k_name%></option>");
			/* 418 */ sb.append("\r\n");
			/* 419 */ sb.append("<%");
			/* 420 */ sb.append("\r\n");
			/* 421 */ sb.append("    }");
			/* 422 */ sb.append("\r\n");
			/* 423 */ sb.append("%>");
			/* 424 */ sb.append("\r\n");
			/* 425 */ sb.append("    </select>");
			/* 426 */ sb.append("\r\n");
		}

		/* 429 */ return sb.toString();
	}

	@Override
	public String getHtmlCode(String style, String dicClassName, String inputName, String values, String desc)
	{
		/* 451 */ if (StringUtils.isEmpty(style) || StringUtils.isEmpty(dicClassName))
		{
			/* 452 */ return "";
		}
		/* 454 */ style = style.toUpperCase();
		/* 455 */ Map items = getItems(dicClassName);
		/* 456 */ if (items == null)
		{
			/* 457 */ return "";
		}
		/* 458 */ Iterator it = null;
		/* 459 */ List item = null;
		/* 460 */ String key = null;
		/* 461 */ String value = null;
		/* 462 */ String[] valueArray = StringUtils.split(values, ";");
		/* 463 */ desc = StringUtils.trimToEmpty(desc);

		/* 465 */ int count = 0;
		/* 466 */ StringBuffer sb = new StringBuffer();
		/* 467 */ if (style.equals("INPUT"))
		{
			/* 468 */ String formName = "form1";
			/* 469 */ if (inputName.indexOf(".") != -1)
			{
				/* 470 */ formName = inputName.substring(0, inputName.indexOf("."));
				/* 471 */ inputName = inputName.substring(inputName.indexOf(".") + 1);
			}
			/* 473 */ sb.append("<input type=\"text\"  name=\"" + inputName +
			/* 474 */ "\" id=\"" + inputName + "\" value=\"" +
			/* 475 */ StringUtils.trimToEmpty(values) + "\" " + desc + "  >");
			/* 476 */ sb.append("\r\n");
			/* 477 */ it = items.keySet().iterator();
			/* 478 */ while (it.hasNext())
			{
				/* 479 */ key = ((String) it.next()).toString();
				/* 480 */ item = (List) items.get(key);
				/* 481 */ value = ((String) item.get(1)).toString();
				/* 482 */ if ("0".equals(((String) item.get(2)).toString()))
				{
					/* 485 */ sb.append("      <a href=\"###\" onClick=\"setValue('" +
					/* 486 */ formName + "','" + inputName + "', '" + value +
					/* 487 */ "');\">" + key + "</a> ");
				}
			}
		}
		/* 491 */ else if (style.equals("RADIO"))
		{
			/* 492 */ it = items.keySet().iterator();
			/* 493 */ while (it.hasNext())
			{
				/* 494 */ key = ((String) it.next()).toString();
				/* 495 */ item = (List) items.get(key);
				/* 496 */ value = ((String) item.get(1)).toString();
				/* 497 */ if ("0".equals(((String) item.get(2)).toString()))
				{
					/* 500 */ count++;
					/* 501 */ sb.append("<input type=\"radio\" name=\"" + inputName +
					/* 502 */ "\"  id=\"" +
					/* 503 */ inputName + "\" value=\"" + value + "\" " + desc);
					/* 504 */ if (valueArray == null || valueArray.length == 0)
					{
						/* 505 */ if (count == 1)
						{
							/* 506 */ sb.append(" checked ");

						}
					}
					else
					{
						for (String element : valueArray)
						{
							/* 509 */ if (element.equals(value))
							{
								/* 510 */ sb.append(" checked ");
							}
						}
					}

					/* 514 */ sb.append(">" + key);
				}
			}
		}
		/* 518 */ else if (style.equals("CHECKBOX"))
		{
			/* 519 */ it = items.keySet().iterator();
			/* 520 */ while (it.hasNext())
			{
				/* 521 */ key = ((String) it.next()).toString();
				/* 522 */ item = (List) items.get(key);
				/* 523 */ value = ((String) item.get(1)).toString();
				/* 524 */ if ("0".equals(((String) item.get(2)).toString()))
				{
					/* 527 */ count++;
					/* 528 */ sb.append("<input type=\"checkbox\" name=\"" +
					/* 529 */ inputName + "\" id=\"" +
					/* 530 */ inputName + "\" value=\"" + value + "\" " + desc);
					/* 531 */ if (valueArray != null && valueArray.length != 0)
					{
						/* 534 */ for (String element : valueArray)
						{
							/* 536 */ if (element.equals(value))
							{
								/* 537 */ sb.append(" checked ");
							}
						}
					}
					/* 541 */ sb.append(">" + key);
				}
			}
		}
		/* 545 */ else if (style.equals("LISTBOX"))
		{
			/* 546 */ it = items.keySet().iterator();
			/* 547 */ sb.append("<select name=\"" + inputName + "\" id=\"" +
			/* 548 */ inputName + "\" size=\"" +
			/* 549 */ items.size() + "\" " + desc + " multiple>");
			/* 550 */ sb.append("\r\n");
			/* 551 */ while (it.hasNext())
			{
				/* 552 */ key = ((String) it.next()).toString();
				/* 553 */ item = (List) items.get(key);
				/* 554 */ value = ((String) item.get(1)).toString();
				/* 555 */ if ("0".equals(((String) item.get(2)).toString()))
				{
					/* 558 */ count++;
					/* 559 */ sb.append("      <option value=\"" + value + "\"");
					/* 560 */ if (valueArray != null && valueArray.length != 0)
					{
						/* 563 */ for (String element : valueArray)
						{
							/* 564 */ if (element.equals(value))
							{
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
		/* 576 */ else if (style.equals("DROPBOX"))
		{
			/* 577 */ it = items.keySet().iterator();
			/* 578 */ sb.append("<select name=\"" + inputName + "\"  id=\"" +
			/* 579 */ inputName + "\" " + desc + " >");
			/* 580 */ sb.append("\r\n");
			/* 581 */ sb.append("      <option value=\"\">--请选择--</option>");
			/* 582 */ while (it.hasNext())
			{
				/* 583 */ key = ((String) it.next()).toString();
				/* 584 */ item = (List) items.get(key);
				/* 585 */ value = ((String) item.get(1)).toString();
				/* 586 */ if ("0".equals(((String) item.get(2)).toString()))
				{
					/* 589 */ count++;
					/* 590 */ sb.append("      <option value=\"" + value + "\"");
					/* 591 */ if (valueArray != null && valueArray.length != 0)
					{
						/* 594 */ for (String element : valueArray)
						{
							/* 595 */ if (element.equals(value))
							{
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

	public static void main(String[] argv)
	{
		/* 614 */ Dictionary dic = getInstance();
		/* 615 */ log.debug(dic.getHtmlCode("listbox", "学历", "XUELI", "3", " canNull=\"false\" "));
		/* 616 */ Integer t = Integer.valueOf(3);
		/* 617 */ String a = Integer.toBinaryString(t.intValue());
		/* 618 */ log.debug(t + " 的二进制序 列:" + a);
	}
}
