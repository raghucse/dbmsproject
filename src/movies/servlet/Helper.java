package movies.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Helper {

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static String getUsernameFromCookie(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        String username = null;
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();
            if(name.equals("user")){
                username = cookies[i].getValue();
            }
        }
        return username;

    }

}
