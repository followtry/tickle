package cn.followtry.comm.spring.converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 针对 Integer、Long、Date 等类型实现 ITypeConverter 接口
 * @author: haozhongweng
 * @create: 2018/6/29 12:00
 */
public class ConverterFactory {

    private static final String timeStampPattern = "yyyy-MM-dd HH:mm:ss";
    private static final String datePattern = "yyyy-MM-dd";
    private static final int dateLen = datePattern.length();
    private static final int timeStampWithoutSecPatternLen = timeStampPattern.length();
    private static final int timePatternLen = "hh:mm:ss".length();
    private static final int timeWithoutSecPatternLen = "hh:mm".length();

    private ConverterFactory() {
    }

    public static class IntegerTypeConverter implements ITypeConverter<Integer> {
        /**
         * mysql type: int, integer, tinyint(n) n > 1, smallint, mediumint
         */
        @Override
        public Integer convert(String s) {
            return Integer.parseInt(s);
        }
    }

    /**
     * 支持需要保持 short 而非转成 int 的场景
     */
    public static class ShortTypeConverter implements ITypeConverter<Short> {
        @Override
        public Short convert(String s) {
            return Short.parseShort(s);
        }
    }

    /**
     * 支持需要保持 byte 而非转成 int 的场景
     */
    public static class ByteTypeConverter implements ITypeConverter<Byte> {
        @Override
        public Byte convert(String s) {
            return Byte.parseByte(s);
        }
    }

    public static class LongTypeConverter implements ITypeConverter<Long> {
        // mysql type: bigint
        @Override
        public Long convert(String s) {
            return Long.parseLong(s);
        }
    }

    public static class FloatTypeConverter implements ITypeConverter<Float> {
        // mysql type: float
        @Override
        public Float convert(String s) {
            return Float.parseFloat(s);
        }
    }

    public static class DoubleTypeConverter implements ITypeConverter<Double> {
        // mysql type: real, double
        @Override
        public Double convert(String s) {
            return Double.parseDouble(s);
        }
    }

    public static class ByteArrayTypeConverter implements ITypeConverter<byte[]> {
        // mysql type: binary, varbinary, tinyblob, blob, mediumblob, longblob. I have not finished the test.
        @Override
        public byte[] convert(String s) {
            return s.getBytes();
        }
    }

    public static class BigIntegerTypeConverter implements ITypeConverter<BigInteger> {
        // mysql type: unsigned bigint
        @Override
        public BigInteger convert(String s) {
            return new BigInteger(s);
        }
    }

    public static class BigDecimalTypeConverter implements ITypeConverter<BigDecimal> {
        // mysql type: decimal, numeric
        @Override
        public BigDecimal convert(String s) {
            return new BigDecimal(s);
        }
    }

    public static class BooleanTypeConverter implements ITypeConverter<Boolean> {
        // mysql type: bit, tinyint(1)
        @Override
        public Boolean convert(String s) {
            String value = s.toLowerCase();
            if ("true".equals(value) || "1".equals(value) /* || "yes".equals(value) || "on".equals(value) */) {
                return Boolean.TRUE;
            } else if ("false".equals(value) || "0".equals(value) /* || "no".equals(value) || "off".equals(value) */) {
                return Boolean.FALSE;
            } else {
                throw new RuntimeException("Can not parse to boolean type of value: " + s);
            }
        }
    }

    public static class DateTypeConverter implements ITypeConverter<Date> {
        // java.util.Date 类型专为传统 java bean 带有该类型的 setter 方法转换做准备，万不可去掉
        // 经测试 JDBC 不会返回 java.util.Data 类型。java.sql.Date, java.sql.Time,java.sql.Timestamp 全部直接继承自 java.util.Data, 所以 getDate可以返回这三类数据
        @Override
        public Date convert(String s) throws ParseException {
            if (timeStampWithoutSecPatternLen == s.length()) {
                s = s + ":00";
            }
            if (s.length() > dateLen) {    // if (x < timeStampLen) 改用 datePattern 转换，更智能
                // Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]
                // return new java.util.Date(java.sql.Timestamp.valueOf(s).getTime());	// error under jdk 64bit(maybe)
                return new SimpleDateFormat(timeStampPattern).parse(s);
            } else {
                // return new java.util.Date(java.sql.Date.valueOf(s).getTime());	// error under jdk 64bit
                return new SimpleDateFormat(datePattern).parse(s);
            }
        }
    }

    public static class SqlDateTypeConverter implements ITypeConverter<java.sql.Date> {
        // mysql type: date, year
        @Override
        public java.sql.Date convert(String s) throws ParseException {
            if (timeStampWithoutSecPatternLen == s.length()) {
                s = s + ":00";
            }
            if (s.length() > dateLen) {    // if (x < timeStampLen) 改用 datePattern 转换，更智能
                // return new java.sql.Date(java.sql.Timestamp.valueOf(s).getTime());	// error under jdk 64bit(maybe)
                return new java.sql.Date(new SimpleDateFormat(timeStampPattern).parse(s).getTime());
            } else {
                // return new java.sql.Date(java.sql.Date.valueOf(s).getTime());	// error under jdk 64bit
                return new java.sql.Date(new SimpleDateFormat(datePattern).parse(s).getTime());
            }
        }
    }

    public static class TimeTypeConverter implements ITypeConverter<Time> {
        // mysql type: time
        @Override
        public Time convert(String s) {
            int len = s.length();
            if (len == timeWithoutSecPatternLen) {
                s = s + ":00";
            }
            if (len > timePatternLen) {
                s = s.substring(0, timePatternLen);
            }
            return Time.valueOf(s);
        }
    }

    public static class TimestampTypeConverter implements ITypeConverter<Timestamp> {
        // mysql type: timestamp, datetime
        @Override
        public Timestamp convert(String s) throws ParseException {
            if (timeStampWithoutSecPatternLen == s.length()) {
                s = s + ":00";
            }
            if (s.length() > dateLen) {
                return Timestamp.valueOf(s);
            } else {
                return new Timestamp(new SimpleDateFormat(datePattern).parse(s).getTime());
            }
        }
    }

    public static class ClassTypeConverter implements ITypeConverter<Class> {

        @Override
        public Class convert(String s) throws ClassNotFoundException {
            return Class.forName(s);
        }
    }

    public static class AtomicIntegerTypeConverter implements ITypeConverter<AtomicInteger> {
        @Override
        public AtomicInteger convert(String s) {
            return new AtomicInteger(Integer.parseInt(s));
        }
    }

    public static class AtomicLongTypeConverter implements ITypeConverter<AtomicLong> {
        @Override
        public AtomicLong convert(String s) {
            return new AtomicLong(Long.parseLong(s));
        }
    }

}
