import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.util.Bytes;

public class DeleteData {

   public static void main(String[] args) throws IOException {

      // Instantiating Configuration class
      //Configuration conf = HBaseConfiguration.create();
	   Configuration conf = HBaseConfiguration.create();
	   conf.set(TableOutputFormat.OUTPUT_TABLE, "Kalikant");
	   conf.set("hbase.zookeeper.quorum", "111.222.333.444");
	   conf.set("hbase.zookeeper.property.clientport", "2181");
	   conf.set("zookeeper.znode.parent", "/hbase-unsecure");
	   conf.set("hbase.master", "111.222.333.444:66000");

      // Instantiating HTable class
      HTable table = new HTable(conf, "emp999");

      // Instantiating Delete class
      Delete delete = new Delete(Bytes.toBytes("row1"));
      delete.deleteColumn(Bytes.toBytes("personal data"), Bytes.toBytes("name"));
      delete.deleteFamily(Bytes.toBytes("professional data"));

      // deleting the data
      table.delete(delete);

      // closing the HTable object
      table.close();
      System.out.println("data deleted.....");
   }
}
