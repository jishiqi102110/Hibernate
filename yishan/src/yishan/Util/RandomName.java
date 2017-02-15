package yishan.Util;

public class RandomName {
	 public static char getRandomChar(char c1,char c2){  
	        return (char)(c1+Math.random()*(c2-c1+1));  
	         }  
	 public static int getRandomNumber(){
		 return (int) (Math.random()*1000);
	 }
	 public static StringBuffer getRandomName(){
		 StringBuffer name=new StringBuffer();
		 name.append(getRandomChar('a','z'));
		 name.append(getRandomChar('a','z'));
		 name.append((int)(Math.random()*1000));
		 System.out.println(name);
		 return name;
	 }
}
