/**
 * 
 */
package salary.managment.system.view.panel.func;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.sun.corba.se.spi.ior.Writeable;
import com.sun.corba.se.spi.orbutil.threadpool.NoSuchWorkQueueException;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import salary.managment.system.base.DatabaseFiled;

/**
 * @author Tortoise
 *
 */
public class FuncBackupDatabase {
	private String backupPath;
	private String backupName;

	public FuncBackupDatabase() {
		setBackupPath(null);
		setBackupName(null);

	}

	public FuncBackupDatabase(String backupPath, String backupName) {
		this.backupPath = backupPath;
		this.backupName = backupName;
	}

	public void backup() {
		try {

			File fileSql = new File(this.backupPath);
			if (!fileSql.exists()) {
				if (!fileSql.mkdirs()) {
					throw new IOException("创建备份路径失败！");
				}
			}

			fileSql = new File(this.backupPath + this.backupName);
			System.out.println(this.backupPath + this.backupName);
			if (!fileSql.exists()) {
				fileSql.createNewFile();
			}

			String execCMD = "mysqldump -u" + DatabaseFiled.DB_DATABASE_ROOT_NAME + " -p"
					+ DatabaseFiled.DB_DATABASE_ROOT_PASS + " --all-databases";

			System.out.println("execCMD = " + execCMD);
			Process process = Runtime.getRuntime().exec(execCMD);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf8"));
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					new FileOutputStream(getBackupPath() + getBackupName()), "utf8");

			String line = bufferedReader.readLine();
			while (line != null) {
				outputStreamWriter.write(line + "\n");
				line = bufferedReader.readLine();
			}
			outputStreamWriter.flush();
			bufferedReader.close();
			outputStreamWriter.close();
			JOptionPane.showMessageDialog(null, "数据库备份成功(" + this.backupPath + this.backupName + ")", "成功",
					JOptionPane.OK_CANCEL_OPTION);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBackupPath() {
		return backupPath;
	}

	public void setBackupPath(String backupPath) {
		if (backupPath == null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
			this.backupPath = System.getProperty("user.dir") + "\\backup_database\\"
					+ simpleDateFormat.format(Calendar.getInstance().getTime());

		} else {
			this.backupPath = backupPath;
		}
		System.out.println(this.backupPath);
	}

	public String getBackupName() {
		return backupName;
	}

	public void setBackupName(String backupName) {
		if (backupName == null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHss");
			this.backupName = "\\" + simpleDateFormat.format(Calendar.getInstance().getTime()) + ".sql";
		} else {
			this.backupName = backupName;
		}
		System.out.println(this.backupName);
	}

	public static void main(String[] args) {

		FuncBackupDatabase funcBackupDatabase = new FuncBackupDatabase();
		funcBackupDatabase.backup();
	}

}
