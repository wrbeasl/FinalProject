import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Parse {
	
	public void parseTextFile(String fileName) throws IOException{
		FileInputStream inFS = new FileInputStream(fileName);
		BufferedReader BufRead = new BufferedReader(new InputStreamReader(inFS));
		
		String strLine = null;
		
		dateGetter dg = new dateGetter();
		
		MovieReview tempMR = new MovieReview();
		
		while((strLine = BufRead.readLine()) != null){
			String temp = new String();
			int space_index = strLine.indexOf(" ");
			if(strLine.contains("product/productId: ")){
				temp = strLine.substring(space_index+1);
				System.out.println(temp + " " + dg.getDate(temp));
				tempMR.productID = temp;
			} else if(strLine.contains("review/helpfulness: ")){
				temp = strLine.substring(space_index);
				//tempMR.helpfulness = Float.parseFloat(temp);
			} else if(strLine.contains("review/time: ")){
				//temp = strLine.substring(space_index);
			}
		}
		
		BufRead.close();
	}

}
