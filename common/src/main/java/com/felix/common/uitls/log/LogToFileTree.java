package com.felix.common.uitls.log;

import com.felix.common.uitls.Constants;

import org.apache.commons.io.IOUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import timber.log.Timber;


/**
 * user Timber, save to file
 */
public final class LogToFileTree extends Timber.DebugTree {

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 10;   //10M

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String VERBOSE = "V/";

    private static final String DEBUG = "D/";

    private static final String INFO = "I/";

    private static final String WARN = "W/";

    private static final String ERROR = "E/";

    private Subject<LogInfo> mSubject;

    private final Context mContext;

    private final String mProcessName;

    public LogToFileTree(Context context, String processName) {
        mContext = context;
        mProcessName = processName;
        mSubject = PublishSubject.create();
        listenToWriteLog();
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        super.log(priority, tag, message, t);
        mSubject.onNext(new LogInfo(priority, tag, message));
    }

    @SuppressLint("CheckResult")
    private void listenToWriteLog() {
        mSubject.ofType(LogInfo.class)
                .toFlowable(BackpressureStrategy.BUFFER)
                .map(this::formatLog)
                .doOnNext(this::logToFile)
                .onErrorReturn(throwable -> null)
                .subscribeOn(Schedulers.io())
                .subscribe(s -> {
                }, throwable -> {
                });
    }

    private String formatLog(LogInfo logInfo) {
        StringBuilder builder = new StringBuilder(DATE_FORMAT.format(new Date()));
        builder.append(' ')
                .append(android.os.Process.myPid())
                .append('/')
                .append(mProcessName)
                .append(' ')
                .append(getPriorityString(logInfo.getPriority()))
                .append(logInfo.getTag())
                .append(": ")
                .append(logInfo.getMessage());
        return builder.toString();
    }

    private void logToFile(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        String fileName = TextUtils.equals(mProcessName, mContext.getPackageName())
                ? Constants.LOG_FILE : mProcessName + ".log";
        String logDir = mContext.getExternalFilesDir("Mashup") + File.separator + fileName;
        if (TextUtils.isEmpty(logDir)) {
            return;
        }

        BufferedWriter buf = null;
        try {
            File logFile = new File(logDir);
            if (!logFile.exists()) {
                if (logFile.createNewFile()) {
                    writeDeviceInfo(logFile);
                } else {
                    return;
                }
            }

            adjustFileSize(logFile, message.length());

            buf = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(logFile, true), "UTF-8"));
            buf.append(message);
            buf.newLine();
            buf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(buf);
        }
    }

    private void writeDeviceInfo(File logFile) {
        BufferedWriter buf = null;
        try {
            String info = "DeviceInfo : " + Build.BRAND + "; " + Build.MODEL + "; " + "android "
                    + Build.VERSION.RELEASE + "; " + "\n";
            buf = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(logFile, true), "UTF-8"));
            buf.append(info);
            buf.newLine();
            buf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(buf);
        }
    }

    private String getPriorityString(int priority) {
        switch (priority) {
            case Log.DEBUG:
                return DEBUG;
            case Log.INFO:
                return INFO;
            case Log.WARN:
                return WARN;
            case Log.ERROR:
                return ERROR;
            default:
                return VERBOSE;
        }
    }

    private void adjustFileSize(File logFile, long appendLength) {
        if (logFile.length() + appendLength > MAX_FILE_SIZE) {
            BufferedWriter buf = null;
            RandomAccessFile rwFile = null;
            try {
                rwFile = new RandomAccessFile(logFile, "rw");
                String firstLine = rwFile.readLine();
                rwFile.seek(logFile.length() / 2);
                List<String> list = new ArrayList<>();
                list.add(firstLine);
                while (true) {
                    String line = rwFile.readLine();
                    if (line == null) {
                        break;
                    } else {
                        list.add(line);
                    }
                }
                buf = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(logFile, false), "UTF-8"));
                IOUtils.writeLines(list, null, buf);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(buf);
                if (rwFile != null) {
                    try {
                        rwFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
