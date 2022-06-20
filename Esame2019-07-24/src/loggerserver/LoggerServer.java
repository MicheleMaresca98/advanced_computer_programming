package loggerserver;

public class LoggerServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = Integer.valueOf(args[0]).intValue();
		LoggerImpl loggerImpl = new LoggerImpl();
		LoggerSkeleton loggerSkeleton = new LoggerSkeleton(port, loggerImpl);
		loggerSkeleton.runSkeleton();
	}

}
