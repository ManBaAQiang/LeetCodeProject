package com.aq.interview;

//import org.apache.log4j.Logger;

import java.io.*;
import java.security.AccessController;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.*;

/**
 * @ClassName KLLoggerTopic
 * @Description :实现要求：
 *      1、根据以下代码片段，参考log4j/slf4j等公共日志库编写一个自定义的简易日志类；
 *      2、至少需要支持文件输出、控制台输出二种日志输出方式，并支持同时输出到文件和控制台；
 *      3、支持DEBUG/INFO/WARN/ERROR四种日志级别；
 *      4、请合理进行设计模式，进行接口类、抽象类等设计；
 *      5、注意代码注释书写。
 * @Author YaoAqiang
 * @Date 2020/6/17 16:44
 * @Version 1.0
 **/
public class KLLoggerTopic {

//    static Logger logger = Logger.getLogger(KLLoggerTopic.class);
    private static final String TAG = KLLoggerTopic.class.getName();

    public static void main(String[] args) throws IOException {

        /*KLLoggerTopic logger = KLLoggerTopic.getLogger(KLLoggerTopic.class);

        // TODO:

        logger.debug("debug 1...");
        logger.debug("debug 2...");
        logger.info("info 1...");
        logger.warn("warn 1...");
        logger.error("error 1...");*/

        // (可选) 设置日志输出级别, 默认为 INFO 级别
//        LoggerUtils.setLogOutLevel(DebugLevel.DEBUG);

        // (可选) 设置日志输出文件(追加到文件尾部)
        LoggerUtils.setLogOutFile(new File("MyLog.log"));

        // (可选) 设置日志输出位置(是否输出到控制台 和 是否输出到文件), 默认只输出到控制台, 不输出到文件
        LoggerUtils.setLogOutTarget(true, true);

        // 输出日志
        LoggerUtils.debug(TAG, "The debug log.");
        LoggerUtils.info(TAG, "The info log.");
        LoggerUtils.warn(TAG, "The warn log.");
        LoggerUtils.error(TAG, "The error log.");


//        Logger fileLogger = MyLog.getFileLogger();
//        fileLogger.log(Level.INFO,"aaaaa");

    }
}

// TODO:
class LoggerUtils {
    /** 每条 Log 的 tag 输出的最大长度, 超过部分将被截断 */
    private static final int TAG_MAX_LENGTH = 20;

    /** 每条 Log 的 message 输出的最大长度, 超过部分将被截断 */
    private static final int MESSAGE_MAX_LENGTH = 1024;

    /** 日期前缀格式化 */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd HH:mm:ss");

    /** 日志当前的输出级别, 默认为 INFO 级别 */
    private static DebugLevel logOutLevel = DebugLevel.INFO;

    /** 是否输出到控制台, 默认输出 */
    private static boolean isOutToConsole = true;

    /** 是否输出到文件 */
    private static boolean isOutToFile = false;

    /** 日志输出文件, 追加到文件尾 */
    private static File logOutFile;

    /** 日志文件输出流, 追加到文件尾  */
    private static RandomAccessFile logOutFileStream;

    public static void setLogOutLevel(DebugLevel currentLevel) {
        if (currentLevel == null) {
            currentLevel = DebugLevel.INFO;
        }
        LoggerUtils.logOutLevel = currentLevel;
    }

    public static synchronized void setLogOutFile(File logOutFile) throws IOException {
        LoggerUtils.logOutFile = logOutFile;

        if (logOutFileStream != null) {
            closeStream(logOutFileStream);
            logOutFileStream = null;
        }

        if (LoggerUtils.logOutFile != null) {
            try {
                logOutFileStream = new RandomAccessFile(LoggerUtils.logOutFile, "rw");
                logOutFileStream.seek(LoggerUtils.logOutFile.length());
            } catch (IOException e) {
                closeStream(logOutFileStream);
                logOutFileStream = null;
                throw e;
            }
        }
    }

    public static void setLogOutTarget(boolean isOutToConsole, boolean isOutToFile) {
        LoggerUtils.isOutToConsole = isOutToConsole;
        LoggerUtils.isOutToFile = isOutToFile;
    }

    public static void debug(String tag, String message) {
        printLog(DebugLevel.DEBUG, tag, message, false);
    }

    public static void info(String tag, String message) {
        printLog(DebugLevel.INFO, tag, message, false);
    }

    public static void warn(String tag, String message) {
        printLog(DebugLevel.WARN, tag, message, false);
    }

    public static void error(String tag, String message) {
        printLog(DebugLevel.ERROR, tag, message, true);
    }

    public static void error(String tag, Exception e) {
        if (e == null) {
            error(tag, (String) null);
            return;
        }

        PrintStream printOut = null;

        try {
            ByteArrayOutputStream bytesBufOut = new ByteArrayOutputStream();
            printOut = new PrintStream(bytesBufOut);
            e.printStackTrace(printOut);
            printOut.flush();
            error(tag, new String(bytesBufOut.toByteArray(), "UTF-8"));

        } catch (Exception e1) {
            e1.printStackTrace();

        } finally {
            closeStream(printOut);
        }
    }

    private static void printLog(DebugLevel level, String tag, String message, boolean isOutToErr) {
        if (level.getLevelValue() >= logOutLevel.getLevelValue()) {
            String log = DATE_FORMAT.format(new Date()) +
                    " " +
                    level.getTag() +
                    "/" +
                    checkTextLengthLimit(tag, TAG_MAX_LENGTH) +
                    ": " +
                    checkTextLengthLimit(message, MESSAGE_MAX_LENGTH);

            if (isOutToConsole) {
                outLogToConsole(isOutToErr, log);
            }
            if (isOutToFile) {
                outLogToFile(log);
            }
        }
    }

    private static void outLogToConsole(boolean isOutToErr, String log) {
        if (isOutToErr) {
            // System.err 和 System.out 是两个不同的输出流通道, 如果极短时间内连
            // 续输出 log 到 err 和 out, 控制台上的打印顺序可能会不完全按时序打印.
            System.err.println(log);
        } else {
            System.out.println(log);
        }
    }

    private static synchronized void outLogToFile(String log) {
        if (logOutFileStream != null) {
            try {
                logOutFileStream.write((log + "\n").getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String checkTextLengthLimit(String text, int maxLength) {
        if ((text != null) && (text.length() >  maxLength)) {
            text = text.substring(0, maxLength - 3) + "...";
        }
        return text;
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                // nothing
            }
        }
    }
}

enum DebugLevel {
    DEBUG("DEBUG", 2),
    INFO("INFO", 1),
    WARN("WARN", 3),
    ERROR("ERROR", 4);

    private String tag;

    private int levelValue;

    private DebugLevel(String tag, int levelValue) {
        this.tag = tag;
        this.levelValue = levelValue;
    }

    public String getTag() {
        return tag;
    }

    public int getLevelValue() {
        return levelValue;
    }
}


//*   -------------------------------------------NO.2--------------------------------------------   */


/**
  * 自定义日志文件处理器
  * @author Administrator
  */
class CustomFileStreamHandler extends StreamHandler {
    //输出流
    private MeteredStream msOut;
    //日志玩家的写入的字节数, limit 为0 表示没有限制
    private int limit = 50000;
    //是否添加的玩家末尾
    private boolean append;
    //保存存在的日志文件
    private LinkedList<File> files;
    //希望写入的日志路径
    private String fileUrl;
    //保存几天之内的日志文件
    private int dateInter = 1;
    //索引文件，用于记录当前日志记录信息，请不要认为的修改
    private File indexFile;

    /**
   * 初始化自定义文件流处理器
   * @param fileUrl 文件路径， 可以是个目录或希望的日志名称，如果是个目录则日志为“未命名”，指定日志名称时不需要包括日期，程序会自动生成日志文件的生成日期及相应的编号
   * @param limit 每个日志希望写入的最大字节数，如果日志达到最大字节数则当天日期的一个新的编号的日志文件将被创建，最新的日志记录在最大编号的文件中
   * @param dateInter 事务保存的日期间隔
   * @param append 是否将日志写入已存在的日志文件中
   * @throws java.lang.Exception
   */
    public CustomFileStreamHandler(String fileUrl, int limit, int dateInter, boolean append) throws Exception {
        super();
        this.fileUrl = fileUrl;
        if (dateInter <= 0) {
            throw new IllegalArgumentException("时间间隔必须大于0");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("写入日志文件的最大字节数必须大于0");
        }
        this.limit = limit;
        this.dateInter = dateInter;
        this.append = append;
        openWriteFiles();
    }

    public CustomFileStreamHandler(String fileUrl, boolean append) throws Exception {
        super();
        this.fileUrl = fileUrl;
        this.append = append;
        openWriteFiles();
    }

    /**
      * 获得将要写入的文件
      */
    private synchronized void openWriteFiles() throws Exception {
        if (fileUrl == null) {
            throw new IllegalArgumentException("文件路径不能为null");
        }
        files = getWritedLog();
        checkLogFile();
        if (append) {
            openFile(files.getLast(), append);
        } else {
            getLastFile();
        }
    }

    /**
   * 打开需要写入的文件
   * @param file 需要打开的文件
   * @param append 是否将内容添加到文件末尾
   */
    private void openFile(File file, boolean append) throws Exception {
        //System.out.println("***opend = true " + file.toString());
        int len = 0;
        if (append) {
            len = (int) file.length();
        }
        FileOutputStream fout = new FileOutputStream(file.toString(), append);
        BufferedOutputStream bout = new BufferedOutputStream(fout);
        msOut = new MeteredStream(bout, len);
        setOutputStream(msOut);
    }

    /**
    * 将离现在最近的文件作为写入文件的文件
    * 例如 D:logmylog_30_2008-02-19.log
    * mylog表示自定义的日志文件名，2008-02-19表示日志文件的生成日期，30 表示此日期的第30个日志文件
    */
    private void getLastFile() {
        try {
            super.close();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String trace = sdf.format(new Date().getTime());
            int maxLogCount = 0; //获得当前最大的日志文件编号
            //System.out.println("********共有文件**********");
            for (File file : files) {
                //System.out.println(file.toString());
                String fileName = file.toString();
                //获得相同同日期日志文件的最大编号
                if (fileName.indexOf(trace) != -1) {
                    int last = fileName.lastIndexOf('_');
                    int beforeLast = fileName.lastIndexOf('_', last - 1);
                    maxLogCount = Integer.valueOf(fileName.substring(beforeLast + 1, last));
                }
            }
            //System.out.println("********");
            //System.out.println("maxLogCount == " + maxLogCount);
            File file = new File(fileUrl);
            String logIndex = (maxLogCount + 1) + "_" + trace;
            if (file.isDirectory()) { //是个目录
                files.add(new File(fileUrl + File.separator + "未命名_" + logIndex + ".log"));
            } else {
                files.add(new File(fileUrl + "_" + logIndex + ".log"));
            }
            writeLogIndex(logIndex, true);
            openFile(files.getLast(), false);
        } catch (Exception ex) {
            Logger.getLogger(CustomFileStreamHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
   * 读取已经记录的日志的时间信息
   */
    private LinkedList<File> getWritedLog() {
        LinkedList<File> fileList = new LinkedList<File>();
        BufferedReader br = null;
        try {
            File file = new File(fileUrl);
            if (fileUrl.endsWith("/") || fileUrl.endsWith("/")) {//是个目录
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            if (file.isDirectory()) { //只有指定file存在且是个目录     
                indexFile = new File(file.toString() + File.separator + "logindex");
            } else {
                indexFile = new File(file.getParent() + File.separator + "logindex");
            }
            if (!indexFile.exists()) {
                indexFile.createNewFile();
            }

            FileReader fr = null;
            fr = new FileReader(indexFile);
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() != 0) {
                    if (file.isDirectory()) { //是个目录
                        fileList.add(new File(fileUrl + File.separator + "未命名" + "_" + line + ".log"));
                    } else {
                    fileList.add(new File(fileUrl + "_" + line + ".log"));
                }
                //System.out.println("line == " + line);
                }
            }
            return fileList;
        } catch (Exception ex) {
            Logger.getLogger(CustomFileStreamHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(CustomFileStreamHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
   * 写入日志索引
   * @param logIndex 日志所以
   * @param isAppend 是否添加到索引文件中
   */
    private void writeLogIndex(String logIndex, boolean isAppend) {
        File file = new File(fileUrl);
        BufferedWriter bw = null;
        try {
            FileWriter fw = null;
            if (file.isDirectory()) { //是个目录     
                //是个目录
                fw = new FileWriter(new File(file.toString() + File.separator + "logindex"), isAppend);
            } else {
                fw = new FileWriter(new File(file.getParent() + File.separator + "logindex"), isAppend);
            }
            bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(logIndex, 0, logIndex.length());
            bw.flush();
        } catch (Exception ex) {
            Logger.getLogger(CustomFileStreamHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(CustomFileStreamHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
   * 检查当前日志时间
   * 删除过期日志，并检查日志索引中是否包含了现在日期
   */
    private void checkLogFile() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String trace = sdf.format(new Date().getTime());
            boolean isIncludeNow = false;
            LinkedList<File> overdueLog = new LinkedList<File>(); //过期的日志文件
            long nowDate = sdf.parse(trace).getTime();
            for (File file : files) {
                if (file.toString().indexOf(trace) != -1) {
                    isIncludeNow = true;
                }
                if ((nowDate - sdf.parse(file.toString(), new ParsePosition(file.toString().lastIndexOf('_') + 1)).getTime()) / (86400 * 1000) > 5) {
                    overdueLog.add(file);
                    //System.err.println("将被删除的日志为 " + file);
                }
            }
            //删除过期日志记录, 并重写日志索引文件
            if (overdueLog.size() != 0) {
                files.removeAll(overdueLog);
                indexFile.delete();
                indexFile.createNewFile();
                String fileStr = null;
                for (File file : files) {
                    fileStr = file.toString();
                    writeLogIndex(fileStr.substring(fileStr.lastIndexOf('_') - 1, fileStr.lastIndexOf('.')), true);
                }
                //删除过期文件
                for (File file : overdueLog) {
                    file.delete();
                }
            }
            //如果没有包含当天的日期同时日志需要添加到文件末尾，则添加当天日期的日志文件
            if (!isIncludeNow && append) {
                File file = new File(fileUrl);
                String logIndex = 1 + "_" + trace;
                if (file.isDirectory()) {
                     //是个目录
                    files.add(new File(fileUrl + File.separator + "未命名_" + logIndex + ".log"));
                } else {
                    files.add(new File(fileUrl + "_" + logIndex + ".log"));
                }
                writeLogIndex(logIndex, true);
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomFileStreamHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
    * 发布日志信息
    */
    @Override
    public synchronized void publish(LogRecord record) {
       super.publish(record);
       super.flush();
       if (limit > 0 && msOut.written >= limit) {
           getLastFile();
       }
    }
    /**
    * 抄自FileHandler的实现，用于跟踪写入文件的字节数
    * 这样以便提高效率
    */
    private class MeteredStream extends OutputStream {
        private OutputStream out;
        //记录当前写入字节数
        private int written;

        MeteredStream(OutputStream out, int written) {
            this.out = out;
            this.written = written;
        }

        public void write(int b) throws IOException {
            out.write(b);
            written++;
        }

        @Override
        public void write(byte buff[]) throws IOException {
            out.write(buff);
            written += buff.length;
        }

        @Override
        public void write(byte buff[], int off, int len) throws IOException {
            out.write(buff, off, len);
            written += len;
        }

        @Override
        public void flush() throws IOException {
            out.flush();
        }

        @Override
        public void close() throws IOException {
            out.close();
        }
    }
}

/**
  * 自定义格式化器
  * @author Administrator
  */
class CustomFormatter extends Formatter {

     //当前是第几条记录
     private int logCount;
     //时间
     private Date dat = new Date();
     //参数
     private Object[] args = new Object[1];
     //消息格式化器
     private MessageFormat formatter;
     //时间参数
     private String format = "日期 {0,date} 时间{0,time}";
     //行分格符
     private String lineSeparator = AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));

     /**
    * @param 日志记录器
    * @return 返回格式化好的日志内容
    */
     @Override
     public String format(LogRecord record) {
         StringBuffer sb = new StringBuffer();
         dat.setTime(record.getMillis());
         args[0] = dat;
         StringBuffer text = new StringBuffer();
         if (formatter == null) {
            formatter = new MessageFormat(format);
         }
         formatter.format(args, text, null);
         sb.append("【第 " + (logCount++) + " 条记录】" + lineSeparator);
         sb.append(text);
         sb.append(" ");
         if (record.getSourceClassName() != null) {
             sb.append("源文件名 " + record.getSourceClassName());
         } else {
             sb.append("日志名 " + record.getLoggerName());
         }
         if (record.getSourceMethodName() != null) {
             sb.append(" ");
             sb.append("方法名 " + record.getSourceMethodName());
         }
         sb.append(lineSeparator);
         String message = formatMessage(record);
         sb.append(record.getLevel().getLocalizedName());
         sb.append(": ");
         sb.append(message);
         sb.append(lineSeparator);
         if (record.getThrown() != null) {
             try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                record.getThrown().printStackTrace(pw);
                pw.close();
                sb.append(sw.toString());
             } catch (Exception ex) { }
         }
         return sb.toString();
     }
}

/**
  * 日记记录器
  * @author twbcb
  */
class MyLog {

    private static Logger fileLogger;

    static {
        fileLogger = Logger.getLogger("com.ckcs.log.customlog");
        fileLogger.setLevel(Level.INFO);
        Handler[] hs = fileLogger.getHandlers();
        for (Handler h : hs) {
            h.close();
            fileLogger.removeHandler(h);
        }
        try {
            //文件 日志文件名为mylog 日志最大写入为4000个字节 保存5天内的日志文件 如果文件没有达到规定大小则将日志文件添加到已有文件
            CustomFileStreamHandler fh = new CustomFileStreamHandler("d:/log/mylog", 4000, 5, true);
            //CustomFileStreamHandler fh = new CustomFileStreamHandler("d:/log/mylog/", 1024, 5, true); //目录
            fh.setEncoding("UTF-8");
            fh.setFormatter(new CustomFormatter());
            fileLogger.setUseParentHandlers(false);
            fileLogger.addHandler(fh);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

     /**
    * Creates a new instance of MyLog
    */
     private MyLog() {
     }

     /**
    * 返回一个文件记录实例
    */
     public static synchronized Logger getFileLogger() {
         return fileLogger;
     }

     public static void main(String[] args) {
         for (int i = 0; i < 10; i++) {
             fileLogger.log(Level.INFO, "我被记录了吗?");
         }
     }
}