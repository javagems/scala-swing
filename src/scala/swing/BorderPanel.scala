package scala.swing

import java.awt.BorderLayout

object BorderPanel {
  /**
   * The position of a component in a <code>BorderPanel</code>
   */
  object Position extends Enumeration {
    val North = Value(BorderLayout.NORTH)
    val South = Value(BorderLayout.SOUTH)
    val West = Value(BorderLayout.WEST)
    val East = Value(BorderLayout.EAST)
    val Center = Value(BorderLayout.CENTER)
  }
  private[swing] def wrapPosition(s: String): Position.Value = s match {
    case BorderLayout.NORTH => Position.North
    case BorderLayout.SOUTH => Position.South
    case BorderLayout.WEST => Position.West	
    case BorderLayout.EAST => Position.East
    case BorderLayout.CENTER => Position.Center
  }
}

/**
 * A container that arranges its children around a central component that 
 * takes most of the space. The other children are placed on one of four 
 * borders: north, east, south, west.
 * 
 * @see javax.swing.BorderLayout
 */
class BorderPanel extends Panel with LayoutContainer {
  import BorderPanel._
  def layoutManager = peer.getLayout.asInstanceOf[BorderLayout]
  override lazy val peer = new javax.swing.JPanel(new BorderLayout) with SuperMixin 
    
  type Constraints = Position.Value
  
  protected def constraintsFor(comp: Component) =
    wrapPosition(layoutManager.getConstraints(comp.peer).asInstanceOf[String])
  
  protected def areValid(c: Constraints): (Boolean, String) = (true, "")
  protected def add(c: Component, l: Constraints) { peer.add(c.peer, l.toString) }
}
