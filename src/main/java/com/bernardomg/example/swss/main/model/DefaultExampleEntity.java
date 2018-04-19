/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2017 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.swss.main.model;

import java.util.Arrays;

//import static com.google.common.base.internal.Preconditions.checkNotNull;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Persistent entity for the example application.
 * <p>
 * This makes use of JPA annotations for the persistence configuration.
 * <p>
 * For the JAXB annotated model check the generated classes folder.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "ExampleEntity")
@Table(name = "example_entities")
public final class DefaultExampleEntity implements ExampleEntity {

	/**
	 * Serialization ID.
	 */
	@Transient
	private static final long serialVersionUID = 1328776989450853491L;

	/**
	 * Entity's ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id = -1;

	/**
	 * Name of the entity.
	 * <p>
	 * This is to have additional data apart from the id, to be used on the tests.
	 */
	@Column(name = "name", nullable = false)
	private String name = "";

	/**
	 * Constructs an example entity.
	 */
	public DefaultExampleEntity() {
		super();
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final DefaultExampleEntity other = (DefaultExampleEntity) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * Returns the identifier assigned to this entity.
	 * <p>
	 * If no identifier has been assigned yet, then the value will be lower than zero.
	 *
	 * @return the entity's identifier
	 */
	@Override
	public final Integer getId() {
		return id;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public final void setId(final Integer value) {
		id = checkNotNull(value, "Received a null pointer as identifier");
	}

	@Override
	public final void setName(final String value) {
		name = checkNotNull(value, "Received a null pointer as name");
	}

	@Override
	public final String toString() {
		return toStringHelper(this).add("entityId", id).toString();
	}

	public static <T> T checkNotNull(T reference, Object errorMessage) {
		if (reference == null) {
			throw new NullPointerException(String.valueOf(errorMessage));
		}
		return reference;
	}

	public static <T> T checkNotNull(T reference) {
		if (reference == null) {
			throw new NullPointerException();
		}
		return reference;
	}

	  public static ToStringHelper toStringHelper(Object self) {
		    return new ToStringHelper(self.getClass().getSimpleName());
	  }
	  
	  public static final class ToStringHelper {
		    private final String className;
		    private ValueHolder holderHead = new ValueHolder();
		    private ValueHolder holderTail = holderHead;
		    private boolean omitNullValues = false;

		    /**
		     * Use {@link MoreObjects#toStringHelper(Object)} to create an instance.
		     */
		    private ToStringHelper(String className) {
		      this.className = DefaultExampleEntity.checkNotNull(className);
		    }

		    /**
		     * Adds a name/value pair to the formatted output in {@code name=value}
		     * format. If {@code value} is {@code null}, the string {@code "null"}
		     * is used, unless {@link #omitNullValues()} is called, in which case this
		     * name/value pair will not be added.
		     */
		    public ToStringHelper add(String name, Object value) {
		      return addHolder(name, value);
		    }

		    /**
		     * Adds a name/value pair to the formatted output in {@code name=value}
		     * format.
		     *
		     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
		     */
		    public ToStringHelper add(String name, boolean value) {
		      return addHolder(name, String.valueOf(value));
		    }

		    /**
		     * Adds a name/value pair to the formatted output in {@code name=value}
		     * format.
		     *
		     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
		     */
		    public ToStringHelper add(String name, char value) {
		      return addHolder(name, String.valueOf(value));
		    }

		    /**
		     * Adds a name/value pair to the formatted output in {@code name=value}
		     * format.
		     *
		     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
		     */
		    public ToStringHelper add(String name, double value) {
		      return addHolder(name, String.valueOf(value));
		    }

		    /**
		     * Adds a name/value pair to the formatted output in {@code name=value}
		     * format.
		     *
		     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
		     */
		    public ToStringHelper add(String name, float value) {
		      return addHolder(name, String.valueOf(value));
		    }

		    /**
		     * Adds a name/value pair to the formatted output in {@code name=value}
		     * format.
		     *
		     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
		     */
		    public ToStringHelper add(String name, int value) {
		      return addHolder(name, String.valueOf(value));
		    }

		    /**
		     * Adds a name/value pair to the formatted output in {@code name=value}
		     * format.
		     *
		     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
		     */
		    public ToStringHelper add(String name, long value) {
		      return addHolder(name, String.valueOf(value));
		    }

		    /**
		     * Returns a string in the format specified by
		     * {@link MoreObjects#toStringHelper(Object)}.
		     *
		     * <p>After calling this method, you can keep adding more properties to later
		     * call toString() again and get a more complete representation of the
		     * same object; but properties cannot be removed, so this only allows
		     * limited reuse of the helper instance. The helper allows duplication of
		     * properties (multiple name/value pairs with the same name can be added).
		     */
		    
		    @Override
		    public String toString() {
		      // create a copy to keep it consistent in case value changes
		      boolean omitNullValuesSnapshot = omitNullValues;
		      String nextSeparator = "";
		      StringBuilder builder = new StringBuilder(32).append(className).append('{');
		      for (ValueHolder valueHolder = holderHead.next;
		          valueHolder != null;
		          valueHolder = valueHolder.next) {
		        Object value = valueHolder.value;
		        if (!omitNullValuesSnapshot || value != null) {
		          builder.append(nextSeparator);
		          nextSeparator = ", ";

		          if (valueHolder.name != null) {
		            builder.append(valueHolder.name).append('=');
		          }
		          if (value != null && value.getClass().isArray()) {
		            Object[] objectArray = {value};
		            String arrayString = Arrays.deepToString(objectArray);
		            builder.append(arrayString.substring(1, arrayString.length() - 1));
		          } else {
		            builder.append(value);
		          }
		        }
		      }
		      return builder.append('}').toString();
		    }

		    private ValueHolder addHolder() {
		      ValueHolder valueHolder = new ValueHolder();
		      holderTail = holderTail.next = valueHolder;
		      return valueHolder;
		    }

		    private ToStringHelper addHolder(String name, Object value) {
		      ValueHolder valueHolder = addHolder();
		      valueHolder.value = value;
		      valueHolder.name = checkNotNull(name);
		      return this;
		    }

		    private static final class ValueHolder {
		      String name;
		      Object value;
		      ValueHolder next;
		    }
		  }
}
