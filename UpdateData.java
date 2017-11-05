import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.util.Bytes;

public class UpdateData{

   public static void main(String[] args) throws IOException {

      // Instantiating Configuration class
      //Configuration config = HBaseConfiguration.create();
	   
	   Configuration config = HBaseConfiguration.create();
	      config.set(TableOutputFormat.OUTPUT_TABLE, "Kalikant");

			config.set("hbase.zookeeper.quorum", "111.222.333.444");
			config.set("hbase.zookeeper.property.clientport", "2181");
			config.set("zookeeper.znode.parent", "/hbase-unsecure");
			config.set("hbase.master", "111.222.333.444:60000");
			
      // Instantiating HTable class
      HTable hTable = new HTable(config, "emp999");

      // Instantiating Put class
      //accepts a row name
      Put p = new Put(Bytes.toBytes("row1"));

      // Updating a cell value
      p.add(Bytes.toBytes("personal data"),
      Bytes.toBytes("city"),Bytes.toBytes("Delih"));

      // Saving the put Instance to the HTable.
      hTable.put(p);
      System.out.println("data Updated");

      // closing HTable
      hTable.close();
   }
}
