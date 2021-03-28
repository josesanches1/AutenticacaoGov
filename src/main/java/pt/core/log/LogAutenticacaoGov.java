package pt.core.log;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;


public class LogAutenticacaoGov{

	private String log_path=null;
	private String log_Name=null;
	private static Logger meuLog = null;	
	
	public void init (){
		
		//BasicConfigurator.configure();
		
		PatternLayout meuLayout = new PatternLayout(" %m%n ");

		StringBuffer sb = new StringBuffer();
		Timestamp time_ = new Timestamp(System.currentTimeMillis());

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date(time_.getTime()));
		sb.append(cal.get(Calendar.YEAR));
		sb.append(((cal.get(Calendar.MONTH)+ 1) < 10)? "0" +  (cal.get(Calendar.MONTH)+ 1):"" + (cal.get(Calendar.MONTH)+ 1));				
		sb.append((cal.get(Calendar.DAY_OF_MONTH) < 10)? "0" +  cal.get(Calendar.DAY_OF_MONTH):"" + cal.get(Calendar.DAY_OF_MONTH));				

		sb.append(".log");
		
		log_Name="WS_aGov_";
		//log_path=Config.getPathLog() + "/";
		log_path = "c:/_APPS/";
		
		
		// Inicia LogTrans
		long tamanho = 512000;
		try{
			meuLog=initLog(meuLayout, sb.toString(), tamanho);
		} catch(Exception e) {
			System.out.println("SRIESP -> Não foi possível iniciar o Log Transaccional" + e);
		}
	}	

	private Logger initLog(PatternLayout meuLayout, String dataHora, long filesSize) throws Exception{
		RollingFileAppender rollingFileAppender = null;
		StringBuffer local = new StringBuffer();
		
		local.append(log_path);
		local.append(log_Name);
		local.append(dataHora);
		rollingFileAppender = new RollingFileAppender(meuLayout,local.toString(),true);
		rollingFileAppender.setMaximumFileSize(filesSize);
		rollingFileAppender.setMaxBackupIndex(3);
		Logger meuLog = Logger.getLogger(log_Name);
		meuLog.addAppender(rollingFileAppender);
		meuLog.setLevel(Level.INFO);
		//meuLog.warn(Level.OFF);
		//meuLog.debug(Level.OFF);
		
		
		return meuLog;				
	}
	/**
	 * Escreve Mensagem no buffer
	 * 
	 * @param mensagem
	 */
	public static synchronized void info(String mensagem){
		//Formatar a mensagem 
		StringBuffer str = new StringBuffer(new Timestamp(System.currentTimeMillis()).toString());

		//Para saber a origem de quem chamou:
		Exception e = new Exception();   
		StackTraceElement[] stack =e.getStackTrace();
		//String classe=null;
		String jsp=null;
		for(int n=1;n<stack.length;n++){
			String tmp="";
			try {
				tmp=stack[n].getClassName();
				if(tmp.toLowerCase().indexOf(".jsp")>-1 || tmp.toLowerCase().indexOf(".htm")>-1){
					jsp=stack[n].getClassName();
					break;
				}
				if(stack[n].getClassName().indexOf("HttpJspBase")>1)
					break;
			}catch(Exception e1) {}
		}
			
		str.append(" CLASSES : ");
		if(null==jsp)
			str.append(stack[1].getClassName());
		else
			str.append(stack[1].getClassName() + " - " + jsp);
		//str.append(classe);
		str.append(", METODO : ");
		str.append(stack[1].getMethodName());
		str.append(", LINHA : ");
		str.append(stack[1].getLineNumber());
		e=null;
		stack=null;
		str.append(" - ");
		str.append(mensagem);
	
		if(null==meuLog){	//Se o Log não Existir
			System.out.println(str);
		}else{//Existe LOG, pode-se escrever no ficheiro?
			if(meuLog.isEnabledFor(Level.INFO)) meuLog.info(str);
			else System.out.println(str);
		}
		str=null;
	}
}