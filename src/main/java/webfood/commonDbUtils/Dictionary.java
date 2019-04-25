package webfood.commonDbUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface Dictionary extends Serializable
{
	public abstract Map<String, List<String>> getItems(String paramString);

	public abstract String getValue(String paramString1, String paramString2);

	public abstract String getName(String paramString1, String paramString2);

	public abstract String getRemark(String paramString1, String paramString2);

	public abstract String getState(String paramString1, String paramString2);

	public abstract void clearForFinalize();

	public abstract void refresh(boolean paramBoolean);

	public abstract String getJspCode(String paramString1, String paramString2, String paramString3,
			String paramString4, String paramString5);

	public abstract String getHtmlCode(String paramString1, String paramString2, String paramString3,
			String paramString4, String paramString5);
}
