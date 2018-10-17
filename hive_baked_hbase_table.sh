create 'accountdetails', 'bank'

put 'accountdetails','row1','bank:account_id','111111'
put 'accountdetails','row1','bank:customer_id','101'
put 'accountdetails','row1','bank:account_type_id','1'
put 'accountdetails','row1','bank:date','2017-03-12'
put 'accountdetails','row1','bank:branch_code','ABC30'
put 'accountdetails','row1','bank:branch_desc','ABC30000'
put 'accountdetails','row2','bank:account_id','111112'
put 'accountdetails','row2','bank:customer_id','105'
put 'accountdetails','row2','bank:account_type_id','2'
put 'accountdetails','row2','bank:date','2017-04-16'
put 'accountdetails','row2','bank:branch_code','ABC60'
put 'accountdetails','row3','bank:account_id','111113'
put 'accountdetails','row3','bank:customer_id','107'
put 'accountdetails','row3','bank:account_type_id','3'
put 'accountdetails','row3','bank:date','2017-05-20'
put 'accountdetails','row3','bank:branch_code','ABC80'

scan 'accountdetails'

get 'accountdetails', 'row1'

HBASE TO HIVE ------------
CREATE EXTERNAL TABLE hbase_hive_accountdetails(key string,account_id string,customer_id string,account_type_id string,datee string, branch_code string,branch_desc string) 
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' 
WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,bank:account_id,bank:customer_id,bank:account_type_id,bank:date,bank:branch_code,bank:branch_desc") 
TBLPROPERTIES ("hbase.table.name" = "accountdetails");

select * from hbase_hive_accountdetails;
row1    111111  101     1       2017-03-12      ABC30   ABC30000
row2    111112  105     2       2017-04-16      ABC60   NULL
row3    111113  107     3       2017-05-20      ABC80   NULL

HIVE TO HBASE ------------
row4,111114,110,4,2017-06-12,XABC30
row5,111115,115,5,2017-07-16,XABC60
row6,111116,117,6,2017-08-24,ABC80
row7,111117,127,7,2017-09-26,XABC80
row8,111118,147,8,2017-10-28,XABC80

CREATE TABLE hive_accountdetails(key string,account_id string,customer_id string,account_type_id string, datee string,branch_code string) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';

LOAD DATA LOCAL INPATH '/dat_source_dir/file.txt' into table hive_accountdetails;

insert into table hbase_hive_accountdetails 
select key ,account_id ,customer_id ,account_type_id , datee ,branch_code ,'' as branch_desc from hive_accountdetails;

select * from hbase_hive_accountdetails;

scan 'accountdetails'


XML - HBase - Hive ------------ ========================
/home/kiu/py/hbasexml\41.xml
<CATALOG>
<CD>
<TITLE>Empire Burlesque</TITLE>
<ARTIST>Bob Dylan</ARTIST>
<COUNTRY>USA</COUNTRY>
<COMPANY>Columbia</COMPANY>
<PRICE>10.90</PRICE>
<YEAR>1985</YEAR>
</CD>
<CD>
<TITLE>Hide your heart</TITLE>
<ARTIST>Bonnie Tyler</ARTIST>
<COUNTRY>UK</COUNTRY>
<COMPANY>CBS Records</COMPANY>
<PRICE>9.90</PRICE>
<YEAR>1988</YEAR>
</CD>
</CATALOG>

create 'movie', 'colname'
put 'movie','row1','colname:TITLE','Empire Burlesque'
put 'movie','row1','colname:ARTIST','Bob Dylan'
put 'movie','row1','colname:COUNTRY','USA'
put 'movie','row1','colname:COMPANY','Columbia'
put 'movie','row1','colname:PRICE','10.90'
put 'movie','row1','colname:YEAR','1985'
put 'movie','row2','colname:TITLE','Hide your heart'
put 'movie','row2','colname:ARTIST','Bonnie Tyler'
put 'movie','row2','colname:COUNTRY','UK'
put 'movie','row2','colname:COMPANY','CBS Records'
put 'movie','row2','colname:PRICE','9.90'
put 'movie','row2','colname:YEAR','1988'


from xml.dom import minidom
xmldoc = minidom.parse('/home/kiu/py/mindata.xml')
itemlist = xmldoc.getElementsByTagName('item')
print(len(itemlist))
print(itemlist)
#print(itemlist[0].attributes['name'].value)
for s in itemlist:
    print(s.attributes['name'].value)

	
python 41.py
