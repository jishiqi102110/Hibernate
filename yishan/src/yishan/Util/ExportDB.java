package yishan.Util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class ExportDB {
       public static void main(String[] args){
    	   Configuration cfg=new Configuration().configure();
    	   SchemaExport export=new SchemaExport(cfg);
    	   export.create(true,true);
    	   System.out.println("数据库导出成功");
       }
}
