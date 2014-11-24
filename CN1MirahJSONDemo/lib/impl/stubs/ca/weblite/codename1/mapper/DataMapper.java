package ca.weblite.codename1.mapper;


/**
 * 
 *  @author shannah
 */
public abstract class DataMapper {

	public DataMapper() {
	}

	public DataMapper(java.util.Map context) {
	}

	protected void init() {
	}

	public void addDateFormat(com.codename1.l10n.DateFormat fmt) {
	}

	public java.util.List getDateFormats() {
	}

	public void removeDateFormat(com.codename1.l10n.DateFormat fmt) {
	}

	public void clearDateFormats() {
	}

	public boolean exists(java.util.Map map, String key) {
	}

	public Object get(java.util.Map map, String key) {
	}

	public java.util.Map getMap(java.util.Map map, String key, Class cls) {
	}

	public java.util.List getList(java.util.Map map, String key, Class cls) {
	}

	public Object get(java.util.Map map, String key, Class cls) {
	}

	public void set(java.util.Map map, String key, Object value) {
	}

	public boolean getBoolean(java.util.Map map, String key) {
	}

	public byte getByte(java.util.Map map, String key) {
	}

	public char getChar(java.util.Map map, String key) {
	}

	public float getFloat(java.util.Map map, String key) {
	}

	public int getInt(java.util.Map map, String key) {
	}

	public short getShort(java.util.Map map, String key) {
	}

	public double getDouble(java.util.Map map, String key) {
	}

	public long getLong(java.util.Map map, String key) {
	}

	public String getString(java.util.Map map, String key) {
	}

	public java.util.Date getDate(java.util.Map map, String key) {
	}

	public Object getObject(java.util.Map map, String key, Class klass) {
	}

	public java.util.List getObjects(java.util.Map map, String key, Class klass) {
	}

	public java.util.Date[] getDateArray(java.util.Map map, String key) {
	}

	public int[] getIntArray(java.util.Map map, String key) {
	}

	public double[] getDoubleArray(java.util.Map map, String key) {
	}

	protected Object createObject(Class klass) {
	}

	public Object readMap(java.util.Map map, Class klass) {
	}

	public Object readJSONFromURL(String url, Class klass) {
	}

	public Object readJSONFromConnection(com.codename1.io.ConnectionRequest req, Class klass) {
	}

	public Object readJSON(java.io.InputStream is, String charset, Class klass) {
	}

	public Object readJSON(java.io.InputStream is, Class klass) {
	}

	public Object readJSON(String data, Class klass) {
	}

	public abstract void writeMap(java.util.Map dest, Object src) {
	}

	public java.util.Map writeMap(Object src) {
	}

	public abstract void readMap(java.util.Map src, Object dest) {
	}

	public void setFieldMapper(String field, FieldMapper mapper) {
	}

	public void removeFieldMapper(String field) {
	}

	public void setFieldMapper(String field, String path) {
	}

	public void register(Class klass, DataMapper mapper) {
	}

	public void unregister(Class klass) {
	}

	public java.util.Map getContext() {
	}

	public void setContext(java.util.Map context) {
	}

	public void setObjectFactory(ObjectFactory f) {
	}

	public ObjectFactory getObjectFactory() {
	}

	public DataMapper getDataMapperForClass(Class cls) {
	}
}
