package scala.swing

/**
 * A component that can contain other components, such as layout panels.
 *
 * @see javax.swing.JPanel
 */
abstract class Panel extends Component with Container.Wrapper {
  override lazy val peer: javax.swing.JPanel = new javax.swing.JPanel with SuperMixin
}
