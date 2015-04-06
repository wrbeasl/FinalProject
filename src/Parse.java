import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;


public class Parse {
	
	public void parseTextFile(String fileName) throws IOException{
		FileInputStream inFS = new FileInputStream(fileName);
		BufferedReader BufRead = new BufferedReader(new InputStreamReader(inFS));
		boolean dateIsGiven = true;
		String strLine = null;
		
		dateGetter dg = new dateGetter();
		
		MovieReview tempMR = new MovieReview();
		
		while((strLine = BufRead.readLine()) != null){
			String temp = new String();
			int space_index = strLine.indexOf(" ");
			if(strLine.contains("product/productId: ")){
				temp = strLine.substring(space_index);
				
				if(dg.getDate(temp) != null)
					System.out.println(temp + " date: " + dg.getDate(temp));
				else{
					System.out.println(temp + " No Date Given");
					dateIsGiven = false;
				}
				
				tempMR.productID = temp;
			} else if(strLine.contains("review/helpfulness: ")){
				
				temp = strLine.substring(space_index);
				
				int indx = temp.indexOf("/");
				
				String leftVal, rightVal;
				leftVal = temp.substring(0, indx);
				rightVal = temp.substring(indx+1, temp.length());
				
				float userReviewRating = Float.parseFloat(leftVal) / Float.parseFloat(rightVal);
				
				//System.out.println(temp + " " +  leftVal + " " + rightVal + " " + test);
			
			} else if(strLine.contains("review/time: ")){
			
				// Get the time
				temp = strLine.substring(space_index+1);
				
				java.util.Date time=new java.util.Date((long)Long.parseLong(temp)*1000);
				
				System.out.println(time.toString().substring(time.toString().indexOf(" ")));
				
				String[] info = time.toString().split(" ");
				
				String month = new String("");
				String day = info[2];
				String year = info[5];
				
				switch(info[1]){
					case "Aug": month = "August"; break;
					case "Oct": month = "October"; break;
					case "Jan": month = "Janurary"; break;
					case "Apr": month = "April"; break;
					case "Feb": month = "Feburary"; break;
					case "Mar": month = "March"; break;
					case "May": month = "May"; break;
					case "Jun": month = "June"; break;
					case "Jul": month = "July"; break;
					case "Nov": month = "November"; break;
					case "Dec": month = "December"; break;
					case "Sep": month = "September"; break;
				}
				
				System.out.println(month);
				System.out.println(day);
				System.out.println(year);

				System.out.println(time);
				System.out.println(temp);
			
			}
		}
		
		BufRead.close();
	}

}
