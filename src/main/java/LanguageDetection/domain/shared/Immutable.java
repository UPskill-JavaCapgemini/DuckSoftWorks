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

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * An immutable object; that is, one that cannot change state after it is created. A java String is
 * an excellent example
 * of an immutable object, as it is the Java 8 Time classes.
 *
 * <p>
 * Immutable objects are quite helpful as they represent a thing in the real world and since they
 * cannot change we are
 * always certain it represents the one thing they were created for. Immutable objects usually
 * participate in operations
 * with other objects creating new objects to represent the new state.
 *
 * <p>
 * For instance, a Color does not change when we mix two colors, what happens is that a third color
 * is created.
 *
 * <pre>
 * <code>
 * Color green= new Color(0, 1, 0);
 * Color red = new Color(1, 0, 0);
 *
 * Color x = green.mixedWith(red);
 * </code>
 * </pre>
 *
 * In the previous example, {@code x} will be equivalent to the color yellow but both {@code green}
 * and {@code red} will
 * still be the same as they were and will always be the same no matter how many times we use them.
 * <p>
 * A simple definition for this Color class would look like:
 *
 * <pre>
 * <code>
 * &#64;Immutable
 * final class Color {
 *     final int r;
 *     final int g;
 *     final int b;
 *
 *     public Color(int r, int, g, int b) {
 *         this.r = r;
 *         this.g = g;
 *         this.b = b;
 *     }
 *
 *     public Color mixedWith(Color other){
 *         return new Color(this.r + other.r, this.g + other.g, this.b + other.b);
 *     }
 * }
 * </code>
 * </pre>
 *
 * Look at the final declaration so that this class cannot be extended and the lack of any mutator
 * method.
 * Make sure you don't provide mutator methods, always construct the object in a
 * valid state and mark all your private fields as final (If you are using
 * ORM tools you might need a default constructor in which case you won't be
 * able to make the fields final but that's a technicality).
 * </p>
 *
 * <p>
 * You should thrive to
 * <a href="https://www.yegor256.com/2014/06/09/objects-should-be-immutable.html">make all of your
 * objects immutable</a>
 * by design.
 *
 * <p>
 * Immutable classes are thread-safe by definition.
 *
 * <p>
 * See Project Lombok's <code>&#64;Value</code> to generate the boilerplate code and ensure
 * immutability.
 *
 * @author Paulo Gandra de Sousa 28/04/2020
 *
 */
@Documented
@Retention(SOURCE)
@Target(TYPE)
public @interface Immutable {

}
