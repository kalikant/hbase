import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.TableName;

import org.apache.hadoop.conf.Configuration;

public class HBaseCreateTable {
      
   public static void main(String[] args) throws IOException {

      // Instantiating configuration class
      Configuration config = HBaseConfiguration.create();
      config.set(TableOutputFormat.OUTPUT_TABLE, "Kalikant");

		config.set("hbase.zookeeper.quorum", "111.222.333.444");
		config.set("hbase.zookeeper.property.clientport", "2181");
		config.set("zookeeper.znode.parent", "/hbase-unsecure");
		config.set("hbase.master", "111.222.333.444:66000");

      // Instantiating HbaseAdmin class
      HBaseAdmin admin = new HBaseAdmin(config);

      // Instantiating table descriptor class
      HTableDescriptor tableDescriptor = new
      HTableDescriptor(TableName.valueOf("emp111"));

      // Adding column families to table descriptor
      tableDescriptor.addFamily(new HColumnDescriptor("personal"));
      tableDescriptor.addFamily(new HColumnDescriptor("professional"));

      // Execute the table through admin
      admin.createTable(tableDescriptor);
      System.out.println(" Table created ");
   }
}
