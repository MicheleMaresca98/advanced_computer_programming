package logger;

import interfaces.ILogger;

public class LoggerServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ILogger loggerImpl = new LoggerImpl();
		LoggerSkeleton loggerSkeleton = new LoggerSkeleton(loggerImpl);
		loggerSkeleton.runSkeleton();

	}

}
