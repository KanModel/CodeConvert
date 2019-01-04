package me.kanmodel.dec18.convert;

import java.util.HashMap;
import java.util.Map;

public class CodeTable {
	public static Map<Integer, String> vars= new HashMap<Integer, String>();
	public static void varsInit(){
		vars.put(Integer.valueOf("C9", 16), "count");
		vars.put(Integer.valueOf("CA", 16), "a0");
		vars.put(Integer.valueOf("CB", 16), "a1");
		vars.put(Integer.valueOf("CC", 16), "a2");
		vars.put(Integer.valueOf("CD", 16), "a3");
	}
	public static boolean isTwo = false;

	/*
	 * @param codeH ¸ß4Î»
	 * @param codeL µÍ4Î»
	 */
	public static String getCode(int codeH, int codeL) {
		switch (codeH) {
		case 7:
			return "CLR r" + codeL;
		case 8:
			return "MOV r" + (codeL >>> 2) + " r" + codeL % 4;
		case 9:
			return "ADC r" + (codeL >>> 2) + " r" + codeL % 4;
		case 10:
			return "SBC r" + (codeL >>> 2) + " r" + codeL % 4;
		case 11:
			return "INC r" + codeL % 4;
		case 12:
			return "AND r" + (codeL >>> 2) + " r" + codeL % 4;
		case 13:
			return "COM r" + codeL % 4;
		case 14:
			return "RRC r" + (codeL >>> 2) + " r" + codeL % 4;
		case 15:
			return "RLC r" + (codeL >>> 2) + " r" + codeL % 4;
		case 4:
			return "IN  r" + codeL % 4;
		case 5:
			return "OUT r" + codeL % 4;
		case 6:
			return "HALT";
		default:
			if(codeH <=3 && codeH >= 0){
				isTwo = true;
				int lowHigh2 = codeL >>> 2;
				switch(lowHigh2){
				case 0:
					return "LDA r" + codeL % 4 + " [" + codeH + "]";
				case 1:
					return "STA r" + codeL % 4 + " [" + codeH + "]";
				case 2:
					return "JMP" + " [" + codeH + "]";
				case 3:
					return "BZC" + " [" + codeH + "]";
				}
			}
		}

		return "UNKNOW";
	}
}
