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
 *//*

package LanguageDetection.domain.util;

import java.util.Arrays;
import java.util.Objects;

*/
/**
 * Helper class for {@code hashCode()} method implementation.
 * <p>
 * Example usage:
 *
 * <pre>
 * <code>
 * public class Example {
 *      private int fi;
 *      private float ff;
 *      private AnotherClass fo;
 *
 *      ...
 *
 *      &#64;Override
 *      public int hashCode(){
 *              return new HashCoder().with(fi).with(ff).with(fo).code();
 *      }
 * }
 * </code>
 * </pre>
 *
 * If you want to include the super class in the hash code you need to
 * explicitly add it as a part of the hash code. For instance:
 *
 * <pre>
 * <code>
 *      &#64;Override
 *      public int hashCode(){
 *              return new HashCoder().with(super.hashcode()).with(oneField).with(anotherField).code();
 *      }
 * </code>
 * </pre>
 * <p>
 * Check <a href=
 * "https://stackoverflow.com/questions/113511/best-implementation-for-hashcode-method">this
 * stackoverflow</a> thread for insights on how to properly calculate an hash code.
 * <p>
 * See also <a href=
 * "https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/builder/HashCodeBuilder.html">org.apache.commons.lang3.builder.HashCodeBuilder</a>
 *
 * @author Paulo Gandra Sousa
 *//*

public final class HashCoder {

    private int result;

    */
/**
     * Constructor.
     *//*

    public HashCoder() {
        result = 31;
    }

    public HashCoder(final int seed) {
        result = seed;
    }

    //
    // utility methods for individual parts of the hash code
    //

    public static int part(final boolean f) {
        return f ? 0 : 1;
    }

    public static int part(final byte f) {
        return f;
    }

    public static int part(final char f) {
        return f;
    }

    public static int part(final short f) {
        return f;
    }

    public static int part(final int f) {
        return f;
    }

    public static int part(final long f) {
        return (int) (f ^ (f >>> 32));
    }

    public static int part(final float f) {
        return Float.floatToIntBits(f);
    }

    public static int part(final double f) {
        return part(Double.doubleToLongBits(f));
    }

    public static int part(final Object f) {
        return Objects.hashCode(f);
    }

    */
/**
     * Computes the hash based on the current hash and the hash of a part
     * (field)
     *
     * @param currentHash
     * @param partHash
     *
     * @return the hash code
     *//*

    public static int compute(final int currentHash, final int partHash) {
        return 37 * currentHash + partHash;
    }

    */
/**
     * utility method to generate the hash of an object
     *
     * @param f
     *
     * @return the hash code
     *//*

    public static int hash(final Object f) {
        return Objects.hash(f);
    }

    //
    // builder parts
    //

    public HashCoder with(final boolean f) {
        result = compute(result, part(f));
        return this;
    }

    public HashCoder with(final boolean[] f) {
        result = compute(result, Arrays.hashCode(f));
        return this;
    }

    public HashCoder with(final byte f) {
        result = compute(result, part(f));
        return this;
    }

    public HashCoder with(final byte[] f) {
        result = compute(result, Arrays.hashCode(f));
        return this;
    }

    public HashCoder with(final char f) {
        result = compute(result, part(f));
        return this;
    }

    public HashCoder with(final char[] f) {
        result = compute(result, Arrays.hashCode(f));
        return this;
    }

    public HashCoder with(final short f) {
        result = compute(result, part(f));
        return this;
    }

    public HashCoder with(final short[] f) {
        result = compute(result, Arrays.hashCode(f));
        return this;
    }

    public HashCoder with(final int f) {
        result = compute(result, part(f));
        return this;
    }

    public HashCoder with(final int[] f) {
        result = compute(result, Arrays.hashCode(f));
        return this;
    }

    public HashCoder with(final long f) {
        result = compute(result, part(f));
        return this;
    }

    public HashCoder with(final long[] f) {
        result = compute(result, Arrays.hashCode(f));
        return this;
    }

    public HashCoder with(final float f) {
        result = compute(result, part(f));
        return this;
    }

    public HashCoder with(final float[] f) {
        result = compute(result, Arrays.hashCode(f));
        return this;
    }

    public HashCoder with(final double f) {
        result = compute(result, part(f));
        return this;
    }

    public HashCoder with(final double[] f) {
        result = compute(result, Arrays.hashCode(f));
        return this;
    }

    public HashCoder with(final Object f) {
        result = compute(result, part(f));
        return this;
    }

    public HashCoder with(final Object[] f) {
        result = compute(result, Arrays.hashCode(f));
        return this;
    }

    */
/**
     * Returns the actual hash code based on the different parts added to this
     * builder
     *
     * @return the hash code
     *//*

    public int code() {
        return result;
    }
}
*/
