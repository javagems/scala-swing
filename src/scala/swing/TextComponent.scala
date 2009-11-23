package scala.swing

import javax.swing._
import javax.swing.text._
import javax.swing.event._
import event._

object TextComponent {
  trait HasColumns extends TextComponent { 
    def columns: Int
    def columns_=(n: Int)
  }
  trait HasRows extends TextComponent {
    def rows: Int
    def rows_=(n: Int)
  }
}

/**
 * A component that allows some kind of text input and display.
 *
 * @see javax.swing.JTextComponent
 */
class TextComponent extends Component with Publisher {
  override lazy val peer: JTextComponent = new JTextComponent with SuperMixin {}
  def text: String = peer.getText
  def text_=(t: String) = peer.setText(t)
  
  class Caret extends Publisher {
    def dot: Int = peer.getCaret.getDot
    def dot_=(n: Int) { peer.getCaret.setDot(n) }
    def mark: Int = peer.getCaret.getMark
    def moveDot(n: Int) { peer.getCaret.moveDot(n) }
    def visible: Boolean = peer.getCaret.isVisible
    def visible_=(b: Boolean) { peer.getCaret.setVisible(b) }
    def selectionVisible: Boolean = peer.getCaret.isSelectionVisible
    def selectionVisible_=(b: Boolean) { peer.getCaret.setSelectionVisible(b) }
    def blinkRate: Int = peer.getCaret.getBlinkRate
    def blinkRate_=(n: Int) { peer.getCaret.setBlinkRate(n) }
    
    peer.addCaretListener {
      new CaretListener {
        def caretUpdate(e: CaretEvent) { publish(CaretUpdate(TextComponent.this)) } 
      }
    }
  }
  
  object caret extends Caret

  def editable: Boolean = peer.isEditable
  def editable_=(x: Boolean) = peer.setEditable(x)
  def cut() { peer.cut() }
  def copy() { peer.copy() }
  def selected: String = peer.getSelectedText
  
  def selectAll() { peer.selectAll() }
  
  peer.getDocument.addDocumentListener(new DocumentListener {
    def changedUpdate(e:DocumentEvent) { publish(ValueChanged(TextComponent.this)) }
    def insertUpdate(e:DocumentEvent) { publish(ValueChanged(TextComponent.this)) }
    def removeUpdate(e:DocumentEvent) { publish(ValueChanged(TextComponent.this)) }
  })
}