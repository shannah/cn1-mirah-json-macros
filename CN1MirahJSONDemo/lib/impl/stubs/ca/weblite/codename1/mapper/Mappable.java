package ca.weblite.codename1.mapper;


/**
 * 
 *  @author shannah
 */
public interface Mappable {

	/**
	 *  Reads values from a map
	 *  @param map 
	 */
	public void readMap(java.util.Map map, Mapper mapper);

	/**
	 *  Write values to map.
	 *  @param map 
	 */
	public void writeMap(java.util.Map map, Mapper mapper);
}
