package br.com.diegosilva.sched.client.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

public abstract class HttpConnectionUtils {

    public static String request(String urlStr, Method method, Map<String, Object> params, String body,
                                 Map<String, String> headerKeys) throws IOException {

        URL url = new URL(urlStr);
        HttpURLConnection connection = null;
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(method.name());
        if (headerKeys != null)
            adicionarHeaders(connection, headerKeys);
        connection.setUseCaches(false);
        connection.connect();
        if (params != null && params.size() > 0) {
            String urlParameters = createParams(params);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
        }

        if (body != null) {
            byte[] bodyBytes = body.getBytes("UTF-8");
            OutputStream os = connection.getOutputStream();
            os.write(bodyBytes);
            os.close();
        }

        String response = null;
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK
                || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
            response = getStringFromInputStream(connection.getInputStream());
        } else {
            response = getStringFromInputStream(connection.getErrorStream());
        }
        return response;
    }

    public static void adicionarHeaders(HttpURLConnection connection, Map<String, String> headerKeys) {
        for (Entry<String, String> entry : headerKeys.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    private static String createParams(Map<String, Object> paramsMap) throws UnsupportedEncodingException {
        StringBuilder paramsData = new StringBuilder();
        for (Entry<String, Object> param : paramsMap.entrySet()) {
            if (paramsData.length() != 0)
                paramsData.append('&');
            try {
                paramsData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                paramsData.append('=');
                paramsData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw e;
            }
        }
        return paramsData.toString();
    }

    private static String getStringFromInputStream(InputStream is) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            if (is != null) {
                br = new BufferedReader(new InputStreamReader(is));
                while (br != null && (line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        return sb.toString();
    }

    public static enum Method {
        OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE
    }

    public static class HeaderKeys {
        public static final String ContentType = "Content-Type";
    }

    public static class ContentTypes {
        public static final String Json = "application/json";
        public static final String Form = "application/x-www-form-urlencoded";
    }

//	public static void main(String ...args) {
//
//		String base64 = "ZTE3dDBlYnNwcWxsOTR1M2U1MWU3ZzQ4cG86aTAzdjRvamFpZ2Q2ZGIzNGQyYnBuNWg2ZzM=";
//
//		Map<String, Object> params = new HashMap<>();
//		params.put("grant_type", "client_credentials");
//		params.put("scope", "escopo_autentikus");
//
//		Map<String, String> headers = new HashMap<>();
//		headers.put("content-type", "application/x-www-form-urlencoded");
//		headers.put("Authorization", "Basic ZTE3dDBlYnNwcWxsOTR1M2U1MWU3ZzQ4cG86aTAzdjRvamFpZ2Q2ZGIzNGQyYnBuNWg2ZzM=");
//
//		try {
//			String retorno = request("https://valautentikus.estaleiro.serpro.gov.br/autentikus-authn/api/v1/token", Method.POST, params, null, headers);
//			System.out.println(retorno);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
