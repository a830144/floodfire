package edu.nccu.floodfire.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.MalformedInputException;
import java.util.List;

import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnOrSuperColumn;
import org.apache.cassandra.thrift.KeySlice;
import org.apache.cassandra.thrift.KsDef;
import org.apache.cassandra.utils.ByteBufferUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JsonMarshaller {
	
	public static String marshallStatus(Object o)
	        throws UnsupportedEncodingException{
		
		String json = net.sf.json.JSONObject.fromObject(o).toString();
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static String marshallColumn(ColumnOrSuperColumn column)
			throws UnsupportedEncodingException {
		JSONObject json = new JSONObject();
		Column c = column.getColumn();
		json.put(string(c.getName()), string(c.getValue()));
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public static JSONObject marshallSlice(List<ColumnOrSuperColumn> slice)
			throws UnsupportedEncodingException {
		JSONObject json = new JSONObject();
		for (ColumnOrSuperColumn column : slice) {
			Column c = column.getColumn();
			json.put(string(c.getName()), string(c.getValue()));
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONArray marshallRows(List<KeySlice> rows, boolean flatten)
			throws Exception {
		if (flatten) {
			JSONArray cfJson = new JSONArray();
			for (KeySlice row : rows) {
				String rowKey = ByteBufferUtil.string(row.key);
				for (ColumnOrSuperColumn column : row.columns) {
					JSONObject rowJson = new JSONObject();
					rowJson.put("row", rowKey);
					try {
						rowJson.put("column",
								ByteBufferUtil.string(column.column.name));
					} catch (MalformedInputException mfie) {
						rowJson.put("column",
								"NON-STRING (Support for non-strings is coming)");
					}
					try {
						rowJson.put("value",
								ByteBufferUtil.string(column.column.value));
					} catch (MalformedInputException mfie) {
						rowJson.put("value",
								"NON-STRING (Support for non-strings is coming)");
					}
					cfJson.add(rowJson);
				}
			}
			return cfJson;
		} else {
			throw new RuntimeException(
					"Virgil does not support hiearchical fetch of column family yet.");
		}
	}

	@SuppressWarnings("unchecked")
	public static JSONArray marshallKeyspaces(List<KsDef> keyspaces,
			boolean flatten) throws UnsupportedEncodingException {
		JSONArray keyspaceJson = new JSONArray();
		if (flatten) {
			for (KsDef keyspace : keyspaces) {
				List<CfDef> columnFamilies = keyspace.getCf_defs();
				for (CfDef columnFamily : columnFamilies) {
					JSONObject json = new JSONObject();
					json.put("keyspace", keyspace.getName());
					json.put("columnFamily", columnFamily.getName());
					keyspaceJson.add(json);
				}
			}
		} else {
			for (KsDef keyspace : keyspaces) {
				JSONObject json = new JSONObject();
				json.put("keyspace", keyspace.getName());
				json.put("strategy", keyspace.getStrategy_class());
				List<CfDef> columnFamilies = keyspace.getCf_defs();
				JSONArray cfJsonArray = new JSONArray();
				for (CfDef columnFamily : columnFamilies) {
					JSONObject cfJson = new JSONObject();
					cfJson.put("name", columnFamily.getName());
					cfJsonArray.add(cfJson);
				}
				json.put("columnFamilies", cfJsonArray);
				keyspaceJson.add(json);
			}
		}
		return keyspaceJson;
	}

	private static String string(byte[] bytes)
			throws UnsupportedEncodingException {
		return new String(bytes, "UTF8");
	}
}
