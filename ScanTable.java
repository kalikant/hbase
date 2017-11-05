import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.util.Bytes;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;


public class ScanTable{

   public static void main(String args[]) throws IOException{

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

      // Instantiating the Scan class
      Scan scan = new Scan();

      // Scanning the required columns
      scan.addColumn(Bytes.toBytes("personal data"), Bytes.toBytes("name"));
      scan.addColumn(Bytes.toBytes("personal data"), Bytes.toBytes("city"));

      // Getting the scan result
      ResultScanner scanner = table.getScanner(scan);

      // Reading values from scan result
      for (Result result = scanner.next(); result != null; result = scanner.next())

      System.out.println("Found row : " + result);
      //closing the scanner
      scanner.close();
   }
}
