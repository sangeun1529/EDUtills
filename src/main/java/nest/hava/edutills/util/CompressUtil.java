package nest.hava.edutills.util;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Stack;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressUtil {

	public static byte[] compress(String src) throws IOException {

		byte[] dataByte = src.getBytes();

		Deflater deflater = new Deflater();

		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(dataByte);
		deflater.finish();

		ByteArrayOutputStream bao = new ByteArrayOutputStream(dataByte.length);

		byte[] buf = new byte[1024];

		while (!deflater.finished()) {
			int compByte = deflater.deflate(buf);
			bao.write(buf, 0, compByte);
		}

		bao.close();
		deflater.end();

		return bao.toByteArray();
	}

	public static byte[] decompress(byte[] data) throws IOException, DataFormatException {

		Inflater inflater = new Inflater();

		inflater.setInput(data);

		ByteArrayOutputStream bao = new ByteArrayOutputStream();

		byte[] buf = new byte[1024];

		while (!inflater.finished()) {
			int compByte = inflater.inflate(buf);
			bao.write(buf, 0, compByte);
		}

		bao.close();

		inflater.end();

		return bao.toByteArray();
	}

	public static void unzip(File zippedFile) throws IOException {
		unzip(zippedFile, Charset.defaultCharset().name());
	}

	public static void unzip(File zippedFile, String charsetName) throws IOException {
		unzip(zippedFile, zippedFile.getParentFile(), charsetName);
	}

	public static void unzip(File zippedFile, File destDir, FilenameFilter filter) throws IOException {
		unzip(new FileInputStream(zippedFile), destDir, Charset.defaultCharset().name(), filter);
	}

	public static void unzip(File zippedFile, File destDir, String charsetName) throws IOException {
		unzip(new FileInputStream(zippedFile), destDir, charsetName, null);
	}

	public static void unzip(InputStream is, File destDir) throws IOException {
		unzip(is, destDir, Charset.defaultCharset().name(), null);
	}

	public static void unzip(InputStream is, File destDir, String charsetName, FilenameFilter filter)
			throws IOException {

		ZipArchiveInputStream zis = null;
		ZipArchiveEntry entry = null;
		String name = null;
		File target = null;
		int nWritten = 0;
		BufferedOutputStream bos = null;
		byte[] buf = new byte[1024 * 8];

		zis = new ZipArchiveInputStream(is, charsetName, false);

		try {
			while ((entry = zis.getNextZipEntry()) != null) {
				name = entry.getName();

				if (filter != null && !filter.accept(destDir, name))
					continue;

				target = new File(destDir, name);

				if (entry.isDirectory()) {
					target.mkdirs(); /* does it always work? */
				} else {
					target.createNewFile();
					bos = new BufferedOutputStream(new FileOutputStream(target));
					while ((nWritten = zis.read(buf)) >= 0) {
						bos.write(buf, 0, nWritten);
					}
					bos.close();
				}
			}
		} finally {
			IOUtils.closeQuietly(bos);
			IOUtils.closeQuietly(zis);
		}
	}

	/**
	 * compresses the given file(or dir) and creates new file under the same
	 * directory.
	 * 
	 * @param src
	 *            file or directory
	 * @throws IOException
	 */
	public static File zip(File src) throws IOException {
		return zip(src, Charset.defaultCharset().name(), true);
	}

	/**
	 * zips the given file(or dir) and create
	 * 
	 * @param src
	 *            file or directory to compress
	 * @param includeSrc
	 *            if true and src is directory, then src is not included in the
	 *            compression. if false, src is included.
	 * @throws IOException
	 */
	public static File zip(File src, boolean includeSrc) throws IOException {
		return zip(src, Charset.defaultCharset().name(), includeSrc);
	}

	/**
	 * compresses the given src file (or directory) with the given encoding
	 * 
	 * @param src
	 * @param charSetName
	 * @param includeSrc
	 * @throws IOException
	 */
	public static File zip(File src, String charSetName, boolean includeSrc) throws IOException {
		return zip(src, src.getParentFile(), charSetName, includeSrc);
	}

	/**
	 * compresses the given src file(or directory) and writes to the given
	 * output stream.
	 * 
	 * @param src
	 * @param os
	 * @throws IOException
	 */
	public static void zip(File src, OutputStream os) throws IOException {
		zip(src, os, Charset.defaultCharset().name(), true);
	}

	/**
	 * compresses the given src file(or directory) and create the compressed
	 * file under the given destDir.
	 * 
	 * @param src
	 * @param destDir
	 * @param charSetName
	 * @param includeSrc
	 * @throws IOException
	 */
	public static File zip(File src, File destDir, String charSetName, boolean includeSrc) throws IOException {
		String fileName = src.getName();
		if (!src.isDirectory()) {
			int pos = fileName.lastIndexOf(".");
			if (pos > 0) {
				fileName = fileName.substring(0, pos);
			}
		}
		fileName += ".zip";

		File zippedFile = new File(destDir, fileName);
		if (!zippedFile.exists())
			zippedFile.createNewFile();
		zip(src, new FileOutputStream(zippedFile), charSetName, includeSrc);
		return zippedFile;
	}

	public static void zip(File src, OutputStream os, String charsetName, boolean includeSrc) throws IOException {
		ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
		zos.setEncoding(charsetName);
		FileInputStream fis = null;

		int length;
		ZipArchiveEntry ze = null;
		byte[] buf = new byte[8 * 1024];
		String name = null;

		Stack<File> stack = new Stack<File>();
		File root;
		if (src.isDirectory()) {
			if (includeSrc) {
				stack.push(src);
				root = src.getParentFile();
			} else {
				File[] fs = src.listFiles();
				for (int i = 0; i < fs.length; i++) {
					stack.push(fs[i]);
				}
				root = src;
			}
		} else {
			stack.push(src);
			root = src.getParentFile();
		}

		try {
			while (!stack.isEmpty()) {
				File f = stack.pop();
				name = toPath(root, f);
				if (f.isDirectory()) {
					File[] fs = f.listFiles();
					for (int i = 0; i < fs.length; i++) {
						if (fs[i].isDirectory())
							stack.push(fs[i]);
						else
							stack.add(0, fs[i]);
					}
				} else {
					ze = new ZipArchiveEntry(name);
					zos.putArchiveEntry(ze);
					fis = new FileInputStream(f);
					while ((length = fis.read(buf, 0, buf.length)) >= 0) {
						zos.write(buf, 0, length);
					}
					fis.close();
					zos.closeArchiveEntry();
				}
			}
		} finally {
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(zos);
		}
	}

	private static String toPath(File root, File dir) {
		String path = dir.getAbsolutePath();
		path = path.substring(root.getAbsolutePath().length()).replace(File.separatorChar, '/');
		if (path.startsWith("/"))
			path = path.substring(1);
		if (dir.isDirectory() && !path.endsWith("/"))
			path += "/";
		return path;
	}
}
