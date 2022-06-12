/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package LanguageDetection.domain.shared;

import java.io.Serializable;

/**
 * A value object is a Domain-Driven Design pattern for domain concepts which do
 * not have a thread of continuity neither need to be tracked by their identity
 * but for the value of its attributes. These are {@link Immutable} objects which
 * can be freely shared and discarded and replaced by another instance. Equality
 * is done thru comparison of the attributes values.
 * <p>
 * Typical examples are:
 * <ul>
 * <li>Address (in most scenarios)
 * <li>Color
 * <li>CustomerNumber
 * <li>Money
 * </ul>
 * </p>
 *
 * Quoting
 * <a href="https://domainlanguage.com/wp-content/uploads/2016/05/DDD_Reference_2015-03.pdf">Eric
 * Evans</a>:
 * <blockquote>
 * When you care only about the attributes and logic of an element of the model, classify it as
 * a value object. Make it express the meaning of the attributes it conveys and give it related
 * functionality. Treat the value object as immutable. Make all operations Side-effect-free
 * Functions that don’t depend on any mutable state. Don’t give a value object any identity
 * and avoid the design complexities necessary to maintain entities.
 * </blockquote>
 *
 * <p>
 * Check the <a href=
 * "https://docs.oracle.com/javase/tutorial/datetime/overview/naming.html">Java
 * Time API naming conventions</a> for an excellent example on how to name
 * methods of a Value Object.
 * </p>
 *
 * <p>
 * Since we will provably persist entities using JPA we are also forcing the
 * implementation of {@code Serializable}.
 * </p>
 *
 * @author Paulo Gandra Sousa
 */
@Immutable
public interface ValueObject extends Serializable {

    /**
     * Returns a representation of this value object as a String.
     *
     * @return a representation of this value object as a String
     */
    @Override
    String toString();

    /**
     * Value objects are compared by the values of its properties.
     *
     * <p>
     * See, for instance, <a href=
     * "https://www.sitepoint.com/implement-javas-equals-method-correctly/">https://www.sitepoint.com/implement-javas-equals-method-correctly/</a>
     * </p>
     *
     * @param other
     *            the other object to compare
     *
     * @return {@code true } if this value object has the same content as the
     *         {@code other}
     */
    @Override
    boolean equals(Object other);

    /**
     * Hash code of this object according to java rules. i.e., the same fields
     * used in equals() should be used in hashCode().
     *
     * <p>
     * see <a href=
     * "http://stackoverflow.com/questions/113511/best-implementation-for-hashcode-method">
     * stack overflow</a> for a nice discussion about hashCode() and equals()
     * </p>
     *
     * @return the hash code
     */
    @Override
    int hashCode();
}
