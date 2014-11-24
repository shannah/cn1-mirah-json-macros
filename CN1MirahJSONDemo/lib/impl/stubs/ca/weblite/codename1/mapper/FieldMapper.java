package ca.weblite.codename1.mapper;


/**
 * 
 *  @author shannah
 */
public interface FieldMapper {

	public Object getValue(java.util.Map map, String fieldName);

	public void putValue(java.util.Map map, String fieldName, Object value);

	public boolean valueExists(java.util.Map map, String fieldName);
}
