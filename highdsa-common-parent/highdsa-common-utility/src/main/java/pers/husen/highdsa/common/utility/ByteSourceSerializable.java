package pers.husen.highdsa.common.utility;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.util.ByteSource;

/**
 * @Desc 采用redis缓存shiro, 在序列化的时候, SimpleByteSource类没有实现Serializable接口，导致序列化失败,
 *       SimpleByteSource没有默认构造方法，导致反序列化的时候失败,
 *       自定义ByteSource的实现类,模仿SimpleByteSource的方法.
 * 
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 上午10:53:48
 * 
 * @Version 1.0.0
 */
public class ByteSourceSerializable implements ByteSource, Serializable {

	private static final long serialVersionUID = -3924520800849353948L;

	private byte[] bytes;
	private String cachedHex;
	private String cachedBase64;

	public ByteSourceSerializable() {
	}

	public ByteSourceSerializable(byte[] bytes) {
		this.bytes = bytes;
	}

	public ByteSourceSerializable(char[] chars) {
		this.bytes = CodecSupport.toBytes(chars);
	}

	public ByteSourceSerializable(String string) {
		this.bytes = CodecSupport.toBytes(string);
	}

	public ByteSourceSerializable(ByteSource source) {
		this.bytes = source.getBytes();
	}

	public ByteSourceSerializable(File file) {
		this.bytes = (new ByteSourceSerializable.BytesHelper()).getBytes(file);
	}

	public ByteSourceSerializable(InputStream stream) {
		this.bytes = (new ByteSourceSerializable.BytesHelper()).getBytes(stream);
	}

	public static boolean isCompatible(Object o) {
		return o instanceof byte[] || o instanceof char[] || o instanceof String || o instanceof ByteSource || o instanceof File || o instanceof InputStream;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public byte[] getBytes() {
		return this.bytes;
	}

	@Override
	public String toHex() {
		if (this.cachedHex == null) {
			this.cachedHex = Hex.encodeToString(this.getBytes());
		}
		return this.cachedHex;
	}

	@Override
	public String toBase64() {
		if (this.cachedBase64 == null) {
			this.cachedBase64 = Base64.encodeToString(this.getBytes());
		}

		return this.cachedBase64;
	}

	@Override
	public boolean isEmpty() {
		return this.bytes == null || this.bytes.length == 0;
	}

	@Override
	public String toString() {
		return this.toBase64();
	}

	@Override
	public int hashCode() {
		return this.bytes != null && this.bytes.length != 0 ? Arrays.hashCode(this.bytes) : 0;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof ByteSource) {
			ByteSource bs = (ByteSource) o;
			return Arrays.equals(this.getBytes(), bs.getBytes());
		} else {
			return false;
		}
	}

	private static final class BytesHelper extends CodecSupport {
		private BytesHelper() {
		}

		public byte[] getBytes(File file) {
			return this.toBytes(file);
		}

		public byte[] getBytes(InputStream stream) {
			return this.toBytes(stream);
		}
	}
}