/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class TwitterAvroData extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -634073007043555287L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TwitterAvroData\",\"namespace\":\"avro\",\"fields\":[{\"name\":\"tweetCreatedDate\",\"type\":\"string\"},{\"name\":\"tweetID\",\"type\":[\"long\",\"null\"]},{\"name\":\"tweetText\",\"type\":[\"string\",\"null\"]},{\"name\":\"tweetUserID\",\"type\":[\"long\",\"null\"]},{\"name\":\"tweetFullName\",\"type\":[\"string\",\"null\"]},{\"name\":\"tweetRelatedTopic\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TwitterAvroData> ENCODER =
      new BinaryMessageEncoder<TwitterAvroData>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TwitterAvroData> DECODER =
      new BinaryMessageDecoder<TwitterAvroData>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<TwitterAvroData> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<TwitterAvroData> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<TwitterAvroData> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<TwitterAvroData>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this TwitterAvroData to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a TwitterAvroData from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a TwitterAvroData instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static TwitterAvroData fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.CharSequence tweetCreatedDate;
   private java.lang.Long tweetID;
   private java.lang.CharSequence tweetText;
   private java.lang.Long tweetUserID;
   private java.lang.CharSequence tweetFullName;
   private java.lang.CharSequence tweetRelatedTopic;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TwitterAvroData() {}

  /**
   * All-args constructor.
   * @param tweetCreatedDate The new value for tweetCreatedDate
   * @param tweetID The new value for tweetID
   * @param tweetText The new value for tweetText
   * @param tweetUserID The new value for tweetUserID
   * @param tweetFullName The new value for tweetFullName
   * @param tweetRelatedTopic The new value for tweetRelatedTopic
   */
  public TwitterAvroData(java.lang.CharSequence tweetCreatedDate, java.lang.Long tweetID, java.lang.CharSequence tweetText, java.lang.Long tweetUserID, java.lang.CharSequence tweetFullName, java.lang.CharSequence tweetRelatedTopic) {
    this.tweetCreatedDate = tweetCreatedDate;
    this.tweetID = tweetID;
    this.tweetText = tweetText;
    this.tweetUserID = tweetUserID;
    this.tweetFullName = tweetFullName;
    this.tweetRelatedTopic = tweetRelatedTopic;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return tweetCreatedDate;
    case 1: return tweetID;
    case 2: return tweetText;
    case 3: return tweetUserID;
    case 4: return tweetFullName;
    case 5: return tweetRelatedTopic;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: tweetCreatedDate = (java.lang.CharSequence)value$; break;
    case 1: tweetID = (java.lang.Long)value$; break;
    case 2: tweetText = (java.lang.CharSequence)value$; break;
    case 3: tweetUserID = (java.lang.Long)value$; break;
    case 4: tweetFullName = (java.lang.CharSequence)value$; break;
    case 5: tweetRelatedTopic = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'tweetCreatedDate' field.
   * @return The value of the 'tweetCreatedDate' field.
   */
  public java.lang.CharSequence getTweetCreatedDate() {
    return tweetCreatedDate;
  }


  /**
   * Sets the value of the 'tweetCreatedDate' field.
   * @param value the value to set.
   */
  public void setTweetCreatedDate(java.lang.CharSequence value) {
    this.tweetCreatedDate = value;
  }

  /**
   * Gets the value of the 'tweetID' field.
   * @return The value of the 'tweetID' field.
   */
  public java.lang.Long getTweetID() {
    return tweetID;
  }


  /**
   * Sets the value of the 'tweetID' field.
   * @param value the value to set.
   */
  public void setTweetID(java.lang.Long value) {
    this.tweetID = value;
  }

  /**
   * Gets the value of the 'tweetText' field.
   * @return The value of the 'tweetText' field.
   */
  public java.lang.CharSequence getTweetText() {
    return tweetText;
  }


  /**
   * Sets the value of the 'tweetText' field.
   * @param value the value to set.
   */
  public void setTweetText(java.lang.CharSequence value) {
    this.tweetText = value;
  }

  /**
   * Gets the value of the 'tweetUserID' field.
   * @return The value of the 'tweetUserID' field.
   */
  public java.lang.Long getTweetUserID() {
    return tweetUserID;
  }


  /**
   * Sets the value of the 'tweetUserID' field.
   * @param value the value to set.
   */
  public void setTweetUserID(java.lang.Long value) {
    this.tweetUserID = value;
  }

  /**
   * Gets the value of the 'tweetFullName' field.
   * @return The value of the 'tweetFullName' field.
   */
  public java.lang.CharSequence getTweetFullName() {
    return tweetFullName;
  }


  /**
   * Sets the value of the 'tweetFullName' field.
   * @param value the value to set.
   */
  public void setTweetFullName(java.lang.CharSequence value) {
    this.tweetFullName = value;
  }

  /**
   * Gets the value of the 'tweetRelatedTopic' field.
   * @return The value of the 'tweetRelatedTopic' field.
   */
  public java.lang.CharSequence getTweetRelatedTopic() {
    return tweetRelatedTopic;
  }


  /**
   * Sets the value of the 'tweetRelatedTopic' field.
   * @param value the value to set.
   */
  public void setTweetRelatedTopic(java.lang.CharSequence value) {
    this.tweetRelatedTopic = value;
  }

  /**
   * Creates a new TwitterAvroData RecordBuilder.
   * @return A new TwitterAvroData RecordBuilder
   */
  public static avro.TwitterAvroData.Builder newBuilder() {
    return new avro.TwitterAvroData.Builder();
  }

  /**
   * Creates a new TwitterAvroData RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TwitterAvroData RecordBuilder
   */
  public static avro.TwitterAvroData.Builder newBuilder(avro.TwitterAvroData.Builder other) {
    if (other == null) {
      return new avro.TwitterAvroData.Builder();
    } else {
      return new avro.TwitterAvroData.Builder(other);
    }
  }

  /**
   * Creates a new TwitterAvroData RecordBuilder by copying an existing TwitterAvroData instance.
   * @param other The existing instance to copy.
   * @return A new TwitterAvroData RecordBuilder
   */
  public static avro.TwitterAvroData.Builder newBuilder(avro.TwitterAvroData other) {
    if (other == null) {
      return new avro.TwitterAvroData.Builder();
    } else {
      return new avro.TwitterAvroData.Builder(other);
    }
  }

  /**
   * RecordBuilder for TwitterAvroData instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TwitterAvroData>
    implements org.apache.avro.data.RecordBuilder<TwitterAvroData> {

    private java.lang.CharSequence tweetCreatedDate;
    private java.lang.Long tweetID;
    private java.lang.CharSequence tweetText;
    private java.lang.Long tweetUserID;
    private java.lang.CharSequence tweetFullName;
    private java.lang.CharSequence tweetRelatedTopic;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(avro.TwitterAvroData.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.tweetCreatedDate)) {
        this.tweetCreatedDate = data().deepCopy(fields()[0].schema(), other.tweetCreatedDate);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.tweetID)) {
        this.tweetID = data().deepCopy(fields()[1].schema(), other.tweetID);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.tweetText)) {
        this.tweetText = data().deepCopy(fields()[2].schema(), other.tweetText);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.tweetUserID)) {
        this.tweetUserID = data().deepCopy(fields()[3].schema(), other.tweetUserID);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.tweetFullName)) {
        this.tweetFullName = data().deepCopy(fields()[4].schema(), other.tweetFullName);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.tweetRelatedTopic)) {
        this.tweetRelatedTopic = data().deepCopy(fields()[5].schema(), other.tweetRelatedTopic);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
    }

    /**
     * Creates a Builder by copying an existing TwitterAvroData instance
     * @param other The existing instance to copy.
     */
    private Builder(avro.TwitterAvroData other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.tweetCreatedDate)) {
        this.tweetCreatedDate = data().deepCopy(fields()[0].schema(), other.tweetCreatedDate);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.tweetID)) {
        this.tweetID = data().deepCopy(fields()[1].schema(), other.tweetID);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.tweetText)) {
        this.tweetText = data().deepCopy(fields()[2].schema(), other.tweetText);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.tweetUserID)) {
        this.tweetUserID = data().deepCopy(fields()[3].schema(), other.tweetUserID);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.tweetFullName)) {
        this.tweetFullName = data().deepCopy(fields()[4].schema(), other.tweetFullName);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.tweetRelatedTopic)) {
        this.tweetRelatedTopic = data().deepCopy(fields()[5].schema(), other.tweetRelatedTopic);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'tweetCreatedDate' field.
      * @return The value.
      */
    public java.lang.CharSequence getTweetCreatedDate() {
      return tweetCreatedDate;
    }


    /**
      * Sets the value of the 'tweetCreatedDate' field.
      * @param value The value of 'tweetCreatedDate'.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder setTweetCreatedDate(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.tweetCreatedDate = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'tweetCreatedDate' field has been set.
      * @return True if the 'tweetCreatedDate' field has been set, false otherwise.
      */
    public boolean hasTweetCreatedDate() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'tweetCreatedDate' field.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder clearTweetCreatedDate() {
      tweetCreatedDate = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'tweetID' field.
      * @return The value.
      */
    public java.lang.Long getTweetID() {
      return tweetID;
    }


    /**
      * Sets the value of the 'tweetID' field.
      * @param value The value of 'tweetID'.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder setTweetID(java.lang.Long value) {
      validate(fields()[1], value);
      this.tweetID = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'tweetID' field has been set.
      * @return True if the 'tweetID' field has been set, false otherwise.
      */
    public boolean hasTweetID() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'tweetID' field.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder clearTweetID() {
      tweetID = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'tweetText' field.
      * @return The value.
      */
    public java.lang.CharSequence getTweetText() {
      return tweetText;
    }


    /**
      * Sets the value of the 'tweetText' field.
      * @param value The value of 'tweetText'.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder setTweetText(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.tweetText = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'tweetText' field has been set.
      * @return True if the 'tweetText' field has been set, false otherwise.
      */
    public boolean hasTweetText() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'tweetText' field.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder clearTweetText() {
      tweetText = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'tweetUserID' field.
      * @return The value.
      */
    public java.lang.Long getTweetUserID() {
      return tweetUserID;
    }


    /**
      * Sets the value of the 'tweetUserID' field.
      * @param value The value of 'tweetUserID'.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder setTweetUserID(java.lang.Long value) {
      validate(fields()[3], value);
      this.tweetUserID = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'tweetUserID' field has been set.
      * @return True if the 'tweetUserID' field has been set, false otherwise.
      */
    public boolean hasTweetUserID() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'tweetUserID' field.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder clearTweetUserID() {
      tweetUserID = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'tweetFullName' field.
      * @return The value.
      */
    public java.lang.CharSequence getTweetFullName() {
      return tweetFullName;
    }


    /**
      * Sets the value of the 'tweetFullName' field.
      * @param value The value of 'tweetFullName'.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder setTweetFullName(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.tweetFullName = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'tweetFullName' field has been set.
      * @return True if the 'tweetFullName' field has been set, false otherwise.
      */
    public boolean hasTweetFullName() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'tweetFullName' field.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder clearTweetFullName() {
      tweetFullName = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'tweetRelatedTopic' field.
      * @return The value.
      */
    public java.lang.CharSequence getTweetRelatedTopic() {
      return tweetRelatedTopic;
    }


    /**
      * Sets the value of the 'tweetRelatedTopic' field.
      * @param value The value of 'tweetRelatedTopic'.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder setTweetRelatedTopic(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.tweetRelatedTopic = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'tweetRelatedTopic' field has been set.
      * @return True if the 'tweetRelatedTopic' field has been set, false otherwise.
      */
    public boolean hasTweetRelatedTopic() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'tweetRelatedTopic' field.
      * @return This builder.
      */
    public avro.TwitterAvroData.Builder clearTweetRelatedTopic() {
      tweetRelatedTopic = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TwitterAvroData build() {
      try {
        TwitterAvroData record = new TwitterAvroData();
        record.tweetCreatedDate = fieldSetFlags()[0] ? this.tweetCreatedDate : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.tweetID = fieldSetFlags()[1] ? this.tweetID : (java.lang.Long) defaultValue(fields()[1]);
        record.tweetText = fieldSetFlags()[2] ? this.tweetText : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.tweetUserID = fieldSetFlags()[3] ? this.tweetUserID : (java.lang.Long) defaultValue(fields()[3]);
        record.tweetFullName = fieldSetFlags()[4] ? this.tweetFullName : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.tweetRelatedTopic = fieldSetFlags()[5] ? this.tweetRelatedTopic : (java.lang.CharSequence) defaultValue(fields()[5]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TwitterAvroData>
    WRITER$ = (org.apache.avro.io.DatumWriter<TwitterAvroData>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TwitterAvroData>
    READER$ = (org.apache.avro.io.DatumReader<TwitterAvroData>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.tweetCreatedDate);

    if (this.tweetID == null) {
      out.writeIndex(1);
      out.writeNull();
    } else {
      out.writeIndex(0);
      out.writeLong(this.tweetID);
    }

    if (this.tweetText == null) {
      out.writeIndex(1);
      out.writeNull();
    } else {
      out.writeIndex(0);
      out.writeString(this.tweetText);
    }

    if (this.tweetUserID == null) {
      out.writeIndex(1);
      out.writeNull();
    } else {
      out.writeIndex(0);
      out.writeLong(this.tweetUserID);
    }

    if (this.tweetFullName == null) {
      out.writeIndex(1);
      out.writeNull();
    } else {
      out.writeIndex(0);
      out.writeString(this.tweetFullName);
    }

    if (this.tweetRelatedTopic == null) {
      out.writeIndex(1);
      out.writeNull();
    } else {
      out.writeIndex(0);
      out.writeString(this.tweetRelatedTopic);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.tweetCreatedDate = in.readString(this.tweetCreatedDate instanceof Utf8 ? (Utf8)this.tweetCreatedDate : null);

      if (in.readIndex() != 0) {
        in.readNull();
        this.tweetID = null;
      } else {
        this.tweetID = in.readLong();
      }

      if (in.readIndex() != 0) {
        in.readNull();
        this.tweetText = null;
      } else {
        this.tweetText = in.readString(this.tweetText instanceof Utf8 ? (Utf8)this.tweetText : null);
      }

      if (in.readIndex() != 0) {
        in.readNull();
        this.tweetUserID = null;
      } else {
        this.tweetUserID = in.readLong();
      }

      if (in.readIndex() != 0) {
        in.readNull();
        this.tweetFullName = null;
      } else {
        this.tweetFullName = in.readString(this.tweetFullName instanceof Utf8 ? (Utf8)this.tweetFullName : null);
      }

      if (in.readIndex() != 0) {
        in.readNull();
        this.tweetRelatedTopic = null;
      } else {
        this.tweetRelatedTopic = in.readString(this.tweetRelatedTopic instanceof Utf8 ? (Utf8)this.tweetRelatedTopic : null);
      }

    } else {
      for (int i = 0; i < 6; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.tweetCreatedDate = in.readString(this.tweetCreatedDate instanceof Utf8 ? (Utf8)this.tweetCreatedDate : null);
          break;

        case 1:
          if (in.readIndex() != 0) {
            in.readNull();
            this.tweetID = null;
          } else {
            this.tweetID = in.readLong();
          }
          break;

        case 2:
          if (in.readIndex() != 0) {
            in.readNull();
            this.tweetText = null;
          } else {
            this.tweetText = in.readString(this.tweetText instanceof Utf8 ? (Utf8)this.tweetText : null);
          }
          break;

        case 3:
          if (in.readIndex() != 0) {
            in.readNull();
            this.tweetUserID = null;
          } else {
            this.tweetUserID = in.readLong();
          }
          break;

        case 4:
          if (in.readIndex() != 0) {
            in.readNull();
            this.tweetFullName = null;
          } else {
            this.tweetFullName = in.readString(this.tweetFullName instanceof Utf8 ? (Utf8)this.tweetFullName : null);
          }
          break;

        case 5:
          if (in.readIndex() != 0) {
            in.readNull();
            this.tweetRelatedTopic = null;
          } else {
            this.tweetRelatedTopic = in.readString(this.tweetRelatedTopic instanceof Utf8 ? (Utf8)this.tweetRelatedTopic : null);
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










