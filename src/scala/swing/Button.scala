package scala.swing

import javax.swing._
import event._

/**
 * A button that can be clicked, usually to perform some action.
 *
 * @see javax.swing.JButton
 */
class Button(text0: String) extends AbstractButton with Publisher {
  override lazy val peer: JButton = new JButton(text0) with SuperMixin
  def this() = this("")
  def this(a: Action) = {
    this("")
    action = a
  }
  
  def defaultButton: Boolean = peer.isDefaultButton
  
  def defaultCapable: Boolean = peer.isDefaultCapable
  def defaultCapable_=(capable: Boolean) { peer.setDefaultCapable(capable) }
}
