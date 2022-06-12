/*
 * Copyright (c) 2013-2020 the original author or authors.
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

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * An object that has an identity and as such is identifiable by that identity.
 *
 * @param <T>
 *            the type of the entity's identity. E.g., if an object {@code Person} is
 *            identified by an {@code IdCardNumber}, the class {@code Person} should implement
 *            interface {@code Identifiable<IdCardNumber>}.
 *
 * @author Paulo Gandra Sousa
 */
public interface Identifiable<T> {

    /**
     * Returns the primary <b>business</b> id of the entity.
     *
     * <p>
     * This method is marked as a getter for Jackson serialization
     *
     * @return the primary <b>business</b> id of the entity
     */
    @JsonGetter
    T identity();

    /**
     * Checks if the object has a certain business identity.
     *
     * @param id
     *            the identity to compare
     * @return {@code true} if the object has that identity
     */
    default boolean hasIdentity(final T id) {
        return identity().equals(id);
    }
}