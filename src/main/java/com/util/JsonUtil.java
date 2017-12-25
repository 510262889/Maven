package util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.ser.StdSerializerProvider;
import org.codehaus.jackson.type.TypeReference;

import com.exception.MyExpection;
import com.fasterxml.jackson.module.hibernate.HibernateModule;


/**
 * Json工具类
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper;
    static {
        StdSerializerProvider sp = new StdSerializerProvider();

        objectMapper = new ObjectMapper( null, sp, null );
        objectMapper.disable( DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES );
        objectMapper.disable( DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES );
        objectMapper.setDateFormat( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) );
        // 配置日期格式自定义的反序列化方法
        SimpleModule dateModule = new SimpleModule( "myCustomerDateModule", new Version( 1, 0, 0, null ) );
        dateModule.addDeserializer( Date.class, new CustomDeSerializer( Date.class ) );
        objectMapper.registerModule( dateModule );
        // 配置hibernate序列号支持
        HibernateModule module = new HibernateModule();
        module.configure( HibernateModule.Feature.FORCE_LAZY_LOADING, false );
        objectMapper.registerModule( module );
    }

    private static class CustomDeSerializer extends StdDeserializer<Date> {
        private Log log = LogFactory.getLog( CustomDeSerializer.class );

        protected CustomDeSerializer(Class<?> vc) {
            super( vc );
        }

        @Override
        public Date deserialize( JsonParser parser, DeserializationContext arg1 ) throws IOException, JsonProcessingException {
            String value = parser.getText();
            if ( (value == null) || (value.equals( "" )) ) return null;
            DateFormat df = null;
            int ymdLength = value.split( "-" ).length;
            int hmsLength = value.split( ":" ).length;
            if ( (ymdLength == 2) && (hmsLength == 1) ) df = new SimpleDateFormat( "yyyy-MM" );
            else if ( (ymdLength == 3) && (hmsLength == 1) ) df = new SimpleDateFormat( "yyyy-MM-dd" );
            else if ( (ymdLength == 3) && (hmsLength == 2) ) df = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
            else if ( (ymdLength == 3) && (hmsLength == 3) ) df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            try {
                if ( df != null ) return df.parse( value );
            } catch ( ParseException e ) {
                if ( this.log.isErrorEnabled() ) this.log.error( e.getMessage() );
            }
            return null;
        }
    }

    /**
     * 将对象转为json字符串
     */
    public static String toJson( Object obj ) {
        try {
            return objectMapper.writeValueAsString( obj );
        } catch ( Exception e ) {
        	throw new MyExpection(e);
        }
    }

    /**
     * 解析json字符串为对象
     */
    public static <T> T parseJson( String text, Class<T> type ) {
        try {
            return objectMapper.readValue( text, type );
        } catch ( Exception e ) {
        	throw new MyExpection(e);
        }
    }

    /**
     * @param addData
     * @param typeReference
     * @return
     */
    public static <T> T parseJson( String text, TypeReference<T> type ) {
        try {
            return objectMapper.readValue( text, type );
        } catch ( Exception e ) {
        	throw new MyExpection(e);
        }
    }

}
