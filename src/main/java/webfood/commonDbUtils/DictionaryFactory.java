package webfood.commonDbUtils;

import org.apache.log4j.Logger;

public class DictionaryFactory
{
	private static final Logger log = Logger.getLogger(DictionaryFactory.class);
	private static Dictionary dic = null;

	public static Dictionary getDictionary(boolean useCache)
	{
		if (useCache)
		{
			if (dic == null)
			{
				dic = Dictionary_Cache_Impl.getInstance();
			}
			return dic;
		}
		if (dic == null)
		{
			dic = Dictionary_NoCache_Impl.getInstance();
		}
		return dic;
	}

	public static void clearForFinalize()
	{
		dic.clearForFinalize();
		dic = null;
	}

	public static void main(String[] argv)
	{
		Dictionary dic = getDictionary(true);
		log.debug(dic.getName("test", "ddd"));
	}
}
