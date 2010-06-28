/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.cloudera.beeswax.api;

import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import org.apache.log4j.Logger;

import org.apache.thrift.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.protocol.*;

public class FileResource implements TBase, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("FileResource");
  private static final TField TYPE_FIELD_DESC = new TField("type", TType.I32, (short)1);
  private static final TField PATH_FIELD_DESC = new TField("path", TType.STRING, (short)2);

  public int type;
  public static final int TYPE = 1;
  public String path;
  public static final int PATH = 2;

  private final Isset __isset = new Isset();
  private static final class Isset implements java.io.Serializable {
    public boolean type = false;
  }

  public static final Map<Integer, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new HashMap<Integer, FieldMetaData>() {{
    put(TYPE, new FieldMetaData("type", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    put(PATH, new FieldMetaData("path", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.STRING)));
  }});

  static {
    FieldMetaData.addStructMetaDataMap(FileResource.class, metaDataMap);
  }

  public FileResource() {
  }

  public FileResource(
    int type,
    String path)
  {
    this();
    this.type = type;
    this.__isset.type = true;
    this.path = path;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public FileResource(FileResource other) {
    __isset.type = other.__isset.type;
    this.type = other.type;
    if (other.isSetPath()) {
      this.path = other.path;
    }
  }

  @Override
  public FileResource clone() {
    return new FileResource(this);
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
    this.__isset.type = true;
  }

  public void unsetType() {
    this.__isset.type = false;
  }

  // Returns true if field type is set (has been asigned a value) and false otherwise
  public boolean isSetType() {
    return this.__isset.type;
  }

  public void setTypeIsSet(boolean value) {
    this.__isset.type = value;
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public void unsetPath() {
    this.path = null;
  }

  // Returns true if field path is set (has been asigned a value) and false otherwise
  public boolean isSetPath() {
    return this.path != null;
  }

  public void setPathIsSet(boolean value) {
    if (!value) {
      this.path = null;
    }
  }

  public void setFieldValue(int fieldID, Object value) {
    switch (fieldID) {
    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((Integer)value);
      }
      break;

    case PATH:
      if (value == null) {
        unsetPath();
      } else {
        setPath((String)value);
      }
      break;

    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  public Object getFieldValue(int fieldID) {
    switch (fieldID) {
    case TYPE:
      return getType();

    case PATH:
      return getPath();

    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  // Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise
  public boolean isSet(int fieldID) {
    switch (fieldID) {
    case TYPE:
      return isSetType();
    case PATH:
      return isSetPath();
    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof FileResource)
      return this.equals((FileResource)that);
    return false;
  }

  public boolean equals(FileResource that) {
    if (that == null)
      return false;

    boolean this_present_type = true;
    boolean that_present_type = true;
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (this.type != that.type)
        return false;
    }

    boolean this_present_path = true && this.isSetPath();
    boolean that_present_path = true && that.isSetPath();
    if (this_present_path || that_present_path) {
      if (!(this_present_path && that_present_path))
        return false;
      if (!this.path.equals(that.path))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();

    boolean present_type = true;
    builder.append(present_type);
    if (present_type)
      builder.append(type);

    boolean present_path = true && (isSetPath());
    builder.append(present_path);
    if (present_path)
      builder.append(path);

    return builder.toHashCode();
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      switch (field.id)
      {
        case TYPE:
          if (field.type == TType.I32) {
            this.type = iprot.readI32();
            this.__isset.type = true;
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case PATH:
          if (field.type == TType.STRING) {
            this.path = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
          break;
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();


    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    oprot.writeFieldBegin(TYPE_FIELD_DESC);
    oprot.writeI32(this.type);
    oprot.writeFieldEnd();
    if (this.path != null) {
      oprot.writeFieldBegin(PATH_FIELD_DESC);
      oprot.writeString(this.path);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("FileResource(");
    boolean first = true;

    sb.append("type:");
    String type_name = FileResourceType.VALUES_TO_NAMES.get(this.type);
    if (type_name != null) {
      sb.append(type_name);
      sb.append(" (");
    }
    sb.append(this.type);
    if (type_name != null) {
      sb.append(")");
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("path:");
    if (this.path == null) {
      sb.append("null");
    } else {
      sb.append(this.path);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // check that fields of type enum have valid values
    if (isSetType() && !FileResourceType.VALID_VALUES.contains(type)){
      throw new TProtocolException("The field 'type' has been assigned the invalid value " + type);
    }
  }

}

