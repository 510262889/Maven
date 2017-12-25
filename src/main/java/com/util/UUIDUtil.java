package util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UUID工具类
 */
public class UUIDUtil {
    /** 生成UUID */
    public static synchronized String generate() {
        String rawUuid = UUID.randomUUID().toString().toLowerCase();
        Pattern p = Pattern.compile( "[^\\w]", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE );
        Matcher m = p.matcher( rawUuid );
        String uuid = m.replaceAll( "" );
        m.matches();
        return uuid;

    }
}
