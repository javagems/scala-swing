package scala.swing

import java.awt.{Image, Point}
import javax.swing._
import event._

/**
 * A window with decoration such as a title, border, and action buttons.
 * 
 * @see javax.swing.JFrame
 */
class Frame extends UIElement with RootPanel with Publisher {
  override lazy val peer: JFrame = new JFrame
  def title: String = peer.getTitle
  def title_=(s: String) = peer.setTitle(s)

  override def contents_=(c: Component) {
    super.contents_=(c)
    peer.pack() // pack also validates, which is generally required after an add
  }
  def defaultButton: Option[Button] = 
    Swing.toOption(peer.getRootPane.getDefaultButton).map(Component.wrapperFor(_))
  def defaultButton_=(b: Button) { 
    peer.getRootPane.setDefaultButton(b.peer) 
  }
  def defaultButton_=(b: Option[Button]) { 
    peer.getRootPane.setDefaultButton(Swing.toNull(b.map(_.peer))) 
  }
  
  def dispose() { peer.dispose() }
  
  def pack(): this.type = { peer.pack(); this }
  
  def menuBar: MenuBar = Component.wrapperFor(peer.getJMenuBar)
  def menuBar_=(m: MenuBar) = peer.setJMenuBar(m.peer)
  
  def setLocationRelativeTo(c: UIElement) { peer.setLocationRelativeTo(c.peer) }
  def location_=(p: Point) { peer.setLocation(p) }
  
  def iconImage: Image = peer.getIconImage
  def iconImage_=(i: Image) { peer.setIconImage(i) }
  
  peer.addWindowListener(new java.awt.event.WindowListener {
    def windowActivated(e: java.awt.event.WindowEvent) { publish(WindowActivated(Frame.this)) }
    def windowClosed(e: java.awt.event.WindowEvent) { publish(WindowClosed(Frame.this)) }
    def windowClosing(e: java.awt.event.WindowEvent) { publish(WindowClosing(Frame.this)) }
    def windowDeactivated(e: java.awt.event.WindowEvent) { publish(WindowDeactivated(Frame.this)) }
    def windowDeiconified(e: java.awt.event.WindowEvent) { publish(WindowDeiconified(Frame.this)) }
    def windowIconified(e: java.awt.event.WindowEvent) { publish(WindowIconified(Frame.this)) }
    def windowOpened(e: java.awt.event.WindowEvent) { publish(WindowOpened(Frame.this)) }
  })
}
