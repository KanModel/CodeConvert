package me.kanmodel.dec18.convert;

import java.util.ArrayList;

/*
 * @author: KanModel
 */
public class Main {
	public static ArrayList<Integer> refers = new ArrayList<Integer>();
	
	public static void main(String args[]) {
		System.out.println("Code Convert Start");
		String filePath = "file url";
		System.out.println("Load file " + filePath);
		ArrayList<String> res = CodeFileReader.readTxtFile(filePath);
		int count = 0;
		for (String line : res) {
			if (line.length() > 0) {
				int codeH = Integer.valueOf(line.substring(4, 5), 16);
				int codeL = Integer.valueOf(line.substring(5, 6), 16);
				int code = Integer.valueOf(line.substring(4, 6), 16);
				if (line.charAt(0) == '$' && line.charAt(1) == 'P') {
					if(CodeTable.isTwo){
						refers.add(code);
						CodeTable.isTwo = false;
					}else{
						CodeTable.getCode(codeH, codeL);
					}
//					count++;
				}
			}
		}
		for (String line : res) {
			if (line.length() > 0) {
				int hCode = Integer.valueOf(line.substring(2, 4), 16);
				int codeH = Integer.valueOf(line.substring(4, 5), 16);
				int codeL = Integer.valueOf(line.substring(5, 6), 16);
				int code = Integer.valueOf(line.substring(4, 6), 16);
				if (line.charAt(0) == '$' && line.charAt(1) == 'P') {
					if(!CodeTable.isTwo){
						System.out.print(line.substring(2, 4) + " : " + Integer.toHexString(codeH) + Integer.toHexString(codeL) + " ");
						binaryToDecimal(code);
						System.out.print(" " + CodeTable.getCode(codeH, codeL));
					}else{
						String adr = Integer.toHexString(codeH) + Integer.toHexString(codeL);
//						refers.add(code);
						System.out.print(line.substring(2, 4) + " : " + adr + " ");
						binaryToDecimal(code);
						System.out.print(" " + line.substring(4, 6));
						CodeTable.isTwo = false;
					}
					if(refers.contains(hCode)){
						System.out.println(" refer");
					}else{
						System.out.println();
					}
					count++;
				}
			}else{
				System.out.println();
			}
		}
		System.out.println(count);
	}

	public static void binaryToDecimal(int n) {
		for (int i = 7; i >= 0; i--)
			System.out.print(n >>> i & 1);
	}
}
