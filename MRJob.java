import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MRJob {
	
	public static class Map extends Mapper<Text, LongWritable, Text, LongWritable>{
		
		public static final log LOG = LogFactory.getLog(Map.class);
		private HashMap<String, Long> matchMap = new HashMap<String, Long>();
	
		@Override
		public void map(Text key, LongWritable value, Context context){
			String line = value.toString();
			String temp = null;
			System.out.println(line);
			
			LOG.info(line);

			int space_index = line.indexOf(" ");
			
			if(line.contains("product/productID: ")){
				temp = line.subString(space_index);
				temp = temp.trim();
			}
			
			if(line.contains("review/time: ")){

				temp = strLine.substring(space_index+1);
				temp = temp.trim();
				
			}
			
			if(line.contains("review/helpfulness: ")){
				
				temp = strLine.substring(space_index);
				
				int indx = temp.indexOf("/");
				
				String leftVal, rightVal;
				leftVal = temp.substring(0, indx);
				rightVal = temp.substring(indx+1, temp.length());
				
				float userReviewRating = Float.parseFloat(leftVal) / Float.parseFloat(rightVal);
				
			}
		}

		@Override
		public void setup(Context context) throws IOException, InterruptedException{
			Configuration config = context.getConfiguration();
			String param = conf.get("matchfile");
			InputStream is = new FileInputStream(param);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line = null; 
			String ID = null;
			Long timeStamp = 0;
			while((line = br.readLine()) != null){
				if(line.contains("product/productID: ")){
					int space_index = line.indexOf(" ");
					ID = line.subString(space_index);
					ID = ID.trim();
					System.out.println(ID);
				}
				if(line.contains("review/time: ")){
					int space_index = line.indexOf(" " );
					timeStamp = line.subString(space_index);
					timeStamp = Long.parseLong(timeStamp);
					System.our.println(timeStamp);
				}
			}
			
			is.close();
			isr.close();
			br.close();
	}
	}
	public static class Reduce extends Reducer<Text, LongWritable, Text, LongWritable>{
		
	}

public static void main(String[] args) throws Exception {
	Configuration conf = new Configuration();
	conf.set("matchfile", "./movies.txt");
	
	Job job = new Job(conf, "MapReduce");
	job.setJarByClass(MRJob.class);
	
	job.setMapOutputKeyClass(Text.class);
	job.setMapOutputValueClass(LongWritable.class);
	
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(LongWritable.class);
	
	job.setMapperClass(Map.class);
	job.setReducerClass(Reduce.class);
	
	job.setInputFormatClass(TextInputFormat.class);
	job.setOutputFormatClass(TextOutputFormat.class);
	
	FileInputFormat.addInputPath(job, new Path("./movies.txt"));
	FileOutputFormat.setOutputPath(job, new Path("./output"));
	
	System.out.println("Test");

	job.waitForCompletion(true);
}

