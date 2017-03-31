package cn.followtry.incubate;

import java.io.UnsupportedEncodingException;

/**
 * Created by followtry on 2017/3/22.
 */
public class StringTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str="荆中志";

		str.chars().mapToObj(ch -> Character.toChars(ch)).forEach(System.out::println);

		str.lastIndexOf(2);
		str.lastIndexOf(2,0);
		str.lastIndexOf("");
		str.lastIndexOf("", 0);
//		str.matches("");
//		str.offsetByCodePoints(0, 2);
//		str.regionMatches(true, 0, "", 0, 2);
//		str.split("\t", 0);
//		str.startsWith("");
//		str.startsWith("", 0);
//		str.substring(0);
//		str.substring(0, 20);
//		str.subSequence(0, 10);
//		str.toLowerCase();
//		str.toString();
//		str.toUpperCase();
//		str.toCharArray();
//		Locale local = Locale.CHINA;
//		str.toLowerCase(local);
//		str.toUpperCase(local);
//		str.trim();
	}
}
