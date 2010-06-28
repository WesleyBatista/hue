/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package org.apache.hadoop.thriftfs.jobtracker.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.protocol.*;

/**
 * Unique task id
 */
public class ThriftTaskID implements TBase<ThriftTaskID._Fields>, java.io.Serializable, Cloneable, Comparable<ThriftTaskID> {
  private static final TStruct STRUCT_DESC = new TStruct("ThriftTaskID");

  private static final TField JOB_ID_FIELD_DESC = new TField("jobID", TType.STRUCT, (short)1);
  private static final TField TASK_TYPE_FIELD_DESC = new TField("taskType", TType.I32, (short)2);
  private static final TField TASK_ID_FIELD_DESC = new TField("taskID", TType.I32, (short)3);
  private static final TField AS_STRING_FIELD_DESC = new TField("asString", TType.STRING, (short)4);

  /**
   * ID of the job to which the task belongs
   */
  public ThriftJobID jobID;
  /**
   * What kind of task is this?
   * 
   * @see ThriftTaskType
   */
  public ThriftTaskType taskType;
  /**
   * Unique (to job) task id
   */
  public int taskID;
  /**
   * Flattened to a unique string
   */
  public String asString;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    /**
     * ID of the job to which the task belongs
     */
    JOB_ID((short)1, "jobID"),
    /**
     * What kind of task is this?
     * 
     * @see ThriftTaskType
     */
    TASK_TYPE((short)2, "taskType"),
    /**
     * Unique (to job) task id
     */
    TASK_ID((short)3, "taskID"),
    /**
     * Flattened to a unique string
     */
    AS_STRING((short)4, "asString");

    private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byId.put((int)field._thriftId, field);
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      return byId.get(fieldId);
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TASKID_ISSET_ID = 0;
  private BitSet __isset_bit_vector = new BitSet(1);

  public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
    put(_Fields.JOB_ID, new FieldMetaData("jobID", TFieldRequirementType.DEFAULT, 
        new StructMetaData(TType.STRUCT, ThriftJobID.class)));
    put(_Fields.TASK_TYPE, new FieldMetaData("taskType", TFieldRequirementType.DEFAULT, 
        new EnumMetaData(TType.ENUM, ThriftTaskType.class)));
    put(_Fields.TASK_ID, new FieldMetaData("taskID", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    put(_Fields.AS_STRING, new FieldMetaData("asString", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.STRING)));
  }});

  static {
    FieldMetaData.addStructMetaDataMap(ThriftTaskID.class, metaDataMap);
  }

  public ThriftTaskID() {
  }

  public ThriftTaskID(
    ThriftJobID jobID,
    ThriftTaskType taskType,
    int taskID,
    String asString)
  {
    this();
    this.jobID = jobID;
    this.taskType = taskType;
    this.taskID = taskID;
    setTaskIDIsSet(true);
    this.asString = asString;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThriftTaskID(ThriftTaskID other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetJobID()) {
      this.jobID = new ThriftJobID(other.jobID);
    }
    if (other.isSetTaskType()) {
      this.taskType = other.taskType;
    }
    this.taskID = other.taskID;
    if (other.isSetAsString()) {
      this.asString = other.asString;
    }
  }

  public ThriftTaskID deepCopy() {
    return new ThriftTaskID(this);
  }

  @Deprecated
  public ThriftTaskID clone() {
    return new ThriftTaskID(this);
  }

  /**
   * ID of the job to which the task belongs
   */
  public ThriftJobID getJobID() {
    return this.jobID;
  }

  /**
   * ID of the job to which the task belongs
   */
  public ThriftTaskID setJobID(ThriftJobID jobID) {
    this.jobID = jobID;
    return this;
  }

  public void unsetJobID() {
    this.jobID = null;
  }

  /** Returns true if field jobID is set (has been asigned a value) and false otherwise */
  public boolean isSetJobID() {
    return this.jobID != null;
  }

  public void setJobIDIsSet(boolean value) {
    if (!value) {
      this.jobID = null;
    }
  }

  /**
   * What kind of task is this?
   * 
   * @see ThriftTaskType
   */
  public ThriftTaskType getTaskType() {
    return this.taskType;
  }

  /**
   * What kind of task is this?
   * 
   * @see ThriftTaskType
   */
  public ThriftTaskID setTaskType(ThriftTaskType taskType) {
    this.taskType = taskType;
    return this;
  }

  public void unsetTaskType() {
    this.taskType = null;
  }

  /** Returns true if field taskType is set (has been asigned a value) and false otherwise */
  public boolean isSetTaskType() {
    return this.taskType != null;
  }

  public void setTaskTypeIsSet(boolean value) {
    if (!value) {
      this.taskType = null;
    }
  }

  /**
   * Unique (to job) task id
   */
  public int getTaskID() {
    return this.taskID;
  }

  /**
   * Unique (to job) task id
   */
  public ThriftTaskID setTaskID(int taskID) {
    this.taskID = taskID;
    setTaskIDIsSet(true);
    return this;
  }

  public void unsetTaskID() {
    __isset_bit_vector.clear(__TASKID_ISSET_ID);
  }

  /** Returns true if field taskID is set (has been asigned a value) and false otherwise */
  public boolean isSetTaskID() {
    return __isset_bit_vector.get(__TASKID_ISSET_ID);
  }

  public void setTaskIDIsSet(boolean value) {
    __isset_bit_vector.set(__TASKID_ISSET_ID, value);
  }

  /**
   * Flattened to a unique string
   */
  public String getAsString() {
    return this.asString;
  }

  /**
   * Flattened to a unique string
   */
  public ThriftTaskID setAsString(String asString) {
    this.asString = asString;
    return this;
  }

  public void unsetAsString() {
    this.asString = null;
  }

  /** Returns true if field asString is set (has been asigned a value) and false otherwise */
  public boolean isSetAsString() {
    return this.asString != null;
  }

  public void setAsStringIsSet(boolean value) {
    if (!value) {
      this.asString = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case JOB_ID:
      if (value == null) {
        unsetJobID();
      } else {
        setJobID((ThriftJobID)value);
      }
      break;

    case TASK_TYPE:
      if (value == null) {
        unsetTaskType();
      } else {
        setTaskType((ThriftTaskType)value);
      }
      break;

    case TASK_ID:
      if (value == null) {
        unsetTaskID();
      } else {
        setTaskID((Integer)value);
      }
      break;

    case AS_STRING:
      if (value == null) {
        unsetAsString();
      } else {
        setAsString((String)value);
      }
      break;

    }
  }

  public void setFieldValue(int fieldID, Object value) {
    setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case JOB_ID:
      return getJobID();

    case TASK_TYPE:
      return getTaskType();

    case TASK_ID:
      return new Integer(getTaskID());

    case AS_STRING:
      return getAsString();

    }
    throw new IllegalStateException();
  }

  public Object getFieldValue(int fieldId) {
    return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    switch (field) {
    case JOB_ID:
      return isSetJobID();
    case TASK_TYPE:
      return isSetTaskType();
    case TASK_ID:
      return isSetTaskID();
    case AS_STRING:
      return isSetAsString();
    }
    throw new IllegalStateException();
  }

  public boolean isSet(int fieldID) {
    return isSet(_Fields.findByThriftIdOrThrow(fieldID));
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ThriftTaskID)
      return this.equals((ThriftTaskID)that);
    return false;
  }

  public boolean equals(ThriftTaskID that) {
    if (that == null)
      return false;

    boolean this_present_jobID = true && this.isSetJobID();
    boolean that_present_jobID = true && that.isSetJobID();
    if (this_present_jobID || that_present_jobID) {
      if (!(this_present_jobID && that_present_jobID))
        return false;
      if (!this.jobID.equals(that.jobID))
        return false;
    }

    boolean this_present_taskType = true && this.isSetTaskType();
    boolean that_present_taskType = true && that.isSetTaskType();
    if (this_present_taskType || that_present_taskType) {
      if (!(this_present_taskType && that_present_taskType))
        return false;
      if (!this.taskType.equals(that.taskType))
        return false;
    }

    boolean this_present_taskID = true;
    boolean that_present_taskID = true;
    if (this_present_taskID || that_present_taskID) {
      if (!(this_present_taskID && that_present_taskID))
        return false;
      if (this.taskID != that.taskID)
        return false;
    }

    boolean this_present_asString = true && this.isSetAsString();
    boolean that_present_asString = true && that.isSetAsString();
    if (this_present_asString || that_present_asString) {
      if (!(this_present_asString && that_present_asString))
        return false;
      if (!this.asString.equals(that.asString))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(ThriftTaskID other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    ThriftTaskID typedOther = (ThriftTaskID)other;

    lastComparison = Boolean.valueOf(isSetJobID()).compareTo(isSetJobID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(jobID, typedOther.jobID);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetTaskType()).compareTo(isSetTaskType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(taskType, typedOther.taskType);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetTaskID()).compareTo(isSetTaskID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(taskID, typedOther.taskID);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetAsString()).compareTo(isSetAsString());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(asString, typedOther.asString);
    if (lastComparison != 0) {
      return lastComparison;
    }
    return 0;
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
      _Fields fieldId = _Fields.findByThriftId(field.id);
      if (fieldId == null) {
        TProtocolUtil.skip(iprot, field.type);
      } else {
        switch (fieldId) {
          case JOB_ID:
            if (field.type == TType.STRUCT) {
              this.jobID = new ThriftJobID();
              this.jobID.read(iprot);
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
          case TASK_TYPE:
            if (field.type == TType.I32) {
              this.taskType = ThriftTaskType.findByValue(iprot.readI32());
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
          case TASK_ID:
            if (field.type == TType.I32) {
              this.taskID = iprot.readI32();
              setTaskIDIsSet(true);
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
          case AS_STRING:
            if (field.type == TType.STRING) {
              this.asString = iprot.readString();
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
        }
        iprot.readFieldEnd();
      }
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.jobID != null) {
      oprot.writeFieldBegin(JOB_ID_FIELD_DESC);
      this.jobID.write(oprot);
      oprot.writeFieldEnd();
    }
    if (this.taskType != null) {
      oprot.writeFieldBegin(TASK_TYPE_FIELD_DESC);
      oprot.writeI32(this.taskType.getValue());
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(TASK_ID_FIELD_DESC);
    oprot.writeI32(this.taskID);
    oprot.writeFieldEnd();
    if (this.asString != null) {
      oprot.writeFieldBegin(AS_STRING_FIELD_DESC);
      oprot.writeString(this.asString);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ThriftTaskID(");
    boolean first = true;

    sb.append("jobID:");
    if (this.jobID == null) {
      sb.append("null");
    } else {
      sb.append(this.jobID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("taskType:");
    if (this.taskType == null) {
      sb.append("null");
    } else {
      String taskType_name = taskType.name();
      if (taskType_name != null) {
        sb.append(taskType_name);
        sb.append(" (");
      }
      sb.append(this.taskType);
      if (taskType_name != null) {
        sb.append(")");
      }
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("taskID:");
    sb.append(this.taskID);
    first = false;
    if (!first) sb.append(", ");
    sb.append("asString:");
    if (this.asString == null) {
      sb.append("null");
    } else {
      sb.append(this.asString);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}

