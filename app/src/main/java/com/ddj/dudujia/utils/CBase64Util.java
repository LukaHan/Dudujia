package com.ddj.dudujia.utils;

import java.util.ArrayList;
import java.util.List;


public class CBase64Util {
	private static final String TAG=CBase64Util.class.getSimpleName();
		private static String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=";
		
		public static void main(String[] args) {
//			System.out.println(encode("172.18.3.216"));
			System.out.println(decode("eyJkIjp7Imludml0ZV9jb2RlIjoiMTQwMzUwMyJ9fQ=="));
//			DatatypeConverter converter
//			System.out.println(decode(encode("172.18.9.89")));
		}

		private static String encode(Integer[] ecodearr){
			int clen=ecodearr.length;
			StringBuilder builder=new StringBuilder();
			int[] arr = new int[4];
			for (int i = 0; i < clen;i++) {
				int cc=ecodearr[i];
				arr[0] = (cc >> 0x2);
				int nc=++i >= clen ? 0:ecodearr[i];
				arr[1] =  ((0x3 & cc) << 0x4 | nc >> 0x4);
				if (i >= clen) {
					arr[2] = arr[3] = 0x40;
				} else {
					int nnc = ++i >= clen ? 0:ecodearr[i];
					arr[2] = ((0xF & nc) << 0x2 | nnc >> 0x6);
					if (i >= clen) {
						arr[3] = 0x40;
					} else {
						arr[3] = (0x3F & nnc);
					}
				}
				for (int j : arr) {
					builder.append(characters.charAt(j));
//					System.out.print(j+"  ");
				}
			}
			return builder.toString();
		}
		
		
		public static String encode(String str){
			int strlen=str.length();
			char[] chars=str.toCharArray();
			List<Integer> list =new ArrayList<>();
			for (int i =0;i< strlen;i++) {
				if(chars[i] < 0x0 || chars[i] > 0x7F){
					if (0x800 > chars[i]) {
						list.add((0x3F & chars[i] | 0x80) ^ 0x96);
					} else {
						list.add((chars[i] >> 0xC | 0xE0) ^ 0x96);
						list.add((chars[i] >> 0x6 & 0x3F | 0x80) ^ 0x96);
						list.add((0x3F & chars[i] | 0x80) ^ 0x96);
					}
				}else{
					list.add(chars[i] ^ 0x96);
				}
			}
//			for (int a:list){
//				System.out.print(a+" ");
//				Log.e(TAG,""+a);
//			}
			Integer[] rets=list.toArray(new Integer[list.size()]);
			return new String(encode(rets));
		}
		
		
		public static String decode(String str){
			char[] chars=str.toCharArray();
			int[] cs=new int[4];
			List<Integer> list=new ArrayList<>();
			for (int i = 0; i < chars.length; ) {
				cs[0]=characters.indexOf(chars[i++]);
				cs[1]=characters.indexOf(chars[i++]);
				cs[2]=characters.indexOf(chars[i++]);
				cs[3]=characters.indexOf(chars[i++]);
				int chr1 = cs[0] << 0x2 | cs[1] >> 0x4 ;
				int chr2 = (0xF & cs[1]) << 0x4 | cs[2] >> 0x2;
				int chr3 = (0x3 & cs[2]) << 0x6 | cs[3];
				list.add(chr1 ^ 0x96);
				if(0x40 != cs[2]) list.add(chr2 ^ 0x96);
				if(0x40 != cs[3]) list.add(chr3 ^ 0x96);
			}
			return decode(list.toArray(new Integer[list.size()]));
		}
		
		private static String decode(Integer[] dcodearr){
			StringBuilder builder=new StringBuilder();
			for (int i = 0; i < dcodearr.length; ) {
				int v=dcodearr[i];
				if(v < 0x80){
					builder.append((char)v);
				}else if(v > 0xBF && v < 0xE0){
					builder.append((char)((0x1F & v) << 0x6 | 0x3F & dcodearr[++i]));
				}else{
	        		builder.append((char)((0xF & v) << 0xC | (0x3F & dcodearr[++i]) << 0x6 | 0x3F & dcodearr[++i]));
				}
				i++;
			}
			return builder.toString();
		} 
	}