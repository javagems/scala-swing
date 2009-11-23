package scala.swing

import javax.swing._
import java.awt.event._
import event._

object FormattedTextField {
  /**
   * The behavior of a formatted text field when it loses its focus.
   */
  object FocusLostBehavior extends Enumeration {
    val Commit = Value(JFormattedTextField.COMMIT)
    val CommitOrRevert = Value(JFormattedTextField.COMMIT_OR_REVERT)
    val Persist = Value(JFormattedTextField.PERSIST)
    val Revert = Value(JFormattedTextField.REVERT)
  }
}

/**
 * A text field with formatted input.
 * 
 * @see javax.swing.JFormattedTextField
 */
class FormattedTextField(format: java.text.Format) extends TextComponent {
  override lazy val peer: JFormattedTextField = new JFormattedTextField(format)
  
  import FormattedTextField._
 
  def commitEdit() { peer.commitEdit() }
  def editValid: Boolean = peer.isEditValid
  
  def focusLostBehavior: FocusLostBehavior.Value = FocusLostBehavior(peer.getFocusLostBehavior)
  def focusLostBehavior_=(b: FocusLostBehavior.Value) { peer.setFocusLostBehavior(b.id) }
}