package webfood.utils.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 *
 * @author Administrator
 *
 */
public class TrustAnyTrustManager implements X509TrustManager
{

	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
	{
		// TODO Auto-generated method stub

	}

	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
	{
		// TODO Auto-generated method stub

	}

	public X509Certificate[] getAcceptedIssuers()
	{
		// TODO Auto-generated method stub
		return null;
	}
}