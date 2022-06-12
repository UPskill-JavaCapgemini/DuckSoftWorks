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
 * An entity is a Domain-Driven Design pattern for concepts in the domain which
 * have a thread of continuity which needs to be tracked.
 *
 * <p>
 * These concepts matter by their identity and we need to track them
 * continuously. Instance equality must be done thru the identity of the objects
 * and we cannot loose track or allow duplication of an entity. By definition a
 * domain entity always has a business identity, so it should be impossible to
 * create new instances without identity.
 * </p>
 * <p>
 * Typical examples are:
 * <ul>
 * <li>Product
 * <li>Person
 * <li>Account
 * </ul>
 * </p>
 *
 *
 * Quoting
 * <a href="https://domainlanguage.com/wp-content/uploads/2016/05/DDD_Reference_2015-03.pdf">Eric
 * Evans</a>:
 * <blockquote>
 * When an object is distinguished by its identity, rather than its attributes, make this primary
 * to its definition in the model. Keep the class definition simple and focused on life cycle
 * continuity and identity.
 * Define a means of distinguishing each object regardless of its form or history. Be alert to
 * requirements that call for matching objects by attributes. Define an operation that is
 * guaranteed to produce a unique result for each object, possibly by attaching a symbol that
 * is guaranteed unique. This means of identification may come from the outside, or it may be
 * an arbitrary identifier created by and for the system, but it must correspond to the identity
 * distinctions in the model.
 * The model must define what it means to be the same thing.
 * </blockquote>
 *
 *
 * <p>
 * Since we will provably persist entities using JPA we are also forcing the
 * implementation of {@code Serializable}.
 * </p>
 *
 * @param <I>
 *            the type of the primary <b>business</b> id of the entity
 *
 * @author Paulo Gandra Sousa
 */
public interface Entity<I extends Comparable<I> /* & ValueObject */>
        extends Identifiable<I>, Comparable<I>, Serializable {

    /**
     * Entities are compared by identity only. No need to compare all fields of
     * the object. You can use the
     * {@link DomainEntities#areEqual(DomainEntity, Object)} method as default
     * implementation.
     * </p>
     *
     * <p>
     * Be careful that if your entities are using the ORM generated primary key
     * as their business identity, you MUST properly implement equals() and
     * hashcode(). See <a href=
     * "https://thoughts-on-java.org/ultimate-guide-to-implementing-equals-and-hashcode-with-hibernate/">Toughts
     * on java</a>
     * </p>
     *
     * <p>
     * see {@link #sameAs(Object)}.
     * </p>
     *
     * @param other
     *            the other object to compare
     *
     * @return true if this domain entity refers to the same real-world entity
     *         as the {@code other}
     */
    @Override
    boolean equals(Object other);

    /**
     * Hash code of this object. According to java rules. i.e., the same fields
     * used in equals() should be used in hashCode(). You can use
     * {@link DomainEntities#hashCode(DomainEntity)} as default implementation
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

    /**
     * Entities are compared by identity only, so equals() must only compare
     * identities. In some situations however we might want to compare the
     * contents of the object by value.
     *
     * @param other
     *            the other object to compare
     * @return {@code true} if this domain entity have the "same content" as the
     *         {@code other}
     */
    boolean sameAs(Object other);

    /**
     * Domain entities are naturally ordered by their identity.
     */
    @Override
    default int compareTo(final I other) {
        return identity().compareTo(other);
    }
}
