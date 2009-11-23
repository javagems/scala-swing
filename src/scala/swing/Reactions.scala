package scala.swing

import event.Event
import scala.collection.mutable.{Buffer, ListBuffer}

object Reactions {
  import scala.ref._
  
  class Impl extends Reactions {
    private val parts: Buffer[Reaction] = new ListBuffer[Reaction]
    def isDefinedAt(e: Event) = parts.exists(_ isDefinedAt e)
    def += (r: Reaction) = { parts += r }
    def -= (r: Reaction) { parts -= r }
    def apply(e: Event) {
      for (p <- parts) if (p isDefinedAt e) p(e)
    }
  }
  
  type Reaction = PartialFunction[Event, Unit]
  
  /**
   * A Reaction implementing this trait is strongly referenced in the reaction list
   */
  trait StronglyReferenced
  
  class Wrapper(listener: Any)(r: Reaction) extends Reaction with StronglyReferenced with Proxy {
    def self = listener
    def isDefinedAt(e: Event) = r.isDefinedAt(e)
    def apply(e: Event) { r(e) }
  }
}

/**
 * Used by reactors to let clients register custom event reactions.
 */
abstract class Reactions extends Reactions.Reaction {
  /**
   * Add a reaction.
   */
  def += (r: Reactions.Reaction) 

  /**
   * Remove the given reaction.
   */
  def -= (r: Reactions.Reaction)
}
