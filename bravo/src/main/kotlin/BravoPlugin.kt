package org.jetbrains.dokka.examples.plugins.bravo

import org.jetbrains.dokka.examples.plugins.alpha.AlphaPlugin
import org.jetbrains.dokka.pages.PageNode
import org.jetbrains.dokka.plugability.DokkaPlugin

class BravoPlugin: DokkaPlugin() {
    val b by extending {
        plugin<AlphaPlugin>().debugExtensionMessage with "Extension B."
    }

    val e by extending {
        plugin<AlphaPlugin>().debugExtensionMessage with "Extension E. Should be after B." order { after(b) }
    }

    val pageFormatter by extending {
        plugin<AlphaPlugin>().formatter with PageNode::name
    }
}