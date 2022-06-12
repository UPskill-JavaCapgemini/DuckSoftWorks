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

/**
 * An aggregate root is a Domain-Driven Design pattern for defining scopes of
 * change and "whole-objects". Some entities cluster other objects (entities and
 * value objects) which don't make sense to exist outside of that entity (e.g.,
 * OrderLine and Order).
 * <p>
 * The aggregate root is the entity serving as a root for that cluster of
 * objects. these are the only objects that client code can interact directly
 * and that is managed by Repositories. "inside" objects cannot be directly
 * manipulated by client code, instead they need to be manipulated thru the
 * aggregate root.
 * </p>
 * <p>
 * Typical examples are:
 * <ol>
 * <li>Order {OrderLine, Billing Address, Shipping Address}
 * <li>Customer {Home Address}
 * <li>Product
 * </ol>
 * </p>
 *
 * Quoting
 * <a href="https://domainlanguage.com/wp-content/uploads/2016/05/DDD_Reference_2015-03.pdf">Eric
 * Evans</a>:
 * <blockquote>
 * Cluster the entities and value objects into aggregates and define boundaries around each.
 * Choose one entity to be the root of each aggregate, and allow external objects to hold
 * references to the root only (references to internal members passed out for use within a
 * single operation only). Define properties and invariants for the aggregate as a whole and
 * give enforcement responsibility to the root or some designated framework mechanism.
 * <br>
 * Use the same aggregate boundaries to govern transactions and distribution.
 * Within an aggregate boundary, apply consistency rules synchronously. Across boundaries,
 * handle updates asynchronously.
 * </blockquote>
 * <p>
 * <b>Aggregate rules:</b>
 * <ul>
 * <li>The root Entity has global identity, entities inside the boundary have local identity, unique
 * only within the
 * Aggregate.
 * <li>The root Entity can hand references to the internal Entities to other objects, but they can
 * only use them
 * transiently (within a single method or block).
 * <li>Objects within the Aggregate can hold references to other Aggregate roots.
 * <li>Nothing outside the Aggregate boundary can hold a reference to anything inside
 * <li>Only Aggregate Roots can be obtained directly with database queries.  Everything else must be
 * done through
 * traversal.
 * <li>A delete operation must remove everything within the Aggregate boundary all at once.
 * <li>When a change to any object within the Aggregate boundary is committed, all invariants of the
 * whole Aggregate
 * must be satisfied.
 * </ul>
 *
 * <p>
 * <b>Aggregates and specialization (inheritance)</b>
 * <p>
 * In the case there is a specialization of domain concepts it is important to not think about
 * implementation
 * inheritance but actual domain specialization. The point is not to reuse code but to capture
 * concepts that are indeed
 * specialization of more abstract concepts.
 * </p>
 * <p>
 * <img src="domain-inheritance.svg">
 * </p>
 *
 * @param <I>
 *            the type of the primary <b>business</b> identity of the entity
 *
 * @author Paulo Gandra Sousa
 *
 *         <!--
 *
 * @startuml domain-inheritance.svg
 *
 *           package domain <<aggregate>>{
 *
 *           abstract class Vehicle <<entity>><<root>>{
 *           licensePlate()
 *           horsePower()
 *           }
 *
 *           class LicensePlate<<value object>>
 *           Vehicle -> LicensePlate
 *
 *           class Power<<value object>>
 *           Vehicle ->Power:horsePower
 *
 *           class Automobile <<entity>>{
 *           -numberOfDoors
 *           }
 *
 *           class MotorBike <<entity>> {
 *           -hasSideCar
 *           }
 *
 *           Vehicle <|-up- Automobile
 *           Vehicle <|-up- MotorBike
 *           }
 * @enduml
 *         -->
 *
 */
public interface AggregateRoot<I extends Comparable<I> /* & ValueObject */>
        extends Entity<I> {
    // empty
}
