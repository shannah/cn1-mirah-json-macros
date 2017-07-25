/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.cn1.mirah.json;

import java.util.List;
import java.util.Map;

/**
 *
 * @author shannah
 */
public class SinkTestClass {
    private int intVal;
    private double doubleVal;
    private short shortVal;
    private long longVal;
    private float floatVal;
    private String stringVal;
    private byte byteVal;
    private char charVal;
    
    private Integer boxedIntVal;
    private Double boxedDoubleVal;
    private Float boxedFloatVal;
    private Short boxedShortVal;
    private Byte boxedByteVal;
    private Character boxedCharVal;
    
    private SinkTestClass childSinkTest;
    
    private int[] intArrayVal;
    private String[] stringArrayVal;
    private SinkTestClass child;
    
    private SinkTestClass[] children;
    private List<SinkTestClass> childrenList;
    
    private List<String> stringList;
    private List<Integer> intList;
    private List<Float> floatList;
    private List<Double> doubleList;
    private List<Byte> byteList;
    private List<Character> charList;
    private List<Long> longList;
    
    public List<String> pubStringList;
    public Map<String,String> pubStringMap;
    
    private Map<String,Integer> integerMap;
    
    
    public int publicInt;
    private String[] stringArr;
    

    /**
     * @return the intVal
     */
    public int getIntVal() {
        return intVal;
    }

    /**
     * @param intVal the intVal to set
     */
    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

    /**
     * @return the doubleVal
     */
    public double getDoubleVal() {
        return doubleVal;
    }

    /**
     * @param doubleVal the doubleVal to set
     */
    public void setDoubleVal(double doubleVal) {
        this.doubleVal = doubleVal;
    }

    /**
     * @return the shortVal
     */
    public short getShortVal() {
        return shortVal;
    }

    /**
     * @param shortVal the shortVal to set
     */
    public void setShortVal(short shortVal) {
        this.shortVal = shortVal;
    }

    /**
     * @return the longVal
     */
    public long getLongVal() {
        return longVal;
    }

    /**
     * @param longVal the longVal to set
     */
    public void setLongVal(long longVal) {
        this.longVal = longVal;
    }

    /**
     * @return the floatVal
     */
    public float getFloatVal() {
        return floatVal;
    }

    /**
     * @param floatVal the floatVal to set
     */
    public void setFloatVal(float floatVal) {
        this.floatVal = floatVal;
    }

    /**
     * @return the stringVal
     */
    public String getStringVal() {
        return stringVal;
    }

    /**
     * @param stringVal the stringVal to set
     */
    public void setStringVal(String stringVal) {
        this.stringVal = stringVal;
    }

    /**
     * @return the byteVal
     */
    public byte getByteVal() {
        return byteVal;
    }

    /**
     * @param byteVal the byteVal to set
     */
    public void setByteVal(byte byteVal) {
        this.byteVal = byteVal;
    }

    /**
     * @return the charVal
     */
    public char getCharVal() {
        return charVal;
    }

    /**
     * @param charVal the charVal to set
     */
    public void setCharVal(char charVal) {
        this.charVal = charVal;
    }

    /**
     * @return the boxedIntVal
     */
    public Integer getBoxedIntVal() {
        return boxedIntVal;
    }

    /**
     * @param boxedIntVal the boxedIntVal to set
     */
    public void setBoxedIntVal(Integer boxedIntVal) {
        this.boxedIntVal = boxedIntVal;
    }

    /**
     * @return the boxedDoubleVal
     */
    public Double getBoxedDoubleVal() {
        return boxedDoubleVal;
    }

    /**
     * @param boxedDoubleVal the boxedDoubleVal to set
     */
    public void setBoxedDoubleVal(Double boxedDoubleVal) {
        this.boxedDoubleVal = boxedDoubleVal;
    }

    /**
     * @return the boxedFloatVal
     */
    public Float getBoxedFloatVal() {
        return boxedFloatVal;
    }

    /**
     * @param boxedFloatVal the boxedFloatVal to set
     */
    public void setBoxedFloatVal(Float boxedFloatVal) {
        this.boxedFloatVal = boxedFloatVal;
    }

    /**
     * @return the boxedShortVal
     */
    public Short getBoxedShortVal() {
        return boxedShortVal;
    }

    /**
     * @param boxedShortVal the boxedShortVal to set
     */
    public void setBoxedShortVal(Short boxedShortVal) {
        this.boxedShortVal = boxedShortVal;
    }

    /**
     * @return the boxedByteVal
     */
    public Byte getBoxedByteVal() {
        return boxedByteVal;
    }

    /**
     * @param boxedByteVal the boxedByteVal to set
     */
    public void setBoxedByteVal(Byte boxedByteVal) {
        this.boxedByteVal = boxedByteVal;
    }

    /**
     * @return the boxedCharVal
     */
    public Character getBoxedCharVal() {
        return boxedCharVal;
    }

    /**
     * @param boxedCharVal the boxedCharVal to set
     */
    public void setBoxedCharVal(Character boxedCharVal) {
        this.boxedCharVal = boxedCharVal;
    }

    /**
     * @return the childSinkTest
     */
    public SinkTestClass getChildSinkTest() {
        return childSinkTest;
    }

    /**
     * @param childSinkTest the childSinkTest to set
     */
    public void setChildSinkTest(SinkTestClass childSinkTest) {
        this.childSinkTest = childSinkTest;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("intVal: ").append(intVal)
                .append(", shortVal: ").append(shortVal)
                .append(", floatVal: ").append(floatVal)
                .append(", doubleVal: ").append(doubleVal)
                .append(", stringVal: ").append(stringVal)
                .append(", pubStringList: ").append(pubStringList)
                .append(", pubStringMap: ").append(pubStringMap);
        return sb.toString();
    }

    /**
     * @return the intArrayVal
     */
    public int[] getIntArrayVal() {
        return intArrayVal;
    }

    /**
     * @param intArrayVal the intArrayVal to set
     */
    public void setIntArrayVal(int[] intArrayVal) {
        this.intArrayVal = intArrayVal;
    }

    /**
     * @return the stringArrayVal
     */
    public String[] getStringArrayVal() {
        return stringArrayVal;
    }

    /**
     * @param stringArrayVal the stringArrayVal to set
     */
    public void setStringArrayVal(String[] stringArrayVal) {
        this.stringArrayVal = stringArrayVal;
    }

    /**
     * @return the child
     */
    public SinkTestClass getChild() {
        return child;
    }

    /**
     * @param child the child to set
     */
    public void setChild(SinkTestClass child) {
        this.child = child;
    }

    /**
     * @return the children
     */
    public SinkTestClass[] getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(SinkTestClass[] children) {
        this.children = children;
    }

    /**
     * @return the stringList
     */
    public List<String> getStringList() {
        return stringList;
    }

    /**
     * @param stringList the stringList to set
     */
    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    /**
     * @return the intList
     */
    public List<Integer> getIntList() {
        return intList;
    }

    /**
     * @param intList the intList to set
     */
    public void setIntList(List<Integer> intList) {
        this.intList = intList;
    }

    /**
     * @return the floatList
     */
    public List<Float> getFloatList() {
        return floatList;
    }

    /**
     * @param floatList the floatList to set
     */
    public void setFloatList(List<Float> floatList) {
        this.floatList = floatList;
    }

    /**
     * @return the doubleList
     */
    public List<Double> getDoubleList() {
        return doubleList;
    }

    /**
     * @param doubleList the doubleList to set
     */
    public void setDoubleList(List<Double> doubleList) {
        this.doubleList = doubleList;
    }

    /**
     * @return the byteList
     */
    public List<Byte> getByteList() {
        return byteList;
    }

    /**
     * @param byteList the byteList to set
     */
    public void setByteList(List<Byte> byteList) {
        this.byteList = byteList;
    }

    /**
     * @return the charList
     */
    public List<Character> getCharList() {
        return charList;
    }

    /**
     * @param charList the charList to set
     */
    public void setCharList(List<Character> charList) {
        this.charList = charList;
    }

    /**
     * @return the longList
     */
    public List<Long> getLongList() {
        return longList;
    }

    /**
     * @param longList the longList to set
     */
    public void setLongList(List<Long> longList) {
        this.longList = longList;
    }

    /**
     * @return the integerMap
     */
    public Map<String,Integer> getIntegerMap() {
        return integerMap;
    }

    /**
     * @param integerMap the integerMap to set
     */
    public void setIntegerMap(Map<String,Integer> integerMap) {
        this.integerMap = integerMap;
    }

    /**
     * @return the childrenList
     */
    public List<SinkTestClass> getChildrenList() {
        return childrenList;
    }

    /**
     * @param childrenList the childrenList to set
     */
    public void setChildrenList(List<SinkTestClass> childrenList) {
        this.childrenList = childrenList;
    }

    /**
     * @return the stringArr
     */
    public String[] getStringArr() {
        return stringArr;
    }

    /**
     * @param stringArr the stringArr to set
     */
    public void setStringArr(String[] stringArr) {
        this.stringArr = stringArr;
    }
}
