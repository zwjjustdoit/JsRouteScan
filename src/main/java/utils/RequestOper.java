package utils;

import burp.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class RequestOper {

    public static List<String> headersProc(List<String> headers) {
        return null;
    }

    public static String geturl(BurpExtender burp, IHttpRequestResponse iHttpRequestResponse) {
        IRequestInfo requestInfo = burp.helpers.analyzeRequest(iHttpRequestResponse);
        URL url = requestInfo.getUrl();
        return url.toString();
    }

    public static String getUri(IHttpRequestResponse iHttpRequestResponse) {
        IHttpService httpService = iHttpRequestResponse.getHttpService();
        return httpService.getProtocol() + "://" + httpService.getHost() + ":" + httpService.getPort();
    }

    public static String getServiceUri(IHttpService httpService) {
        return httpService.getProtocol() + "://" + httpService.getHost() + ":" + httpService.getPort();
    }

    public static List<String> getHeaders(BurpExtender burp, IHttpRequestResponse iHttpRequestResponse) {
        //        headers.remove(0);
        return burp.helpers.analyzeRequest(iHttpRequestResponse).getHeaders();
    }

    public static String getHost(IHttpRequestResponse iHttpRequestResponse) {
        IHttpService httpService = iHttpRequestResponse.getHttpService();
        return httpService.getHost() + ":" + httpService.getPort();
    }

    public static String getRoute(BurpExtender burp, IHttpRequestResponse iHttpRequestResponse) {
        IRequestInfo iRequestInfo = burp.helpers.analyzeRequest(iHttpRequestResponse);
        return iRequestInfo.getUrl().getPath();
    }

    public static String getStatus(BurpExtender burp, IHttpRequestResponse iHttpRequestResponse) {
        if (iHttpRequestResponse.getResponse() == null) {
            return "0";
        }
        IResponseInfo iResponseInfo = burp.helpers.analyzeResponse(iHttpRequestResponse.getResponse());
        return String.valueOf(iResponseInfo.getStatusCode());
    }

    public static String getTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
        return now.format(formatter);
    }

    public static List<IHttpRequestResponse> matchPrefix(List<IHttpRequestResponse> siteMap, IExtensionHelpers helpers) {
        List<IHttpRequestResponse> retList = new ArrayList<>();
        for (IHttpRequestResponse item : siteMap) {
            URL url = helpers.analyzeRequest(item).getUrl();
            if (url.getPort() == 443 || url.getPort() == 80) {
                retList.add(item);
            }
        }
        return retList;
    }

    public static List<String> parseHead(String headerText) {
        if (headerText.equals("")) {
            return null;
        }
        List<String> rows = new ArrayList<>();
        for (String row : headerText.split("\n")) {
            if (!row.equals("")) {
                rows.add(row);
            }
        }
        if (rows.size() == 0) {
            return null;
        }
        return rows;
    }
//    public static List<Map<String,String>> parseHead(String headerText) {
//        if (headerText.equals("")) {
//            return null;
//        }
//        List<Map<String,String>> rows = new ArrayList<>();
//        for (String row : headerText.split("\n")) {
//            if (!row.equals("")) {
//                String[] split = row.split(":", 2);
//                if (split.length == 2) {
//                    String key = split[0].trim();
//                    String value = split[1].trim();
//                    Map<String, String> map = new HashMap<>();
//                    map.put(key, value);
//                    rows.add(map);
//                }
//            }
//        }
//        if (rows.size() == 0) {
//            return null;
//        }
//        return rows;
//    }
}
