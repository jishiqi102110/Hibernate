package Control;

import java.io.File;

public class deleteFile {
	public boolean DeleteFolder(String sPath) {
		boolean flag = false;
		sPath="d:/javaEE/javaEEWorkSpace/ShoolTiaoZao/WebContent/"+sPath;
		File f = new File(sPath);
		if(f.exists()){			
			f.delete();
			flag=true;
		}
		return flag;
	}
}
