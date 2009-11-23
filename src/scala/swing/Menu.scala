package scala.swing

import scala.collection.mutable._
import javax.swing._

/**
 * A menu bar. Each window can contain at most one. Contains a number of menus.
 * 
 * @see javax.swing.JMenuBar
 */
class MenuBar extends Component with SequentialContainer.Wrapper {
  override lazy val peer: JMenuBar = new JMenuBar
  
  def menus: Seq[Menu] = contents.filter(_.isInstanceOf[Menu]).map(_.asInstanceOf[Menu])
  
  // Not implemented by Swing
  //def helpMenu: Menu = Component.wrapperFor(peer.getHelpMenu)
  //def helpMenu_=(m: Menu) { peer.setHelpMenu(m.peer) }
}

/**
 * A menu item that can be used in a menu.
 * 
 * @see javax.swing.JMenuItem
 */
class MenuItem(title0: String) extends AbstractButton {
  override lazy val peer: JMenuItem = new JMenuItem(title0)
  def this(a: Action) = {
    this("")
    action = a
  }
}

/**
 * A menu. Contains menu items. Being a menu item itself, menus can be nested.
 * 
 * @see javax.swing.JMenu
 */
class Menu(title0: String) extends MenuItem(title0) with SequentialContainer.Wrapper { self: Menu =>
  override lazy val peer: JMenu = new JMenu(title0)
}

/**
 * A menu item with a radio button.
 * 
 * @see javax.swing.JRadioButtonMenuItem
 */
class RadioMenuItem(title0: String) extends MenuItem(title0) {
  override lazy val peer: JRadioButtonMenuItem = new JRadioButtonMenuItem(title0)
}
/**
 * A menu item with a check box.
 * 
 * @see javax.swing.JCheckBoxMenuItem
 */
class CheckMenuItem(title0: String) extends MenuItem(title0) {
  override lazy val peer: JCheckBoxMenuItem = new JCheckBoxMenuItem(title0)
}
