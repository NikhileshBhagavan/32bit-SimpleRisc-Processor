import java.util.*;
import java.io.*;
public class assembler{
		static int no_of_instructions=0;
		
		public static String addzero(String immx){
        String t="";
		for(int i=0;i<16-immx.length();i++){
				t+="0";
							}
		immx=t+immx;
        
		return immx;



		}
		
		public static int binaryToDecimal(long binary)
		{
			int decimalNumber = 0, i = 0;
			while (binary > 0) 
			{
 
          
            decimalNumber
                += Math.pow(2, i++) * (binary % 10);
 
     
            binary /= 10;
			}
 
      
        return decimalNumber;
		}
	
		public static String BinaryToHexadecimal(String s){
			HashMap<Integer,String> codes=new HashMap<Integer,String> ();
			codes.put(10,"A");
			codes.put(11,"B");
			codes.put(12,"C");
			codes.put(13,"D");
			codes.put(14,"E");
			codes.put(15,"F");
			String output="";
			for(int i=0;i<8;i++){
				String dec=s.substring(i*4,(i*4)+4);
				int decimal=binaryToDecimal(Integer.parseInt(dec));
				if(codes.containsKey(decimal)){
					output=output+codes.get(decimal);
				}
				else{
					output+=decimal;
				}
				
				
				
				
			}
			
			
			
			
			
			
			
			return output;
			
			
		}
		public static String ZERO_ADDRESS_INSTRUCTIONS(HashMap<String,String> zero_address_instructions, String ins){
			
			
			return zero_address_instructions.get(ins)+"000000000000000000000000000";
			
			
		}
		public static String ONE_ADDRESS_INSTRUCTIONS(HashMap<String,String> one_address_instructions,HashMap<String,Integer> labels,String ins,String array[]){
			
			int in=labels.get(array[1]);
			int k=in-(no_of_instructions+1);
			String output="";
			if(k<0){
				output=one_address_instructions.get(ins)+Integer.toBinaryString(k).substring(5,32);
			}
			else{
				String z=Integer.toBinaryString(k);
				String t="";
				for(int q=0;q<27-z.length();q++)
				{
					t=t+"0";
								
				}
					t=t+z;
					output=one_address_instructions.get(ins)+t;
			}
			
			
			return output;
			
			
			
			
		
		}
		public static String TWO_ADDRESS_INSTRUCTIONS(HashMap<String,String> two_address_instructions,String array[],String modifier){
			String arr[]=array[1].split(",");
			boolean isnotImmediate=arr[1].substring(0,1).equals("r");
			String output="";
			if(isnotImmediate){
				if(array[0].equals("cmp")){
				int rs1=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[0].substring(1))));
				int rs2=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[1].substring(1))));
				output=two_address_instructions.get(array[0])+"0"+"0000"+String.format("%04d",rs1)+String.format("%04d",rs2)+"00000000000000";
				
				
				}
				else{
				int rd=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[0].substring(1))));
				int rs2=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[1].substring(1))));
						
						
				output=two_address_instructions.get(array[0])+"0"+String.format("%04d",rd)+"0000"+String.format("%04d",rs2)+"00000000000000";
					
					
				}
				
				
					
				
			}
			else{
				if(array[0].equals("cmp")){
					int rs1=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[0].substring(1))));
					String immx=Integer.toBinaryString(Integer.parseInt(arr[1]));
					if(Integer.parseInt(arr[1])<0){
						immx=immx.substring(16);
					}
					output=two_address_instructions.get(array[0])+"1"+"0000"+String.format("%04d",rs1)+modifier+addzero(immx);
					
					
				}
				else{
					
					int rd=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[0].substring(1))));
					String immx=Integer.toBinaryString(Integer.parseInt(arr[1]));
					if(Integer.parseInt(arr[1])<0){
						immx=immx.substring(16);
					}
					output=two_address_instructions.get(array[0])+"1"+String.format("%04d",rd)+"0000"+modifier+addzero(immx);
					
					
				}
				
				
				
				
			}
			
			
			
			
			
			return output;
			
			
			
		}
		public static String LOAD_STORE_ADDRESS_INSTRUCTIONS(HashMap<String,String> load_store_address_instructions,String array[],String modifier){
			
			String arr[]=array[1].split(",");
			//System.out.println(arr);
			String output="";
			int rd=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[0].substring(1))));
			//System.out.println(rd);
			String s=arr[1];
			//System.out.println(s);
			int start=s.indexOf('[');
			//System.out.println(start);
			int end=s.indexOf(']');
			//System.out.println(end);
			String immx=Integer.toBinaryString(Integer.parseInt(arr[1].substring(0,start)));
			if(Integer.parseInt(arr[1].substring(0,start))<0){
						immx=immx.substring(16);
					}
					//System.out.println(immx);
			int rs1=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[1].substring(start+2,end))));
			//System.out.println(rs1);
			
			
			output=load_store_address_instructions.get(array[0])+"1"+String.format("%04d",rd)+String.format("%04d",rs1)+modifier+addzero(immx);
			//System.out.println(output);
			return output;
			
			
			
		}
		public static String THREE_ADDRESS_INSTRUCTIONS(HashMap<String,String> three_address_instructions,String array[],String modifier){
			
			String arr[]=array[1].split(",");
			boolean isnotImmediate=arr[2].substring(0,1).equals("r");
			String output="";
			if(isnotImmediate){
				int rd=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[0].substring(1))));
				int rs1=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[1].substring(1))));
				int rs2=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[2].substring(1))));
				output=three_address_instructions.get(array[0])+"0"+String.format("%04d",rd)+String.format("%04d",rs1)+String.format("%04d",rs2)+"00000000000000";
				
				
			}
			else{
				int rd=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[0].substring(1))));
				int rs1=Integer.parseInt(Integer.toBinaryString(Integer.parseInt(arr[1].substring(1))));
				String immx=Integer.toBinaryString(Integer.parseInt(arr[2]));
					if(Integer.parseInt(arr[2])<0){
						immx=immx.substring(16);
					}
					
				output=three_address_instructions.get(array[0])+"1"+String.format("%04d",rd)+String.format("%04d",rs1)+modifier+addzero(immx);
				
				
			}
			
			return output;
			
			
			
		}
		public static void main(String[] args){
		
	
		try{
			
			
			File file=new File("TestCase.asm");
			
			Scanner sc=new Scanner(file);
			
			File obj=new File("assembleroutput");
			
			if(obj.createNewFile())
		
		
			{
			
			
				HashMap<String,String> zero_address_instructions=new HashMap<String,String>();
				zero_address_instructions.put("nop","01101");
				zero_address_instructions.put("ret","10100");
				HashMap<String,String> one_address_instructions=new HashMap<String,String>();
				one_address_instructions.put("beq","10000");
				one_address_instructions.put("bgt","10001");
				one_address_instructions.put("b","10010");
				one_address_instructions.put("call","10011");
				HashMap<String,String> two_address_instructions=new HashMap<String,String>();
				two_address_instructions.put("cmp","00101");
				two_address_instructions.put("not","01000");
				two_address_instructions.put("mov","01001");
				HashMap<String,String> load_store_address_instructions=new HashMap<String,String>();
				load_store_address_instructions.put("ld","01110");
				load_store_address_instructions.put("st","01111");
				HashMap<String,String> three_address_instructions=new HashMap<String,String>();
				three_address_instructions.put("add","00000");
				three_address_instructions.put("sub","00001");
				three_address_instructions.put("mul","00010");
				three_address_instructions.put("div","00011");
				three_address_instructions.put("mod","00100");
				three_address_instructions.put("and","00110");
				three_address_instructions.put("or","00111");
				
				three_address_instructions.put("lsl","01010");
				three_address_instructions.put("lsr","01011");
				three_address_instructions.put("asr","01100");
				three_address_instructions.put("xor","10101");
				three_address_instructions.put("xnor","10110");
				
				
				
			
		
				FileWriter myWriter = new FileWriter("assembleroutput");
				myWriter.write("v2.0 raw\n");
				HashMap<String,Integer> labels=new HashMap<String,Integer>();
				int i=0;
				while(sc.hasNextLine()){
					String s=sc.nextLine();
					if(s.charAt(0)=='.'){
						labels.put(s.substring(0,s.length()-1),i+1);
					
					
					}
					else{
					i++;
					
					}
					
					
					
					
					
				}
				
				Scanner o=new Scanner(file);
			
				while(o.hasNextLine()){
					String s=o.nextLine();
					System.out.println(s);
					s=s.trim();
					if(s.charAt(0)=='.'){
						continue;
					}
					String modifier=null;
					String array[]=s.split(" ");
					
					String output="";
					if(array[0].charAt(array[0].length()-1)=='u'){
						modifier="01";
						array[0]=array[0].substring(0,array[0].length()-1);
						
					}
					else{
						if(array[0].charAt(array[0].length()-1)=='h'){
						modifier="10";
						array[0]=array[0].substring(0,array[0].length()-1);
						
					}
					else{
						modifier="00";
					}
					
					
					
					
					}
					
					if(zero_address_instructions.containsKey(array[0])){
						
						output=ZERO_ADDRESS_INSTRUCTIONS(zero_address_instructions,array[0]);
						
					}
					if(one_address_instructions.containsKey(array[0])){
						
						output=ONE_ADDRESS_INSTRUCTIONS(one_address_instructions,labels,array[0],array);
						
					}
					if(two_address_instructions.containsKey(array[0])){
						
						output=TWO_ADDRESS_INSTRUCTIONS(two_address_instructions,array,modifier);
						
					}
					if(load_store_address_instructions.containsKey(array[0])){
						System.out.println("hello");
						output=LOAD_STORE_ADDRESS_INSTRUCTIONS(load_store_address_instructions,array,modifier);
						
					}
					if(three_address_instructions.containsKey(array[0])){
						
						output=THREE_ADDRESS_INSTRUCTIONS(three_address_instructions,array,modifier);
					}
						
						
					
					
					
					
					
					//System.out.println("hello2");
				
				System.out.println(output);
				output=BinaryToHexadecimal(output);
				System.out.println(output);
				//System.out.println("aaa");
				myWriter.write(output+"\n");
				//System.out.println("bbb");
				no_of_instructions++;
				
	
				
				
				
				
				
				}
								myWriter.close();
				
				}
		}
		
				
				catch(Exception e){
					System.out.println("file not found");
					
					
				}
				
		}
		
	
	
		
				
				
				
				


				
				
				
				}
				
				
				
				
				
				
				
				
				
				
				