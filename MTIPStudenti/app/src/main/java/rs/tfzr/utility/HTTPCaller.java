package rs.tfzr.utility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPCaller
{
    public static RESTResponseData performMethodCall(String requestURL, EnumHTTPMethod method, String data) {

        RESTResponseData _response = new RESTResponseData();

        URL url;
        HttpURLConnection connection;
        try
        {
            url = new URL(requestURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toString());

            connection.setUseCaches(false);

            switch (method)
            {
                case GET:
                    {
                        connection.connect();
                        if (connection.getResponseCode() == 200)
                        {
                        InputStream stream = connection.getInputStream();
                        if (stream != null)
                            _response.set_responseData(convertInputStreamToString(stream));
                    } else
                        _response.set_responseData("");
                    break;
                }
                case POST:
                case PUT:
                    {
                    connection.connect();
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(data);
                    out.close();
                    break;
                }
                case DELETE:
                    {
                        connection.connect();
                        break;
                    }
            }

            _response.set_requestData(data);
            _response.set_requestMethod(method.toString());
            _response.set_requestURL(requestURL);
            _response.set_responseStatusCode(connection.getResponseCode());

            connection.disconnect();

        }
        catch (Exception e)
        {
            _response.set_responseData(e.toString());
            _response.set_responseStatusCode(-1);
        }
        return _response;
    }

    private static String convertInputStreamToString(InputStream inputStream)
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";

        try
        {
            while ((line = bufferedReader.readLine()) != null)
                result += line;
            inputStream.close();
        }
        catch (Exception ex)
        {
        }
        return result;
    }
}

