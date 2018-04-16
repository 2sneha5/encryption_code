/*
 * LibStoken.java - Java wrapper for libstoken.so
 *
 * 
 */


public class LibStoken {

	/* constants */

	public static final int SUCCESS = 0;
	public static final int INVALID_FORMAT = -1;
	public static final int IO_ERROR = -2;
	public static final int FILE_NOT_FOUND = -3;

	/* create/destroy library instances */

	public LibStoken() {
		libctx = init();
	}

	public synchronized void destroy() {
		if (libctx != 0) {
			free();
			libctx = 0;
		}
	}

	public static class StokenInfo {
		public String serial;
		public long unixExpDate;
		public int interval;
		public int tokenVersion;
		public boolean usesPin;
	};

	public static class StokenGUID {
		public String tag;
		public String longName;
		public String GUID;
	}

	/* public APIs */

	public synchronized native int importRCFile(String path);
	public synchronized native int importString(String str);
	public synchronized native StokenInfo getInfo();
	public synchronized native int getMinPIN();
	public synchronized native int getMaxPIN();
	public synchronized native boolean isPINRequired();
	public synchronized native boolean isPassRequired();
	public synchronized native boolean isDevIDRequired();
	public synchronized native boolean checkPIN(String PIN);
	public synchronized native boolean checkDevID(String DevID);
	public synchronized native StokenGUID[] getGUIDList();
	public synchronized native int decryptSeed(String pass, String devID);
	public synchronized native String encryptSeed(String pass, String devID);
	public synchronized native String computeTokencode(long when, String PIN);
	public synchronized native String formatTokencode(String tokencode);

	/* LibStoken internals */

	long libctx;
	synchronized native long init();
	synchronized native void free();
}