package rs.tfzr.data;

public class MTIPShared {

    private static String _serviceBaseURL = "http://192.168.106.21/mtip/services/";

    public static String get_serviceBaseURL() {
        return _serviceBaseURL;
    }

    public static void set_serviceBaseURL(String _serviceBaseURL) {
        MTIPShared._serviceBaseURL = _serviceBaseURL;
    }
}
