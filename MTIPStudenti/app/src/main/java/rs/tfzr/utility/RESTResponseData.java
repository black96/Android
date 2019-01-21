package rs.tfzr.utility;

public class RESTResponseData {

    protected String _responseData;
    protected int _responseStatusCode;
    protected String _requestMethod;
    protected String _requestURL;
    protected String _requestData;

    public RESTResponseData()
    {
        this._requestData = "";
        this._responseStatusCode = -1;
        this._responseData = "";
        this._requestMethod = "";
        this._requestURL = "";
    }

    public String get_responseData() {
        return _responseData;
    }

    public void set_responseData(String _responseData) {
        this._responseData = _responseData;
    }

    public int get_responseStatusCode() {
        return _responseStatusCode;
    }

    public void set_responseStatusCode(int _responseStatusCode) {
        this._responseStatusCode = _responseStatusCode;
    }

    public String get_requestMethod() {
        return _requestMethod;
    }

    public void set_requestMethod(String _requestMethod) {
        this._requestMethod = _requestMethod;
    }

    public String get_requestURL() {
        return _requestURL;
    }

    public void set_requestURL(String _requestURL) {
        this._requestURL = _requestURL;
    }

    public String get_requestData() {
        return _requestData;
    }

    public void set_requestData(String _requestData) {
        this._requestData = _requestData;
    }
}
