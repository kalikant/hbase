import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.util.Bytes;

public class RetriveData{

   public static void main(String[] args) throws IOException, Exception{
   
      // Instantiating Configuration class
      //Configuration config = HBaseConfiguration.create();
	   
	   Configuration config = HBaseConfiguration.create();
	      config.set(TableOutputFormat.OUTPUT_TABLE, "Kalikant");

			config.set("hbase.zookeeper.quorum", "111.222.333.444");
			config.set("hbase.zookeeper.property.clientport", "2181");
			config.set("zookeeper.znode.parent", "/hbase-unsecure");
			config.set("hbase.master", "111.222.333.444:66000");

      // Instantiating HTable class
      HTable table = new HTable(config, "emp999");

      // Instantiating Get class
      Get g = new Get(Bytes.toBytes("row1"));

      // Reading the data
      Result result = table.get(g);

      // Reading values from Result class object
      byte [] value = result.getValue(Bytes.toBytes("personal data"),Bytes.toBytes("name"));

      byte [] value1 = result.getValue(Bytes.toBytes("personal data"),Bytes.toBytes("city"));

      // Printing the values
      String name = Bytes.toString(value);
      String city = Bytes.toString(value1);
      
      System.out.println("name: " + name + " city: " + city);
   }
}
